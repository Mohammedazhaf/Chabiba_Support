package com.Chabiba_Support.Chabiba_Support.repositories;

import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.models.Role;
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
public class EmployeeRepositoryTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void testFindEmployeeByPersonne() {
        // Mocking the repository behavior
        Personne personne = new Personne();
        personne.setIdPersonne(1L);
        personne.setNom("John Doe");
        Employee employee = new Employee();
        employee.setIdEmployee(1L);
        employee.setPersonne(personne);
        when(employeeRepository.findEmployeeByPersonne(personne)).thenReturn(Optional.of(employee));

        // Calling the repository method
        Optional<Employee> result = employeeRepository.findEmployeeByPersonne(personne);

        // Verifying the repository method was called
        verify(employeeRepository, times(1)).findEmployeeByPersonne(personne);

        // Asserting the result
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(employee.getIdEmployee(), result.get().getIdEmployee());
        Assertions.assertEquals(employee.getPersonne().getNom(), result.get().getPersonne().getNom());
    }

    @Test
    public void testFindEmployeeByRole() {
        // Mocking the repository behavior
        Role role = Role.Responsable;
        Employee employee1 = new Employee();
        employee1.setIdEmployee(1L);
        employee1.setPersonne(new Personne());
        Employee employee2 = new Employee();
        employee2.setIdEmployee(2L);
        employee2.setPersonne(new Personne());
        when(employeeRepository.findEmployeeByRole(role)).thenReturn(Arrays.asList(employee1, employee2));

        // Calling the repository method
        List<Employee> result = employeeRepository.findEmployeeByRole(role);

        // Verifying the repository method was called
        verify(employeeRepository, times(1)).findEmployeeByRole(role);

        // Asserting the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(employee1.getIdEmployee(), result.get(0).getIdEmployee());
        Assertions.assertEquals(employee2.getIdEmployee(), result.get(1).getIdEmployee());
    }

    @Test
    public void testFindByNomLikeIgnoreCase() {
        // Mocking the repository behavior
        String nom = "Doe";
        Employee employee1 = new Employee();
        employee1.setIdEmployee(1L);
        employee1.setPersonne(new Personne());
        Employee employee2 = new Employee();
        employee2.setIdEmployee(2L);
        employee2.setPersonne(new Personne());
        when(employeeRepository.findByNomLikeIgnoreCase(contains(nom))).thenReturn(Arrays.asList(employee1, employee2));

        // Calling the repository method
        List<Employee> result = employeeRepository.findByNomLikeIgnoreCase(nom);

        // Verifying the repository method was called
        verify(employeeRepository, times(1)).findByNomLikeIgnoreCase(contains(nom));

        // Asserting the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(employee1.getIdEmployee(), result.get(0).getIdEmployee());
        Assertions.assertEquals(employee2.getIdEmployee(), result.get(1).getIdEmployee());
    }

    @Test
    public void testFindEmployeeById() {
        // Mocking the repository behavior
        Long employeeId = 1L;
        Personne personne = new Personne();
        personne.setIdPersonne(1L);
        personne.setNom("John Doe");
        Employee employee = new Employee();
        employee.setIdEmployee(employeeId);
        employee.setPersonne(personne);
        when(employeeRepository.findEmployeeById(employeeId)).thenReturn(employee);

        // Calling the repository method
        Employee result = employeeRepository.findEmployeeById(employeeId);

        // Verifying the repository method was called
        verify(employeeRepository, times(1)).findEmployeeById(employeeId);

        // Asserting the result
        Assertions.assertEquals(employee.getIdEmployee(), result.getIdEmployee());
        Assertions.assertEquals(employee.getPersonne().getNom(), result.getPersonne().getNom());
    }
}
