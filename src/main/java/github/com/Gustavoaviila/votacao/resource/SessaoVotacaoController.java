package github.com.Gustavoaviila.votacao.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import github.com.Gustavoaviila.votacao.domain.SessaoVotacao;
import github.com.Gustavoaviila.votacao.domain.dto.SessaoVotacaoDTO;
import github.com.Gustavoaviila.votacao.service.SessaoVotacaoService;

@RestController
@RequestMapping(value = "/api/sessoes")
public class SessaoVotacaoController {

  @Autowired
  private SessaoVotacaoService service;

  @GetMapping
  public ResponseEntity<List<SessaoVotacaoDTO>> findAll(){
    List<SessaoVotacao> sessoes = new ArrayList<>();
    sessoes = service.findAll();
    List<SessaoVotacaoDTO> dtos = service.convertListaEntityToListDto(sessoes);
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("{id}")
  public ResponseEntity<SessaoVotacaoDTO> findById(@PathVariable Long id){
    SessaoVotacao sessaoVotacao = service.findById(id);
    SessaoVotacaoDTO dto = service.convertEntityToDto(sessaoVotacao);
    return ResponseEntity.ok().body(dto);
  }

  @PostMapping
  public ResponseEntity<SessaoVotacaoDTO> abrirSessao(@RequestBody SessaoVotacaoDTO dto) {
    SessaoVotacao sessaoAberta = service.abrirSessao(dto);
    dto = service.convertEntityToDto(sessaoAberta);
    return ResponseEntity.status(HttpStatus.CREATED).body(dto);
  }
}
