package br.com.peixoto.atacadista.repository;

import br.com.peixoto.atacadista.domain.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoriaDescricaoContaining(String descricao);

}
