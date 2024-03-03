package br.com.peixoto.atacadista.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstadoResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(example = "6")
    private String id;

    @Schema(example = "Minas Gerais")
    private String nome;

}