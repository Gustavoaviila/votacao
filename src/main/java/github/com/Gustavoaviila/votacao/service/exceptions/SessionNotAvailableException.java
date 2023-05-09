package github.com.Gustavoaviila.votacao.service.exceptions;

public class SessionNotAvailableException extends RuntimeException{

  public SessionNotAvailableException(String msg) {
    super (msg);
  }
}
