package com.Chabiba_Support.Chabiba_Support.requests;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentaireRequestDTO {
    private int etoile;
    private String texteC;
    private Long idDemande;
    private Long idClient;

}
