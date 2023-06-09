package com.Chabiba_Support.Chabiba_Support.repositories;

import com.Chabiba_Support.Chabiba_Support.models.Client;
import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.models.Etat;
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
public class DemandeRepositoryTest {

    @Mock
    private DemandeRepository demandeRepository;

    @Test
    public void testFindByEtat() {
        // Mocking the repository behavior
        Etat etat = Etat.EnCours;
        Demande demande1 = new Demande();
        demande1.setIdDemande(1L);
        Demande demande2 = new Demande();
        demande2.setIdDemande(2L);
        when(demandeRepository.findByEtat(etat)).thenReturn(Arrays.asList(demande1, demande2));

        // Calling the repository method
        List<Demande> result = demandeRepository.findByEtat(etat);

        // Verifying the repository method was called
        verify(demandeRepository, times(1)).findByEtat(etat);

        // Asserting the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(demande1.getIdDemande(), result.get(0).getIdDemande());
        Assertions.assertEquals(demande2.getIdDemande(), result.get(1).getIdDemande());
    }

    @Test
    public void testFindByClient() {
        // Mocking the repository behavior
        Client client = new Client();
        client.setIdClient(1L);
        Demande demande1 = new Demande();
        demande1.setIdDemande(1L);
        Demande demande2 = new Demande();
        demande2.setIdDemande(2L);
        when(demandeRepository.findByClient(client)).thenReturn(Arrays.asList(demande1, demande2));

        // Calling the repository method
        List<Demande> result = demandeRepository.findByClient(client);

        // Verifying the repository method was called
        verify(demandeRepository, times(1)).findByClient(client);

        // Asserting the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(demande1.getIdDemande(), result.get(0).getIdDemande());
        Assertions.assertEquals(demande2.getIdDemande(), result.get(1).getIdDemande());
    }
}
