package com.Chabiba_Support.Chabiba_Support.repositories;

import com.Chabiba_Support.Chabiba_Support.models.Commentaire;
import com.Chabiba_Support.Chabiba_Support.models.Demande;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentaireRepositoryTest {

    @Mock
    private CommentaireRepository commentaireRepository;

    @Test
    public void testFindByEtoile() {
        // Mocking the repository behavior
        int etoile = 5;
        Commentaire commentaire1 = new Commentaire();
        commentaire1.setIdCommentaire(1L);
        Commentaire commentaire2 = new Commentaire();
        commentaire2.setIdCommentaire(2L);
        when(commentaireRepository.findByEtoile(etoile)).thenReturn(Arrays.asList(commentaire1, commentaire2));

        // Calling the repository method
        List<Commentaire> result = commentaireRepository.findByEtoile(etoile);

        // Verifying the repository method was called
        verify(commentaireRepository, times(1)).findByEtoile(etoile);

        // Asserting the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(commentaire1.getIdCommentaire(), result.get(0).getIdCommentaire());
        Assertions.assertEquals(commentaire2.getIdCommentaire(), result.get(1).getIdCommentaire());
    }

    @Test
    public void testFindByDemande() {
        // Mocking the repository behavior
        Demande demande = new Demande();
        demande.setIdDemande(1L);
        Commentaire commentaire1 = new Commentaire();
        commentaire1.setIdCommentaire(1L);
        Commentaire commentaire2 = new Commentaire();
        commentaire2.setIdCommentaire(2L);
        when(commentaireRepository.findByDemande(demande)).thenReturn(Arrays.asList(commentaire1, commentaire2));

        // Calling the repository method
        List<Commentaire> result = commentaireRepository.findByDemande(demande);

        // Verifying the repository method was called
        verify(commentaireRepository, times(1)).findByDemande(demande);

        // Asserting the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(commentaire1.getIdCommentaire(), result.get(0).getIdCommentaire());
        Assertions.assertEquals(commentaire2.getIdCommentaire(), result.get(1).getIdCommentaire());
    }

    @Test
    public void testSave() {
        // Mocking the repository behavior
        Commentaire commentaire = new Commentaire();
        commentaire.setIdCommentaire(1L);
        when(commentaireRepository.save(commentaire)).thenReturn(commentaire);

        // Calling the repository method
        Commentaire result = commentaireRepository.save(commentaire);

        // Verifying the repository method was called
        verify(commentaireRepository, times(1)).save(commentaire);

        // Asserting the result
        Assertions.assertEquals(commentaire.getIdCommentaire(), result.getIdCommentaire());
    }

    @Test
    public void testDelete() {
        // Mocking the repository behavior
        Commentaire commentaire = new Commentaire();
        commentaire.setIdCommentaire(1L);

        // Calling the repository method
        commentaireRepository.delete(commentaire);

        // Verifying the repository method was called
        verify(commentaireRepository, times(1)).delete(commentaire);
    }

    @Test
    public void testFindAll() {
        // Mocking the repository behavior
        Commentaire commentaire1 = new Commentaire();
        commentaire1.setIdCommentaire(1L);
        Commentaire commentaire2 = new Commentaire();
        commentaire2.setIdCommentaire(2L);
        when(commentaireRepository.findAll()).thenReturn(Arrays.asList(commentaire1, commentaire2));

        // Calling the repository method
        List<Commentaire> result = commentaireRepository.findAll();

        // Verifying the repository method was called
        verify(commentaireRepository, times(1)).findAll();

        // Asserting the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(commentaire1.getIdCommentaire(), result.get(0).getIdCommentaire());
        Assertions.assertEquals(commentaire2.getIdCommentaire(), result.get(1).getIdCommentaire());
    }
}
