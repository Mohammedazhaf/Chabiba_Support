package com.Chabiba_Support.Chabiba_Support.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "Client")
public class Client extends Personne {
    @Column(name = "nom_entreprise")
    private String nomEntreprise;

    public Client(String nom, String prenom, String numTel, String email, String motDePasse, String nomEntreprise,Role role) {
        super(nom, prenom, numTel, email, motDePasse,role);
        this.nomEntreprise = nomEntreprise;
    }

    public Client(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public Client() {

    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }


}
