package com.Chabiba_Support.Chabiba_Support.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.repositories.EmployeeRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;
import com.Chabiba_Support.Chabiba_Support.services.EmployeeService;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Md4PasswordEncoder;

class EmployeeControllerTest {
    /**
     * Method under test: {@link EmployeeController#getAllEmployees()}
     */
    @Test
    void testGetAllEmployees() {
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.findAll()).thenReturn(new ArrayList<>());
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        ResponseEntity<List<Employee>> actualAllEmployees = (new EmployeeController(
                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder()))).getAllEmployees();
        assertTrue(actualAllEmployees.hasBody());
        assertEquals(200, actualAllEmployees.getStatusCodeValue());
        assertTrue(actualAllEmployees.getHeaders().isEmpty());
        verify(employeeRepository).findAll();
    }


    /**
     * Method under test: {@link EmployeeController#getAllEmployees()}
     */
    @Test
    void testGetAllEmployees3() {
        EmployeeService employeeService = mock(EmployeeService.class);
        when(employeeService.findAllEmployees()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Employee>> actualAllEmployees = (new EmployeeController(employeeService)).getAllEmployees();
        assertTrue(actualAllEmployees.hasBody());
        assertEquals(200, actualAllEmployees.getStatusCodeValue());
        assertTrue(actualAllEmployees.getHeaders().isEmpty());
        verify(employeeService).findAllEmployees();
    }

    /**
     * Method under test: {@link EmployeeController#getEmployeeBYIdPersonne(Long)}
     */
    @Test
    void testGetEmployeeBYIdPersonne() {
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.findEmployeeById((Long) any())).thenReturn(new Employee());
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        ResponseEntity<Employee> actualEmployeeBYIdPersonne = (new EmployeeController(
                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder())))
                .getEmployeeBYIdPersonne(1L);
        assertTrue(actualEmployeeBYIdPersonne.hasBody());
        assertTrue(actualEmployeeBYIdPersonne.getHeaders().isEmpty());
        assertEquals(200, actualEmployeeBYIdPersonne.getStatusCodeValue());
        verify(employeeRepository).findEmployeeById((Long) any());
    }

    /**
     * Method under test: {@link EmployeeController#getEmployeeBYIdPersonne(Long)}
     */
    @Test
    void testGetEmployeeBYIdPersonne3() {
        EmployeeService employeeService = mock(EmployeeService.class);
        when(employeeService.findEmployeeById((Long) any())).thenReturn(new Employee());
        ResponseEntity<Employee> actualEmployeeBYIdPersonne = (new EmployeeController(employeeService))
                .getEmployeeBYIdPersonne(1L);
        assertTrue(actualEmployeeBYIdPersonne.hasBody());
        assertTrue(actualEmployeeBYIdPersonne.getHeaders().isEmpty());
        assertEquals(200, actualEmployeeBYIdPersonne.getStatusCodeValue());
        verify(employeeService).findEmployeeById((Long) any());
    }

    /**
     * Method under test: {@link EmployeeController#updateEmployee(Employee)}
     */
    @Test
    void testUpdateEmployee2() {
        EmployeeService employeeService = mock(EmployeeService.class);
        when(employeeService.updateEmployee((Employee) any())).thenReturn(new Employee());
        EmployeeController employeeController = new EmployeeController(employeeService);
        Employee employee = new Employee();
        ResponseEntity<Employee> actualUpdateEmployeeResult = employeeController.updateEmployee(employee);
        assertEquals(employee, actualUpdateEmployeeResult.getBody());
        assertTrue(actualUpdateEmployeeResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateEmployeeResult.getStatusCodeValue());
        verify(employeeService).updateEmployee((Employee) any());
    }

    /**
     * Method under test: {@link EmployeeController#updateEmployee(Employee)}
     */
    @Test
    void testUpdateEmployee6() {
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.save((Employee) any())).thenReturn(new Employee());
        when(employeeRepository.findById((Long) any())).thenReturn(Optional.of(new Employee()));
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        when(personneRepository.save((Personne) any())).thenReturn(new Personne());
        when(personneRepository.findById((Long) any())).thenReturn(Optional.of(new Personne()));
        EmployeeController employeeController = new EmployeeController(
                new EmployeeService(employeeRepository, personneRepository, new Md4PasswordEncoder()));

        Employee employee = new Employee();
        employee.setPersonne(new Personne());
        ResponseEntity<Employee> actualUpdateEmployeeResult = employeeController.updateEmployee(employee);
        assertTrue(actualUpdateEmployeeResult.hasBody());
        assertTrue(actualUpdateEmployeeResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateEmployeeResult.getStatusCodeValue());
        verify(employeeRepository).save((Employee) any());
        verify(employeeRepository).findById((Long) any());
        verify(personneRepository).save((Personne) any());
        verify(personneRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link EmployeeController#deleteEmployee(Long)}
     */
    @Test
    void testDeleteEmployee2() {
        Personne personne = new Personne();
        personne.setIdPersonne(1L);

        Employee employee = new Employee();
        employee.setPersonne(personne);
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        doNothing().when(employeeRepository).delete((Employee) any());
        when(employeeRepository.findEmployeeById((Long) any())).thenReturn(employee);
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        doNothing().when(personneRepository).deleteById((Long) any());
        when(personneRepository.findById((Long) any())).thenReturn(Optional.of(new Personne()));
        ResponseEntity<Map<String, Boolean>> actualDeleteEmployeeResult = (new EmployeeController(
                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder()))).deleteEmployee(1L);
        assertEquals(1, actualDeleteEmployeeResult.getBody().size());
        assertTrue(actualDeleteEmployeeResult.hasBody());
        assertEquals(200, actualDeleteEmployeeResult.getStatusCodeValue());
        assertTrue(actualDeleteEmployeeResult.getHeaders().isEmpty());
        verify(employeeRepository).findEmployeeById((Long) any());
        verify(employeeRepository).delete((Employee) any());
        verify(personneRepository).findById((Long) any());
        verify(personneRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link EmployeeController#deleteEmployee(Long)}
     */
    @Test
    void testDeleteEmployee5() {
        EmployeeService employeeService = mock(EmployeeService.class);
        when(employeeService.findEmployeeById((Long) any())).thenReturn(new Employee());
        doNothing().when(employeeService).deleteEmployee((Employee) any());
        ResponseEntity<Map<String, Boolean>> actualDeleteEmployeeResult = (new EmployeeController(employeeService))
                .deleteEmployee(1L);
        assertEquals(1, actualDeleteEmployeeResult.getBody().size());
        assertTrue(actualDeleteEmployeeResult.hasBody());
        assertEquals(200, actualDeleteEmployeeResult.getStatusCodeValue());
        assertTrue(actualDeleteEmployeeResult.getHeaders().isEmpty());
        verify(employeeService).findEmployeeById((Long) any());
        verify(employeeService).deleteEmployee((Employee) any());
    }

    /**
     * Method under test: {@link EmployeeController#getEmployeeByNomLikeIgnoreCase(String)}
     */
    @Test
    void testGetEmployeeByNomLikeIgnoreCase() {
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.findByNomLikeIgnoreCase((String) any())).thenReturn(new ArrayList<>());
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        ResponseEntity<List<Employee>> actualEmployeeByNomLikeIgnoreCase = (new EmployeeController(
                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder())))
                .getEmployeeByNomLikeIgnoreCase("Nom");
        assertTrue(actualEmployeeByNomLikeIgnoreCase.hasBody());
        assertEquals(200, actualEmployeeByNomLikeIgnoreCase.getStatusCodeValue());
        assertTrue(actualEmployeeByNomLikeIgnoreCase.getHeaders().isEmpty());
        verify(employeeRepository).findByNomLikeIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link EmployeeController#getEmployeeByNomLikeIgnoreCase(String)}
     */
    @Test
    void testGetEmployeeByNomLikeIgnoreCase3() {
        EmployeeService employeeService = mock(EmployeeService.class);
        when(employeeService.findByNomLikeIgnoreCase((String) any())).thenReturn(new ArrayList<>());
        ResponseEntity<List<Employee>> actualEmployeeByNomLikeIgnoreCase = (new EmployeeController(employeeService))
                .getEmployeeByNomLikeIgnoreCase("Nom");
        assertTrue(actualEmployeeByNomLikeIgnoreCase.hasBody());
        assertEquals(200, actualEmployeeByNomLikeIgnoreCase.getStatusCodeValue());
        assertTrue(actualEmployeeByNomLikeIgnoreCase.getHeaders().isEmpty());
        verify(employeeService).findByNomLikeIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link EmployeeController#countAllUser()}
     */
    @Test
    void testCountAllUser() {
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.count()).thenReturn(3L);
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        ResponseEntity<Long> actualCountAllUserResult = (new EmployeeController(
                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder()))).countAllUser();
        assertEquals(3L, actualCountAllUserResult.getBody().longValue());
        assertEquals(200, actualCountAllUserResult.getStatusCodeValue());
        assertTrue(actualCountAllUserResult.getHeaders().isEmpty());
        verify(employeeRepository).count();
    }

    /**
     * Method under test: {@link EmployeeController#countAllUser()}
     */
    @Test
    void testCountAllUser3() {
        EmployeeService employeeService = mock(EmployeeService.class);
        when(employeeService.countAllEmployees()).thenReturn(3L);
        ResponseEntity<Long> actualCountAllUserResult = (new EmployeeController(employeeService)).countAllUser();
        assertEquals(3L, actualCountAllUserResult.getBody().longValue());
        assertEquals(200, actualCountAllUserResult.getStatusCodeValue());
        assertTrue(actualCountAllUserResult.getHeaders().isEmpty());
        verify(employeeService).countAllEmployees();
    }
}

