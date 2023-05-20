package com.Chabiba_Support.Chabiba_Support.services;

import com.Chabiba_Support.Chabiba_Support.exception.CommentaireNotFoundException;
import com.Chabiba_Support.Chabiba_Support.models.Commentaire;
import com.Chabiba_Support.Chabiba_Support.repositories.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireService {
    @Autowired
    private CommentaireRepository commentaireRepository;


    public List<Commentaire> getAllCommentaires() {
        return commentaireRepository.findAll();
    }


    public Commentaire getCommentaireById(Long id) {
        return commentaireRepository.findById(id)
                .orElseThrow(() -> new CommentaireNotFoundException("Commentaire introuvable avec l'ID : " + id));
    }


    public Commentaire createCommentaire(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }


    public Commentaire updateCommentaire(Long id, Commentaire commentaireDetails) {
        Commentaire commentaire = commentaireRepository.findById(id)
                .orElseThrow(() -> new CommentaireNotFoundException("Commentaire introuvable avec l'ID : " + id));

        commentaire.setEtoile(commentaireDetails.getEtoile());
        commentaire.setTexteC(commentaireDetails.getTexteC());
        commentaire.setClient(commentaireDetails.getClient());
        commentaire.setDemande(commentaireDetails.getDemande());

        Commentaire updatedCommentaire = commentaireRepository.save(commentaire);
        return updatedCommentaire;
    }


    public void deleteCommentaire(Long id) {
        Commentaire commentaire = commentaireRepository.findById(id)
                .orElseThrow(() -> new CommentaireNotFoundException("Commentaire introuvable avec l'ID : " + id));

                   commentaireRepository.delete(commentaire);
    }
}
