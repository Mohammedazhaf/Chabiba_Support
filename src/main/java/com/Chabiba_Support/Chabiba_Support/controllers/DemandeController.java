package com.Chabiba_Support.Chabiba_Support.controllers;

import com.Chabiba_Support.Chabiba_Support.models.*;
import com.Chabiba_Support.Chabiba_Support.requests.DemandeRequestDTO;
import com.Chabiba_Support.Chabiba_Support.requests.RapportRequestDTO;
import com.Chabiba_Support.Chabiba_Support.services.DemandeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = {"/api/demande"},produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class DemandeController {

    private DemandeService demandeService;
    private ModelMapper modelMapper;

    @GetMapping({"/all"})
    public List<Demande> findAll(){
        return demandeService.findAll();
    }

    @GetMapping({"/client/{id}"})
    public List<Demande> findByIdClient(@PathVariable("id") Long id){
        return demandeService.getDemandeByClient(id);
    }

    @GetMapping({"/etat/{etat}"})
    public List<Demande> findByEtat(@PathVariable("etat") Etat etat) {
        return demandeService.findByEtat(etat);
    }

    @PostMapping(value = {"/add"}, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> createDemande(@RequestPart("file") MultipartFile file, @RequestPart("demande") String demandeJson) throws IOException {
        // Convert DemandeRequestDTO to Demande entity  (Can Be changed)
        DemandeRequestDTO demandeDTO = new ObjectMapper().readValue(demandeJson, DemandeRequestDTO.class);
        Demande demande = new Demande();
        demande = modelMapper.map(demandeDTO, Demande.class);
        MultipartFile f = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
        demande.setDocumentD(f.getBytes());
        demande.setIdDemande(null);
        demande.setResponseResponsable(ResponseResponsable.pending);
        demande.setEtat(Etat.EnAttente);
        demande.setType(Type.NonUrgent);

        if (!file.isEmpty()) {
            String uploadsDir = System.getProperty("user.dir") + "/uploads/documents/";
            String filePath = uploadsDir + file.getOriginalFilename();
            File directory = new File(uploadsDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            file.transferTo(new File(filePath));
            demande.setFilePath("http:/localhost:8080/personnes/document/"+filePath);
        }
        demandeService.saveDemande(demande);
        return ResponseEntity.ok("Demande created successfully");
    }

    /*@PostMapping(value = {"/add"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createRapport(@RequestPart("file") MultipartFile file, @RequestPart("rapport") String rapportJson) throws IOException {
        // Convert demandeJson (String) to RapportRequestDTO
        RapportRequestDTO rapportDTO = new ObjectMapper().readValue(rapportJson, RapportRequestDTO.class);
        Rapport rapport = new Rapport();
        rapport.setDate(rapportDTO.getDate());
        rapport.setContenu(rapportDTO.getContenu());
        ////Get a EMployee and Demande Objects/////////
        Employee employee = employeeService.findEmployeeById(rapportDTO.getIdEmployee());
        Demande demande = demandeService.findById(rapportDTO.getIdDemande());
        rapport.setEmployee(employee);
        rapport.setDemande(demande);

        //rapport.setDocumentR(file.getBytes());
        rapport.setIdRapport(null);
        if (!file.isEmpty()) {
            String filePath = "C:/Users/issam/Desktop/BackendNew/uploads/" + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            rapport.setFilePath(filePath);
        }
        rapportService.saveRapport(rapport);
        return ResponseEntity.ok("Rapport created succusfully");
    }*/
    @DeleteMapping({"/delete/{id}"})
    public void deleteDemande(@PathVariable("id") Long idDemande) {
        this.demandeService.deleteById(idDemande);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countAllDemandes() {
        long count = demandeService.countAllDemandes();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    //that accesptation from Version secretaire to version acceptable by Responsable
    @PutMapping("/accepterResponsable/{id}")
    public Demande acceptDemande(@PathVariable(value = "id") long id) {
        Demande currentDemande = demandeService.findById(id);
        currentDemande.setResponseResponsable(ResponseResponsable.acceptable);
        currentDemande.setEtat(Etat.EnCours);
        return demandeService.save(currentDemande);
    }
    @PutMapping("/doneResponsable/{id}")
    public Demande doneDemande(@PathVariable(value = "id") long id) {
        Demande currentDemande = demandeService.findById(id);
        currentDemande.setEtat(Etat.Termine);
        return demandeService.save(currentDemande);
    }

    @PutMapping("/rejeterResponsable/{id}")
    public Demande rejectDemande(@PathVariable(value = "id") long id) {
        Demande currentDemande = demandeService.findById(id);
        currentDemande.setResponseResponsable(ResponseResponsable.rejected);
        return demandeService.save(currentDemande);
    }

    @PutMapping("/secretairePassed/{id}")
    public Demande secretairePassed(@PathVariable(value = "id") long id){
        Demande currentDemande = demandeService.findById(id);
        currentDemande.setVerSecretaire(true);
        return demandeService.save(currentDemande);
    }

    /*@PutMapping("/update/{id}")
    public ResponseEntity<String> updateDemande(@RequestPart("demande") String demandeJson,@RequestPart("file") MultipartFile file, @PathVariable(value = "id") long id) throws IOException {
        Demande currentDemande = demandeService.findById(id);
        DemandeRequestDTO demandeRequestDTO = new ObjectMapper().readValue(demandeJson, DemandeRequestDTO.class);


        currentDemande.setDate(demandeRequestDTO.getDate());
        currentDemande.setTitre(demandeRequestDTO.getTitre());
        currentDemande.setEtat(demandeRequestDTO.getEtat());
        currentDemande.setService(demandeRequestDTO.getService());
        currentDemande.setMessage(demandeRequestDTO.getMessage());
        currentDemande.setType(demandeRequestDTO.getType());

        if(Arrays.equals(currentDemande.getDocumentD(), file.getBytes())){
            System.out.println("The Document has Equals");
        }else{
            System.out.println("The Document has Not Equals");
            currentDemande.setDocumentD(file.getBytes());
        }
        demandeService.saveDemande(currentDemande);
        return ResponseEntity.ok("Demande updated successfully");
    }*/
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateDemande(@RequestPart("demande") String demandeJson, @PathVariable(value = "id") long id) throws IOException {
        Demande currentDemande = demandeService.findById(id);
        DemandeRequestDTO demandeRequestDTO = new ObjectMapper().readValue(demandeJson, DemandeRequestDTO.class);
        currentDemande.setDate(demandeRequestDTO.getDate());
        currentDemande.setTitre(demandeRequestDTO.getTitre());
        currentDemande.setEtat(demandeRequestDTO.getEtat());
        currentDemande.setService(demandeRequestDTO.getService());
        currentDemande.setMessage(demandeRequestDTO.getMessage());
        currentDemande.setType(demandeRequestDTO.getType());
        currentDemande.setVerSecretaire(true);
        demandeService.saveDemande(currentDemande);
        return ResponseEntity.ok("Demande updated successfully");
    }
    @GetMapping({"/getById/{id}"})
    public ResponseEntity<Demande> findDemandeById(@PathVariable("id") Long id){
        Demande demande = demandeService.findById(id);
        if (demande == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(demande);
    }


}
