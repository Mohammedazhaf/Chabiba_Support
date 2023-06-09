package com.Chabiba_Support.Chabiba_Support.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.Chabiba_Support.Chabiba_Support.exception.RapportNotFoundException;
import com.Chabiba_Support.Chabiba_Support.models.Rapport;
import com.Chabiba_Support.Chabiba_Support.repositories.RapportRepository;
import com.Chabiba_Support.Chabiba_Support.services.RapportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RapportServiceTest {

    @Mock
    private RapportRepository rapportRepository;

    @InjectMocks
    private RapportService rapportService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRapports() {
        // Create a list of mock rapports
        List<Rapport> mockRapports = new ArrayList<>();
        Rapport rapport1 = new Rapport();
        rapport1.setIdRapport(1L);
        rapport1.setContenu("Rapport Content");
        Date date = new Date(12);
        rapport1.setDate(date);



        Rapport rapport2 = new Rapport();
        rapport2.setIdRapport(2L);
        rapport2.setContenu("Rapport Content");
        Date date2 = new Date(12);
        rapport2.setDate(date2);

        mockRapports.add(rapport1);
        mockRapports.add(rapport2);

        // Mock the behavior of the rapportRepository.findAll() method
        when(rapportRepository.findAll()).thenReturn(mockRapports);

        // Call the service method
        List<Rapport> result = rapportService.getAllRapports();

        // Verify the result
        assertEquals(mockRapports, result);
        verify(rapportRepository, times(1)).findAll();
    }

    @Test
    public void testGetRapportByIdRapport() {
        // Create a mock rapport
        Rapport mockRapport = new Rapport();
        mockRapport.setIdRapport(1L);
        mockRapport.setContenu("Rapport Content");
        Date date = new Date(12);
        mockRapport.setDate(date);

        // Mock the behavior of the rapportRepository.findByIdRapport() method
        when(rapportRepository.findByIdRapport(1L)).thenReturn(Optional.of(mockRapport));

        // Call the service method
        Rapport result = rapportService.getRapportByIdRapport(1L);

        // Verify the result
        assertEquals(mockRapport, result);
        verify(rapportRepository, times(1)).findByIdRapport(1L);
    }

    @Test
    public void testGetRapportByIdRapport_NotFound() {
        // Mock the behavior of the rapportRepository.findByIdRapport() method
        when(rapportRepository.findByIdRapport(1L)).thenReturn(Optional.empty());

        // Call the service method and assert that it throws RapportNotFoundException
        assertThrows(RapportNotFoundException.class, () -> rapportService.getRapportByIdRapport(1L));

        verify(rapportRepository, times(1)).findByIdRapport(1L);
    }

    @Test
    public void testSaveRapport() {
        // Create a mock rapport
        Rapport mockRapport = new Rapport();
        mockRapport.setIdRapport(1L);
        mockRapport.setContenu("Rapport Content");
        Date date = new Date(12);
        mockRapport.setDate(date);

        // Mock the behavior of the rapportRepository.save() method
        when(rapportRepository.save(mockRapport)).thenReturn(mockRapport);

        // Call the service method
        Rapport result = rapportService.saveRapport(mockRapport);

        // Verify the result
        assertEquals(mockRapport, result);
        verify(rapportRepository, times(1)).save(mockRapport);
    }

    @Test
    public void testDeleteRapport() {
        // Create a mock rapport
        Rapport mockRapport = new Rapport();
        mockRapport.setIdRapport(1L);
        mockRapport.setContenu("Rapport Content");
        Date date = new Date(12);
        mockRapport.setDate(date);

        // Mock the behavior of the rapportRepository.findByIdRapport() method
        when(rapportRepository.findByIdRapport(1L)).thenReturn(Optional.of(mockRapport));

        // Call the service method
        rapportService.deleteRapport(1L);

        // Verify that the rapportRepository.delete() method was called
        verify(rapportRepository, times(1)).delete(mockRapport);
    }

    @Test
    public void testDeleteRapport_NotFound() {
        // Mock the behavior of the rapportRepository.findByIdRapport() method
        when(rapportRepository.findByIdRapport(1L)).thenReturn(Optional.empty());

        // Call the service method and assert that it throws RapportNotFoundException
        assertThrows(RapportNotFoundException.class, () -> rapportService.deleteRapport(1L));

        verify(rapportRepository, times(0)).delete(any());
    }
}
