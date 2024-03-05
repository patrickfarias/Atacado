package br.com.peixoto.atacadista.service;

import br.com.peixoto.atacadista.domain.Categoria;
import br.com.peixoto.atacadista.dto.CategoriaRequestDTO;
import br.com.peixoto.atacadista.dto.CategoriaResponseDTO;
import br.com.peixoto.atacadista.exception.AbstractMessageErrorCode;
import br.com.peixoto.atacadista.exception.BadRequestException;
import br.com.peixoto.atacadista.exception.ErrorMessage;
import br.com.peixoto.atacadista.jpamodel.CrudService;
import br.com.peixoto.atacadista.jpamodel.FindService;
import br.com.peixoto.atacadista.jpamodel.MergeService;
import br.com.peixoto.atacadista.repository.CategoriaRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

@Slf4j
@AllArgsConstructor
@Service
public class CategoriaService implements
        CrudService<CategoriaRequestDTO, Categoria>,
        FindService<CategoriaRequestDTO, CategoriaResponseDTO>,
        MergeService<Categoria, Categoria> {

    private final CategoriaRepository categoriaRepository;

    private final ModelMapperFactory modelMapperFactory;

    private final ModelMapper modelMapper;

    @Override
    public Categoria save(CategoriaRequestDTO requestBody) {

        final var categoria = new Categoria();
        modelMapperFactory.getModelMapper().map(requestBody, categoria);
        return categoriaRepository.save(categoria);
    }

    @Override
    @Transactional
    public Categoria merge(final Categoria categoria) {

        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria getObjectAtual(Long idCategoria, Object requestBody) {

        final Categoria categoriaAtual = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.CATEGORIA_NAO_ENCONTRADA, idCategoria)));

        BeanUtils.copyProperties(requestBody, categoriaAtual, "id", "dataCriacao");

        return categoriaAtual;
    }

    @Override
    public List<CategoriaResponseDTO> findAll() {

        final List<Categoria> categoriaList = categoriaRepository.findAll();

        final List<CategoriaResponseDTO> catogoriaResponseList = new ArrayList<>();

        categoriaList.forEach(categoria -> {

            final var categoriaResponse = new CategoriaResponseDTO();
            modelMapperFactory.getModelMapper().map(categoria, categoriaResponse);

            catogoriaResponseList.add(categoriaResponse);

        });
        return catogoriaResponseList;
    }

    @Override
    public CategoriaResponseDTO findById(final Long idCategoria) {

        final Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.CATEGORIA_NAO_ENCONTRADA, idCategoria)));

        final var categoriaResponse = new CategoriaResponseDTO();
        modelMapperFactory.getModelMapper().map(categoria, categoriaResponse);

        return categoriaResponse;
    }

    @Override
    @Transactional
    public Categoria update(final Long objectId, final CategoriaRequestDTO requestBody) {

        final Categoria categoriaAtual = getObjectAtual(objectId, requestBody);

        return merge(categoriaAtual);
    }

    @Override
    @Transactional
    public void delete(@RequestHeader(value="categoriaId") final Long categoriaId) {

        final Categoria categoriaAtual = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.CATEGORIA_NAO_ENCONTRADA, categoriaId)));

        categoriaRepository.delete(categoriaAtual);
    }
}
