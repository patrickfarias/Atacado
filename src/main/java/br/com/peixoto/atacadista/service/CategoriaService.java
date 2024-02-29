package br.com.peixoto.atacadista.service;

import br.com.peixoto.atacadista.domain.Categoria;
import br.com.peixoto.atacadista.dto.CategoriaRequestDTO;
import br.com.peixoto.atacadista.dto.CategoriaResponseDTO;
import br.com.peixoto.atacadista.exception.AbstractMessageErrorCode;
import br.com.peixoto.atacadista.exception.BadRequestException;
import br.com.peixoto.atacadista.exception.ErrorMessage;
import br.com.peixoto.atacadista.jpamodel.CrudRepository;
import br.com.peixoto.atacadista.jpamodel.FindRepository;
import br.com.peixoto.atacadista.jpamodel.MergeRepository;
import br.com.peixoto.atacadista.repository.CategoriaRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class CategoriaService implements
        CrudRepository<CategoriaRequestDTO, CategoriaResponseDTO>,
        FindRepository<CategoriaRequestDTO, CategoriaResponseDTO>,
        MergeRepository<Categoria, CategoriaResponseDTO> {

    private final CategoriaRepository categoriaRepository;

    private final ModelMapper modelMapper;

    @Override
    public CategoriaResponseDTO save(CategoriaRequestDTO requestBody) {

        final var categoria = new Categoria();
        modelMapper.map(requestBody, categoria);

        final var response = new CategoriaResponseDTO();

        modelMapper.map(categoriaRepository.save(categoria), response);

        return response;
    }

    @Override
    @Transactional
    public CategoriaResponseDTO merge(final Categoria categoria) {

        final var response = new CategoriaResponseDTO();
        modelMapper.map(categoriaRepository.save(categoria), response);

        return response;
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
            modelMapper.map(categoria, categoriaResponse);

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
        modelMapper.map(categoria, categoriaResponse);

        return categoriaResponse;
    }

    @Override
    @Transactional
    public CategoriaResponseDTO update(final Long objectId, final CategoriaRequestDTO requestBody) {

        final Categoria categoriaAtual = getObjectAtual(objectId, requestBody);

        return merge(categoriaAtual);

    }

    @Override
    @Transactional
    public void delete(final Long idCategoria) {

        final Categoria categoriaAtual = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.CATEGORIA_NAO_ENCONTRADA, idCategoria)));

        categoriaRepository.delete(categoriaAtual);

    }
}
