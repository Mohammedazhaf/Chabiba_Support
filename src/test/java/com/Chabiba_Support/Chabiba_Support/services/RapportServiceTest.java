package com.Chabiba_Support.Chabiba_Support.services;

import com.Chabiba_Support.Chabiba_Support.exception.RapportNotFoundException;
import com.Chabiba_Support.Chabiba_Support.models.Rapport;
import com.Chabiba_Support.Chabiba_Support.repositories.RapportRepository;
import com.Chabiba_Support.Chabiba_Support.services.RapportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RapportServiceTest {

    @Mock
    private RapportRepository rapportRepository;

    @InjectMocks
    private RapportService rapportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRapports() {
        // Create a list of sample rapports
        List<Rapport> rapports = new ArrayList<>();
        rapports.add(new Rapport(1L, "Rapport 1"));
        rapports.add(new Rapport(2L, "Rapport 2"));

        // Configure the mock repository
        when(rapportRepository.findAll()).thenReturn(rapports);

        // Call the service method
        List<Rapport> result = rapportService.getAllRapports();

        // Verify the repository method was called and returned the expected result
        verify(rapportRepository, times(1)).findAll();
        assertEquals(rapports, result);
    }

    @Test
    public void testGetRapportByIdRapport() {
        // Create a sample rapport
        long idRapport = 1L;
        Rapport rapport = new Rapport(idRapport, "Rapport 1");

        // Configure the mock repository
        when(rapportRepository.findByIdRapport(idRapport)).thenReturn(Optional.of(rapport));

        // Call the service method
        Rapport result = rapportService.getRapportByIdRapport(idRapport);

        // Verify the repository method was called and returned the expected result
        verify(rapportRepository, times(1)).findByIdRapport(idRapport);
        assertEquals(rapport, result);
    }

    @Test
    public void testAddRapport() {
        // Create a sample rapport
        Rapport rapport = new Rapport(1L, "Rapport 1");

        // Configure the mock repository
        when(rapportRepository.save(rapport)).thenReturn(rapport);

        // Call the service method
        Rapport savedRapport = rapportService.addRapport(rapport);

        // Verify the repository method was called and returned the expected result
        verify(rapportRepository, times(1)).save(rapport);
        assertEquals(rapport, savedRapport);
    }

    @Test
    public void testDeleteRapport() {
        // Create a sample rapport
        long idRapport = 1L;
        Rapport rapport = new Rapport(idRapport, "Rapport 1");

        // Configure the mock repository
        when(rapportRepository.findByIdRapport(idRapport)).thenReturn(Optional.of(rapport));

        // Call the service method
        rapportService.deleteRapport(idRapport);

        // Verify the repository method was called
        verify(rapportRepository, times(1)).findByIdRapport(idRapport);
        verify(rapportRepository, times(1)).delete(rapport);
    }

    // Add more test cases for other methods

}
