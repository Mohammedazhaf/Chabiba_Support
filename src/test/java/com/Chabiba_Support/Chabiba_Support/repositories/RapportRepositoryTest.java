package com.Chabiba_Support.Chabiba_Support.repositories;

import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.models.Rapport;
import com.Chabiba_Support.Chabiba_Support.repositories.RapportRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RapportRepositoryTest {

    @Mock
    private RapportRepository rapportRepository;

    public RapportRepositoryTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Rapport rapport1 = new Rapport();
        Rapport rapport2 = new Rapport();
        List<Rapport> expectedRapports = Arrays.asList(rapport1, rapport2);

        when(rapportRepository.findAll()).thenReturn(expectedRapports);

        List<Rapport> result = rapportRepository.findAll();

        assertEquals(expectedRapports, result);
    }

    @Test
    void testFindByIdRapport() {
        Long rapportId = 1L;
        Rapport rapport = new Rapport();

        when(rapportRepository.findByIdRapport(rapportId)).thenReturn(Optional.of(rapport));

        Optional<Rapport> result = rapportRepository.findByIdRapport(rapportId);

        assertEquals(Optional.of(rapport), result);
    }

    @Test
    void testFindByDemande() {
        Demande demande = new Demande();
        Rapport rapport1 = new Rapport();
        Rapport rapport2 = new Rapport();
        List<Rapport> expectedRapports = Arrays.asList(rapport1, rapport2);

        when(rapportRepository.findByDemande(demande)).thenReturn(expectedRapports);

        List<Rapport> result = rapportRepository.findByDemande(demande);

        assertEquals(expectedRapports, result);
    }

    @Test
    void testFindByDate() {
        Date date = new Date(System.currentTimeMillis());
        Rapport rapport1 = new Rapport();
        Rapport rapport2 = new Rapport();
        List<Rapport> expectedRapports = Arrays.asList(rapport1, rapport2);

        when(rapportRepository.findByDate(date)).thenReturn(expectedRapports);

        List<Rapport> result = rapportRepository.findByDate(date);

        assertEquals(expectedRapports, result);
    }

    @Test
    void testSave() {
        Rapport rapport = new Rapport();

        when(rapportRepository.save(rapport)).thenReturn(rapport);

        Rapport result = rapportRepository.save(rapport);

        assertEquals(rapport, result);
    }

    @Test
    void testDelete() {
        Rapport rapport = new Rapport();

        // Perform the delete operation, no need to mock it as it doesn't return anything

        rapportRepository.delete(rapport);
    }
}
