package com.Chabiba_Support.Chabiba_Support.models;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

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
<<<<<<< Updated upstream

	@CreationTimestamp
	private LocalDateTime creationDate;
	@ManyToOne
	@JoinColumn(
			name = "id_client"
	)
	private Client client;

=======
	private String filePath;
	@CreationTimestamp
	private LocalDateTime creationDate;
	@ManyToOne
	@JoinColumn(
			name = "id_client"
	)
	private Client client;

>>>>>>> Stashed changes
	public void setIdClient(Long id){
		this.client.setIdClient(id);
	}
}
