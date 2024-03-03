package br.com.peixoto.atacadista;

import br.com.peixoto.atacadista.domain.Categoria;
import br.com.peixoto.atacadista.domain.Cidade;
import br.com.peixoto.atacadista.domain.Cliente;
import br.com.peixoto.atacadista.domain.Endereco;
import br.com.peixoto.atacadista.domain.Estado;
import br.com.peixoto.atacadista.domain.Produto;
import br.com.peixoto.atacadista.enumeration.TipoEmbalagem;
import br.com.peixoto.atacadista.repository.CategoriaRepository;
import br.com.peixoto.atacadista.repository.CidadeRepository;
import br.com.peixoto.atacadista.repository.ClienteRepository;
import br.com.peixoto.atacadista.repository.EstadoRepository;
import br.com.peixoto.atacadista.repository.ProdutoRepository;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.TimeZone;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@AllArgsConstructor
@SpringBootApplication
@ConfigurationPropertiesScan("br.com.peixoto.atacadista.configuration")
public class AtacadistaApplication implements CommandLineRunner {

	public static final long ID_01 = 1L;
	public static final long ID_02 = 2L;
	public static final long ID_03 = 3L;
	public static final long ID_04 = 4L;
	public static final long ID_05 = 5L;
	public static final long ID_06 = 6L;
	public static final long ID_07 = 7L;
	public static final long ID_08 = 8L;
	public static final long ID_09 = 9L;
	public static final long ID_10 = 10L;
	private final EstadoRepository estadoRepository;

	private final CidadeRepository cidadeRepository;

	private final ClienteRepository clienteRepository;

	private final CategoriaRepository categoriaRepository;

	private final ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(AtacadistaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		final Estado e1 = new Estado(ID_01, "Minas Gerais");
		final Estado e2 = new Estado(ID_02, "São Paulo");
		final Estado e3 = new Estado(ID_03, "Goiás");
		estadoRepository.saveAll(Arrays.asList(e1, e2, e3));

		final Cidade c1 = new Cidade(ID_01, "Uberlândia", e1);
		final Cidade c2 = new Cidade(ID_02, "Campinas", e2);
		final Cidade c3 = new Cidade(ID_03, "Goiânia", e3);
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		final Endereco end1 = new Endereco("38400-000", "Rua da Lavoura", "4.000",
				"Frente", "Jardim América", c1);

		final Endereco end2 = new Endereco("99000-000", "Rua do lauro", "40",
				"Frente", "Industrial", c3);

		final Cliente cliente01 = new Cliente(ID_01, "Patrick Farias", "45822715028", end1, new BigDecimal("5000.00"));
		final Cliente cliente02 = new Cliente(ID_02, "Lauro", "45822715028", end2, new BigDecimal("30000.00"));
		clienteRepository.saveAll(Arrays.asList(cliente01, cliente02));

		final Categoria cat1 = Categoria.builder()
				.id(ID_01)
				.descricao("Agro & Pet")
				.build();

		final Categoria cat2 = Categoria.builder()
				.id(ID_02)
				.descricao("Alimentos & Bebidas")
				.build();

		final Categoria cat3 = Categoria.builder()
				.id(ID_03)
				.descricao("Bazar, Eletro & Utilidades")
				.build();

		final Categoria cat4 = Categoria.builder()
				.id(ID_04)
				.descricao("Beleza & Saude")
				.build();

		final Categoria cat5 = Categoria.builder()
				.id(ID_05)
				.descricao("Chinelo & Sandalias")
				.build();

		final Categoria cat6 = Categoria.builder()
				.id(ID_06)
				.descricao("Faxina & Limpezq")
				.build();

		final Categoria cat7 = Categoria.builder()
				.id(ID_07)
				.descricao("Ferramentas & Materiais")
				.build();

		final Categoria cat8 = Categoria.builder()
				.id(ID_08)
				.descricao("Papelaria & Escritorio")
				.build();

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));

		final Produto p1 = Produto.builder()
				.id(ID_01)
				.descricao("Racao Valor Bifinho De Ouro Adulto 1x20kg")
				.preco(new BigDecimal("10.00"))
				.ativo(true)
				.percentualMaximoDesconto(BigDecimal.TEN)
				.tipoEmbalagem(TipoEmbalagem.PLASTICO)
				.categoria(cat1)
				.build();

		final Produto p2 = Produto.builder()
				.id(ID_02)
				.descricao("Beb Vodka Absolut Extrakt 1x750ml")
				.preco(new BigDecimal("20.00"))
				.ativo(true)
				.percentualMaximoDesconto(BigDecimal.TEN)
				.tipoEmbalagem(TipoEmbalagem.VIDRO)
				.categoria(cat2)
				.build();

		final Produto p3 = Produto.builder()
				.id(ID_03)
				.descricao("Eletro Liquidificador Mondial L-550-W 220v/60hz 1x1")
				.preco(new BigDecimal("30.00"))
				.ativo(true)
				.percentualMaximoDesconto(BigDecimal.TEN)
				.tipoEmbalagem(TipoEmbalagem.ALUMINIO)
				.categoria(cat3)
				.build();

		final Produto p4 = Produto.builder()
				.id(ID_04)
				.descricao("Protetor Solar Above Aerosol Fps 30 1x150ml/120g")
				.preco(new BigDecimal("40.00"))
				.ativo(true)
				.percentualMaximoDesconto(BigDecimal.TEN)
				.tipoEmbalagem(TipoEmbalagem.PLASTICO)
				.categoria(cat4)
				.build();

		final Produto p5 = Produto.builder()
				.id(ID_05)
				.descricao("Sandália Havaianas Slim Princesas Argila 33/34 1 Par")
				.preco(new BigDecimal("50.00"))
				.ativo(true)
				.percentualMaximoDesconto(BigDecimal.TEN)
				.tipoEmbalagem(TipoEmbalagem.PLASTICO)
				.categoria(cat5)
				.build();

		final Produto p6 = Produto.builder()
				.id(ID_06)
				.descricao("Amaciante Comfort Original Refil 1x900mll")
				.preco(new BigDecimal("60.00"))
				.ativo(true)
				.percentualMaximoDesconto(BigDecimal.TEN)
				.tipoEmbalagem(TipoEmbalagem.PLASTICO)
				.categoria(cat6)
				.build();

		final Produto p7 = Produto.builder()
				.id(ID_07)
				.descricao("Eletro Parafusadeira Hammer S/Fio Bits 4,8v Male Bivolt 1x1")
				.preco(new BigDecimal("70.00"))
				.ativo(true)
				.percentualMaximoDesconto(new BigDecimal("15.00"))
				.tipoEmbalagem(TipoEmbalagem.ISOPOR)
				.categoria(cat7)
				.build();

		final Produto p8 = Produto.builder()
				.id(ID_08)
				.descricao("Combo Teclado E Mouse Usb Letron Preto 1x1 Ref74330")
				.preco(new BigDecimal("80.00"))
				.ativo(true)
				.percentualMaximoDesconto(BigDecimal.TEN)
				.tipoEmbalagem(TipoEmbalagem.PLASTICO)
				.categoria(cat8)
				.build();

		final Produto p9 = Produto.builder()
				.id(ID_09)
				.descricao("Caderno Credeal Desenho Espiral Capa Forte 48 Fls 10 Unid")
				.preco(new BigDecimal("90.00"))
				.ativo(true)
				.percentualMaximoDesconto(BigDecimal.TEN)
				.tipoEmbalagem(TipoEmbalagem.PLASTICO)
				.categoria(cat8)
				.build();

		final Produto p10 = Produto.builder()
				.id(ID_10)
				.descricao("Caneta Compactor Economic Esferografica Preta Cx/50")
				.preco(new BigDecimal("100.00"))
				.ativo(true)
				.percentualMaximoDesconto(new BigDecimal("5.00"))
				.tipoEmbalagem(TipoEmbalagem.PLASTICO)
				.categoria(cat8)
				.build();

		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));

	}

}

