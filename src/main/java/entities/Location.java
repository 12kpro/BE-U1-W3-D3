package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Entity
@Table(name = "location")
@Getter
@Setter
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue
    private UUID id;
    private String nome;
    private String citta;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private Set<Evento> eventi = new HashSet<>();

    public Location(String nome, String citta) {
        this.nome = nome;
        this.citta = citta;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", citta='" + citta + '\'' +
                '}';
    }
}
