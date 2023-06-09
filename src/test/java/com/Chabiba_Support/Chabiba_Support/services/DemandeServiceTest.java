package com.Chabiba_Support.Chabiba_Support.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.Chabiba_Support.Chabiba_Support.models.Client;
import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.models.Etat;
import com.Chabiba_Support.Chabiba_Support.repositories.ClientRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.DemandeRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;
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

public class DemandeServiceTest {

    @Mock
    private DemandeRepository demandeRepository;

    @Mock
    private PersonneRepository personneRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private DemandeService demandeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Create a list of demandes
        List<Demande> demandes = new ArrayList<>();
        demandes.add(new Demande());
        demandes.add(new Demande());

        // Configure the mock repository to return the list of demandes
        Mockito.when(demandeRepository.findAll()).thenReturn(demandes);

        // Call the service method
        List<Demande> result = demandeService.findAll();

        // Verify the result
        Assertions.assertEquals(demandes, result);
    }

    @Test
    void testFindByEtat() {
        // Create a list of demandes with a specific etat
        Etat etat = Etat.EnAttente; // Replace 'EN_ATTENTE' with the appropriate value from your Etat enum
        List<Demande> demandes = new ArrayList<>();
        demandes.add(new Demande());
        demandes.add(new Demande());

        // Configure the mock repository to return the list of demandes with the etat
        Mockito.when(demandeRepository.findByEtat(etat)).thenReturn(demandes);

        // Call the service method
        List<Demande> result = demandeService.findByEtat(etat);

        // Verify the result
        Assertions.assertEquals(demandes, result);
    }

    @Test
    void testGetDemandeByClient() {
        // Create a client with a specific ID
        Long clientId = 1L;
        Client client = new Client();
        client.setIdClient(clientId);

        // Create a list of demandes for the client
        List<Demande> demandes = new ArrayList<>();
        demandes.add(new Demande());
        demandes.add(new Demande());

        // Configure the mock repository to return the list of demandes for the client
        Mockito.when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        Mockito.when(demandeRepository.findByClient(client)).thenReturn(demandes);

        // Call the service method
        List<Demande> result = demandeService.getDemandeByClient(clientId);

        // Verify the result
        Assertions.assertEquals(demandes, result);
    }

    @Test
    void testSave() {
        // Create a new demande
        Demande demande = new Demande();

        // Configure the mock repository to return the saved demande
        Mockito.when(demandeRepository.save(demande)).thenReturn(demande);

        // Call the service method
        Demande result = demandeService.save(demande);

        // Verify the result
        Assertions.assertEquals(demande, result);
    }

    @Test
    void testDeleteById() {
        // Call the service method to delete a demande by ID
        demandeService.deleteById(1L);

        // Verify that the deleteById method of the mock repository is called
        Mockito.verify(demandeRepository).deleteById(ArgumentMatchers.eq(1L));
    }

    @Test
    void testCountAllDemandes() {
        // Configure the mock repository to return a specific count
        Long count = 5L;
        Mockito.when(demandeRepository.count()).thenReturn(count);

        // Call the service method
        Long result = demandeService.countAllDemandes();

        // Verify the result
        Assertions.assertEquals(count, result);
    }

    @Test
    void testFindById() {
        // Create a demande with a specific ID
        Long demandeId = 1L;
        Demande demande = new Demande();
        demande.setIdDemande(demandeId);

        // Configure the mock repository to return the demande with the ID
        Mockito.when(demandeRepository.findById(demandeId)).thenReturn(Optional.of(demande));

        // Call the service method
        Demande result = demandeService.findById(demandeId);

        // Verify the result
        Assertions.assertEquals(demande, result);
    }

    @Test
    void testSaveDemande() {
        // Create a new demande
        Demande demande = new Demande();

        // Call the service method to save the demande
        demandeService.saveDemande(demande);

        // Verify that the save method of the mock repository is called
        Mockito.verify(demandeRepository).save(ArgumentMatchers.eq(demande));
    }
}
