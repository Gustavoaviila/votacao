package github.com.Gustavoaviila.votacao.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pauta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String titulo;

  @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL)
  private List<Voto> votos;

  @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL)
  private List<SessaoVotacao> sessoesVotacao;
}

