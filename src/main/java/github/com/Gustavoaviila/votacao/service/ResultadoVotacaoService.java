package github.com.Gustavoaviila.votacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import github.com.Gustavoaviila.votacao.Enum.OpcaoVoto;
import github.com.Gustavoaviila.votacao.domain.Voto;
import github.com.Gustavoaviila.votacao.domain.dto.ResultadoVotacaoDTO;


@Service
public class ResultadoVotacaoService {

  @Autowired
  private SessaoVotacaoService sessaoVotacaoService;

  @Autowired
  private PautaService pautaService;

  public ResultadoVotacaoDTO gerarResultado(ResultadoVotacaoDTO dto){
    var sessaoVotacao = sessaoVotacaoService.findById(dto.getSessaoVotacaoDTO().getId());
    dto.setVotosSim(0L);
    dto.setVotosNao(0L);
    dto.setPautaDTO(pautaService.convertEntityToDto(sessaoVotacao.getPauta()));
    dto.setSessaoVotacaoDTO(sessaoVotacaoService.convertEntityToDto(sessaoVotacao));
    dto.setTotalVotos(sessaoVotacao.getVotos().size() + 0L);
    for (Voto voto : sessaoVotacao.getVotos()){
      if(voto.getOpcaoVoto().equals(OpcaoVoto.SIM)){
        dto.setVotosSim(dto.getVotosSim() + 1);
      } else
        dto.setVotosNao(dto.getVotosNao() + 1);
    }

    return dto;
  }
}
