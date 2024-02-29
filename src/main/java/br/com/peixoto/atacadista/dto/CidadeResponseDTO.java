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
public class CidadeResponseDTO {

    @Schema(example = "1300")
    private String id;

    @Schema(example = "Uberlândia")
    private String nome;

    private EstadoResponseDTO command;

}