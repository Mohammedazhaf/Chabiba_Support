package com.Chabiba_Support.Chabiba_Support.controllers;

import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.models.Etat;
import com.Chabiba_Support.Chabiba_Support.services.DemandeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping({"/api/demande"})
public class DemandeController {

    private DemandeService demandeService;

    @GetMapping({"/all"})
    public List<Demande> findAll(){
        return this.demandeService.findAll();
    }

    @GetMapping({"/etat/{etat}"})
    public List<Demande> findByEtat(@PathVariable("etat") Etat etat) {
        return this.demandeService.findByEtat(etat);
    }

    @PostMapping({"/add"})
    public Demande addDemande(@RequestBody Demande demande) {
        return this.demandeService.save(demande);
    }

    @DeleteMapping({"/delete/{id}"})
    public void deleteDemande(@PathVariable("id") Long idDemande) {
        this.demandeService.deleteById(idDemande);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countAllDemandes() {
        long count = demandeService.countAllDemandes();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    @PutMapping("/accepter/{id}")
    public Demande acceptDemande(@PathVariable(value = "id") long id) {
        Demande currentDemande = demandeService.findById(id);
        currentDemande.setEtat(Etat.valueOf("EnAttente"));
        return demandeService.save(currentDemande);
    }
    @PutMapping("/rejeter/{id}")
    public Demande rejectDemande(@PathVariable(value = "id") long id) {
        Demande currentDemande = demandeService.findById(id);
        currentDemande.setEtat(Etat.valueOf("Termine"));
        return demandeService.save(currentDemande);
    }
    // @PostMapping("/update") {
    //   return;
    //}
}
