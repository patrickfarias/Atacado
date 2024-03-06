package br.com.peixoto.atacadista.controller;


import br.com.peixoto.atacadista.dto.ClienteRequestDTO;
import br.com.peixoto.atacadista.dto.ClienteResponseDTO;
import br.com.peixoto.atacadista.jpamodel.CrudController;
import br.com.peixoto.atacadista.jpamodel.FindController;
import br.com.peixoto.atacadista.openapi.controller.ClienteControllerOpenApi;
import br.com.peixoto.atacadista.service.ClienteService;
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
@RequestMapping(path = "/v1/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController implements
        CrudController<ClienteRequestDTO, ClienteResponseDTO>,
        FindController<ClienteRequestDTO, ClienteResponseDTO>,
        ClienteControllerOpenApi {

    private final ClienteService clienteService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ClienteResponseDTO save(@Valid @RequestBody ClienteRequestDTO requestBody) {
        return clienteService.save(requestBody);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update")
    public ClienteResponseDTO update(@RequestHeader(value="clienteId") Long clienteId,
            @Valid @RequestBody ClienteRequestDTO requestBody) {
        return clienteService.update(clienteId, requestBody);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void delete(@RequestHeader(value="clienteId") Long clienteId) {
        clienteService.delete(clienteId);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ClienteResponseDTO> findAll() {
        return clienteService.findAll();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{clienteId}")
    public ClienteResponseDTO findById(@PathVariable Long clienteId) {
        return clienteService.findById(clienteId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cpf/{cpf}")
    public ClienteResponseDTO findByCpf(@PathVariable String cpf) {
        return clienteService.findByCpf(cpf);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/uf")
    public List<ClienteResponseDTO> findByUF(@RequestHeader(value="estadoId") Long estadoId) {
        return clienteService.findByUF(estadoId);
    }

}

