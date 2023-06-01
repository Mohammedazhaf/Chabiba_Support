package com.Chabiba_Support.Chabiba_Support.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter

@Entity
public class Commentaire implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long idCommentaire;
    @Column(nullable = false, length = 5)
    private int etoile;
    @Column(nullable = false, length = 100)
    private String texteC;
    @ManyToOne
    @JoinColumn(
            name = "id_personne"
    )
    public Personne personne;
    @OneToOne
    @JoinColumn(
            name = "id_demande"
    )
    public Demande demande;

    public Commentaire(){

    }

    public Commentaire(long l, String s) {
    }

    public void setIdCommentaire(Long idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public Long getIdCommentaire() {
        return idCommentaire;
    }
    public int getEtoile() {
        return etoile;
    }

    public void setEtoile(int etoile) {
        this.etoile = etoile;
    }

    public String getTexteC() {
        return texteC;
    }

    public void setTexteC(String texte) {
        this.texteC = texte;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }
}