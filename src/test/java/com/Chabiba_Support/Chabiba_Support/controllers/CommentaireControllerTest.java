package com.Chabiba_Support.Chabiba_Support.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Chabiba_Support.Chabiba_Support.controllers.CommentaireController;
import com.Chabiba_Support.Chabiba_Support.models.Commentaire;
import com.Chabiba_Support.Chabiba_Support.requests.CommentaireRequestDTO;
import com.Chabiba_Support.Chabiba_Support.services.ClientService;
import com.Chabiba_Support.Chabiba_Support.services.CommentaireService;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class CommentaireControllerTest {

    @Mock
    private CommentaireService commentaireService;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private CommentaireController commentaireController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCommentaire() {
        Commentaire commentaire = new Commentaire();
        commentaire.setIdCommentaire(1L);
        commentaire.setTexteC("Some content");

        CommentaireRequestDTO requestDTO = new CommentaireRequestDTO();
        requestDTO.setTexteC(commentaire.getTexteC());
        requestDTO.setIdDemande(1L); // Set a valid idDemande value

        when(clientService.getClientByID(1L)).thenReturn(null);

        commentaireController.createCommentaire(requestDTO);

        verify(commentaireService).saveCommentaire(commentaire);
    }

    @Test
    void testDeleteCommentaire() {
        Commentaire commentaire = new Commentaire();
        commentaire.setIdCommentaire(1L);

        commentaireController.deleteCommentaire(1L);

        verify(commentaireService).deleteCommentaire(1L);
    }
}
