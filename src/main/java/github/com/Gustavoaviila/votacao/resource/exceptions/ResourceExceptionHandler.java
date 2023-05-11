package github.com.Gustavoaviila.votacao.resource.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import github.com.Gustavoaviila.votacao.service.exceptions.DatabaseException;
import github.com.Gustavoaviila.votacao.service.exceptions.ObjectNotAvailableException;
import github.com.Gustavoaviila.votacao.service.exceptions.ResourceNotFoundException;
import github.com.Gustavoaviila.votacao.service.exceptions.SessionNotAvailableException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
    String error = "Resource not found";
    HttpStatus status = HttpStatus.NOT_FOUND;
    StandardError err = new StandardError(LocalDateTime.now(),
        status.value(), error, e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<StandardError> dataBaseException(DatabaseException e, HttpServletRequest request){
    String error = "Database error";
    HttpStatus status = HttpStatus.BAD_REQUEST;
    StandardError err = new StandardError(LocalDateTime.now(),
        status.value(), error, e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(SessionNotAvailableException.class)
  public ResponseEntity<StandardError> sessionNotAvailable(SessionNotAvailableException e, HttpServletRequest request){
    String error = "Session not Available";
    HttpStatus status = HttpStatus.FORBIDDEN;
    StandardError err = new StandardError(LocalDateTime.now(),
        status.value(), error, e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(ObjectNotAvailableException.class)
  public ResponseEntity<StandardError> objectNotAvailable(ObjectNotAvailableException e, HttpServletRequest request){
    String error = "Object not available";
    HttpStatus status = HttpStatus.CONFLICT;
    StandardError err = new StandardError(LocalDateTime.now(),
        status.value(), error, e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }
}
