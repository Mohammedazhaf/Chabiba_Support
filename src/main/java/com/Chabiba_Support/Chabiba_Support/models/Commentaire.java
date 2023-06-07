package com.Chabiba_Support.Chabiba_Support.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Commentaire implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idCommentaire;
    @Column(nullable = false, length = 5)
    private int etoile;
    @Column(nullable = false, length = 100)
    private String texteC;
    @ManyToOne
    @JoinColumn(
            name = "idClient"
    )
    public Client client;
    @OneToOne
    @JoinColumn(
            name = "id_demande"
    )
    public Demande demande;

    public long getIdClient(){
        return this.client.getIdClient();
    }
    public long getIdDemande(){
        return this.demande.getIdDemande();
    }
}