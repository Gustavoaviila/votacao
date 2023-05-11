package github.com.Gustavoaviila.votacao.domain.dto;

import java.time.LocalDateTime;
import github.com.Gustavoaviila.votacao.domain.Pauta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessaoVotacaoDTO {

  private Long id;
  private PautaDTO pautaDTO;
  private LocalDateTime inicio;
  private LocalDateTime fim;
  private Long duracao;
}
