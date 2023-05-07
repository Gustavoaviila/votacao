package github.com.Gustavoaviila.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import github.com.Gustavoaviila.votacao.domain.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
