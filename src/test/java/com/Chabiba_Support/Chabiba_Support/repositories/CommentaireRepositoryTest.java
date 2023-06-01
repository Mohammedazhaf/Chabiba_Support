package com.Chabiba_Support.Chabiba_Support.repositories;

import com.Chabiba_Support.Chabiba_Support.models.Commentaire;
import com.Chabiba_Support.Chabiba_Support.repositories.CommentaireRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CommentaireRepositoryTest {

    @Mock
    private CommentaireRepository commentaireRepository;

    public CommentaireRepositoryTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByEtoile() {
        int etoile = 5;

        Commentaire commentaire1 = new Commentaire();
        commentaire1.setEtoile(etoile);

        Commentaire commentaire2 = new Commentaire();
        commentaire2.setEtoile(etoile);

        List<Commentaire> expectedCommentaires = Arrays.asList(commentaire1, commentaire2);

        when(commentaireRepository.findByEtoile(etoile)).thenReturn(expectedCommentaires);

        List<Commentaire> result = commentaireRepository.findByEtoile(etoile);

        assertEquals(expectedCommentaires, result);
    }

    @Test
    void testSave() {
        Commentaire commentaire = new Commentaire();

        when(commentaireRepository.save(commentaire)).thenReturn(commentaire);

        Commentaire result = commentaireRepository.save(commentaire);

        assertEquals(commentaire, result);
    }

    @Test
    void testDelete() {
        Commentaire commentaire = new Commentaire();

        // Perform the delete operation, no need to mock it as it doesn't return anything

        commentaireRepository.delete(commentaire);
    }

    @Test
    void testFindAll() {
        Commentaire commentaire1 = new Commentaire();
        Commentaire commentaire2 = new Commentaire();
        List<Commentaire> expectedCommentaires = Arrays.asList(commentaire1, commentaire2);

        when(commentaireRepository.findAll()).thenReturn(expectedCommentaires);

        List<Commentaire> result = commentaireRepository.findAll();

        assertEquals(expectedCommentaires, result);
    }
}
