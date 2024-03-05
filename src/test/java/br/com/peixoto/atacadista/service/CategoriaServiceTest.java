package br.com.peixoto.atacadista.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import br.com.peixoto.atacadista.FactoryObjects;
import br.com.peixoto.atacadista.domain.Categoria;
import br.com.peixoto.atacadista.dto.CategoriaRequestDTO;
import br.com.peixoto.atacadista.dto.CategoriaResponseDTO;
import br.com.peixoto.atacadista.exception.BadRequestException;
import br.com.peixoto.atacadista.exception.ErrorMessage;
import br.com.peixoto.atacadista.repository.CategoriaRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService categoriaService;
    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ModelMapperFactory modelMapperFactory;

    private CategoriaRequestDTO categoriaRequestDTO;

    private Categoria categoria;

    private List<Categoria> categoriaList;

    private Optional<Categoria> categoriaOptional;


    @BeforeEach
    void setUp() {
        FactoryObjects factoryObjectsForTest = new FactoryObjects();
        categoriaRequestDTO = factoryObjectsForTest.getCategoriaRequestDTOGenericForTest();
        categoria = factoryObjectsForTest.getCategoriaGenericForTest();
        categoriaOptional = factoryObjectsForTest.getCategoriaOptionalGenericForTest();
        categoriaList = factoryObjectsForTest.getCategoriaListGenericForTest();
    }

    @Test
    void save() {
        when(modelMapperFactory.getModelMapper()).thenReturn(modelMapper);
        when(categoriaRepository.save(any())).thenReturn(categoria);

        Categoria response = categoriaService.save(categoriaRequestDTO);
        assertNotNull(response);
        assertEquals(Categoria.class, response.getClass());
        assertEquals("Higiene Pessoal", response.getDescricao());
    }
    @Test
    void merge() {
    }

    @Test
    void getObjectAtual() {

        String expectedMessage = "Nao existe um cadastro de categoria com id = 1";

        when(categoriaRepository.findById(anyLong())).thenReturn(Optional.empty());

        Long idCategoria = 1L;

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            categoriaService.getObjectAtual(idCategoria, categoriaRequestDTO);
        });

        assertNotNull(exception);

        Set<ErrorMessage> errorMessages = exception.getErrors();
        assertTrue(errorMessages.stream()
                .anyMatch(error -> expectedMessage.equals(error.getMessage())));
    }

    @Test
    void findAll() {

        when(categoriaRepository.findAll()).thenReturn(categoriaList);
        when(modelMapperFactory.getModelMapper()).thenReturn(modelMapper);

        List<CategoriaResponseDTO>  response = categoriaService.findAll();
        assertNotNull(response);

    }

    @Test
    void findById() {

        when(categoriaRepository.findById(anyLong())).thenReturn(categoriaOptional);
        when(modelMapperFactory.getModelMapper()).thenReturn(modelMapper);

        Long id = 1L;

        CategoriaResponseDTO response = categoriaService.findById(id);

        assertNotNull(response);
        assertEquals(CategoriaResponseDTO.class, response.getClass());
    }



    @Test
    void update() {

        when(categoriaRepository.findById(anyLong())).thenReturn(categoriaOptional);
        when(categoriaRepository.save(any())).thenReturn(categoria);


        Long id = 1L;

        Categoria response = categoriaService.update(id, categoriaRequestDTO);

        assertNotNull(response);
        assertEquals(Categoria.class, response.getClass());
        assertEquals("Higiene Pessoal", response.getDescricao());
    }

    @Test
    void delete() {

        when(categoriaRepository.findById(anyLong())).thenReturn(categoriaOptional);
        doNothing().when(categoriaRepository).delete(any());

        Long id = 1L;

        categoriaService.delete(id);

    }

}
