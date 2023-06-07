package com.Chabiba_Support.Chabiba_Support.controllers;

import com.Chabiba_Support.Chabiba_Support.Response.CommentaireResponseDTO;
import com.Chabiba_Support.Chabiba_Support.models.Client;
import com.Chabiba_Support.Chabiba_Support.models.Commentaire;
import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.models.Etat;
import com.Chabiba_Support.Chabiba_Support.requests.CommentaireRequestDTO;
import com.Chabiba_Support.Chabiba_Support.services.ClientService;
import com.Chabiba_Support.Chabiba_Support.services.CommentaireService;
import com.Chabiba_Support.Chabiba_Support.services.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/commentaires"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentaireController {
    @Autowired
    private CommentaireService commentaireService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private DemandeService demandeService;

    @GetMapping({"/all"})
    public List<Commentaire> getAllCommentaires() {
        return commentaireService.getAllCommentaires();
    }

    @GetMapping("/getById/{id}")
    public CommentaireResponseDTO getCommentaireById(@PathVariable Long id) {
        Commentaire commentaire = commentaireService.getCommentaireById(id);

        CommentaireResponseDTO commentaireResponse = new CommentaireResponseDTO();
        commentaireResponse.setEtoile(commentaire.getEtoile());
        commentaireResponse.setIdCommentaire(commentaire.getIdCommentaire());
        commentaireResponse.setTexteC(commentaire.getTexteC());
        commentaireResponse.setIdClient(commentaire.getIdClient());
        commentaireResponse.setIdDemande(commentaire.getIdDemande());
        return commentaireResponse;
    }

    @PostMapping({"/add"})
    public ResponseEntity<String> createCommentaire(@RequestBody CommentaireRequestDTO commentaireRequestDTO) {
        Client client = clientService.getClientByID(commentaireRequestDTO.getIdClient());
        Demande demande = demandeService.findById(commentaireRequestDTO.getIdDemande());
        if(demande.getClient().getIdClient().equals(client.getIdClient()) && demande.getEtat().equals(Etat.Termine) && !commentaireService.isReviewedDemande(demande)){
            Commentaire commentaire = new Commentaire();
            commentaire.setClient(client);
            commentaire.setDemande(demande);
            commentaire.setTexteC(commentaireRequestDTO.getTexteC());
            commentaire.setEtoile(commentaireRequestDTO.getEtoile());
            commentaireService.saveCommentaire(commentaire);
            return ResponseEntity.ok("The Commentaire has Created");
        }
        return ResponseEntity.ok("That Client can't add that Commentaire");
    }

    @PutMapping("/update/{id}")
    public Commentaire updateCommentaire(@PathVariable Long id, @RequestBody CommentaireRequestDTO commentaireRequestDTO) {
        Commentaire currentCommentaire = commentaireService.getCommentaireById(id);
        if(currentCommentaire.getIdClient()!=commentaireRequestDTO.getIdClient() && currentCommentaire.getIdDemande()!=commentaireRequestDTO.getIdDemande()){
            return null;
        }
        currentCommentaire.setEtoile(commentaireRequestDTO.getEtoile());
        currentCommentaire.setTexteC(commentaireRequestDTO.getTexteC());
        return commentaireService.saveCommentaire(currentCommentaire);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCommentaire(@PathVariable Long id) {
        commentaireService.deleteCommentaire(id);
        return ResponseEntity.ok().build();
    }
}
