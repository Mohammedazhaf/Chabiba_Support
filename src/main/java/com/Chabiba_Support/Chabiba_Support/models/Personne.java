package com.Chabiba_Support.Chabiba_Support.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import java.util.List;



@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public  class Personne implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idPersonne;

    private String nom;
    private String prenom;
    @Column(name ="num_tel")
    private String numTel;
    @Column(nullable = false, length = 80, unique = true)
    private String email;
    @Column(name ="mot_de_passe",nullable = false)
    private String motDePasse;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToOne(mappedBy = "personne")
    @JsonManagedReference
    private Client client;
    @OneToOne(mappedBy = "personne")
    @JsonBackReference
    private Employee employee;

    public Personne(long l, String personne, String personne1, String s, String s1, String motdepasse, Role responsable) {
        this.setIdPersonne(l);
        this.setNom(personne);
        this.setPrenom(personne1);
        this.setNumTel(s);
        this.setEmail(s1);
        this.setMotDePasse(motdepasse);
        this.setRole(responsable);
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


}
