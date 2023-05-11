package github.com.Gustavoaviila.votacao.domain.dto;

import github.com.Gustavoaviila.votacao.domain.SessaoVotacao;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoVotacaoDTO {


  private Long totalVotos;
  private Long votosSim;
  private Long votosNao;
  private PautaDTO pautaDTO;

  @Column(nullable = false)
  private SessaoVotacaoDTO sessaoVotacaoDTO;
}
