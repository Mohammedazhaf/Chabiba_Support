package com.Chabiba_Support.Chabiba_Support.repositories;

import com.Chabiba_Support.Chabiba_Support.models.Client;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientRepositoryTest {

    @Mock
    private ClientRepository clientRepository;

    @Test
    public void testFindClientByPersonne() {
        // Mocking the repository behavior
        Personne personne = new Personne();
        personne.setIdPersonne(1L);
        personne.setNom("John Doe");
        Client client = new Client();
        client.setIdClient(1L);
        client.setPersonne(personne);
        when(clientRepository.findClientByPersonne(personne)).thenReturn(Optional.of(client));

        // Calling the repository method
        Optional<Client> result = clientRepository.findClientByPersonne(personne);

        // Verifying the repository method was called
        verify(clientRepository, times(1)).findClientByPersonne(personne);

        // Asserting the result
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(client.getIdClient(), result.get().getIdClient());
        Assertions.assertEquals(client.getPersonne().getNom(), result.get().getPersonne().getNom());
    }

    @Test
    public void testFindClientByNomEntreprise() {
        // Mocking the repository behavior
        String nomEntreprise = "Example Company";
        Client client1 = new Client();
        client1.setIdClient(1L);
        client1.setNomEntreprise(nomEntreprise);
        Client client2 = new Client();
        client2.setIdClient(2L);
        client2.setNomEntreprise(nomEntreprise);
        when(clientRepository.findClientByNomEntreprise(nomEntreprise)).thenReturn(Arrays.asList(client1, client2));

        // Calling the repository method
        List<Client> result = clientRepository.findClientByNomEntreprise(nomEntreprise);

        // Verifying the repository method was called
        verify(clientRepository, times(1)).findClientByNomEntreprise(nomEntreprise);

        // Asserting the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(client1.getNomEntreprise(), result.get(0).getNomEntreprise());
        Assertions.assertEquals(client2.getNomEntreprise(), result.get(1).getNomEntreprise());
    }

    @Test
    public void testFindByNomLikeIgnoreCase() {
        // Mocking the repository behavior
        String nom = "Doe";
        Personne personne1 = new Personne();
        personne1.setIdPersonne(1L);
        personne1.setNom("John Doe");
        Client client1 = new Client();
        client1.setIdClient(1L);
        client1.setPersonne(personne1);
        Personne personne2 = new Personne();
        personne2.setIdPersonne(2L);
        personne2.setNom("Jane Doe");
        Client client2 = new Client();
        client2.setIdClient(2L);
        client2.setPersonne(personne2);
        when(clientRepository.findByNomLikeIgnoreCase(contains(nom))).thenReturn(Arrays.asList(client1, client2));

        // Calling the repository method
        List<Client> result = clientRepository.findByNomLikeIgnoreCase(nom);

        // Verifying the repository method was called
        verify(clientRepository, times(1)).findByNomLikeIgnoreCase(contains(nom));

        // Asserting the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(client1.getPersonne().getNom(), result.get(0).getPersonne().getNom());
        Assertions.assertEquals(client2.getPersonne().getNom(), result.get(1).getPersonne().getNom());
    }

    @Test
    public void testFindClientById() {
        // Mocking the repository behavior
        Long clientId = 1L;
        Personne personne = new Personne();
        personne.setIdPersonne(1L);
        personne.setNom("John Doe");
        Client client = new Client();
        client.setIdClient(clientId);
        client.setPersonne(personne);
        when(clientRepository.findClientById(clientId)).thenReturn(client);

        // Calling the repository method
        Client result = clientRepository.findClientById(clientId);

        // Verifying the repository method was called
        verify(clientRepository, times(1)).findClientById(clientId);

        // Asserting the result
        Assertions.assertEquals(client.getIdClient(), result.getIdClient());
        Assertions.assertEquals(client.getPersonne().getNom(), result.getPersonne().getNom());
    }
}
