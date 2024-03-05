package br.com.peixoto.atacadista.controller;

import br.com.peixoto.atacadista.dto.CategoriaRequestDTO;
import br.com.peixoto.atacadista.dto.CategoriaResponseDTO;
import br.com.peixoto.atacadista.jpamodel.CrudController;
import br.com.peixoto.atacadista.jpamodel.FindController;
import br.com.peixoto.atacadista.openapi.controller.CategoriaControllerOpenApi;
import br.com.peixoto.atacadista.service.CategoriaService;
import br.com.peixoto.atacadista.service.ModelMapperFactory;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
@RequestMapping(path = "/v1/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaController implements
        CrudController<CategoriaRequestDTO, CategoriaResponseDTO>,
        FindController<CategoriaRequestDTO, CategoriaResponseDTO>,
        CategoriaControllerOpenApi {

    private final CategoriaService categoriaService;

    private final ModelMapperFactory modelMapperFactory;

    private final ModelMapper modelMapper;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoriaResponseDTO save(@Valid @RequestBody CategoriaRequestDTO requestBody) {

        final var response = new CategoriaResponseDTO();
        modelMapperFactory.getModelMapper().map(categoriaService.save(requestBody), response);
        return response;

    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update")
    public CategoriaResponseDTO update(@RequestHeader(value="categoriaId") Long categoriaId,
            @Valid @RequestBody CategoriaRequestDTO requestBody) {

        final var response = new CategoriaResponseDTO();
        modelMapperFactory.getModelMapper().map(categoriaService.update(categoriaId, requestBody), response);
        return response;

    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void delete(@RequestHeader(value="categoriaId") Long categoriaId) {
        categoriaService.delete(categoriaId);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<CategoriaResponseDTO> findAll() {
        return categoriaService.findAll();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{categoriaId}")
    public CategoriaResponseDTO findById(@PathVariable Long categoriaId) {
        return categoriaService.findById(categoriaId);
    }

}

