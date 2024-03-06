package br.com.peixoto.atacadista.openapi.controller;

import br.com.peixoto.atacadista.dto.ClienteRequestDTO;
import br.com.peixoto.atacadista.dto.ClienteResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "Cliente")
public interface ClienteControllerOpenApi {

    @Operation(summary = "Save", description = "Cadastro de clientes",
            responses = {
                    @ApiResponse(responseCode = "201", description = "CREATED"),
                    @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(ref = "ErrorMessage"))),
                    @ApiResponse(responseCode = "422", description = "UNPROCESSABLE ENTITY", content = @Content(schema = @Schema(ref = "ErrorMessage")))
            })
    ClienteResponseDTO save(@RequestBody(description = "Salva um cliente", required = true) ClienteRequestDTO descricao);

    @Operation(summary = "Update", description = "Atualizaçao de clientes",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "idCliente",
                            description = "idCliente", example = "1499", schema = @Schema(type = "Long"))},
            responses = {@ApiResponse(responseCode = "200")}
    )
    ClienteResponseDTO update(@Parameter(hidden = true, description = "Id do cliente", schema = @Schema(type = "clienteId")) Long clienteId,
                                @RequestBody(description = "Atualiza um Cliente", required = true) ClienteRequestDTO requestBody);

    @Operation(summary = "Delete", description = "Remove uma cliente",
            responses = {@ApiResponse(responseCode = "204")})
    void delete(@RequestBody(description = "Deleta um Cliente", required = true) Long idCliente);

    @Operation(summary = "findById", description = "Lista Clientes por ID",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "clienteId",
                            description = "ID do cliente",
                            example = "1499",
                            schema = @Schema(type = "Long"))
            })
    ClienteResponseDTO findById(@RequestBody(description = "Retorna um cliente", required = true) Long idCliente);

    @Operation(summary = "findAll", description = "Lista todas os clientes")
    List<ClienteResponseDTO> findAll();

}