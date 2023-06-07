package com.Chabiba_Support.Chabiba_Support.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

    private String nom;
    private String prenom;
    private String email;
    private String numTel;
    private  String motDePasse;
    private String role;
    private String nomEntreprise;
    private String cin;

}
