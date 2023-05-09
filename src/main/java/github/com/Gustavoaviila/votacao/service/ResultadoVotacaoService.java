package github.com.Gustavoaviila.votacao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.com.Gustavoaviila.votacao.Enum.OpcaoVoto;
import github.com.Gustavoaviila.votacao.domain.SessaoVotacao;
import github.com.Gustavoaviila.votacao.domain.Voto;
import github.com.Gustavoaviila.votacao.domain.dto.ResultadoVotacaoDTO;
import github.com.Gustavoaviila.votacao.repository.SessaoVotacaoRepository;

@Service
public class ResultadoVotacaoService {

  @Autowired
  private SessaoVotacaoRepository sessaoVotacaoRepository;

  public ResultadoVotacaoDTO gerarResultado(ResultadoVotacaoDTO dto){
    Optional<SessaoVotacao> sessaoVotacao = sessaoVotacaoRepository.findById(dto.getSessaoVotacao().getId());
    dto.setVotosSim(0L);
    dto.setVotosNao(0L);
    dto.setPauta(sessaoVotacao.get().getPauta());
    dto.setTotalVotos(sessaoVotacao.get().getVotos().size() + 1L);
    for (Voto voto : sessaoVotacao.get().getVotos()){
      if(voto.getOpcaoVoto().equals(OpcaoVoto.SIM)){
        dto.setVotosSim(dto.getVotosSim() + 1);
      } else
        dto.setVotosNao(dto.getVotosNao() + 1);
    }
    return dto;
  }
}
