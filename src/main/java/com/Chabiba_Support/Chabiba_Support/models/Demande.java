package com.Chabiba_Support.Chabiba_Support.models;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Demande  implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long idDemande;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,columnDefinition = "VARCHAR(50) DEFAULT 'pending'")
    private ResponseResponsable responseResponsable;
    @Column(nullable=false, columnDefinition = "BIT DEFAULT 0")
    private boolean verSecretaire;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private String titre;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Etat etat;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Service service;

    @Column(nullable = false)
    private String budget;
    @Column(nullable = false, length = 200)
    private String  message;
    @Column(nullable = true,columnDefinition = "LONGBLOB",name = "documentD")
    private byte[] documentD;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;
    @ManyToOne
    @JoinColumn(
            name = "id_client"
    )
    private Client client;

    public Demande(long l, ResponseResponsable pending, boolean b, Date date, String titre, Etat enCours, Service developpement, String s, String message, Type urgent, Client client) {
        this.setIdDemande(l);
        this.setResponseResponsable(pending);
        this.setVerSecretaire(b);
        this.setDate(date);
        this.setTitre(titre);
        this.setEtat(enCours);
        this.setService(developpement);
        this.setBudget(s);
        this.setMessage(message);
        this.setType(urgent);
        this.setClient(client);
    }

    public void setIdClient(Long id){
        this.client.setIdClient(id);
    }
}