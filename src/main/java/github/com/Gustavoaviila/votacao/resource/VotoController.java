package github.com.Gustavoaviila.votacao.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import github.com.Gustavoaviila.votacao.domain.Voto;
import github.com.Gustavoaviila.votacao.domain.dto.VotoDTO;
import github.com.Gustavoaviila.votacao.service.VotoService;

@RestController
@RequestMapping(value = "/api/votar")
public class VotoController {

  @Autowired
  private VotoService service;

  @PostMapping
  public ResponseEntity<VotoDTO> votar(@RequestBody VotoDTO dto) {
    Voto voto = new Voto();
    voto = service.votar(dto);
    return ResponseEntity.ok(dto);
  }
}
