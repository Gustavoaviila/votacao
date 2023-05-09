package github.com.Gustavoaviila.votacao.domain.dto;

import github.com.Gustavoaviila.votacao.Enum.OpcaoVoto;
import github.com.Gustavoaviila.votacao.domain.Associado;
import github.com.Gustavoaviila.votacao.domain.SessaoVotacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VotoDTO {

  private Associado associado;
  private SessaoVotacao sessaoVotacao;
  private OpcaoVoto opcaoVoto;
}
