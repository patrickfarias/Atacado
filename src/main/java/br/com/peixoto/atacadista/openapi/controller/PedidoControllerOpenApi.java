package br.com.peixoto.atacadista.openapi.controller;

import br.com.peixoto.atacadista.dto.PedidoRequestDTO;
import br.com.peixoto.atacadista.dto.PedidoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;


@Tag(name = "Pedido")
public interface PedidoControllerOpenApi {

    @Operation(summary = "Save", description = "Cadastro de pedidos",
            responses = {
                    @ApiResponse(responseCode = "201", description = "CREATED"),
                    @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(ref = "ErrorMessage"))),
                    @ApiResponse(responseCode = "422", description = "UNPROCESSABLE ENTITY", content = @Content(schema = @Schema(ref = "ErrorMessage")))
            })
    PedidoResponseDTO save(@RequestBody(description = "Salva um pedido", required = true) PedidoRequestDTO descricao);

    @Operation(summary = "Update", description = "Atualizaçao de pedidos",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "pedidoId",
                            description = "pedidoId", example = "1499", schema = @Schema(type = "Long"))},
            responses = {@ApiResponse(responseCode = "200")}
    )
    PedidoResponseDTO update(@Parameter(hidden = true, description = "Id do pedido", schema = @Schema(type = "pedidoId")) Long pedidoId,
                                @RequestBody(description = "Atualiza um pedido", required = true) PedidoRequestDTO requestBody);

    @Operation(summary = "Delete", description = "Remove um pedido",
            responses = {@ApiResponse(responseCode = "204")})
    void delete(@RequestBody(description = "Deleta um pedido", required = true) Long pedidoId);

    @Operation(summary = "findById", description = "Lista Pedidos por ID",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "pedidoId",
                            description = "ID do pedido",
                            example = "1499",
                            schema = @Schema(type = "Long"))
            })
    PedidoResponseDTO findById(@RequestBody(description = "Retorna um pedido", required = true) Long pedidoId);

    @Operation(summary = "findAll", description = "Lista todas os pedidos")
    List<PedidoResponseDTO> findAll();

}