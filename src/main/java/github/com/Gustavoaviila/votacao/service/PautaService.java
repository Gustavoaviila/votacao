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
import github.com.Gustavoaviila.votacao.service.exceptions.ResourceNotFoundException;

@Service
public class PautaService {

  @Autowired
  private PautaRepository repository;

  public List<Pauta> findAll() {
    return repository.findAll();
  }

  public Pauta findById(Long id) {
    var pauta = repository.findById(id);
    return pauta.orElseThrow(() -> new ResourceNotFoundException(id));
  }

  @Transactional
  public Pauta createPauta(PautaDTO dto){
    if(Objects.isNull(dto)){
      return new Pauta();
    }

    var pauta = new Pauta();
    pauta = convertDtoToEntity(dto);
    pauta = repository.save(pauta);
    return pauta;
  }

  public Pauta convertDtoToEntity (PautaDTO dto){

    var pauta = new Pauta();
    pauta.setId(dto.getId());
    pauta.setTitulo(dto.getTitulo());
    return pauta;
  }

  public PautaDTO convertEntityToDto (Pauta pauta){

    var dto = new PautaDTO();
    dto.setId(pauta.getId());
    dto.setTitulo(pauta.getTitulo());
    return dto;
  }
}
