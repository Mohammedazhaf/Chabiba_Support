package com.Chabiba_Support.Chabiba_Support.services;
import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.models.Etat;
import com.Chabiba_Support.Chabiba_Support.repositories.DemandeRepository;
import com.Chabiba_Support.Chabiba_Support.services.DemandeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DemandeServiceTest {



    @Mock
    private DemandeRepository demandeRepository;

    private DemandeService demandeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        demandeService = new DemandeService(demandeRepository);
    }



    @Test
    public void testFindAll() {
        List<Demande> demandes = new ArrayList<>();
        demandes.add(new Demande());
        demandes.add(new Demande());
        Mockito.when(demandeRepository.findAll()).thenReturn(demandes);

        List<Demande> result = demandeService.findAll();

        Assertions.assertEquals(demandes, result);
        Mockito.verify(demandeRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testFindByEtat() {
        List<Demande> demandes = new ArrayList<>();
        Etat etat = Etat.EnAttente;
        Mockito.when(demandeRepository.findByEtat(etat)).thenReturn(demandes);

        List<Demande> result = demandeService.findByEtat(etat);

        Assertions.assertEquals(demandes, result);
        Mockito.verify(demandeRepository, Mockito.times(1)).findByEtat(etat);
    }

    @Test
    public void testGetDemandeByClient() {
        List<Demande> demandes = new ArrayList<>();
        Long idPersonne = 1L;
        Mockito.when(demandeRepository.findByClientIdPersonne(idPersonne)).thenReturn(demandes);

        List<Demande> result = demandeService.getDemandeByClient(idPersonne);

        Assertions.assertEquals(demandes, result);
        Mockito.verify(demandeRepository, Mockito.times(1)).findByClientIdPersonne(idPersonne);
    }

    @Test
    public void testSave() {
        Demande demande = new Demande();
        Mockito.when(demandeRepository.save(demande)).thenReturn(demande);

        Demande result = demandeService.save(demande);

        Assertions.assertEquals(demande, result);
        Mockito.verify(demandeRepository, Mockito.times(1)).save(demande);
    }

    @Test
    public void testDeleteById() {
        Long idDemande = 1L;
        Mockito.doNothing().when(demandeRepository).deleteById(idDemande);

        demandeService.deleteById(idDemande);

        Mockito.verify(demandeRepository, Mockito.times(1)).deleteById(idDemande);
    }

    @Test
    public void testCountAllDemandes() {
        Long count = 5L;
        Mockito.when(demandeRepository.count()).thenReturn(count);

        Long result = demandeService.countAllDemandes();

        Assertions.assertEquals(count, result);
        Mockito.verify(demandeRepository, Mockito.times(1)).count();
    }

    @Test
    public void testFindById() {
        Long idDemande = 1L;
        Demande demande = new Demande();
        Mockito.when(demandeRepository.findById(idDemande)).thenReturn(Optional.of(demande));

        Demande result = demandeService.findById(idDemande);

        Assertions.assertEquals(demande, result);
        Mockito.verify(demandeRepository, Mockito.times(1)).findById(idDemande);
    }

    // Add more test cases as needed...

    // ...
}
