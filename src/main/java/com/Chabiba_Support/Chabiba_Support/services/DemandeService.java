package com.Chabiba_Support.Chabiba_Support.services;

import com.Chabiba_Support.Chabiba_Support.models.Client;
import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.models.Etat;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.repositories.ClientRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.DemandeRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DemandeService {
    private final DemandeRepository demandeRepository;
    private final PersonneRepository personneRepository;
    private final ClientRepository clientRepository;

    //afficher tous les demandes envoyer
    public List<Demande> findAll(){
        return demandeRepository.findAll();
    }

    //afficher la demande par etat
    public List<Demande> findByEtat(Etat etat) {
        return demandeRepository.findByEtat(etat);
    }

    //afficher la demande par l'id de chaque client
    public List<Demande> getDemandeByClient(Long idClient) {
        Client findedClient = clientRepository.findById(idClient).orElse(null);

        return demandeRepository.findByClient(findedClient);
    }

    //accepter a demande
    public Demande save(Demande demande) {
        return   demandeRepository.save(demande);
    }

    //refuser la demande
    public void deleteById(Long idDemande) {
        demandeRepository.deleteById(idDemande);
    }

    //la somme de toute les demande envoyer au cas ou on vous genere un diagramme
    public Long countAllDemandes() {
        return demandeRepository.count();
    }

    //chercher par l'id de la demande
    public Demande findById(long idDemande) {
        return demandeRepository.findById(idDemande).orElse(null);
    }

//    public void createDemande(Demande demande, MultipartFile document) throws IOException {
//        // Set the document file in the demande
//        if (document != null) {
//            // You can handle the file as per your requirements
//            byte[] documentData = document.getBytes();
//            demande.setDocumentD(documentData);
//        }
//
//        demandeRepository.save(demande);
//    }
public void saveDemande(Demande demande) {
    // Save the demande in the database using your repository or data access layer
    demandeRepository.save(demande);
}

}
