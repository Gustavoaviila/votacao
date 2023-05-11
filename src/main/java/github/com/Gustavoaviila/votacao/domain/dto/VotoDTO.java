package github.com.Gustavoaviila.votacao.domain.dto;

import github.com.Gustavoaviila.votacao.Enum.OpcaoVoto;
import github.com.Gustavoaviila.votacao.domain.Associado;
import github.com.Gustavoaviila.votacao.domain.SessaoVotacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VotoDTO {

  private AssociadoDTO associadoDTO;
  private SessaoVotacaoDTO sessaoVotacaoDTO;
  private OpcaoVoto opcaoVoto;

}
