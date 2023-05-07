package github.com.Gustavoaviila.votacao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoVotacao {

  private Integer totalVotos;
  private Integer votosSim;
  private Integer votosNao;
  private String resultado;
}
