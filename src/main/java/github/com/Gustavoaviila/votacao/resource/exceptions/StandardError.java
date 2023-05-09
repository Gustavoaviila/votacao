package github.com.Gustavoaviila.votacao.resource.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StandardError {

  private LocalDateTime timeStamp;
  private Integer status;
  private String error;
  private String path;
}
