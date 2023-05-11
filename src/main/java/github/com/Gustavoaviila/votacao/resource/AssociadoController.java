package github.com.Gustavoaviila.votacao.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import github.com.Gustavoaviila.votacao.domain.Associado;
import github.com.Gustavoaviila.votacao.domain.dto.AssociadoDTO;
import github.com.Gustavoaviila.votacao.service.AssociadoService;

@RestController
@RequestMapping(value = "/api/associados")
public class AssociadoController {

  @Autowired
  private AssociadoService service;

  @GetMapping
  public ResponseEntity<List<AssociadoDTO>> findAll(){
    List<Associado> associados = service.findAll();
    List<AssociadoDTO> dtos = associados.stream()
        .map(associado -> service.convertEntityToDto(associado)).collect(Collectors.toList());
    return ResponseEntity.ok().body(dtos);
  }

  @GetMapping("{id}")
  public ResponseEntity<AssociadoDTO> findById(@PathVariable Long id){
    var associado = service.findById(id);
    AssociadoDTO dto = service.convertEntityToDto(associado);
    return ResponseEntity.ok().body(dto);

  }

  @PostMapping
  public ResponseEntity<AssociadoDTO> insert (@RequestBody AssociadoDTO dto){
    var associado = new Associado();
    associado = service.insert(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(associado.getId()).toUri();
    return ResponseEntity.created(uri).body(service.convertEntityToDto(associado));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete (@PathVariable Long id){
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<AssociadoDTO> update (@PathVariable Long id, @RequestBody AssociadoDTO dto){
    var associado = new Associado();
    associado = service.update(id, dto);
    dto = service.convertEntityToDto(associado);
    return ResponseEntity.ok().body(dto);
  }
}
