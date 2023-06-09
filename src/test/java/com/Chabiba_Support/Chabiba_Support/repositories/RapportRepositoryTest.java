package com.Chabiba_Support.Chabiba_Support.repositories;

import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.models.Rapport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RapportRepositoryTest {

    @Mock
    private RapportRepository rapportRepository;

    @Test
    public void testFindAll() {
        // Mocking the repository behavior
        Rapport rapport1 = new Rapport();
        rapport1.setIdRapport(1L);
        Rapport rapport2 = new Rapport();
        rapport2.setIdRapport(2L);
        when(rapportRepository.findAll()).thenReturn(Arrays.asList(rapport1, rapport2));

        // Calling the repository method
        List<Rapport> result = rapportRepository.findAll();

        // Verifying the repository method was called
        verify(rapportRepository, times(1)).findAll();

        // Asserting the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(rapport1.getIdRapport(), result.get(0).getIdRapport());
        Assertions.assertEquals(rapport2.getIdRapport(), result.get(1).getIdRapport());
    }

    @Test
    public void testFindByIdRapport() {
        // Mocking the repository behavior
        Long rapportId = 1L;
        Rapport rapport = new Rapport();
        rapport.setIdRapport(rapportId);
        when(rapportRepository.findByIdRapport(rapportId)).thenReturn(Optional.of(rapport));

        // Calling the repository method
        Optional<Rapport> result = rapportRepository.findByIdRapport(rapportId);

        // Verifying the repository method was called
        verify(rapportRepository, times(1)).findByIdRapport(rapportId);

        // Asserting the result
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(rapportId, result.get().getIdRapport());
    }

    @Test
    public void testFindByDemande() {
        // Mocking the repository behavior
        Demande demande = new Demande();
        demande.setIdDemande(1L);
        Rapport rapport1 = new Rapport();
        rapport1.setIdRapport(1L);
        Rapport rapport2 = new Rapport();
        rapport2.setIdRapport(2L);
        when(rapportRepository.findByDemande(demande)).thenReturn(Arrays.asList(rapport1, rapport2));

        // Calling the repository method
        List<Rapport> result = rapportRepository.findByDemande(demande);

        // Verifying the repository method was called
        verify(rapportRepository, times(1)).findByDemande(demande);

        // Asserting the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(rapport1.getIdRapport(), result.get(0).getIdRapport());
        Assertions.assertEquals(rapport2.getIdRapport(), result.get(1).getIdRapport());
    }

    @Test
    public void testFindByDate() {
        // Mocking the repository behavior
        Date date = new Date(System.currentTimeMillis());
        Rapport rapport1 = new Rapport();
        rapport1.setIdRapport(1L);
        Rapport rapport2 = new Rapport();
        rapport2.setIdRapport(2L);
        when(rapportRepository.findByDate(date)).thenReturn(Arrays.asList(rapport1, rapport2));

        // Calling the repository method
        List<Rapport> result = rapportRepository.findByDate(date);

        // Verifying the repository method was called
        verify(rapportRepository, times(1)).findByDate(date);

        // Asserting the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(rapport1.getIdRapport(), result.get(0).getIdRapport());
        Assertions.assertEquals(rapport2.getIdRapport(), result.get(1).getIdRapport());
    }

    @Test
    public void testSave() {
        // Mocking the repository behavior
        Rapport rapport = new Rapport();
        rapport.setIdRapport(1L);
        when(rapportRepository.save(rapport)).thenReturn(rapport);

        // Calling the repository method
        Rapport result = rapportRepository.save(rapport);

        // Verifying the repository method was called
        verify(rapportRepository, times(1)).save(rapport);

        // Asserting the result
        Assertions.assertEquals(rapport.getIdRapport(), result.getIdRapport());
    }

    @Test
    public void testDelete() {
        // Mocking the repository behavior
        Rapport rapport = new Rapport();
        rapport.setIdRapport(1L);

        // Calling the repository method
        rapportRepository.delete(rapport);

        // Verifying the repository method was called
        verify(rapportRepository, times(1)).delete(rapport);
    }
}
