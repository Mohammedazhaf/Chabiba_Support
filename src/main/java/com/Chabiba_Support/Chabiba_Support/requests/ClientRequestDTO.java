package com.Chabiba_Support.Chabiba_Support.requests;

import com.Chabiba_Support.Chabiba_Support.models.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientRequestDTO {
    private String nom;
    private String prenom;
    private String numTel;
    private String email;
    private String motDePasse;
    private String nomEntreprise;

}
