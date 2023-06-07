package com.Chabiba_Support.Chabiba_Support.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;


@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "Client")
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    @Column(name = "nom_entreprise")
    private String nomEntreprise;

    @OneToOne
    @JoinColumn(name = "id_personne")
    @JsonBackReference
    private Personne personne;

    public Client(String nomEntreprise, Personne savedPersonne) {
        this.nomEntreprise = nomEntreprise;
        this.personne = savedPersonne;
    }

    public Long getPersonneId(){
        return this.personne.getIdPersonne();
    }
}
