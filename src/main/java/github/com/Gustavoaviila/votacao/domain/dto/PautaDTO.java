package github.com.Gustavoaviila.votacao.domain.dto;

import github.com.Gustavoaviila.votacao.domain.Pauta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PautaDTO {

  private String titulo;

  public PautaDTO convertEntityToDto (Pauta pauta){

    PautaDTO dto = new PautaDTO();
    dto.setTitulo(pauta.getTitulo());
    return dto;
  }
}
