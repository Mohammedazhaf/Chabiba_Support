package com.Chabiba_Support.Chabiba_Support.controllers;


import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.models.Rapport;
import com.Chabiba_Support.Chabiba_Support.requests.DemandeRequestDTO;
import com.Chabiba_Support.Chabiba_Support.requests.RapportRequestDTO;
import com.Chabiba_Support.Chabiba_Support.services.DemandeService;
import com.Chabiba_Support.Chabiba_Support.services.EmployeeService;
import com.Chabiba_Support.Chabiba_Support.services.RapportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping({"/api/rapport"})
public class RapportController {

    @Autowired
    private RapportService rapportService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DemandeService demandeService;
    @Autowired
    private EmployeeService employeeService;



    @GetMapping({"/all"})
    public List<Rapport> getAllRapports() {
        return rapportService.getAllRapports();
    }

    @GetMapping("/getone/{idRapport}")
    public Rapport getRapportByIdRapport(@PathVariable Long idRapport) {
        return rapportService.getRapportByIdRapport(idRapport);
    }


    @PostMapping(value = {"/add"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createRapport(@RequestPart("file") MultipartFile file, @RequestPart("rapport") String rapportJson) throws IOException {
        // Convert demandeJson (String) to RapportRequestDTO
        RapportRequestDTO rapportDTO = new ObjectMapper().readValue(rapportJson, RapportRequestDTO.class);
        Rapport rapport = new Rapport();
        rapport = new Rapport();
        rapport.setDate(rapportDTO.getDate());
        rapport.setContenu(rapportDTO.getContenu());
        ////Get a EMployee and Demande Objects/////////
        Employee employee = employeeService.findEmployeeById(rapportDTO.getIdEmployee());
        Demande demande = demandeService.findById(rapportDTO.getIdDemande());
        rapport.setEmployee(employee);
        rapport.setDemande(demande);

        rapport.setDocumentR(file.getBytes());
        rapport.setIdRapport(null);
        rapportService.saveRapport(rapport);
        return ResponseEntity.ok("Rapport created succusfully");
    }

    @DeleteMapping("/delete/{idRapport}")
    public ResponseEntity<?> deleteRapport(@PathVariable Long idRapport) {
        rapportService.deleteRapport(idRapport);
        return ResponseEntity.ok().build();
    }
    @PutMapping(value = {"/update/{id}"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateRapport(@RequestPart("file") MultipartFile file, @RequestPart("rapport") String rapportJson, @PathVariable(value = "id") long id) throws IOException {
        Rapport currentRapport = rapportService.getRapportByIdRapport(id);
        RapportRequestDTO rapportDTO = new ObjectMapper().readValue(rapportJson, RapportRequestDTO.class);

        currentRapport.setContenu(rapportDTO.getContenu());
        currentRapport.setDate(rapportDTO.getDate());
        if(Arrays.equals(currentRapport.getDocumentR(), file.getBytes())){
            System.out.println("The Document has Equals");
        }else{
            System.out.println("The Document has Not Equals (Updated)");
            currentRapport.setDocumentR(file.getBytes());
        }
        rapportService.saveRapport(currentRapport);
        return ResponseEntity.ok("Demande updated successfully");
    }

}
