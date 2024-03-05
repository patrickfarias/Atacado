package br.com.peixoto.atacadista.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import br.com.peixoto.atacadista.FactoryObjects;
import br.com.peixoto.atacadista.domain.Categoria;
import br.com.peixoto.atacadista.dto.CategoriaRequestDTO;
import br.com.peixoto.atacadista.dto.CategoriaResponseDTO;
import br.com.peixoto.atacadista.service.CategoriaService;
import br.com.peixoto.atacadista.service.ModelMapperFactory;
import java.util.List;
import java.util.Optional;
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

    private Optional<Categoria> categoriaOptional;

    private List<Categoria> categoriaList;

    private List<CategoriaResponseDTO> categoriaResponseList;
    private List<Optional<CategoriaResponseDTO>> categoriaResponseOptionalList;

    private CategoriaResponseDTO categoriaResponse;

    @BeforeEach
    void setUp() {

        FactoryObjects factoryObjectsForTest = new FactoryObjects();

        categoriaRequestDTO = factoryObjectsForTest.getCategoriaRequestDTOGenericForTest();
        categoria = factoryObjectsForTest.getCategoriaGenericForTest();
        categoriaOptional = factoryObjectsForTest.getCategoriaOptionalGenericForTest();
        categoriaResponseOptionalList = factoryObjectsForTest.getCategoriaResponseOptionalListGenericForTest();
        categoriaResponseList = factoryObjectsForTest.getCategoriaResponseListGenericForTest();
        categoriaResponse = factoryObjectsForTest.getCategoriaResponseDTOGenericForTest();

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

        doNothing().when(categoriaService).delete(anyLong());

        Long id = 1L;
        categoriaController.delete(id);
    }


    @Test
    void findAll() {

        when(categoriaService.findAll()).thenReturn(categoriaResponseList);

        List<CategoriaResponseDTO>  response = categoriaController.findAll();
        assertNotNull(response);

    }

    @Test
    void findById() {

        when(categoriaService.findById(anyLong())).thenReturn(categoriaResponse);

        Long id = 1L;
        CategoriaResponseDTO  response = categoriaController.findById(id);
        assertEquals(CategoriaResponseDTO.class, response.getClass());
        assertNotNull(response);
    }
}