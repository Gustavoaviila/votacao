package github.com.Gustavoaviila.votacao.service.exceptions;

public class DatabaseException extends RuntimeException{

  public DatabaseException (String msg) {
    super(msg);
  }
}
