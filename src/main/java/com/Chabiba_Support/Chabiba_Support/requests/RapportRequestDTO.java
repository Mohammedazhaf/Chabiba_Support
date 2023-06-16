package com.Chabiba_Support.Chabiba_Support.requests;

import lombok.*;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RapportRequestDTO {

    private Date date;
    private String contenu;
    private long idEmployee;
    private long idDemande;

}
