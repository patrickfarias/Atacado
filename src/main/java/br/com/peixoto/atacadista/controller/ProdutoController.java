package br.com.peixoto.atacadista.controller;

import br.com.peixoto.atacadista.dto.ProdutoRequestDTO;
import br.com.peixoto.atacadista.dto.ProdutoResponseDTO;
import br.com.peixoto.atacadista.jpamodel.CrudRepository;
import br.com.peixoto.atacadista.jpamodel.FindRepository;
import br.com.peixoto.atacadista.openapi.controller.ProdutoControllerOpenApi;
import br.com.peixoto.atacadista.service.ProdutoService;
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
@RequestMapping(path = "/v1/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController implements
        CrudRepository<ProdutoRequestDTO, ProdutoResponseDTO>,
        FindRepository<ProdutoRequestDTO, ProdutoResponseDTO>,
        ProdutoControllerOpenApi {

    private final ProdutoService produtoService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProdutoResponseDTO save(@Valid @RequestBody ProdutoRequestDTO requestBody) {
        return produtoService.save(requestBody);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update")
    public ProdutoResponseDTO update(@RequestHeader(value="produtoId") Long produtoId,
            @Valid @RequestBody ProdutoRequestDTO requestBody) {
        return produtoService.update(produtoId, requestBody);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void delete(@RequestHeader(value="produtoId") Long produtoId) {
        produtoService.delete(produtoId);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ProdutoResponseDTO> findAll() {
        return produtoService.findAll();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{produtoId}")
    public ProdutoResponseDTO findById(@PathVariable Long produtoId) {
        return produtoService.findById(produtoId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/categoria")
    public List<ProdutoResponseDTO> findByNomeCategoria(@RequestHeader(value="nomeCategoria") String nomeCategoria) {
        return produtoService.findByNomeCategoria(nomeCategoria);
    }

}

