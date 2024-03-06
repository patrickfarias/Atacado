package br.com.peixoto.atacadista.openapi.controller;

import br.com.peixoto.atacadista.dto.ProdutoRequestDTO;
import br.com.peixoto.atacadista.dto.ProdutoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;


@Tag(name = "Produto")
public interface ProdutoControllerOpenApi {

    @Operation(summary = "Save", description = "Cadastro de Produtos",
            responses = {
                    @ApiResponse(responseCode = "201", description = "CREATED"),
                    @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(ref = "ErrorMessage"))),
                    @ApiResponse(responseCode = "422", description = "UNPROCESSABLE ENTITY", content = @Content(schema = @Schema(ref = "ErrorMessage")))
            })
    ProdutoResponseDTO save(@RequestBody(description = "Salva uma produtos", required = true) ProdutoRequestDTO descricao);

    @Operation(summary = "Update", description = "Atualizaçao de Produtos",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "produtoId",
                            description = "produtoId", example = "1499", schema = @Schema(type = "Long"))},
            responses = {@ApiResponse(responseCode = "200")}
    )
    ProdutoResponseDTO update(@Parameter(hidden = true, description = "Id do Produto", schema = @Schema(type = "produtoId")) Long produtoId,
                                @RequestBody(description = "Atualiza um Produto", required = true) ProdutoRequestDTO requestBody);

    @Operation(summary = "Delete", description = "Remove um Produto",
            responses = {@ApiResponse(responseCode = "204")})
    void delete(@RequestBody(description = "Deleta um Produto", required = true) Long produtoId);

    @Operation(summary = "findById", description = "Lista Produtos por ID",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "produtoId",
                            description = "ID do Produto",
                            example = "1499",
                            schema = @Schema(type = "Long"))
            })
    ProdutoResponseDTO findById(@RequestBody(description = "Retorna um Produto", required = true) Long produtoId);

    @Operation(summary = "findAll", description = "Lista todas os Produtos")
    List<ProdutoResponseDTO> findAll();

}