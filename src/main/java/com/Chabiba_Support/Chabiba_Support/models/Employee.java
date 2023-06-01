package com.Chabiba_Support.Chabiba_Support.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
 @Setter
 @EqualsAndHashCode
 @Entity
 @Table(name = "Employee")
    public class Employee extends Personne{
        @Column(nullable = false, unique = true)
        private String cin;


        public Employee(){

        }

        public Employee(String nom, String prenom, String numTel, String email, String motDePasse, String cin, Role role) {
            super(nom, prenom, numTel, email, motDePasse,role);
            this.cin = cin;
        }

        public String getCin() {
            return cin;
        }

        public void setCin(String cin) {
            this.cin = cin;
        }



}


