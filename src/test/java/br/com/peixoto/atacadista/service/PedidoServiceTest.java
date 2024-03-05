package br.com.peixoto.atacadista.service;

import br.com.peixoto.atacadista.FactoryObjects;
import br.com.peixoto.atacadista.domain.Cliente;
import br.com.peixoto.atacadista.domain.Pedido;
import br.com.peixoto.atacadista.domain.Produto;
import br.com.peixoto.atacadista.dto.PedidoRequestDTO;
import br.com.peixoto.atacadista.dto.ProdutoResponseDTO;
import br.com.peixoto.atacadista.repository.ClienteRepository;
import br.com.peixoto.atacadista.repository.ItemPedidoRepository;
import br.com.peixoto.atacadista.repository.PedidoRepository;
import br.com.peixoto.atacadista.repository.ProdutoRepository;
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

    @BeforeEach
    void setUp() {

        FactoryObjects factoryObjectsForTest = new FactoryObjects();
        cliente = factoryObjectsForTest.getClienteGenericForTest();
        clienteOptional = factoryObjectsForTest.getClienteOptionalGenericForTest();
        produtoOptional = factoryObjectsForTest.getProdutoOptionalGenericForTest();
        produtoResponse = factoryObjectsForTest.getProdutoResponseGenericForTest();
        pedido = factoryObjectsForTest.getPedidoGenericForTest();
        pedidoRequest = factoryObjectsForTest.getPedidoRequestGenericForTest();

    }

    @Test
    void save() {

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