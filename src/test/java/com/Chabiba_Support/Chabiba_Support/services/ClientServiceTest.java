package com.Chabiba_Support.Chabiba_Support.services;

import com.Chabiba_Support.Chabiba_Support.exception.ClientNotFoundException;
import com.Chabiba_Support.Chabiba_Support.models.Client;
import com.Chabiba_Support.Chabiba_Support.repositories.ClientRepository;
import com.Chabiba_Support.Chabiba_Support.services.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        clientService = new ClientService(clientRepository);
    }

    @Test
    public void testAddClient() {
        Client client = new Client();
        Mockito.when(clientRepository.save(client)).thenReturn(client);

        Client result = clientService.addClient(client);

        Assertions.assertEquals(client, result);
        Mockito.verify(clientRepository, Mockito.times(1)).save(client);
    }

    @Test
    public void testFindAllClients() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client());
        clients.add(new Client());
        Mockito.when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = clientService.findAllClients();

        Assertions.assertEquals(clients, result);
        Mockito.verify(clientRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testDeleteClient() {
        Client client = new Client();

        clientService.deleteClient(client);

        Mockito.verify(clientRepository, Mockito.times(1)).delete(client);
    }

    @Test
    public void testFindClientByIdPersonne() {
        Long idPersonne = 1L;
        Client client = new Client();
        Mockito.when(clientRepository.findClientByIdPersonne(idPersonne)).thenReturn(Optional.of(client));

        Client result = clientService.findClientByIdPersonne(idPersonne);

        Assertions.assertEquals(client, result);
        Mockito.verify(clientRepository, Mockito.times(1)).findClientByIdPersonne(idPersonne);
    }

    @Test
    public void testFindClientByIdPersonneNotFound() {
        Long idPersonne = 1L;
        Mockito.when(clientRepository.findClientByIdPersonne(idPersonne)).thenReturn(Optional.empty());

        Assertions.assertThrows(ClientNotFoundException.class, () -> {
            clientService.findClientByIdPersonne(idPersonne);
        });
    }

    @Test
    public void testFindByNomLikeIgnoreCase() {
        String nom = "John";
        List<Client> clients = new ArrayList<>();
        clients.add(new Client());
        clients.add(new Client());
        Mockito.when(clientRepository.findByNomLikeIgnoreCase(nom)).thenReturn(clients);

        List<Client> result = clientService.findByNomLikeIgnoreCase(nom);

        Assertions.assertEquals(clients, result);
        Mockito.verify(clientRepository, Mockito.times(1)).findByNomLikeIgnoreCase(nom);
    }

    @Test
    public void testCountAllClients() {
        long count = 5L;
        Mockito.when(clientRepository.count()).thenReturn(count);

        long result = clientService.countAllClients();

        Assertions.assertEquals(count, result);
        Mockito.verify(clientRepository, Mockito.times(1)).count();
    }
    @Test
    public void testUpdateClient() {
        Client client = new Client();
        Mockito.when(clientRepository.save(client)).thenReturn(client);

        Client result = clientService.updateClient(client);

        Assertions.assertEquals(client, result);
        Mockito.verify(clientRepository, Mockito.times(1)).save(client);
    }
}