package github.com.Gustavoaviila.votacao.domain;

import github.com.Gustavoaviila.votacao.Enum.OpcaoVoto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Voto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "associado_id", nullable = false)
  private Associado associado;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pauta_id", nullable = false)
  private Pauta pauta;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private OpcaoVoto opcaoVoto;
}

