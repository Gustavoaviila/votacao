package github.com.Gustavoaviila.votacao.resource.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import github.com.Gustavoaviila.votacao.service.exceptions.DatabaseException;
import github.com.Gustavoaviila.votacao.service.exceptions.ObjectNotAvailable;
import github.com.Gustavoaviila.votacao.service.exceptions.ResourceNotFoundException;
import github.com.Gustavoaviila.votacao.service.exceptions.SessionNotAvailableException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ObjectNotAvailable.class)
  public ResponseEntity<StandardError> objectNotAvailable(ObjectNotAvailable ex, HttpServletRequest request){
    StandardError error = new StandardError(LocalDateTime.now(),
        HttpStatus.NO_CONTENT.value(), ex.getMessage(), request.getRequestURI());
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(error);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<StandardError> dataBaseException(DatabaseException ex, HttpServletRequest request){
    StandardError error = new StandardError(LocalDateTime.now(),
        HttpStatus.NO_CONTENT.value(), ex.getMessage(), request.getRequestURI());
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(error);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<StandardError> resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request){
    StandardError error = new StandardError(LocalDateTime.now(),
        HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(SessionNotAvailableException.class)
  public ResponseEntity<StandardError> sessionNotAvailableException(SessionNotAvailableException ex, HttpServletRequest request){
    StandardError error = new StandardError(LocalDateTime.now(),
        HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage(), request.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
  }
}
