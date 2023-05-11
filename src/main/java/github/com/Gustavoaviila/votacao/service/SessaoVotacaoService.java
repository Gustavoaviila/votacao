package github.com.Gustavoaviila.votacao.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import github.com.Gustavoaviila.votacao.domain.Pauta;
import github.com.Gustavoaviila.votacao.domain.SessaoVotacao;
import github.com.Gustavoaviila.votacao.domain.dto.SessaoVotacaoDTO;
import github.com.Gustavoaviila.votacao.repository.SessaoVotacaoRepository;
import github.com.Gustavoaviila.votacao.service.exceptions.ResourceNotFoundException;


@Service
public class SessaoVotacaoService {

  @Autowired
  private SessaoVotacaoRepository repository;

  @Autowired
  private PautaService pautaService;

  @Transactional
  public SessaoVotacao abrirSessao(SessaoVotacaoDTO dto){
    dto = verificaPautaEDuracao(dto);
    var sessaoVotacao = new SessaoVotacao();
    sessaoVotacao = convertDtoToEntity(dto);
    return repository.save(sessaoVotacao);
  }

  public List<SessaoVotacao> findAll(){
    return repository.findAll();
  }

  public SessaoVotacao findById(Long id){
    var sessaoVotacao = repository.findById(id);
    return sessaoVotacao.orElseThrow(() -> new ResourceNotFoundException(id));
  }

  public SessaoVotacao convertDtoToEntity (SessaoVotacaoDTO dto){

    var sessaoVotacao = new SessaoVotacao();
    sessaoVotacao.setId(dto.getId());
    sessaoVotacao.setInicio(dto.getInicio());
    sessaoVotacao.setFim(dto.getFim());
    sessaoVotacao.setPauta(pautaService.convertDtoToEntity(dto.getPautaDTO()));
    sessaoVotacao.setDuracao(dto.getDuracao());
    return sessaoVotacao;
  }

  public SessaoVotacaoDTO convertEntityToDto (SessaoVotacao sessaoVotacao){

    var dto = new SessaoVotacaoDTO();
    dto.setId(sessaoVotacao.getId());
    dto.setInicio(sessaoVotacao.getInicio());
    dto.setFim(sessaoVotacao.getFim());
    dto.setPautaDTO(pautaService.convertEntityToDto(sessaoVotacao.getPauta()));
    dto.setDuracao(sessaoVotacao.getDuracao());
    return dto;
  }

  public List<SessaoVotacaoDTO> convertListaEntityToListDto (List<SessaoVotacao> sessoesVotacao){
    List<SessaoVotacaoDTO> dtos = new ArrayList<>();
    for(var sessao : sessoesVotacao){
      dtos.add(convertEntityToDto(sessao));
    }
    return dtos;
  }

  public SessaoVotacaoDTO verificaPautaEDuracao(SessaoVotacaoDTO dto){

    var pauta = pautaService.findById(dto.getPautaDTO().getId());

    dto.setPautaDTO(pautaService.convertEntityToDto(pauta));
    dto.setInicio(LocalDateTime.now());
    if (Objects.isNull(dto.getDuracao())){
      dto.setDuracao(0L);
      dto.setFim(dto.getInicio().plus(Duration.ofMinutes(1)));
    }
    dto.setFim(dto.getInicio().plus(Duration.ofMinutes(dto.getDuracao())));
    return dto;
  }
}
