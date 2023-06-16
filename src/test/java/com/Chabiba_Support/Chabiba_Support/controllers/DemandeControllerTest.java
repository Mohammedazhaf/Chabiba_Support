//package com.Chabiba_Support.Chabiba_Support.controllers;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.anyLong;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.Chabiba_Support.Chabiba_Support.models.*;
//import com.Chabiba_Support.Chabiba_Support.repositories.ClientRepository;
//import com.Chabiba_Support.Chabiba_Support.repositories.DemandeRepository;
//import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;
//import com.Chabiba_Support.Chabiba_Support.services.DemandeService;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.Disabled;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.modelmapper.ModelMapper;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.multipart.MultipartFile;
//
//class DemandeControllerTest {
//    /**
//     * Method under test: {@link DemandeController#findAll()}
//     */
//    @InjectMocks
//    DemandeService demandeService;
//    @Test
//    void testFindAll() {
//        DemandeRepository demandeRepository = mock(DemandeRepository.class);
//        ArrayList<Demande> demandeList = new ArrayList<>();
//        when(demandeRepository.findAll()).thenReturn(demandeList);
//        DemandeService demandeService = new DemandeService(demandeRepository, mock(PersonneRepository.class),
//                mock(ClientRepository.class));
//
//        List<Demande> actualFindAllResult = (new DemandeController(demandeService, new ModelMapper())).findAll();
//        assertSame(demandeList, actualFindAllResult);
//        assertTrue(actualFindAllResult.isEmpty());
//        verify(demandeRepository).findAll();
//    }
//
//    /**
//     * Method under test: {@link DemandeController#findAll()}
//     */
//    @Test
//    void testFindAll3() {
//        DemandeService demandeService = mock(DemandeService.class);
//        ArrayList<Demande> demandeList = new ArrayList<>();
//        when(demandeService.findAll()).thenReturn(demandeList);
//        List<Demande> actualFindAllResult = (new DemandeController(demandeService, new ModelMapper())).findAll();
//        assertSame(demandeList, actualFindAllResult);
//        assertTrue(actualFindAllResult.isEmpty());
//        verify(demandeService).findAll();
//    }
//
//    /**
//     * Method under test: {@link DemandeController#findByEtat(Etat)}
//     */
//    @Test
//    void testFindByEtat() {
//        DemandeRepository demandeRepository = mock(DemandeRepository.class);
//        ArrayList<Demande> demandeList = new ArrayList<>();
//        when(demandeRepository.findByEtat((Etat) any())).thenReturn(demandeList);
//        DemandeService demandeService = new DemandeService(demandeRepository, mock(PersonneRepository.class),
//                mock(ClientRepository.class));
//
//        List<Demande> actualFindByEtatResult = (new DemandeController(demandeService, new ModelMapper()))
//                .findByEtat(Etat.EnAttente);
//        assertSame(demandeList, actualFindByEtatResult);
//        assertTrue(actualFindByEtatResult.isEmpty());
//        verify(demandeRepository).findByEtat((Etat) any());
//    }
//
//    /**
//     * Method under test: {@link DemandeController#findByEtat(Etat)}
//     */
//    @Test
//    void testFindByEtat3() {
//        DemandeService demandeService = mock(DemandeService.class);
//        ArrayList<Demande> demandeList = new ArrayList<>();
//        when(demandeService.findByEtat((Etat) any())).thenReturn(demandeList);
//        List<Demande> actualFindByEtatResult = (new DemandeController(demandeService, new ModelMapper()))
//                .findByEtat(Etat.EnAttente);
//        assertSame(demandeList, actualFindByEtatResult);
//        assertTrue(actualFindByEtatResult.isEmpty());
//        verify(demandeService).findByEtat((Etat) any());
//    }
//
//    /**
//     * Method under test: {@link DemandeController#findByEtat(Etat)}
//     */
//    @Test
//    void testFindByEtat4() {
//        DemandeService demandeService = mock(DemandeService.class);
//        ArrayList<Demande> demandeList = new ArrayList<>();
//        when(demandeService.findByEtat((Etat) any())).thenReturn(demandeList);
//        List<Demande> actualFindByEtatResult = (new DemandeController(demandeService, new ModelMapper()))
//                .findByEtat(Etat.EnCours);
//        assertSame(demandeList, actualFindByEtatResult);
//        assertTrue(actualFindByEtatResult.isEmpty());
//        verify(demandeService).findByEtat((Etat) any());
//    }
//
//    /**
//     * Method under test: {@link DemandeController#findByEtat(Etat)}
//     */
//    @Test
//    void testFindByEtat5() {
//        DemandeService demandeService = mock(DemandeService.class);
//        ArrayList<Demande> demandeList = new ArrayList<>();
//        when(demandeService.findByEtat((Etat) any())).thenReturn(demandeList);
//        List<Demande> actualFindByEtatResult = (new DemandeController(demandeService, new ModelMapper()))
//                .findByEtat(Etat.Termine);
//        assertSame(demandeList, actualFindByEtatResult);
//        assertTrue(actualFindByEtatResult.isEmpty());
//        verify(demandeService).findByEtat((Etat) any());
//    }
//
//    /**
//     * Method under test: {@link DemandeController#createDemande(MultipartFile, String)}
//     */
//    @Test
//    void testCreateDemande() throws IOException {
//        DemandeService demandeService = new DemandeService(mock(DemandeRepository.class), mock(PersonneRepository.class),
//                mock(ClientRepository.class));
//
//        DemandeController demandeController = new DemandeController(demandeService, new ModelMapper());
//        demandeController.createDemande(
//                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), "{\"date\":\"2023-06-07T12:00:00Z\",\"titre\":\"Demand Title Updated\",\"etat\":\"EnCours\",\"service\":\"Developpement\",\"budget\":\"200\",\"message\":\"Demande Message Updated\",\"type\":\"Urgent\",\"idClient\":2}");
//    }
//
//
//    /**
//     * Method under test: {@link DemandeController#deleteDemande(Long)}
//     */
//    @Test
//    void testDeleteDemande() {
//        DemandeRepository demandeRepository = mock(DemandeRepository.class);
//        doNothing().when(demandeRepository).deleteById((Long) any());
//        DemandeService demandeService = new DemandeService(demandeRepository, mock(PersonneRepository.class),
//                mock(ClientRepository.class));
//
//        (new DemandeController(demandeService, new ModelMapper())).deleteDemande(1L);
//        verify(demandeRepository).deleteById((Long) any());
//    }
//
//    /**
//     * Method under test: {@link DemandeController#deleteDemande(Long)}
//     */
//    @Test
//    void testDeleteDemande3() {
//        DemandeService demandeService = mock(DemandeService.class);
//        doNothing().when(demandeService).deleteById((Long) any());
//        (new DemandeController(demandeService, new ModelMapper())).deleteDemande(1L);
//        verify(demandeService).deleteById((Long) any());
//    }
//
//    /**
//     * Method under test: {@link DemandeController#countAllDemandes()}
//     */
//    @Test
//    void testCountAllDemandes() {
//        DemandeRepository demandeRepository = mock(DemandeRepository.class);
//        when(demandeRepository.count()).thenReturn(3L);
//        DemandeService demandeService = new DemandeService(demandeRepository, mock(PersonneRepository.class),
//                mock(ClientRepository.class));
//
//        ResponseEntity<Long> actualCountAllDemandesResult = (new DemandeController(demandeService, new ModelMapper()))
//                .countAllDemandes();
//        assertEquals(3L, actualCountAllDemandesResult.getBody().longValue());
//        assertEquals(200, actualCountAllDemandesResult.getStatusCodeValue());
//        assertTrue(actualCountAllDemandesResult.getHeaders().isEmpty());
//        verify(demandeRepository).count();
//    }
//
//
//    /**
//     * Method under test: {@link DemandeController#countAllDemandes()}
//     */
//    @Test
//    void testCountAllDemandes3() {
//        DemandeService demandeService = mock(DemandeService.class);
//        when(demandeService.countAllDemandes()).thenReturn(3L);
//        ResponseEntity<Long> actualCountAllDemandesResult = (new DemandeController(demandeService, new ModelMapper()))
//                .countAllDemandes();
//        assertEquals(3L, actualCountAllDemandesResult.getBody().longValue());
//        assertEquals(200, actualCountAllDemandesResult.getStatusCodeValue());
//        assertTrue(actualCountAllDemandesResult.getHeaders().isEmpty());
//        verify(demandeService).countAllDemandes();
//    }
//
//    /**
//     * Method under test: {@link DemandeController#acceptDemande(long)}
//     */
//    @Test
//    void testAcceptDemande() {
//        DemandeRepository demandeRepository = mock(DemandeRepository.class);
//        Demande demande = new Demande();
//        when(demandeRepository.save((Demande) any())).thenReturn(demande);
//        when(demandeRepository.findById((Long) any())).thenReturn(Optional.of(new Demande()));
//        DemandeService demandeService = new DemandeService(demandeRepository, mock(PersonneRepository.class),
//                mock(ClientRepository.class));
//
//        assertSame(demande, (new DemandeController(demandeService, new ModelMapper())).acceptDemande(1L));
//        verify(demandeRepository).save((Demande) any());
//        verify(demandeRepository).findById((Long) any());
//    }
//
//    /**
//     * Method under test: {@link DemandeController#acceptDemande(long)}
//     */
//    @Test
//    void testAcceptDemande4() {
//        DemandeService demandeService = mock(DemandeService.class);
//        Demande demande = new Demande();
//        when(demandeService.save((Demande) any())).thenReturn(demande);
//        when(demandeService.findById(anyLong())).thenReturn(new Demande());
//        assertSame(demande, (new DemandeController(demandeService, new ModelMapper())).acceptDemande(1L));
//        verify(demandeService).findById(anyLong());
//        verify(demandeService).save((Demande) any());
//    }
//
//    /**
//     * Method under test: {@link DemandeController#rejectDemande(long)}
//     */
//    @Test
//    void testRejectDemande() {
//        DemandeRepository demandeRepository = mock(DemandeRepository.class);
//        Demande demande = new Demande();
//        when(demandeRepository.save((Demande) any())).thenReturn(demande);
//        when(demandeRepository.findById((Long) any())).thenReturn(Optional.of(new Demande()));
//        DemandeService demandeService = new DemandeService(demandeRepository, mock(PersonneRepository.class),
//                mock(ClientRepository.class));
//
//        assertSame(demande, (new DemandeController(demandeService, new ModelMapper())).rejectDemande(1L));
//        verify(demandeRepository).save((Demande) any());
//        verify(demandeRepository).findById((Long) any());
//    }
//
//    /**
//     * Method under test: {@link DemandeController#rejectDemande(long)}
//     */
//    @Test
//    void testRejectDemande4() {
//        DemandeService demandeService = mock(DemandeService.class);
//        Demande demande = new Demande();
//        when(demandeService.save((Demande) any())).thenReturn(demande);
//        when(demandeService.findById(anyLong())).thenReturn(new Demande());
//        assertSame(demande, (new DemandeController(demandeService, new ModelMapper())).rejectDemande(1L));
//        verify(demandeService).findById(anyLong());
//        verify(demandeService).save((Demande) any());
//    }
//
//    /**
//     * Method under test: {@link DemandeController#secretairePassed(long)}
//     */
//    @Test
//    void testSecretairePassed() {
//        DemandeRepository demandeRepository = mock(DemandeRepository.class);
//        Demande demande = new Demande();
//        when(demandeRepository.save((Demande) any())).thenReturn(demande);
//        when(demandeRepository.findById((Long) any())).thenReturn(Optional.of(new Demande()));
//        DemandeService demandeService = new DemandeService(demandeRepository, mock(PersonneRepository.class),
//                mock(ClientRepository.class));
//
//        assertSame(demande, (new DemandeController(demandeService, new ModelMapper())).secretairePassed(1L));
//        verify(demandeRepository).save((Demande) any());
//        verify(demandeRepository).findById((Long) any());
//    }
//
//    /**
//     * Method under test: {@link DemandeController#secretairePassed(long)}
//     */
//    @Test
//    void testSecretairePassed4() {
//        DemandeService demandeService = mock(DemandeService.class);
//        Demande demande = new Demande();
//        when(demandeService.save((Demande) any())).thenReturn(demande);
//        when(demandeService.findById(anyLong())).thenReturn(new Demande());
//        assertSame(demande, (new DemandeController(demandeService, new ModelMapper())).secretairePassed(1L));
//        verify(demandeService).findById(anyLong());
//        verify(demandeService).save((Demande) any());
//    }
//
//    /**
//     * Method under test: {@link DemandeController#updateDemande(String, MultipartFile, long)}
//     */
//
//    @Test
//    void testUpdateDemande3() throws IOException {
//        // Create a mock instance of DemandeService
//        DemandeService demandeService = mock(DemandeService.class);
//
//        DemandeController demandeController = mock(DemandeController.class);
//        demandeController.updateDemande("{\"date\":\"2023-06-07T12:00:00Z\",\"titre\":\"Demand Title Updated\",\"etat\":\"EnCours\",\"service\":\"Developpement\",\"budget\":\"200\",\"message\":\"Demande Message Updated\",\"type\":\"Urgent\",\"idClient\":2}",
//                new MockMultipartFile("file", new ByteArrayInputStream("AXAXAXAX".getBytes())), 1L);
//    }
//
//
//    /**
//     * Method under test: {@link DemandeController#findDemandeById(Long)}
//     */
//    @Test
//    void testFindDemandeById() {
//        DemandeRepository demandeRepository = mock(DemandeRepository.class);
//        when(demandeRepository.findById((Long) any())).thenReturn(Optional.of(new Demande()));
//        DemandeService demandeService = new DemandeService(demandeRepository, mock(PersonneRepository.class),
//                mock(ClientRepository.class));
//
//        ResponseEntity<Demande> actualFindDemandeByIdResult = (new DemandeController(demandeService, new ModelMapper()))
//                .findDemandeById(1L);
//        assertTrue(actualFindDemandeByIdResult.hasBody());
//        assertTrue(actualFindDemandeByIdResult.getHeaders().isEmpty());
//        assertEquals(200, actualFindDemandeByIdResult.getStatusCodeValue());
//        verify(demandeRepository).findById((Long) any());
//    }
//
//
//    /**
//     * Method under test: {@link DemandeController#findDemandeById(Long)}
//     */
//    @Test
//    void testFindDemandeById3() {
//        DemandeRepository demandeRepository = mock(DemandeRepository.class);
//        when(demandeRepository.findById((Long) any())).thenReturn(Optional.empty());
//        DemandeService demandeService = new DemandeService(demandeRepository, mock(PersonneRepository.class),
//                mock(ClientRepository.class));
//
//        ResponseEntity<Demande> actualFindDemandeByIdResult = (new DemandeController(demandeService, new ModelMapper()))
//                .findDemandeById(1L);
//        assertNull(actualFindDemandeByIdResult.getBody());
//        assertEquals(404, actualFindDemandeByIdResult.getStatusCodeValue());
//        assertTrue(actualFindDemandeByIdResult.getHeaders().isEmpty());
//        verify(demandeRepository).findById((Long) any());
//    }
//
//    /**
//     * Method under test: {@link DemandeController#findDemandeById(Long)}
//     */
//    @Test
//    void testFindDemandeById4() {
//        DemandeService demandeService = mock(DemandeService.class);
//        when(demandeService.findById(anyLong())).thenReturn(new Demande());
//        ResponseEntity<Demande> actualFindDemandeByIdResult = (new DemandeController(demandeService, new ModelMapper()))
//                .findDemandeById(1L);
//        assertTrue(actualFindDemandeByIdResult.hasBody());
//        assertTrue(actualFindDemandeByIdResult.getHeaders().isEmpty());
//        assertEquals(200, actualFindDemandeByIdResult.getStatusCodeValue());
//        verify(demandeService).findById(anyLong());
//    }
//
//}
//
