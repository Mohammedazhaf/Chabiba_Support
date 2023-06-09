package com.Chabiba_Support.Chabiba_Support.repositories;

import com.Chabiba_Support.Chabiba_Support.models.Affectation;
import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.models.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AffectationRepositoryTest {

    @Mock
    private AffectationRepository affectationRepository;

    @Test
    public void testFindAll() {
        // Mocking the repository behavior
        Affectation affectation1 = new Affectation();
        affectation1.setIdAffectation(1L);
        Affectation affectation2 = new Affectation();
        affectation2.setIdAffectation(2L);
        when(affectationRepository.findAll()).thenReturn(Arrays.asList(affectation1, affectation2));

        // Calling the repository method
        List<Affectation> result = affectationRepository.findAll();

        // Verifying the repository method was called
        verify(affectationRepository, times(1)).findAll();

        // Asserting the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(affectation1.getIdAffectation(), result.get(0).getIdAffectation());
        Assertions.assertEquals(affectation2.getIdAffectation(), result.get(1).getIdAffectation());
    }

    @Test
    public void testFindByDemande() {
        // Mocking the repository behavior
        Demande demande = new Demande();
        demande.setIdDemande(1L);
        Affectation affectation1 = new Affectation();
        affectation1.setIdAffectation(1L);
        Affectation affectation2 = new Affectation();
        affectation2.setIdAffectation(2L);
        when(affectationRepository.findByDemande(demande)).thenReturn(Arrays.asList(affectation1, affectation2));

        // Calling the repository method
        List<Affectation> result = affectationRepository.findByDemande(demande);

        // Verifying the repository method was called
        verify(affectationRepository, times(1)).findByDemande(demande);

        // Asserting the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(affectation1.getIdAffectation(), result.get(0).getIdAffectation());
        Assertions.assertEquals(affectation2.getIdAffectation(), result.get(1).getIdAffectation());
    }

    @Test
    public void testFindByEmployee() {
        // Mocking the repository behavior
        Employee employee = new Employee();
        employee.setIdEmployee(1L);
        Affectation affectation1 = new Affectation();
        affectation1.setIdAffectation(1L);
        Affectation affectation2 = new Affectation();
        affectation2.setIdAffectation(2L);
        when(affectationRepository.findByEmployee(employee)).thenReturn(Arrays.asList(affectation1, affectation2));

        // Calling the repository method
        List<Affectation> result = affectationRepository.findByEmployee(employee);

        // Verifying the repository method was called
        verify(affectationRepository, times(1)).findByEmployee(employee);

        // Asserting the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(affectation1.getIdAffectation(), result.get(0).getIdAffectation());
        Assertions.assertEquals(affectation2.getIdAffectation(), result.get(1).getIdAffectation());
    }

    @Test
    public void testSave() {
        // Mocking the repository behavior
        Affectation affectation = new Affectation();
        affectation.setIdAffectation(1L);
        when(affectationRepository.save(affectation)).thenReturn(affectation);

        // Calling the repository method
        Affectation result = affectationRepository.save(affectation);

        // Verifying the repository method was called
        verify(affectationRepository, times(1)).save(affectation);

        // Asserting the result
        Assertions.assertEquals(affectation.getIdAffectation(), result.getIdAffectation());
    }

    @Test
    public void testDelete() {
        // Mocking the repository behavior
        Affectation affectation = new Affectation();
        affectation.setIdAffectation(1L);

        // Calling the repository method
        affectationRepository.delete(affectation);

        // Verifying the repository method was called
        verify(affectationRepository, times(1)).delete(affectation);
    }
}
