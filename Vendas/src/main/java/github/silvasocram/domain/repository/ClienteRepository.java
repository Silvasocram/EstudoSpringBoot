package github.silvasocram.domain.repository;

import github.silvasocram.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNomeLike(String nome);

    List<Cliente> findByNomeContaining(String nome);
}
