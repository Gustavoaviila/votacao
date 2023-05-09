package github.com.Gustavoaviila.votacao.domain;

import github.com.Gustavoaviila.votacao.Enum.OpcaoVoto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Voto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "associado_id", nullable = false)
  private Associado associado;

  @ManyToOne
  @JoinColumn(name = "sessao_id", nullable = false)
  private SessaoVotacao sessaoVotacao;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private OpcaoVoto opcaoVoto;
}

