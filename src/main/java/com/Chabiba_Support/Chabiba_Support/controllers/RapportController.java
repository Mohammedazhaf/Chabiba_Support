package com.Chabiba_Support.Chabiba_Support.controllers;


import com.Chabiba_Support.Chabiba_Support.models.Rapport;
import com.Chabiba_Support.Chabiba_Support.services.RapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/rapports"})
public class RapportController {

    @Autowired
    private RapportService rapportService;


    @GetMapping({"/all"})
    public List<Rapport> getAllRapports() {
        return rapportService.getAllRapports();
    }

    @GetMapping("/afficher/{idRapport}")
    public Rapport getRapportByIdRapport(@PathVariable Long idRapport) {
        return rapportService.getRapportByIdRapport(idRapport);
    }


    @PostMapping({"/add"})
    public Rapport createRapport(@RequestBody Rapport rapport) {
        return rapportService.addRapport(rapport);
    }

    @DeleteMapping("/delete/{idRapport}")
    public ResponseEntity<?> deleteRapport(@PathVariable Long idRapport) {
        rapportService.deleteRapport(idRapport);
        return ResponseEntity.ok().build();
    }

}
