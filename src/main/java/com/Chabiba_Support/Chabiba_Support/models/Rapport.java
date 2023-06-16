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
	@Column(nullable = true)
	private String filePath;

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

	public byte[] getDocumentR() {
		return documentR;
	}

	public void setDocumentR(byte[] documentR) {
		this.documentR = documentR;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
