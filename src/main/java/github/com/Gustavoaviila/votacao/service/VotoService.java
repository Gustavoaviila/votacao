package github.com.Gustavoaviila.votacao.service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import github.com.Gustavoaviila.votacao.domain.Associado;
import github.com.Gustavoaviila.votacao.domain.SessaoVotacao;
import github.com.Gustavoaviila.votacao.domain.Voto;
import github.com.Gustavoaviila.votacao.domain.dto.VotoDTO;
import github.com.Gustavoaviila.votacao.repository.AssociadoRepository;
import github.com.Gustavoaviila.votacao.repository.SessaoVotacaoRepository;
import github.com.Gustavoaviila.votacao.repository.VotoRepository;
import github.com.Gustavoaviila.votacao.service.exceptions.ObjectNotAvailable;
import github.com.Gustavoaviila.votacao.service.exceptions.ObjectNotFound;
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

    if(dto.getSessaoVotacao().getFim().isBefore(LocalDateTime.now())){
      throw new SessionNotAvailableException("Essa sessao ja esta encerrada");
    }

    if (repository.existsByAssociadoIdAndSessaoVotacaoId(dto.getAssociado().getId(), dto.getSessaoVotacao().getId())) {
      throw new ObjectNotAvailable("O associado já votou nesta sessao");
    }

    return repository.save(convertDtoToEntity(dto));
  }

  public Voto convertDtoToEntity (VotoDTO dto){
    Voto voto = new Voto();
    voto.setAssociado(dto.getAssociado());
    voto.setOpcaoVoto(dto.getOpcaoVoto());
    voto.setSessaoVotacao(dto.getSessaoVotacao());
    return voto;
  }

  public VotoDTO convertEntityToDto (Voto voto){

    VotoDTO dto = new VotoDTO();
    dto.setAssociado(voto.getAssociado());
    dto.setOpcaoVoto(voto.getOpcaoVoto());
    dto.setSessaoVotacao(voto.getSessaoVotacao());
    return dto;
  }

  private VotoDTO setVoto(VotoDTO dto){
    Optional<Associado> associado = Optional.ofNullable(associadoService.findById(dto.getAssociado().getId()));

    Optional<SessaoVotacao> sessaoVotacao = Optional.ofNullable(sessaoService.findById(dto.getSessaoVotacao().getId()));

    dto.setSessaoVotacao(sessaoVotacao.get());
    dto.setAssociado(associado.get());
    return dto;
  }
}
