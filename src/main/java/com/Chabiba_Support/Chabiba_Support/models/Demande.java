package com.Chabiba_Support.Chabiba_Support.models;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Demande  implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long idDemande;
    @Column(nullable = false)
    private boolean reponse;
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
    @Column(nullable = false, length = 40)
    private String  message;
    @Column(nullable = false)
    private File documentD;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;
    @ManyToOne
    @JoinColumn(
            name = "idPersonne"
    )
    private Client client;
    public Demande(){

    }
    public Long getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(Long idDemande) {
        this.idDemande = idDemande;
    }

    public boolean isReponse() {
        return reponse;
    }

    public void setReponse(boolean reponse) {
        this.reponse = reponse;
    }

    public Etat getEtat() {
        return etat;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Service getService() {
        return service;
    }
    public void setService(Service service) {
        this.service = service;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public File getDocumentD() {
        return documentD;
    }

    public void setDocumentD(File document) {
        this.documentD = document;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}