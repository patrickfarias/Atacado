package br.com.peixoto.atacadista.service;

import br.com.peixoto.atacadista.domain.Categoria;
import br.com.peixoto.atacadista.domain.Produto;
import br.com.peixoto.atacadista.dto.ProdutoRequestDTO;
import br.com.peixoto.atacadista.dto.ProdutoResponseDTO;
import br.com.peixoto.atacadista.exception.AbstractMessageErrorCode;
import br.com.peixoto.atacadista.exception.BadRequestException;
import br.com.peixoto.atacadista.exception.ErrorMessage;
import br.com.peixoto.atacadista.jpamodel.CrudService;
import br.com.peixoto.atacadista.jpamodel.FindService;
import br.com.peixoto.atacadista.jpamodel.MergeService;
import br.com.peixoto.atacadista.repository.CategoriaRepository;
import br.com.peixoto.atacadista.repository.ProdutoRepository;
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
public class ProdutoService implements
        CrudService<ProdutoRequestDTO, ProdutoResponseDTO>,
        FindService<ProdutoRequestDTO, ProdutoResponseDTO>,
        MergeService<Produto, ProdutoResponseDTO> {

    private final ProdutoRepository produtoRepository;

    private final CategoriaRepository categoriaRepository;

    private final ModelMapper modelMapper;

    @Override
    public ProdutoResponseDTO save(ProdutoRequestDTO requestBody) {

        final Long idCategoria = Long.valueOf(requestBody.getCategoria().getId());

        final Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.CATEGORIA_NAO_ENCONTRADA, idCategoria)));

        final var produto = new Produto();
        modelMapper.map(requestBody, produto);

        produto.setCategoria(categoria);

        final var response = new ProdutoResponseDTO();

        modelMapper.map(produtoRepository.save(produto), response);

        return response;
    }

    @Override
    @Transactional
    public ProdutoResponseDTO merge(final Produto produto) {

        final var response = new ProdutoResponseDTO();
        modelMapper.map(produtoRepository.save(produto), response);

        return response;
    }

    @Override
    public Produto getObjectAtual(Long idProduto, Object requestBody) {

        final Produto produtoAtual = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.PRODUTO_NAO_ENCONTRADO, idProduto)));

        BeanUtils.copyProperties(requestBody, produtoAtual, "id", "dataCriacao");

        return produtoAtual;
    }

    @Override
    public List<ProdutoResponseDTO> findAll() {

        final List<Produto> produtoList = produtoRepository.findAll();

        final List<ProdutoResponseDTO> produtoResponseList = new ArrayList<>();

        produtoList.forEach(produto -> {

            final var produtoResponse = new ProdutoResponseDTO();
            modelMapper.map(produto, produtoResponse);

            produtoResponseList.add(produtoResponse);

        });
        return produtoResponseList;
    }

    @Override
    public ProdutoResponseDTO findById(final Long idProduto) {

        final Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.PRODUTO_NAO_ENCONTRADO, idProduto)));

        final var produtoResponse = new ProdutoResponseDTO();
        modelMapper.map(produto, produtoResponse);

        return produtoResponse;
    }

    @Override
    @Transactional
    public ProdutoResponseDTO update(final Long objectId, final ProdutoRequestDTO requestBody) {

       final Long idCategoria = Long.valueOf(requestBody.getCategoria().getId());

        final Produto produtoAtual = getObjectAtual(objectId, requestBody);

        if(produtoAtual.getCategoria().getId().equals(idCategoria)){

            final Categoria categoria = categoriaRepository.findById(idCategoria)
                    .orElseThrow(() -> new BadRequestException(
                            new ErrorMessage(AbstractMessageErrorCode.CATEGORIA_NAO_ENCONTRADA, idCategoria)));

            produtoAtual.setCategoria(categoria);
        }

        return merge(produtoAtual);

    }

    @Override
    @Transactional
    public void delete(final Long idProduto) {

        final Produto produtoAtual = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.PRODUTO_NAO_ENCONTRADO, idProduto)));

        produtoRepository.delete(produtoAtual);

    }

    public List<ProdutoResponseDTO> findByNomeCategoria(final String nomeCategoria) {

        final List<Produto> produtoList = produtoRepository.findByCategoriaDescricaoContaining(nomeCategoria);

        final List<ProdutoResponseDTO> produtoResponseList = new ArrayList<>();

        produtoList.forEach(produto -> {

            final var produtoResponse = new ProdutoResponseDTO();
            modelMapper.map(produto, produtoResponse);

            produtoResponseList.add(produtoResponse);

        });
        return produtoResponseList;
    }
}
