package github.com.Gustavoaviila.votacao.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import github.com.Gustavoaviila.votacao.domain.Associado;
import github.com.Gustavoaviila.votacao.domain.dto.AssociadoDTO;
import github.com.Gustavoaviila.votacao.repository.AssociadoRepository;
import github.com.Gustavoaviila.votacao.service.exceptions.DatabaseException;
import github.com.Gustavoaviila.votacao.service.exceptions.ObjectNotFound;
import github.com.Gustavoaviila.votacao.service.exceptions.ResourceNotFoundException;

@Service
public class AssociadoService {

  @Autowired
  private AssociadoRepository repository;

  public List<Associado> findAll() {
    return repository.findAll();
  }

  public Associado findById(Long id) {
    Optional<Associado> associado = repository.findById(id);
    return associado.get();
  }
    @Transactional
    public Associado insert(AssociadoDTO dto) {
    Associado associado = new Associado();
    associado = convertDtoToEntity(dto);
    return repository.save(associado);
  }

  @Transactional
  public void delete(Long id) {
    try {
      repository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException(id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException(e.getMessage());
    }

  }

  @Transactional
  public Associado update (Long id, AssociadoDTO dto) {
    try {
      Optional<Associado> associado = repository.findById(id);
      return repository.save(associado.get());
    } catch (NoSuchElementException e) {
      throw new ResourceNotFoundException(id);
    }
  }

  public Associado convertDtoToEntity (AssociadoDTO dto){

    Associado associado = new Associado();
    associado.setCpf(dto.getCpf());
    associado.setNome(dto.getNome());
    return associado;
  }

  public AssociadoDTO convertEntityToDto (Associado associado){

    AssociadoDTO dto = new AssociadoDTO();
    dto.setCpf(associado.getCpf());
    dto.setNome(associado.getNome());
    return dto;
  }
}
