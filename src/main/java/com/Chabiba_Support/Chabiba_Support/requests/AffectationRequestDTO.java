package com.Chabiba_Support.Chabiba_Support.requests;

import lombok.*;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AffectationRequestDTO {
    private String mission;
    private long idDemande;
    private long idEmployee;
    private Date delaiDate;
}
