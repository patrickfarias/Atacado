package br.com.peixoto.atacadista.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(example = "1499")
    private String id;

    @Schema(example = "Silvio Santos")
    private String nome;

    @Schema(example = "12345678900")
    private String cpf;

    private EnderecoResponseDTO endereco;

    @Schema(example = "1200.00")
    private BigDecimal limitePorPedido;


}