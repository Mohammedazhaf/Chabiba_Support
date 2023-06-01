package com.Chabiba_Support.Chabiba_Support.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Rapport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long idRapport;

    @Column(nullable = false, length = 20)
    private Date date;

    @Column(nullable = false, length = 150)
    private String contenu;



    @ManyToOne
    @JoinColumn(
            name = "id_personne"
    )
    public Employee employee;

    @ManyToOne
    @JoinColumn(
            name = "id_demande"
    )
    public Demande demande;

    @Column(nullable = false)
    private File documentR;

    public Long getIdRapport() {
        return idRapport;
    }

    public void setIdRapport(Long idRapport) {
        this.idRapport = idRapport;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public File getDocumentR() {
        return documentR;
    }

    public void setDocumentR(File documentR) {
        this.documentR = documentR;
    }
}
