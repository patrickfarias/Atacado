package br.com.peixoto.atacadista.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import br.com.peixoto.atacadista.FactoryObjects;
import br.com.peixoto.atacadista.domain.Categoria;
import br.com.peixoto.atacadista.domain.Cliente;
import br.com.peixoto.atacadista.domain.ItemPedido;
import br.com.peixoto.atacadista.domain.Pedido;
import br.com.peixoto.atacadista.domain.Produto;
import br.com.peixoto.atacadista.dto.PedidoRequestDTO;
import br.com.peixoto.atacadista.dto.ProdutoResponseDTO;
import br.com.peixoto.atacadista.repository.ClienteRepository;
import br.com.peixoto.atacadista.repository.ItemPedidoRepository;
import br.com.peixoto.atacadista.repository.PedidoRepository;
import br.com.peixoto.atacadista.repository.ProdutoRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    FactoryObjects factoryObjectsForTest = new FactoryObjects();

    @InjectMocks
    private PedidoService pedidoService;
    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ItemPedidoRepository itemPedidoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoService produtoService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ModelMapperFactory modelMapperFactory;

    private Cliente cliente;

    private Optional<Cliente> clienteOptional;

    private Optional<Produto> produtoOptional;

    private ProdutoResponseDTO produtoResponse;

    private Pedido pedido;

    private PedidoRequestDTO pedidoRequest;

    private ItemPedido itemPedido;

    @BeforeEach
    void setUp() {

        clienteOptional = factoryObjectsForTest.getClienteOptionalGenericForTest();
        pedidoRequest = factoryObjectsForTest.getPedidoRequestGenericForTest();
        produtoOptional = factoryObjectsForTest.getProdutoOptionalGenericForTest();
        itemPedido = factoryObjectsForTest.getItemPedidoGenericForTest();
        pedido = factoryObjectsForTest.getPedidoGenericForTest();

    }

    @Test
    void save() {

        ItemPedido itemPedido = ItemPedido.builder()
                .id(1L)
                .quantidade(1)
                .precoUnitario(new BigDecimal("10.00"))
                .precoTotal(new BigDecimal("20.00"))
                .percentualDescontoConcedido(new BigDecimal("5.00"))
                .valorDescontoConcedido(new BigDecimal("1.00"))
                .pedido(factoryObjectsForTest.getPedidoGenericForTest())
                .produto(factoryObjectsForTest.getProdutoGenericForTest())
                .build();

        final List<ItemPedido> itemPedidoList = new ArrayList<>();
        itemPedidoList.add(itemPedido);

        pedidoRequest.setItens(itemPedidoList);

        when(clienteRepository.findById(anyLong())).thenReturn(clienteOptional);
        when(produtoRepository.findById(anyLong())).thenReturn(produtoOptional);
        when(clienteRepository.findById(anyLong())).thenReturn(clienteOptional);
        when(itemPedidoRepository.save(any())).thenReturn(itemPedido);
        when(pedidoRepository.save(any())).thenReturn(pedido);

        Pedido response = pedidoService.save(pedidoRequest);
        assertNotNull(response);
        assertEquals(Pedido.class, response.getClass());

    }
    @Test
    void merge() {
    }

    @Test
    void getObjectAtual() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByIdCliente() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}