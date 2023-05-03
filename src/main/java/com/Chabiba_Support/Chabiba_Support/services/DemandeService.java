package com.Chabiba_Support.Chabiba_Support.services;

import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.models.Etat;
import com.Chabiba_Support.Chabiba_Support.repositories.DemandeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeService {
    private DemandeRepository demandeRepository;
    public DemandeService(){
    }

    //afficher tous les demandes envoyer
    public List<Demande> findAll(){
        return this.demandeRepository.findAll();
    }

    //afficher la demande par etat
    public List<Demande> findByEtat(Etat etat) {
        return this.demandeRepository.findByEtat(etat);
    }

    //afficher la demande par l'id de chaque client
    public List<Demande> getDemandeByClient(Long idPersonne) {
        return this.demandeRepository.findByClientIdPersonne(idPersonne);
    }

    //accepter a demande
    public Demande save(Demande demande) {
        return   this.demandeRepository.save(demande);
    }

    //refuser la demande
    public void deleteById(Long idDemande) {
        this.demandeRepository.deleteById(idDemande);
    }
    //la somme de toute les demande envoyer au cas ou on vous genere un diagramme
    public Long countAllDemandes() {
        return demandeRepository.count();
    }

    //chercher par l'id de la demande
    public Demande findById(long idDemande) {
        return this.demandeRepository.findById(idDemande).orElse(null);
    }
}
