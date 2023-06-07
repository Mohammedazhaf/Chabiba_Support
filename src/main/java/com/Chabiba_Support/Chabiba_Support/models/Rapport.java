package com.Chabiba_Support.Chabiba_Support.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Rapport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idRapport;

    @Column(nullable = false, length = 20)
    private Date date;

    @Column(nullable = false, length = 150)
    private String contenu;



    @ManyToOne
    @JoinColumn(
            name = "idEmployee"
    )
    public Employee employee;

    @ManyToOne
    @JoinColumn(
            name = "idDemande"
    )
    public Demande demande;

    @Column(nullable = true,columnDefinition = "LONGBLOB",name = "documentR")
    private byte[] documentR;


    public long getIdEmployee(){
        return this.employee.getIdEmployee();
    }
    public long getIdDemande(){
        return this.demande.getIdDemande();
    }
}
