package entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.TipoEvento;

@Entity
@Table(name = "eventi")
@Getter
@Setter
@NoArgsConstructor
public class Evento {
	@Id
	@GeneratedValue
	private UUID id;
	private String titolo;
	private LocalDate dataEvento;
	private String descrizione;
	private TipoEvento tipoEvento;
	private int numeroMassimoPartecipanti;

	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
	private Set<Partecipazione> partecipazione = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "location", referencedColumnName = "id")
	private Location location;
	public Evento(String titolo, String descrizione, LocalDate dataEvento, TipoEvento tipoEvento,
			int numeroMassimoPartecipanti, Location location) {
		this.titolo = titolo;
		this.dataEvento = dataEvento;
		this.descrizione = descrizione;
		this.tipoEvento = tipoEvento;
		this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
		this.location = location;

	}

	@Override
	public String toString() {
		return "Evento{" +
				"titolo='" + titolo + '\'' +
				", dataEvento=" + dataEvento +
				", descrizione='" + descrizione + '\'' +
				", tipoEvento=" + tipoEvento +
				", numeroMassimoPartecipanti=" + numeroMassimoPartecipanti +
				'}';
	}
}
