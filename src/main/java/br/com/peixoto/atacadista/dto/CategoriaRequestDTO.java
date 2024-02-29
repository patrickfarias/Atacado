package br.com.peixoto.atacadista.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class CategoriaRequestDTO {

    @Schema(example = "Higiene Pessoal")
    @Valid
    @NotNull
    private String descricao;


}