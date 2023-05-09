package github.com.Gustavoaviila.votacao.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import github.com.Gustavoaviila.votacao.domain.Pauta;
import github.com.Gustavoaviila.votacao.domain.dto.PautaDTO;
import github.com.Gustavoaviila.votacao.service.PautaService;


@RestController
@RequestMapping(value = "/api/pautas")
public class PautaController {

  @Autowired
  private PautaService service;

  @GetMapping
  public ResponseEntity<List<PautaDTO>> findAll(){
    List<Pauta> pautas = service.findAll();
    List<PautaDTO> dtos = pautas.stream()
        .map(pauta -> service.convertEntityToDto(pauta)).collect(Collectors.toList());
    return ResponseEntity.ok().body(dtos);
  }

  @GetMapping("{id}")
  public ResponseEntity<PautaDTO> findById(@PathVariable Long id){
    Pauta pauta = service.findById(id);
    PautaDTO dto = service.convertEntityToDto(pauta);
    return ResponseEntity.ok().body(dto);

  }

  @PostMapping
  public ResponseEntity<PautaDTO> createPauta(@RequestBody  PautaDTO pautaDTO) {
    Pauta pauta = service.createPauta(pautaDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pauta.getId()).toUri();
    pautaDTO.convertEntityToDto(pauta);
    return ResponseEntity.created(uri).body(pautaDTO);
  }

}
