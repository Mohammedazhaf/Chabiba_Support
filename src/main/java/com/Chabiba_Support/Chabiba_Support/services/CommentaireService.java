package com.Chabiba_Support.Chabiba_Support.services;


import com.Chabiba_Support.Chabiba_Support.exception.CommentaireNotFoundException;
import com.Chabiba_Support.Chabiba_Support.models.Commentaire;
import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.repositories.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireService {
    @Autowired
    private CommentaireRepository commentaireRepo;

    public List<Commentaire> getAllCommentaires() {
        return commentaireRepo.findAll();
    }


    public Commentaire getCommentaireById(Long id) {
        return commentaireRepo.findById(id)
                .orElseThrow(() -> new CommentaireNotFoundException("Commentaire introuvable avec l'ID : " + id));
    }


    public Commentaire saveCommentaire(Commentaire commentaire) {
        return commentaireRepo.save(commentaire);
    }





    public void deleteCommentaire(Long id) {
        Commentaire commentaire = commentaireRepo.findById(id)
                .orElseThrow(() -> new CommentaireNotFoundException("Commentaire introuvable avec l'ID : " + id));

        commentaireRepo.delete(commentaire);
    }
    public boolean isReviewedDemande(Demande demande){
        List<Commentaire> commentaires=commentaireRepo.findByDemande(demande);
        if(!commentaires.isEmpty()){
            return true;
        }
            return false;
    }
}
