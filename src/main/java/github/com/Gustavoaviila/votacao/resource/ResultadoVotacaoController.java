package github.com.Gustavoaviila.votacao.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import github.com.Gustavoaviila.votacao.domain.dto.ResultadoVotacaoDTO;
import github.com.Gustavoaviila.votacao.service.ResultadoVotacaoService;

@RestController
@RequestMapping("/api/resultado")
public class ResultadoVotacaoController {

  @Autowired
  private ResultadoVotacaoService service;

  @PostMapping
  public ResponseEntity<ResultadoVotacaoDTO> gerarResultado(@RequestBody ResultadoVotacaoDTO dto){
    return ResponseEntity.ok().body(service.gerarResultado(dto));
  }
}
