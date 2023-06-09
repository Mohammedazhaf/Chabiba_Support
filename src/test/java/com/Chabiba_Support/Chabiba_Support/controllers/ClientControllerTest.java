package com.Chabiba_Support.Chabiba_Support.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Chabiba_Support.Chabiba_Support.models.Client;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.models.Role;
import com.Chabiba_Support.Chabiba_Support.repositories.ClientRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;
import com.Chabiba_Support.Chabiba_Support.requests.ClientRequestDTO;
import com.Chabiba_Support.Chabiba_Support.services.ClientService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;
    /**
     * Method under test: {@link ClientController#getAllClients()}
     */
    @Test
    void testGetAllClients2() {
        ArrayList<Client> clientList = new ArrayList<>();
        clientList.add(new Client());
        ClientRepository clientRepository = mock(ClientRepository.class);
        when(clientRepository.findAll()).thenReturn(clientList);
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        ResponseEntity<List<Client>> actualAllClients = (new ClientController(
                new ClientService(clientRepository, personneRepository, new BCryptPasswordEncoder()))).getAllClients();
        assertTrue(actualAllClients.hasBody());
        assertTrue(actualAllClients.getHeaders().isEmpty());
        assertEquals(200, actualAllClients.getStatusCodeValue());
        verify(clientRepository).findAll();
    }

    @Test
    void testAddClient3() {
        // Set up mock ServletRequestAttributes
        MockHttpServletRequest request = MockMvcRequestBuilders.get("/").buildRequest(new MockServletContext());
        ServletRequestAttributes attributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attributes);

        // Mock the clientService behavior
        when(clientService.addClient(any(Personne.class), any(String.class)))
                .thenReturn(new Client());

        // Prepare the test data
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO("Nom", "Prenom", "Num Tel", "jane.doe@example.org", "Mot De Passe", "Nom Entreprise");
        // Set the properties of the clientRequestDTO

        // Invoke the addClient method
        ResponseEntity<Client> response = clientController.addClient(clientRequestDTO);

        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        // Add additional assertions as needed

        // Reset the RequestContextHolder
        RequestContextHolder.resetRequestAttributes();
    }
    

    /**
     * Method under test: {@link ClientController#getClientBYIdPersonne(Long)}
     */
    @Test
    void testGetClientBYIdPersonne() {
        ClientRepository clientRepository = mock(ClientRepository.class);
        Client client = new Client();
        when(clientRepository.findClientByPersonne((Personne) any())).thenReturn(Optional.of(client));
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        when(personneRepository.findById((Long) any())).thenReturn(Optional.of(new Personne()));
        assertSame(client,
                (new ClientController(new ClientService(clientRepository, personneRepository, new BCryptPasswordEncoder())))
                        .getClientBYIdPersonne(1L));
        verify(clientRepository).findClientByPersonne((Personne) any());
        verify(personneRepository).findById((Long) any());
    }


    /**
     * Method under test: {@link ClientController#getClientBYIdPersonne(Long)}
     */
    @Test
    void testGetClientBYIdPersonne3() {
        ClientService clientService = mock(ClientService.class);
        Client client = new Client();
        when(clientService.findClientByIdPersonne((Long) any())).thenReturn(client);
        assertSame(client, (new ClientController(clientService)).getClientBYIdPersonne(1L));
        verify(clientService).findClientByIdPersonne((Long) any());
    }

    /**
     * Method under test: {@link ClientController#getClientBYId(Long)}
     */
    @Test
    void testGetClientBYId() {
        ClientRepository clientRepository = mock(ClientRepository.class);
        Client client = new Client();
        when(clientRepository.findClientById((Long) any())).thenReturn(client);
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        assertSame(client,
                (new ClientController(new ClientService(clientRepository, personneRepository, new BCryptPasswordEncoder())))
                        .getClientBYId(1L));
        verify(clientRepository).findClientById((Long) any());
    }

    /**
     * Method under test: {@link ClientController#getClientBYId(Long)}
     */
    @Test
    void testGetClientBYId3() {
        ClientService clientService = mock(ClientService.class);
        Client client = new Client();
        when(clientService.findClientById((Long) any())).thenReturn(client);
        assertSame(client, (new ClientController(clientService)).getClientBYId(1L));
        verify(clientService).findClientById((Long) any());
    }


    /**
     * Method under test: {@link ClientController#updateClient(Client)}
     */
    @Test
    void testUpdateClient2() {
        ClientService clientService = mock(ClientService.class);
        when(clientService.updateClient((Client) any())).thenReturn(new Client());
        ClientController clientController = new ClientController(clientService);
        Client client = new Client();
        ResponseEntity<Client> actualUpdateClientResult = clientController.updateClient(client);
        assertEquals(client, actualUpdateClientResult.getBody());
        assertTrue(actualUpdateClientResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateClientResult.getStatusCodeValue());
        verify(clientService).updateClient((Client) any());
    }

    /**
     * Method under test: {@link ClientController#deleteClient(Long)}
     */
    @Test
    void testDeleteClient2() {
        Personne personne = new Personne();
        personne.setIdPersonne(1L);

        Client client = new Client();
        client.setPersonne(personne);
        ClientRepository clientRepository = mock(ClientRepository.class);
        doNothing().when(clientRepository).delete((Client) any());
        when(clientRepository.findClientById((Long) any())).thenReturn(client);
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        doNothing().when(personneRepository).deleteById((Long) any());
        when(personneRepository.findById((Long) any())).thenReturn(Optional.of(new Personne()));
        ResponseEntity<Map<String, Boolean>> actualDeleteClientResult = (new ClientController(
                new ClientService(clientRepository, personneRepository, new BCryptPasswordEncoder()))).deleteClient(1L);
        assertEquals(1, actualDeleteClientResult.getBody().size());
        assertTrue(actualDeleteClientResult.hasBody());
        assertEquals(200, actualDeleteClientResult.getStatusCodeValue());
        assertTrue(actualDeleteClientResult.getHeaders().isEmpty());
        verify(clientRepository).findClientById((Long) any());
        verify(clientRepository).delete((Client) any());
        verify(personneRepository).findById((Long) any());
        verify(personneRepository).deleteById((Long) any());
    }


    /**
     * Method under test: {@link ClientController#deleteClient(Long)}
     */
    @Test
    void testDeleteClient5() {
        ClientService clientService = mock(ClientService.class);
        when(clientService.findClientById((Long) any())).thenReturn(new Client());
        doNothing().when(clientService).deleteClient((Client) any());
        ResponseEntity<Map<String, Boolean>> actualDeleteClientResult = (new ClientController(clientService))
                .deleteClient(1L);
        assertEquals(1, actualDeleteClientResult.getBody().size());
        assertTrue(actualDeleteClientResult.hasBody());
        assertEquals(200, actualDeleteClientResult.getStatusCodeValue());
        assertTrue(actualDeleteClientResult.getHeaders().isEmpty());
        verify(clientService).findClientById((Long) any());
        verify(clientService).deleteClient((Client) any());
    }

    /**
     * Method under test: {@link ClientController#getClientByNomLikeIgnoreCase(String)}
     */
    @Test
    void testGetClientByNomLikeIgnoreCase() {
        ClientRepository clientRepository = mock(ClientRepository.class);
        when(clientRepository.findByNomLikeIgnoreCase((String) any())).thenReturn(new ArrayList<>());
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        ResponseEntity<List<Client>> actualClientByNomLikeIgnoreCase = (new ClientController(
                new ClientService(clientRepository, personneRepository, new BCryptPasswordEncoder())))
                .getClientByNomLikeIgnoreCase("Nom");
        assertTrue(actualClientByNomLikeIgnoreCase.hasBody());
        assertEquals(200, actualClientByNomLikeIgnoreCase.getStatusCodeValue());
        assertTrue(actualClientByNomLikeIgnoreCase.getHeaders().isEmpty());
        verify(clientRepository).findByNomLikeIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link ClientController#getClientByNomLikeIgnoreCase(String)}
     */
    @Test
    void testGetClientByNomLikeIgnoreCase3() {
        ClientService clientService = mock(ClientService.class);
        when(clientService.findByNomLikeIgnoreCase((String) any())).thenReturn(new ArrayList<>());
        ResponseEntity<List<Client>> actualClientByNomLikeIgnoreCase = (new ClientController(clientService))
                .getClientByNomLikeIgnoreCase("Nom");
        assertTrue(actualClientByNomLikeIgnoreCase.hasBody());
        assertEquals(200, actualClientByNomLikeIgnoreCase.getStatusCodeValue());
        assertTrue(actualClientByNomLikeIgnoreCase.getHeaders().isEmpty());
        verify(clientService).findByNomLikeIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link ClientController#countAllClient()}
     */
    @Test
    void testCountAllClient() {
        ClientRepository clientRepository = mock(ClientRepository.class);
        when(clientRepository.count()).thenReturn(3L);
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        ResponseEntity<Long> actualCountAllClientResult = (new ClientController(
                new ClientService(clientRepository, personneRepository, new BCryptPasswordEncoder()))).countAllClient();
        assertEquals(3L, actualCountAllClientResult.getBody().longValue());
        assertEquals(200, actualCountAllClientResult.getStatusCodeValue());
        assertTrue(actualCountAllClientResult.getHeaders().isEmpty());
        verify(clientRepository).count();
    }
    /**
     * Method under test: {@link ClientController#countAllClient()}
     */
    @Test
    void testCountAllClient3() {
        ClientService clientService = mock(ClientService.class);
        when(clientService.countAllClients()).thenReturn(3L);
        ResponseEntity<Long> actualCountAllClientResult = (new ClientController(clientService)).countAllClient();
        assertEquals(3L, actualCountAllClientResult.getBody().longValue());
        assertEquals(200, actualCountAllClientResult.getStatusCodeValue());
        assertTrue(actualCountAllClientResult.getHeaders().isEmpty());
        verify(clientService).countAllClients();
    }
}

