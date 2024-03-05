package br.com.peixoto.atacadista;

import br.com.peixoto.atacadista.domain.Categoria;
import br.com.peixoto.atacadista.dto.CategoriaRequestDTO;
import br.com.peixoto.atacadista.dto.CategoriaResponseDTO;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FactoryObjects {

    public UUID getUUIDGenericForTest() {
        return UUID.fromString("c715489d-2267-49f1-9eae-5dd98dd7ece4");
    }

    public Categoria getCategoriaGenericForTest() {

        return Categoria.builder()
                .id(1L)
                .descricao("Higiene Pessoal")
                .dataCriacao(OffsetDateTime.now())
                .dataAlteracao(OffsetDateTime.now())
                .build();
    }

    public Optional<Categoria> getCategoriaOptionalGenericForTest() {
        return Optional.of(getCategoriaGenericForTest());
    }

    public List<Categoria> getCategoriaListGenericForTest(){

        final List<Categoria> categoriaList = new ArrayList<>();
        categoriaList.add(getCategoriaGenericForTest());

        return categoriaList;
    }

    public CategoriaRequestDTO getCategoriaRequestDTOGenericForTest() {

        return CategoriaRequestDTO.builder()
                .descricao("Higiene Pessoal")
                .build();
    }

    public CategoriaResponseDTO getCategoriaResponseDTOGenericForTest() {

        return CategoriaResponseDTO.builder()
                .id(1L)
                .descricao("Higiene Pessoal")
                .dataCriacao(OffsetDateTime.now())
                .dataAlteracao(OffsetDateTime.now())
                .build();
    }

}