package br.com.peixoto.atacadista.dto;

import static br.com.peixoto.atacadista.exception.AbstractMessageErrorCode.FIELD_REQUIRED;

import br.com.peixoto.atacadista.domain.Categoria;
import br.com.peixoto.atacadista.enumeration.TipoEmbalagem;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequestDTO {

    @Schema(example = "Amaciante Ype Ternura 1x2l")
    @Valid
    @NotNull
    private String descricao;

    @Schema(example = "11.99")
    @NotNull
    private BigDecimal preco;

    @Schema(example = "true")
    @NotNull(message = FIELD_REQUIRED)
    private Boolean ativo;

    @Schema(example = "10.00")
    @NotNull
    private BigDecimal percentualMaximoDesconto;

    @Schema(example = "PLASTICO")
    @NotNull
    private TipoEmbalagem tipoEmbalagem;

    private Categoria categoria;

}