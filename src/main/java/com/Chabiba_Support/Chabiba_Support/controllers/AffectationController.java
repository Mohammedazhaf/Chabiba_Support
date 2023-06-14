package com.Chabiba_Support.Chabiba_Support.controllers;

import com.Chabiba_Support.Chabiba_Support.models.Affectation;
import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.models.Rapport;
import com.Chabiba_Support.Chabiba_Support.requests.AffectationRequestDTO;
import com.Chabiba_Support.Chabiba_Support.services.AffectationService;
import com.Chabiba_Support.Chabiba_Support.services.DemandeService;
import com.Chabiba_Support.Chabiba_Support.services.EmployeeService;
import com.Chabiba_Support.Chabiba_Support.services.RapportService;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping({"/api/affectation"})
public class AffectationController {
    @Autowired
    private AffectationService affectationService;
    @Autowired
    private DemandeService demandeService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public List<Affectation> getAllAffectations(){
        return affectationService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<String> createAffectation(@RequestBody AffectationRequestDTO affectationDTO){
        Affectation affectation1 = new Affectation();
        Employee employee = employeeService.findEmployeeById(affectationDTO.getIdEmployee());
        Demande demande = demandeService.findById(affectationDTO.getIdDemande());
        affectation1.setEmployee(employee);
        affectation1.setDemande(demande);
        affectation1.setMission(affectationDTO.getMission());
        affectation1.setDelaiDate(affectationDTO.getDelaiDate());
        affectation1.setIdAffectation(null);
        affectationService.saveAffectation(affectation1);
        return ResponseEntity.ok("The affectation was created");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAffectation(@RequestBody AffectationRequestDTO affectationDTO,@PathVariable(value = "id") long id){
        Affectation currentAffectation = affectationService.getAffectationById(id);
        Employee employee = employeeService.findEmployeeById(affectationDTO.getIdEmployee());
        Demande demande = demandeService.findById(affectationDTO.getIdDemande());
        // Initialize the affectation and its associations
//        Hibernate.initialize(currentAffectation);
//        Hibernate.initialize(currentAffectation.getEmployee());
//        Hibernate.initialize(currentAffectation.getDemande());



        currentAffectation.setMission(affectationDTO.getMission());
        currentAffectation.setEmployee(employee);
        currentAffectation.setDemande(demande);
        currentAffectation.setDelaiDate(affectationDTO.getDelaiDate());
        System.out.println(currentAffectation.getIdAffectation());
        affectationService.saveAffectation(currentAffectation);
        return ResponseEntity.ok("The affectation has updated");
    }

}
