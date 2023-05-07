package github.com.Gustavoaviila.votacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import github.com.Gustavoaviila.votacao.domain.Pauta;
import github.com.Gustavoaviila.votacao.domain.SessaoVotacao;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {

  Optional<SessaoVotacao> findTopByPautaOrderByFimDesc(Pauta pauta);
}

