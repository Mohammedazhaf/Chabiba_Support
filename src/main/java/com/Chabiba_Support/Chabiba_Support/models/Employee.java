package com.Chabiba_Support.Chabiba_Support.models;

import jakarta.persistence.*;

    @Entity
    @Table(name = "Employee")
    public class Employee extends Personne{
        @Column(nullable = false, unique = true)
        private String cin;
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private EmployeeRole employeeRole;

        public Employee(){

        }

        public Employee(String nom, String prenom, String numTel, String email, String motDePasse, String cin, EmployeeRole employeeRole) {
            super(nom, prenom, numTel, email, motDePasse);
            this.cin = cin;
            this.employeeRole = employeeRole;
        }

        public String getCin() {
            return cin;
        }

        public void setCin(String cin) {
<<<<<<< Updated upstream
            this.cin = cin;
        }

        public EmployeeRole getEmployeeRole() {
            return employeeRole;
        }

        public void setEmployeeRole(EmployeeRole employeeRole) {
            this.employeeRole = employeeRole;
        }
    }
=======
			this.cin = cin;
		}
}
>>>>>>> Stashed changes


