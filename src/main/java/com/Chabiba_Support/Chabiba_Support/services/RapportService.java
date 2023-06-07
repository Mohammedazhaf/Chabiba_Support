package com.Chabiba_Support.Chabiba_Support.services;


import com.Chabiba_Support.Chabiba_Support.exception.RapportNotFoundException;
import com.Chabiba_Support.Chabiba_Support.models.Rapport;
import com.Chabiba_Support.Chabiba_Support.repositories.RapportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RapportService {

    @Autowired
    private RapportRepository rapportRepository;

    public List<Rapport> getAllRapports() {
        return rapportRepository.findAll();
    }

    public Rapport getRapportByIdRapport(Long idRapport) {
        return rapportRepository.findByIdRapport(idRapport)
                .orElseThrow(() -> new RapportNotFoundException("rapport introuvable avec l'ID : " + idRapport));
    }

    public Rapport saveRapport(Rapport rapport) {
        return rapportRepository.save(rapport);
    }

    public void deleteRapport(Long idRapport) {
        Rapport rapport = rapportRepository.findByIdRapport(idRapport)
                .orElseThrow(() -> new RapportNotFoundException("rapport introuvable avec l'ID : " + idRapport));
        rapportRepository.delete(rapport);
    }














}
