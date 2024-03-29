package br.com.peixoto.atacadista.repository;

import br.com.peixoto.atacadista.domain.Cliente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCpf(String cpf);
    Optional<List<Cliente>> findByEnderecoCidadeEstadoId(Long estadoId);

}
