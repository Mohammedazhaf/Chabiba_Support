package com.Chabiba_Support.Chabiba_Support.services;

import static org.junit.jupiter.api.Assertions.*;

import com.Chabiba_Support.Chabiba_Support.exception.ClientNotFoundException;
import com.Chabiba_Support.Chabiba_Support.models.Client;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.models.Role;
import com.Chabiba_Support.Chabiba_Support.repositories.ClientRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private PersonneRepository personneRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ClientService clientService;


    private Personne personne;

    @BeforeEach
    public void setup() {
        personne = new Personne();
        // Initialiser les valeurs de personne
    }

    @Test
    void testAddClient() {
        // Mocking the repositories
        Personne savedPersonne = new Personne();
        when(personneRepository.save(any(Personne.class))).thenReturn(savedPersonne);

        Client savedClient = new Client();
        when(clientRepository.save(any(Client.class))).thenReturn(savedClient);

        // Creating test data
        Personne personne = new Personne();
        String nomEntreprise = "Test Company";

        // Calling the method under test
        Client result = clientService.addClient(personne, nomEntreprise);

        // Verifying the interactions
        verify(personneRepository).save(personne);
        verify(clientRepository).save(any(Client.class));

        // Asserting the result
        assertNotNull(result);
        assertEquals(savedClient, result);
    }

    @Test
    void testFindAllClients() {
        // Mocking the repository
        List<Client> clients = Arrays.asList(new Client(), new Client());
        when(clientRepository.findAll()).thenReturn(clients);

        // Calling the method under test
        List<Client> result = clientService.findAllClients();

        // Verifying the interaction
        verify(clientRepository).findAll();

        // Asserting the result
        assertNotNull(result);
        assertEquals(clients, result);
    }

    @Test
    public void testDeleteClient() {
        // Création d'un client fictif pour le test
        Client client = new Client();
        client.setIdClient(1L);
        Personne personne = new Personne();
        personne.setIdPersonne(2L);
        client.setPersonne(personne);

        // Stubbing pour findById dans personneRepository
        when(personneRepository.findById(anyLong())).thenReturn(Optional.of(personne));

        // Appel de la méthode à tester
        clientService.deleteClient(client);

        // Vérification que la méthode delete a été appelée avec le bon argument
        verify(clientRepository).delete(eq(client));

        // Vérification que la méthode deleteById a été appelée avec le bon argument
        verify(personneRepository).deleteById(eq(personne.getIdPersonne()));
    }



    @Test
    void testFindClientByIdPersonne() {
        // Mocking the repository
        Long idPersonne = 1L;
        Personne personne = new Personne();
        Client client = new Client();
        when(personneRepository.findById(anyLong())).thenReturn(Optional.of(personne));
        when(clientRepository.findClientByPersonne(any(Personne.class))).thenReturn(Optional.of(client));

        // Calling the method under test
        Client result = clientService.findClientByIdPersonne(idPersonne);

        // Verifying the interactions
        verify(personneRepository).findById(idPersonne);
        verify(clientRepository).findClientByPersonne(personne);

        // Asserting the result
        assertNotNull(result);
        assertEquals(client, result);
    }

    @Test
    void testFindClientByIdPersonneNotFound() {
        // Mocking the repository
        Long idPersonne = 1L;
        when(personneRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Calling the method under test and asserting the exception
        assertThrows(ClientNotFoundException.class, () -> clientService.findClientByIdPersonne(idPersonne));
    }

    @Test
    void testFindByNomLikeIgnoreCase() {
        // Mocking the repository
        String nom = "test";
        List<Client> clients = Arrays.asList(new Client(), new Client());
        when(clientRepository.findByNomLikeIgnoreCase(anyString())).thenReturn(clients);

        // Calling the method under test
        List<Client> result = clientService.findByNomLikeIgnoreCase(nom);

        // Verifying the interaction
        verify(clientRepository).findByNomLikeIgnoreCase(nom);

        // Asserting the result
        assertNotNull(result);
        assertEquals(clients, result);
    }

    @Test
    void testCountAllClients() {
        // Mocking the repository
        long count = 5L;
        when(clientRepository.count()).thenReturn(count);

        // Calling the method under test
        long result = clientService.countAllClients();

        // Verifying the interaction
        verify(clientRepository).count();

        // Asserting the result
        assertEquals(count, result);
    }

    @Test
    void testUpdateClient() {
        // Mocking the repositories
        Long clientId = 1L;
        Long personneId = 2L;
        Client existingClient = new Client();
        Personne existingPersonne = new Personne();
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(existingClient));
        when(personneRepository.findById(anyLong())).thenReturn(Optional.of(existingPersonne));
        when(personneRepository.save(any(Personne.class))).thenReturn(existingPersonne);
        when(clientRepository.save(any(Client.class))).thenReturn(existingClient);

        // Creating test data
        Client client = new Client();
        client.setIdClient(clientId);
        Personne personne = new Personne();
        personne.setIdPersonne(personneId);
        personne.setNom("Test");
        personne.setPrenom("Personne");
        personne.setEmail("test@example.com");
        personne.setNumTel("123456789");
        personne.setMotDePasse("password");
        personne.setRole(Role.client);
        client.setPersonne(personne);

        // Calling the method under test
        Client result = clientService.updateClient(client);

        // Verifying the interactions
        verify(clientRepository).findById(clientId);
        verify(personneRepository).findById(personneId);
        verify(personneRepository).save(existingPersonne);
        verify(clientRepository).save(existingClient);

        // Asserting the result
        assertNotNull(result);
        assertEquals(existingClient, result);
    }

    @Test
    void testUpdateClientNotFound() {
        // Creating test data
        Client client = new Client();
        client.setIdClient(1L);

        // Calling the method under test and asserting the exception
        assertThrows(RuntimeException.class, () -> clientService.updateClient(client));
    }

    @Test
    void testGetClientByID() {
        // Mocking the repository
        Long id = 1L;
        Client client = new Client();
        when(clientRepository.getById(anyLong())).thenReturn(client);

        // Calling the method under test
        Client result = clientService.getClientByID(id);

        // Verifying the interaction
        verify(clientRepository).getById(id);

        // Asserting the result
        assertNotNull(result);
        assertEquals(client, result);
    }

    @Test
    void testFindClientById() {
        // Mocking the repository
        Long id = 1L;
        Client client = new Client();
        when(clientRepository.findClientById(anyLong())).thenReturn(client);

        // Calling the method under test
        Client result = clientService.findClientById(id);

        // Verifying the interaction
        verify(clientRepository).findClientById(id);

        // Asserting the result
        assertNotNull(result);
        assertEquals(client, result);
    }
}