package br.com.peixoto.atacadista.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
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
public class CategoriaResponseDTO {

    @Schema(example = "1499")
    private String id;

    @Schema(example = "Higiene Pessoal")
    private String descricao;

    @Schema(example = "2024-02-27T18:33:29.624557Z")
    private OffsetDateTime dataCriacao;

    @Schema(example = "2024-02-28T10:08:52.935113Z")
    private OffsetDateTime dataAlteracao;

}