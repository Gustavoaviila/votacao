package github.com.Gustavoaviila.votacao.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import github.com.Gustavoaviila.votacao.domain.Pauta;
import github.com.Gustavoaviila.votacao.domain.dto.PautaDTO;
import github.com.Gustavoaviila.votacao.repository.PautaRepository;
import github.com.Gustavoaviila.votacao.service.exceptions.ObjectNotFound;

@Service
public class PautaService {

  @Autowired
  private PautaRepository repository;

  public List<Pauta> findAll() {
    return repository.findAll();
  }

  public Pauta findById(Long id) {
    Optional<Pauta> pauta = repository.findById(id);
    return pauta.get();
  }

  @Transactional
  public Pauta createPauta(PautaDTO dto){
    if(Objects.isNull(dto)){
      return new Pauta();
    }

    Pauta pauta = new Pauta();
    pauta = convertDtoToEntity(dto);
    pauta = repository.save(pauta);
    return pauta;
  }

  public Pauta convertDtoToEntity (PautaDTO dto){

    Pauta pauta = new Pauta();
    pauta.setTitulo(dto.getTitulo());
    return pauta;
  }

  public PautaDTO convertEntityToDto (Pauta pauta){

    PautaDTO dto = new PautaDTO();
    dto.setTitulo(pauta.getTitulo());
    return dto;
  }
}
