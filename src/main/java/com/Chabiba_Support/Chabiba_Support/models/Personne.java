package com.Chabiba_Support.Chabiba_Support.models;

import jakarta.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Personne {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersonne;

    private String nom;
    private String prenom;
    @Column(name ="num_tel")
    private String numTel;
    private String email;
    @Column(name ="mot_de_passe")
    private String motDePasse;

<<<<<<< Updated upstream
    public Personne(String nom, String prenom, String numTel, String email, String motDePasse) {
        this.nom = nom;
=======

    public Personne(String nom, String prenom, String numTel, String email, String motDePasse,Role role) {
		this.nom = nom;
>>>>>>> Stashed changes
        this.prenom = prenom;
        this.numTel = numTel;
        this.email = email;
        this.motDePasse = motDePasse;
    }
	public Personne(Personne personne){
		this.nom = personne.nom;
		this.prenom = personne.prenom;
		this.numTel = personne.numTel;
		this.email = personne.email;
		this.motDePasse = personne.motDePasse;
		this.role=personne.role;
	}
    public Personne() {

    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
<<<<<<< Updated upstream
=======
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


>>>>>>> Stashed changes
}
