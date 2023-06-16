package com.Chabiba_Support.Chabiba_Support.models;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;


@Getter
 @Setter
 @EqualsAndHashCode
 @Entity
 @AllArgsConstructor
 @NoArgsConstructor
 @Table(name = "Employee")
    public class Employee {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long idEmployee;
    @Column(nullable = false, unique = true)
    private String cin;

	private String speciality;
    @OneToOne
    @JoinColumn(name = "id_personne")
    @JsonIgnore
    private Personne personne;

    public Employee(Personne personne, String cin) {
        this.personne = personne;
        this.cin = cin;
    }


    public String getCin() {
            return cin;
        }

        public void setCin(String cin) {
            this.cin = cin;
        }


        public Long getIdPersonne(){
        return this.personne.getIdPersonne();
        }
 }


