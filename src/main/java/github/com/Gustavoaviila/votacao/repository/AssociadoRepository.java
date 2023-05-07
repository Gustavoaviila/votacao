package github.com.Gustavoaviila.votacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import github.com.Gustavoaviila.votacao.domain.Associado;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {

  Optional<Associado> findByCpf(String cpf);
}
