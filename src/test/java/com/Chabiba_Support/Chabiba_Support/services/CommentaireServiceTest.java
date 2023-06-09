package com.Chabiba_Support.Chabiba_Support.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.Chabiba_Support.Chabiba_Support.exception.CommentaireNotFoundException;
import com.Chabiba_Support.Chabiba_Support.models.Commentaire;
import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.repositories.CommentaireRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentaireServiceTest {

    @Mock
    private CommentaireRepository commentaireRepo;

    @InjectMocks
    private CommentaireService commentaireService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCommentaires() {
        // Create a list of commentaires
        List<Commentaire> commentaires = new ArrayList<>();
        Commentaire commentaire1 = new Commentaire();
        commentaire1.setIdCommentaire(1L);
        commentaire1.setEtoile(5);
        commentaire1.setTexteC("Commentaire 1");

        Commentaire commentaire2 = new Commentaire();
        commentaire2.setIdCommentaire(2L);
        commentaire2.setEtoile(5);
        commentaire2.setTexteC("Commentaire 1");
        commentaires.add(commentaire1);
        commentaires.add(commentaire2);

        // Configure the mock repository to return the list of commentaires
        Mockito.when(commentaireRepo.findAll()).thenReturn(commentaires);

        // Call the service method
        List<Commentaire> result = commentaireService.getAllCommentaires();

        // Verify the result
        Assertions.assertEquals(commentaires, result);
    }

    @Test
    void testGetCommentaireById() {
        // Create a commentaire with a specific ID
        Long commentaireId = 1L;
        Commentaire commentaire = new Commentaire();
        commentaire.setIdCommentaire(commentaireId);
        commentaire.setEtoile(5);
        commentaire.setTexteC("Commentaire 1");
        // Configure the mock repository to return the commentaire
        Mockito.when(commentaireRepo.findById(commentaireId)).thenReturn(Optional.of(commentaire));

        // Call the service method
        Commentaire result = commentaireService.getCommentaireById(commentaireId);

        // Verify the result
        Assertions.assertEquals(commentaire, result);
    }

    @Test
    void testGetCommentaireById_ThrowsCommentaireNotFoundException() {
        // Configure the mock repository to return an empty optional
        Mockito.when(commentaireRepo.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        // Call the service method and verify that CommentaireNotFoundException is thrown
        Assertions.assertThrows(CommentaireNotFoundException.class, () -> {
            commentaireService.getCommentaireById(1L);
        });
    }

    @Test
    void testSaveCommentaire() {
        // Create a new commentaire
        Commentaire commentaire = new Commentaire();
        commentaire.setIdCommentaire(1L);
        commentaire.setEtoile(5);
        commentaire.setTexteC("Commentaire 1");

        // Configure the mock repository to return the saved commentaire
        Mockito.when(commentaireRepo.save(commentaire)).thenReturn(commentaire);

        // Call the service method
        Commentaire result = commentaireService.saveCommentaire(commentaire);

        // Verify the result
        Assertions.assertEquals(commentaire, result);
    }

    @Test
    void testDeleteCommentaire() {
        // Create a commentaire with a specific ID
        Long commentaireId = 1L;
        Commentaire commentaire = new Commentaire();
        commentaire.setIdCommentaire(commentaireId);
        commentaire.setEtoile(5);
        commentaire.setTexteC("Commentaire 1");

        // Configure the mock repository to return the commentaire when findById is called
        Mockito.when(commentaireRepo.findById(commentaireId)).thenReturn(Optional.of(commentaire));

        // Call the service method
        commentaireService.deleteCommentaire(commentaireId);

        // Verify that the delete method was called on the mock repository with the commentaire
        Mockito.verify(commentaireRepo).delete(commentaire);
    }

    @Test
    void testDeleteCommentaire_ThrowsCommentaireNotFoundException() {
        // Configure the mock repository to return an empty optional
        Mockito.when(commentaireRepo.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        // Call the service method and verify that CommentaireNotFoundException is thrown
        Assertions.assertThrows(CommentaireNotFoundException.class, () -> {
            commentaireService.deleteCommentaire(1L);
        });
    }

    @Test
    void testIsReviewedDemande_ReturnsTrue() {
        // Create a demande and a list of commentaires
        Demande demande = new Demande();
        List<Commentaire> commentaires = new ArrayList<>();

        Commentaire commentaire1 = new Commentaire();
        commentaire1.setIdCommentaire(1L);
        commentaire1.setEtoile(5);
        commentaire1.setTexteC("Commentaire 1");

        Commentaire commentaire2 = new Commentaire();
        commentaire2.setIdCommentaire(2L);
        commentaire2.setEtoile(5);
        commentaire2.setTexteC("Commentaire 1");
        commentaires.add(commentaire1);
        commentaires.add(commentaire2);

        commentaires.add(commentaire1);
        commentaires.add(commentaire2);

        // Configure the mock repository to return the list of commentaires
        Mockito.when(commentaireRepo.findByDemande(demande)).thenReturn(commentaires);

        // Call the service method
        boolean result = commentaireService.isReviewedDemande(demande);

        // Verify the result
        Assertions.assertTrue(result);
    }

    @Test
    void testIsReviewedDemande_ReturnsFalse() {
        // Create a demande and an empty list of commentaires
        Demande demande = new Demande();
        List<Commentaire> commentaires = new ArrayList<>();

        // Configure the mock repository to return the empty list of commentaires
        Mockito.when(commentaireRepo.findByDemande(demande)).thenReturn(commentaires);

        // Call the service method
        boolean result = commentaireService.isReviewedDemande(demande);

        // Verify the result
        Assertions.assertFalse(result);
    }
}
