package br.com.peixoto.atacadista.dto;

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
public class ClienteRequestDTO {

    @Schema(example = "Paulo Roberto dos Santos Silva")
    @Valid
    @NotNull
    private String nome;

    private String cpf;

    private EnderecoRequestDTO endereco;

    private BigDecimal limitePorPedido;

}