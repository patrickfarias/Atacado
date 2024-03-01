package br.com.peixoto.atacadista.dto;

import br.com.peixoto.atacadista.domain.Categoria;
import br.com.peixoto.atacadista.enumeration.TipoEmbalagem;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDTO {

    @Schema(example = "1499")
    private String id;

    @Schema(example = "Amaciante Ype Ternura 1x2l")
    private String descricao;

    @Schema(example = "2024-02-27T18:33:29.624557Z")
    private OffsetDateTime dataCriacao;

    @Schema(example = "2024-02-28T10:08:52.935113Z")
    private OffsetDateTime dataAlteracao;

    @Schema(example = "11.99")
    @NotNull
    private BigDecimal preco;

    @Schema(example = "true")
    @NotNull
    private Boolean ativo;

    @Schema(example = "10.00")
    @NotNull
    private BigDecimal percentualMaximoDesconto;

    @Schema(example = "PLASTICO")
    @NotNull
    private TipoEmbalagem tipoEmbalagem;

    private Categoria categoria;

}