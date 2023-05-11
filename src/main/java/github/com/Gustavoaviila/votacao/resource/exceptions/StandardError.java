package github.com.Gustavoaviila.votacao.resource.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {

  private LocalDateTime timeStamp;
  private Integer status;
  private String error;
  private String message;
  private String path;
}
