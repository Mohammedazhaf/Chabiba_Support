package com.Chabiba_Support.Chabiba_Support.Response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentaireResponseDTO {
    private Long idCommentaire;
    private int etoile;
    private String texteC;
    private Long idClient;
    private Long idDemande;
}
