package github.com.Gustavoaviila.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import github.com.Gustavoaviila.votacao.domain.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long> {

  boolean existsByAssociadoIdAndPautaId(Long associadoId, Long pautaId);
}

