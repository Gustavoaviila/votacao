package github.com.Gustavoaviila.votacao.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import github.com.Gustavoaviila.votacao.domain.Voto;
import github.com.Gustavoaviila.votacao.domain.dto.VotoDTO;
import github.com.Gustavoaviila.votacao.repository.VotoRepository;
import github.com.Gustavoaviila.votacao.service.exceptions.ObjectNotAvailableException;
import github.com.Gustavoaviila.votacao.service.exceptions.SessionNotAvailableException;

@Service
public class VotoService {

  @Autowired
  private VotoRepository repository;

  @Autowired
  private SessaoVotacaoService sessaoService;

  @Autowired
  private AssociadoService associadoService;

  @Transactional
  public Voto votar(VotoDTO dto) {
    dto = setVoto(dto);

    if(dto.getSessaoVotacaoDTO().getFim().isBefore(LocalDateTime.now())){
      throw new SessionNotAvailableException("Essa sessao ja esta encerrada");
    }

    if (repository.existsByAssociadoIdAndSessaoVotacaoId(dto.getAssociadoDTO().getId(), dto.getSessaoVotacaoDTO().getId())) {
      throw new ObjectNotAvailableException("O associado já votou nesta sessao");
    }

    var voto = convertDtoToEntity(dto);
    return repository.save(voto);
  }

  public Voto convertDtoToEntity (VotoDTO dto){
    var voto = new Voto();
    voto.setAssociado(associadoService.convertDtoToEntity(dto.getAssociadoDTO()));
    voto.setOpcaoVoto(dto.getOpcaoVoto());
    voto.setSessaoVotacao(sessaoService.convertDtoToEntity(dto.getSessaoVotacaoDTO()));
    return voto;
  }

  public VotoDTO convertEntityToDto (Voto voto){

    var dto = new VotoDTO();
    dto.setAssociadoDTO(associadoService.convertEntityToDto(voto.getAssociado()));
    dto.setOpcaoVoto(voto.getOpcaoVoto());
    dto.setSessaoVotacaoDTO(sessaoService.convertEntityToDto(voto.getSessaoVotacao()));
    return dto;
  }

  private VotoDTO setVoto(VotoDTO dto){
    var associado = associadoService.findById(dto.getAssociadoDTO().getId());

    var sessaoVotacao = sessaoService.findById(dto.getSessaoVotacaoDTO().getId());

    dto.setSessaoVotacaoDTO(sessaoService.convertEntityToDto(sessaoVotacao));
    dto.setAssociadoDTO(associadoService.convertEntityToDto(associado));
    return dto;
  }
}
