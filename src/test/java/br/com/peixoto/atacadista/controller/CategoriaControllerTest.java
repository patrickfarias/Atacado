package br.com.peixoto.atacadista.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.peixoto.atacadista.FactoryObjects;
import br.com.peixoto.atacadista.domain.Categoria;
import br.com.peixoto.atacadista.dto.CategoriaRequestDTO;
import br.com.peixoto.atacadista.dto.CategoriaResponseDTO;
import br.com.peixoto.atacadista.service.CategoriaService;
import br.com.peixoto.atacadista.service.ModelMapperFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class CategoriaControllerTest {

    @InjectMocks
    private CategoriaController categoriaController;
    @Mock
    private CategoriaService categoriaService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ModelMapperFactory modelMapperFactory;

    private CategoriaRequestDTO categoriaRequestDTO;

    private Categoria categoria;

    @BeforeEach
    void setUp() {

        FactoryObjects factoryObjectsForTest = new FactoryObjects();

        categoriaRequestDTO = factoryObjectsForTest.getCategoriaRequestDTOGenericForTest();
        categoria = factoryObjectsForTest.getCategoriaGenericForTest();

    }

    @Test
    void save() {

        when(modelMapperFactory.getModelMapper()).thenReturn(modelMapper);
        when(categoriaService.save(any())).thenReturn(categoria);

        CategoriaResponseDTO response = categoriaController.save(categoriaRequestDTO);

        assertNotNull(response);
        assertEquals(CategoriaResponseDTO.class, response.getClass());

    }


    @Test
    void update() {

        when(modelMapperFactory.getModelMapper()).thenReturn(modelMapper);
        when(categoriaService.update(any(), any())).thenReturn(categoria);

        Long id = 1L;

        CategoriaResponseDTO response = categoriaController.update(id, categoriaRequestDTO);

        assertNotNull(response);
        assertEquals(CategoriaResponseDTO.class, response.getClass());
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }
}