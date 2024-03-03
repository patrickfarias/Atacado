package br.com.peixoto.atacadista.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class EnderecoResponseDTO {

    @Schema(example = "38.400-000")
    private String cep;

    @Schema(example = "Rua dos Piratas Teimosos")
    private String logradouro;

    @Schema(example = "20")
    private String numero;

    @Schema(example = "CASA")
    private String complemento;

    @Schema(example = "Jardim América")
    private String bairro;
    private CidadeResponseDTO cidade;

}