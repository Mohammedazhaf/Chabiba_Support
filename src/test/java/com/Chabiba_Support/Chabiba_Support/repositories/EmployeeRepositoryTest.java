package com.Chabiba_Support.Chabiba_Support.repositories;
import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.models.Role;
import com.Chabiba_Support.Chabiba_Support.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeRepositoryTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void testFindEmployeeByIdPersonne() {
        Long idPersonne = 1L;
        Employee employee = new Employee();
        employee.setIdPersonne(idPersonne);

        when(employeeRepository.findEmployeeByIdPersonne(idPersonne)).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeRepository.findEmployeeByIdPersonne(idPersonne);

        assertEquals(Optional.of(employee), result);
    }

    @Test
    public void testFindEmployeeByRole() {
        Role role = Role.Secretaire;
        Employee employee1 = new Employee();
        employee1.setIdPersonne(1L);
        employee1.setRole(role);

        Employee employee2 = new Employee();
        employee2.setIdPersonne(2L);
        employee2.setRole(role);

        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findEmployeeByRole(eq(role))).thenReturn(employees);

        List<Employee> result = employeeRepository.findEmployeeByRole(role);

        assertEquals(employees, result);
    }

    @Test
    public void testFindByNomLikeIgnoreCase() {
        String nom = "John";
        Employee employee1 = new Employee();
        employee1.setIdPersonne(1L);
        employee1.setNom("John Doe");

        Employee employee2 = new Employee();
        employee2.setIdPersonne(2L);
        employee2.setNom("John Smith");

        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findByNomLikeIgnoreCase(any(String.class))).thenReturn(employees);

        List<Employee> result = employeeRepository.findByNomLikeIgnoreCase(nom);

        assertEquals(employees, result);
    }
}
