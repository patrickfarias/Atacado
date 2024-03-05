package br.com.peixoto.atacadista;

import br.com.peixoto.atacadista.domain.Categoria;
import br.com.peixoto.atacadista.domain.Cidade;
import br.com.peixoto.atacadista.domain.Cliente;
import br.com.peixoto.atacadista.domain.Endereco;
import br.com.peixoto.atacadista.domain.Estado;
import br.com.peixoto.atacadista.domain.ItemPedido;
import br.com.peixoto.atacadista.domain.Pedido;
import br.com.peixoto.atacadista.domain.Produto;
import br.com.peixoto.atacadista.dto.CategoriaRequestDTO;
import br.com.peixoto.atacadista.dto.CategoriaResponseDTO;
import br.com.peixoto.atacadista.dto.PedidoRequestDTO;
import br.com.peixoto.atacadista.dto.ProdutoResponseDTO;
import br.com.peixoto.atacadista.enumeration.StatusPedido;
import br.com.peixoto.atacadista.enumeration.TipoEmbalagem;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FactoryObjects {

    public UUID getUUIDGenericForTest() {
        return UUID.fromString("c715489d-2267-49f1-9eae-5dd98dd7ece4");
    }

    public Categoria getCategoriaGenericForTest() {

        return Categoria.builder()
                .id(1L)
                .descricao("Higiene Pessoal")
                .dataCriacao(OffsetDateTime.now())
                .dataAlteracao(OffsetDateTime.now())
                .build();
    }

    public Optional<Categoria> getCategoriaOptionalGenericForTest() {
        return Optional.of(getCategoriaGenericForTest());
    }

    public List<Categoria> getCategoriaListGenericForTest(){

        final List<Categoria> categoriaList = new ArrayList<>();
        categoriaList.add(getCategoriaGenericForTest());

        return categoriaList;
    }

    public List<CategoriaResponseDTO> getCategoriaResponseListGenericForTest(){

        final List<CategoriaResponseDTO> categoriaResponseDTOList = new ArrayList<>();
        categoriaResponseDTOList.add(getCategoriaResponseDTOGenericForTest());

        return categoriaResponseDTOList;
    }

    public List<Optional<CategoriaResponseDTO>> getCategoriaResponseOptionalListGenericForTest(){

        final List<Optional<CategoriaResponseDTO>> categoriaResponseOptionalList = new ArrayList<>();

        categoriaResponseOptionalList.add(Optional.of(getCategoriaResponseDTOGenericForTest()));

        return categoriaResponseOptionalList;
    }

    public CategoriaRequestDTO getCategoriaRequestDTOGenericForTest() {

        return CategoriaRequestDTO.builder()
                .descricao("Higiene Pessoal")
                .build();
    }

    public CategoriaResponseDTO getCategoriaResponseDTOGenericForTest() {

        return CategoriaResponseDTO.builder()
                .id(1L)
                .descricao("Higiene Pessoal")
                .dataCriacao(OffsetDateTime.now())
                .dataAlteracao(OffsetDateTime.now())
                .build();
    }


    // ITEM

    public ItemPedido getItemPedidoResponseDTOGenericForTest() {

        return ItemPedido.builder()
                .id(1L)
                .quantidade(2)
                .precoUnitario(new BigDecimal("10.00"))
                .precoTotal(new BigDecimal("20.00"))
                .percentualDescontoConcedido(new BigDecimal("5.00"))
                .valorDescontoConcedido(new BigDecimal("1.00"))
                .build();
    }

    public List<ItemPedido> getItemPedidoListGenericForTest(){

        final List<ItemPedido> itemPedidoList = new ArrayList<>();
        itemPedidoList.add(getItemPedidoResponseDTOGenericForTest());

        return itemPedidoList;
    }

    // PEDIDO

    public Pedido getPedidoGenericForTest() {

        return Pedido.builder()
                .id(1L)
                .codigo("10")
                .status(StatusPedido.CRIADO)
                .cliente(getClienteGenericForTest())
                .itens(getItemPedidoListGenericForTest())
                .subtotal(new BigDecimal("20.00"))
                .valorTotal(new BigDecimal("19.00"))
                .valorTotalDesconto(new BigDecimal("1.00"))
                .quantidadeTotalItens(2)
                .dataCriacao(OffsetDateTime.now())
                .dataAlteracao(OffsetDateTime.now())
                .observacao("observacao")
                .build();
    }

    public PedidoRequestDTO getPedidoRequestGenericForTest() {

        return PedidoRequestDTO.builder()
                .descricao("TV 45")
                .status(StatusPedido.CRIADO)
                .cliente(getClienteGenericForTest())
                .itens(getItemPedidoListGenericForTest())
                .build();
    }

    // ENDERECO

    public Endereco getEnderecoGenericForTest() {

        return Endereco.builder()
                .cep("38400999")
                .logradouro("Rua dos Passaros")
                .numero("50")
                .complemento("Apartamento 304 - Bloco 03")
                .bairro("Industrial")
                .cidade(getCidadeGenericForTest())
                .build();
    }

    // CIDADE

    public Cidade getCidadeGenericForTest() {

        return Cidade.builder()
                .id(1L)
                .nome("Uberlandia")
                .estado(getEstadoGenericForTest())
                .build();
    }

    // ESTADO

    public Estado getEstadoGenericForTest() {

        return Estado.builder()
                .id(1L)
                .nome("Minas Gerais")
                .build();
    }





    // CLIENTE

    public Cliente getClienteGenericForTest() {

        return Cliente.builder()
                .id(1L)
                .nome("Patrick Farias")
                .cpf("12345678900")
                .endereco(getEnderecoGenericForTest())
                .limitePorPedido(new BigDecimal("20000.00"))
                .build();
    }

    public Optional<Cliente> getClienteOptionalGenericForTest() {
        return Optional.of(getClienteGenericForTest());
    }

    // ITEM



    // PRODUTO

    public Produto getProdutoGenericForTest() {

        return Produto.builder()
                .id(1L)
                .dataCriacao(OffsetDateTime.now())
                .dataAlteracao(OffsetDateTime.now())
                .descricao("TV 45")
                .preco(new BigDecimal("4000.00"))
                .ativo(true)
                .percentualMaximoDesconto(new BigDecimal("9.00"))
                .tipoEmbalagem(TipoEmbalagem.MADEIRA)
                .categoria(getCategoriaGenericForTest())
                .build();
    }

    public ProdutoResponseDTO getProdutoResponseGenericForTest() {

        return ProdutoResponseDTO.builder()
                .dataCriacao(OffsetDateTime.now())
                .dataAlteracao(OffsetDateTime.now())
                .descricao("TV 45")
                .preco(new BigDecimal("4000.00"))
                .ativo(true)
                .percentualMaximoDesconto(new BigDecimal("9.00"))
                .tipoEmbalagem(TipoEmbalagem.MADEIRA)
                .categoria(getCategoriaGenericForTest())
                .build();
    }

    public Optional<Produto> getProdutoOptionalGenericForTest() {
        return Optional.of(getProdutoGenericForTest());
    }
























}