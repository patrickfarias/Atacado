package br.com.peixoto.atacadista.openapi.controller;

//import br.com.peixoto.atacadista.dto.CategoriaRequestDTO;
//import br.com.peixoto.atacadista.dto.CategoriaResponseDTO;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.enums.ParameterIn;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Produto")
public interface ProdutoControllerOpenApi {

//    @Operation(summary = "Save", description = "Cadastro de categorias",
//            responses = {
//                    @ApiResponse(responseCode = "201", description = "CREATED"),
//                    @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(ref = "ErrorMessage"))),
//                    @ApiResponse(responseCode = "422", description = "UNPROCESSABLE ENTITY", content = @Content(schema = @Schema(ref = "ErrorMessage")))
//            })
//    CategoriaResponseDTO save(@RequestBody(description = "Salva uma categoria", required = true) CategoriaRequestDTO descricao);
//
//    @Operation(summary = "Update", description = "Atualizaçao de categorias",
//            parameters = {
//                    @Parameter(in = ParameterIn.QUERY, name = "categoriaId",
//                            description = "categoriaId", example = "1499", schema = @Schema(type = "Long"))},
//            responses = {@ApiResponse(responseCode = "200")}
//    )
//    CategoriaResponseDTO update(@Parameter(hidden = true, description = "Id da categoria", schema = @Schema(type = "categoriaId")) Long categoriaId,
//                                @RequestBody(description = "Atualiza uma Categoria", required = true) CategoriaRequestDTO requestBody);
//
//    @Operation(summary = "Delete", description = "Remove uma categoria",
//            responses = {@ApiResponse(responseCode = "204")})
//    void delete(@RequestBody(description = "Deleta umaCategoria", required = true) Long idCategoria);
//
//    @Operation(summary = "findById", description = "Lista Categorias por ID",
//            parameters = {
//                    @Parameter(in = ParameterIn.QUERY, name = "categoriaId",
//                            description = "ID da Categoria",
//                            example = "1499",
//                            schema = @Schema(type = "Long"))
//            })
//    CategoriaResponseDTO findById(@RequestBody(description = "Retorna uma categoria", required = true) Long idCategoria);
//
//    @Operation(summary = "findAll", description = "Lista todas as categorias")
//    List<CategoriaResponseDTO> findAll();

}