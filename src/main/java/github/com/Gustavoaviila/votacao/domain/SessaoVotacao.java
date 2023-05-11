package github.com.Gustavoaviila.votacao.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SessaoVotacao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime inicio;
  private LocalDateTime fim;
  private Long duracao;

  @ManyToOne
  @JoinColumn(name = "pauta_id", nullable = false)
  private Pauta pauta;

  @JsonIgnore
  @OneToMany(mappedBy = "sessaoVotacao")
  private List<Voto> votos = new ArrayList<>();
}
