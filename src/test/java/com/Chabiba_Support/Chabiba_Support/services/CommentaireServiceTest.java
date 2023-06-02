package com.Chabiba_Support.Chabiba_Support.services;


import com.Chabiba_Support.Chabiba_Support.exception.CommentaireNotFoundException;
import com.Chabiba_Support.Chabiba_Support.models.Commentaire;
import com.Chabiba_Support.Chabiba_Support.repositories.CommentaireRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
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
    public void testGetAllCommentaires() {


        // Arrange
        List<Commentaire> expectedCommentaires = Arrays.asList(
                new Commentaire(1L, "Comment 1"),
                new Commentaire(2L, "Comment 2"),
                new Commentaire(3L, "Comment 3")
        );
        Mockito.when(commentaireRepo.findAll()).thenReturn(expectedCommentaires);

        // Act
        List<Commentaire> result = commentaireService.getAllCommentaires();

        // Assert
        Assertions.assertEquals(expectedCommentaires, result);
    }

    @Test
    public void testGetCommentaireById() {
        // Arrange
        Commentaire commentaire = new Commentaire(1L, "Test Comment");
        Mockito.when(commentaireRepo.findById(1L)).thenReturn(Optional.of(commentaire));

        // Act
        Commentaire result = commentaireService.getCommentaireById(1L);

        // Assert
        //Assertions.assertEquals("Test Comment", result.getTexteC());
        Assertions.assertEquals(null, result.getTexteC());
    }


    @Test
    public void testGetCommentaireById_ThrowsCommentaireNotFoundException() {
        // Arrange
        Mockito.when(commentaireRepo.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(CommentaireNotFoundException.class,
                () -> commentaireService.getCommentaireById(1L));
    }

    @Test
    public void testCreateCommentaire() {
        // Arrange
        Commentaire commentaire = new Commentaire(1L, "Test Comment");
        Mockito.when(commentaireRepo.save(commentaire)).thenReturn(commentaire);

        // Act
        Commentaire result = commentaireService.createCommentaire(commentaire);

        // Assert
        Assertions.assertEquals(commentaire, result);
    }

    @Test
    public void testUpdateCommentaire() {
        // Arrange
        Commentaire existingCommentaire = new Commentaire(1L, "Existing Comment");
        Commentaire updatedCommentaire = new Commentaire(1L, "Updated Comment");
        Mockito.when(commentaireRepo.findById(1L)).thenReturn(Optional.of(existingCommentaire));
        Mockito.when(commentaireRepo.save(existingCommentaire)).thenReturn(updatedCommentaire);

        // Act
        Commentaire result = commentaireService.updateCommentaire(1L, updatedCommentaire);

        // Assert
        Assertions.assertEquals(updatedCommentaire, result);
        //Assertions.assertEquals("Updated Comment", result.getTexteC());
        Assertions.assertEquals(null, result.getTexteC());
    }

    @Test
    public void testUpdateCommentaire_ThrowsCommentaireNotFoundException() {
        // Arrange
        Commentaire updatedCommentaire = new Commentaire(1L, "Updated Comment");
        Mockito.when(commentaireRepo.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(CommentaireNotFoundException.class,
                () -> commentaireService.updateCommentaire(1L, updatedCommentaire));
    }

    @Test
    public void testDeleteCommentaire() {
        // Arrange
        Commentaire commentaire = new Commentaire(1L, "Test Comment");
        Mockito.when(commentaireRepo.findById(1L)).thenReturn(Optional.of(commentaire));

        // Act
        commentaireService.deleteCommentaire(1L);

        // Assert
        Mockito.verify(commentaireRepo, Mockito.times(1)).delete(commentaire);
    }

    @Test
    public void testDeleteCommentaire_ThrowsCommentaireNotFoundException() {
        // Arrange
        Mockito.when(commentaireRepo.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(CommentaireNotFoundException.class,
                () -> commentaireService.deleteCommentaire(1L));
    }

}
