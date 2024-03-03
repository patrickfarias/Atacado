package br.com.peixoto.atacadista.service;

import br.com.peixoto.atacadista.domain.Cliente;
import br.com.peixoto.atacadista.domain.ItemPedido;
import br.com.peixoto.atacadista.domain.Pedido;
import br.com.peixoto.atacadista.domain.Produto;
import br.com.peixoto.atacadista.dto.PedidoRequestDTO;
import br.com.peixoto.atacadista.dto.PedidoResponseDTO;
import br.com.peixoto.atacadista.dto.ProdutoResponseDTO;
import br.com.peixoto.atacadista.exception.AbstractMessageErrorCode;
import br.com.peixoto.atacadista.exception.BadRequestException;
import br.com.peixoto.atacadista.exception.ErrorMessage;
import br.com.peixoto.atacadista.jpamodel.CrudRepository;
import br.com.peixoto.atacadista.jpamodel.FindRepository;
import br.com.peixoto.atacadista.jpamodel.MergeRepository;
import br.com.peixoto.atacadista.repository.ClienteRepository;
import br.com.peixoto.atacadista.repository.ItemPedidoRepository;
import br.com.peixoto.atacadista.repository.PedidoRepository;
import br.com.peixoto.atacadista.repository.ProdutoRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class PedidoService implements
        CrudRepository<PedidoRequestDTO, PedidoResponseDTO>,
        FindRepository<PedidoRequestDTO, PedidoResponseDTO>,
        MergeRepository<Pedido, PedidoResponseDTO> {

    private final PedidoRepository pedidoRepository;

    private final ItemPedidoRepository itemPedidoRepository;

    private final ClienteRepository clienteRepository;

    private final ProdutoRepository produtoRepository;

    private final ProdutoService produtoService;

    private final ModelMapper modelMapper;


    private Pedido validarPedido(PedidoRequestDTO requestBody) {

        final var pedido = new Pedido();
        final Cliente cliente = clienteRepository.findById(requestBody.getCliente().getId())
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.CLIENTE_NAO_ENCONTRADO, requestBody.getCliente().getId())));
        pedido.setCliente(cliente);

        // Item nao pode ter quantidade zerada
        requestBody.getItens().forEach(item -> {
            if(item.getQuantidade() == 0) {

                final ProdutoResponseDTO produto = produtoService.findById(item.getProduto().getId());
                throw new BadRequestException(new ErrorMessage(AbstractMessageErrorCode.ITEM_COM_QUANTIDADE_ZERADA, produto.getDescricao()));
            }
        });

        // Deve ter pelo menos um item
        if(requestBody.getItens().isEmpty()) {
            throw new BadRequestException(new ErrorMessage(
                    AbstractMessageErrorCode.LIMITE_MINIMO_ITEM_ATINGIDO));
        }

        return pedido;

    }

    private List<ItemPedido> validarItens(PedidoRequestDTO requestBody) {

        final List<ItemPedido> itensAprovados = new ArrayList<>();
        requestBody.getItens().forEach(item -> {

            final Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.PRODUTO_NAO_ENCONTRADO, item.getProduto().getId())));

            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
            item.validadarItens();
            item.calcularPrecoTotal();
            itensAprovados.add(item);

        });

        return itensAprovados;
    }

    private BigDecimal calcularTotalDesconto(List<ItemPedido> itensAprovados) {

        return itensAprovados.stream()
                .map(ItemPedido::getValorDescontoConcedido)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }


    private void validarLimitePorPedido(Pedido pedido, List<ItemPedido> itensAprovados) {

        final BigDecimal somaPrecoTotal = itensAprovados.stream()
                .map(ItemPedido::getPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if(somaPrecoTotal.compareTo(pedido.getCliente().getLimitePorPedido()) > 0){
            throw new BadRequestException(new ErrorMessage(AbstractMessageErrorCode.LIMITE_POR_PEDIDO_EXCEDIDO,
                    pedido.getCliente().getId(), pedido.getCliente().getNome(),
                    pedido.getCliente().getLimitePorPedido(), somaPrecoTotal));
        }
    }


    @Override
    public PedidoResponseDTO save(PedidoRequestDTO requestBody) {

        final var pedido = validarPedido(requestBody);
        final List<ItemPedido> itensAprovados = validarItens(requestBody);
        validarLimitePorPedido(pedido, itensAprovados);

        pedidoRepository.save(pedido);

        final Cliente cliente = clienteRepository.findById(requestBody.getCliente().getId())
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.CLIENTE_NAO_ENCONTRADO, requestBody.getCliente().getId())));

        pedido.setCliente(cliente);

        itensAprovados.forEach(item -> item.setPedido(pedido));

        pedido.setItens(itensAprovados);

        pedido.calcularValorTotal();

        pedido.setValorTotalDesconto(calcularTotalDesconto(itensAprovados));

        pedido.setSubtotal(pedido.getValorTotal().add(pedido.getValorTotalDesconto()));

        pedido.setQuantidadeTotalItens(itensAprovados.size());

        final var response = new PedidoResponseDTO();

        modelMapper.map(pedidoRepository.save(pedido), response);

        return response;
    }

    @Override
    public PedidoResponseDTO merge(final Pedido pedido) {

        final var response = new PedidoResponseDTO();
        modelMapper.map(pedidoRepository.save(pedido), response);

        return response;
    }

    @Override
    public Pedido getObjectAtual(Long pedidoId, Object requestBody) {

        final PedidoRequestDTO pedidoRequestDTO = new PedidoRequestDTO();

        modelMapper.map(requestBody, pedidoRequestDTO);

        final var pedido = validarPedido(pedidoRequestDTO);
        final List<ItemPedido> itensAprovados = validarItens(pedidoRequestDTO);
        validarLimitePorPedido(pedido, itensAprovados);

        final Pedido pedidoAtual = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.PEDIDO_NAO_ENCONTRADO, pedidoId)));

        BeanUtils.copyProperties(pedido, pedidoAtual, "id", "dataCriacao", "codigo");

        // Salvar itens de pedido antes de associá-los ao pedido atual
        itensAprovados.forEach(item -> {
            item.setPedido(pedidoAtual);
            itemPedidoRepository.save(item);
        });

        pedidoAtual.getItens().clear();
        pedidoAtual.getItens().addAll(itensAprovados);
        pedidoAtual.calcularValorTotal();
        pedidoAtual.setValorTotalDesconto(calcularTotalDesconto(itensAprovados));
        pedidoAtual.setSubtotal(pedidoAtual.getValorTotal().add(pedidoAtual.getValorTotalDesconto()));
        pedidoAtual.setQuantidadeTotalItens(itensAprovados.size());

        return pedidoAtual;
    }

    @Override
    public List<PedidoResponseDTO> findAll() {

        final List<Pedido> pedidoList = pedidoRepository.findAll();

        final List<PedidoResponseDTO> catogoriaResponseList = new ArrayList<>();

        pedidoList.forEach(pedido -> {

            final var pedidoResponse = new PedidoResponseDTO();
            modelMapper.map(pedido, pedidoResponse);

            catogoriaResponseList.add(pedidoResponse);

        });

        return catogoriaResponseList;
    }

    @Override
    public PedidoResponseDTO findById(final Long pedidoId) {

        final Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.PEDIDO_NAO_ENCONTRADO, pedidoId)));

        final var pedidoResponse = new PedidoResponseDTO();
        modelMapper.map(pedido, pedidoResponse);

        return pedidoResponse;
    }

    public List<PedidoResponseDTO> findByIdCliente(final Long clienteId) {

        final List<Pedido> pedidoList = pedidoRepository.findByClienteId(clienteId);
        final List<PedidoResponseDTO> pedidoResponseList = new ArrayList<>();

        pedidoList.forEach(pedido -> {

            final var pedidoResponse = new PedidoResponseDTO();
            modelMapper.map(pedido, pedidoResponse);

            pedidoResponseList.add(pedidoResponse);

        });

        return pedidoResponseList;

    }

    @Override
    public PedidoResponseDTO update(final Long objectId, final PedidoRequestDTO requestBody) {

        final Pedido pedidoAtual = getObjectAtual(objectId, requestBody);

        return merge(pedidoAtual);

    }

    @Override
    @Transactional
    public void delete(final Long pedidoId) {

        final Pedido pedidoAtual = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.PEDIDO_NAO_ENCONTRADO, pedidoId)));

        pedidoRepository.delete(pedidoAtual);

    }
}
