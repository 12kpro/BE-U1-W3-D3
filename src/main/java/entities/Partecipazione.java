package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.Stato;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "partecipazione")
@Getter
@Setter
@NoArgsConstructor
public class Partecipazione {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id", nullable = false)
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "evento_id", referencedColumnName = "id", nullable = false)
    private Evento evento;

    @Enumerated(EnumType.STRING)
    private Stato stato;

    public Partecipazione(Persona persona, Evento evento, Stato stato) {
        this.persona = persona;
        this.evento = evento;
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Partecipazione{" +
                "persona=" + persona +
                ", evento=" + evento +
                ", stato=" + stato +
                '}';
    }
}
