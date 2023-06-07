package com.Chabiba_Support.Chabiba_Support.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@NamedEntityGraph(name = "affectationGraph",
        attributeNodes = {
                @NamedAttributeNode(value = "employee"),
                @NamedAttributeNode(value = "demande")
        })
public class Affectation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idAffectation;

    @ManyToOne
    @JoinColumn(name = "id_demande")
    private Demande demande;


    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;


    @Column(nullable = false)
    private String mission;
    @Column(nullable = false)
    private Date delaiDate;
    public long getIdDemande(){
        return this.demande.getIdDemande();
    }
    public long getIdEmployee(){
        return this.employee.getIdEmployee();
    }



}
