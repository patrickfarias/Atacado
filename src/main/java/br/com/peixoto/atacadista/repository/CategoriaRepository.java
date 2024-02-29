package br.com.peixoto.atacadista.repository;

import br.com.peixoto.atacadista.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
