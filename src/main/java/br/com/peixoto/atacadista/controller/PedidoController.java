package br.com.peixoto.atacadista.controller;

import br.com.peixoto.atacadista.dto.PedidoRequestDTO;
import br.com.peixoto.atacadista.dto.PedidoResponseDTO;
import br.com.peixoto.atacadista.jpamodel.CrudRepository;
import br.com.peixoto.atacadista.jpamodel.FindRepository;
import br.com.peixoto.atacadista.openapi.controller.PedidoControllerOpenApi;
import br.com.peixoto.atacadista.service.PedidoService;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/v1/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements
        CrudRepository<PedidoRequestDTO, PedidoResponseDTO>,
        FindRepository<PedidoRequestDTO, PedidoResponseDTO>,
        PedidoControllerOpenApi {

    private final PedidoService pedidoService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PedidoResponseDTO save(@Valid @RequestBody PedidoRequestDTO requestBody) {
        return pedidoService.save(requestBody);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update")
    public PedidoResponseDTO update(@RequestHeader(value="pedidoId") Long pedidoId,
            @Valid @RequestBody PedidoRequestDTO requestBody) {
        return pedidoService.update(pedidoId, requestBody);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void delete(@RequestHeader(value="pedidoId") Long pedidoId) {
        pedidoService.delete(pedidoId);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PedidoResponseDTO> findAll() {
        return pedidoService.findAll();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{pedidoId}")
    public PedidoResponseDTO findById(@PathVariable Long pedidoId) {
        return pedidoService.findById(pedidoId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cliente")
    public List<PedidoResponseDTO> findByCliente(@RequestHeader(value="clienteId") Long clienteId) {

        return pedidoService.findByIdCliente(clienteId);
    }

}

