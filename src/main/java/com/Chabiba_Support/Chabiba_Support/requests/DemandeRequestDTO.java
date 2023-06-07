package com.Chabiba_Support.Chabiba_Support.requests;

import com.Chabiba_Support.Chabiba_Support.models.Etat;
import com.Chabiba_Support.Chabiba_Support.models.ResponseResponsable;
import com.Chabiba_Support.Chabiba_Support.models.Service;
import com.Chabiba_Support.Chabiba_Support.models.Type;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DemandeRequestDTO {
//    private ResponseResponsable responseResponsable;
    private Date date;
    private String titre;
    private Etat etat;
    private Service service;
    private String budget;
    private String message;
    private Type type;
    private Long idClient;

}
