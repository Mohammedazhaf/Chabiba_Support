package com.Chabiba_Support.Chabiba_Support.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
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
                name = "idPersonne"
        )
        public Client client;
        @OneToOne
        @JoinColumn(

                name = "idDemande"
        )
        public Demande demande;

        public Commentaire(){

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

        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
        }

        public Demande getDemande() {
            return demande;
        }

        public void setDemande(Demande demande) {
            this.demande = demande;
        }
    }
