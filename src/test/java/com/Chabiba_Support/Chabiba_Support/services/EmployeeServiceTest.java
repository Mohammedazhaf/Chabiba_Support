package com.Chabiba_Support.Chabiba_Support.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



import com.Chabiba_Support.Chabiba_Support.exception.EmployeeNotFoundException;
import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.models.Role;
import com.Chabiba_Support.Chabiba_Support.repositories.EmployeeRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.*;
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PersonneRepository personneRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddEmployee() {
        // Create a Personne object
        Personne personne = new Personne();
        personne.setIdPersonne(1L);
        personne.setNom("John");
        personne.setPrenom("Doe");
        personne.setEmail("john.doe@example.com");
        personne.setNumTel("123456789");
        personne.setMotDePasse("password");
        personne.setRole(Role.client);

        // Create an Employee object
        Employee employee = new Employee(personne, "1234567890");

        // Configure the mock repository to return the saved employee
        Mockito.when(personneRepository.save(Mockito.any(Personne.class))).thenReturn(personne);
        Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);

        // Call the service method
        Employee result = employeeService.addEmployee(personne, "1234567890");

        // Verify the result
        Assertions.assertEquals(employee, result);
    }

    @Test
    void testFindAllEmployees() {
        // Create a list of employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());

        // Configure the mock repository to return the list of employees
        Mockito.when(employeeRepository.findAll()).thenReturn(employees);

        // Call the service method
        List<Employee> result = employeeService.findAllEmployees();

        // Verify the result
        Assertions.assertEquals(employees, result);
    }

    @Test
    void testDeleteEmployee() {
        // Create an employee object
        Employee employee = new Employee();
        employee.setPersonne(new Personne());
        employee.getPersonne().setIdPersonne(1L);

        // Configure the mock repository to return the employee
        Mockito.when(personneRepository.findById(employee.getPersonne().getIdPersonne())).thenReturn(Optional.of(employee.getPersonne()));
        Mockito.when(employeeRepository.findEmployeeByPersonne(employee.getPersonne())).thenReturn(Optional.of(employee));

        // Call the service method
        employeeService.deleteEmployee(employee);

        // Verify that the delete methods were called on the repositories
        Mockito.verify(employeeRepository).delete(employee);
        Mockito.verify(personneRepository).deleteById(employee.getPersonne().getIdPersonne());
    }

    @Test
    void testFindEmployeeByIdPersonne() {
        // Create a personne object
        Personne personne = new Personne();
        personne.setIdPersonne(1L);

        // Create an employee object
        Employee employee = new Employee(personne, "1234567890");

        // Configure the mock repository to return the employee
        Mockito.when(personneRepository.findById(personne.getIdPersonne())).thenReturn(Optional.of(personne));
        Mockito.when(employeeRepository.findEmployeeByPersonne(personne)).thenReturn(Optional.of(employee));

        // Call the service method
        Employee result = employeeService.findEmployeeByIdPersonne(personne.getIdPersonne());

        // Verify the result
        Assertions.assertEquals(employee, result);
    }


    @Test
    void testFindByNomLikeIgnoreCase() {
        // Prepare test data
        String nom = "John";
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());
        when(employeeRepository.findByNomLikeIgnoreCase(nom)).thenReturn(employees);

        // Call the service method
        List<Employee> result = employeeService.findByNomLikeIgnoreCase(nom);

        // Verify the repository method call
        verify(employeeRepository, times(1)).findByNomLikeIgnoreCase(nom);

        // Assertions
        assertNotNull(result);
        assertEquals(employees.size(), result.size());
    }

    @Test
    void testUpdateEmployee() {
        // Prepare test data
        Long employeeId = 1L;
        Long personneId = 2L;
        String cin = "123456";
        Employee existingEmployee = new Employee();
        existingEmployee.setIdEmployee(employeeId);
        existingEmployee.setCin(cin);
        Personne existingPersonne = new Personne();
        existingPersonne.setIdPersonne(personneId);
        existingPersonne.setNom("John");
        existingPersonne.setPrenom("Doe");
        existingPersonne.setEmail("john.doe@example.com");
        existingPersonne.setNumTel("1234567890");
        existingPersonne.setMotDePasse("password");
        existingPersonne.setRole(Role.client);
        existingEmployee.setPersonne(existingPersonne);

        Employee updatedEmployee = new Employee();
        updatedEmployee.setIdEmployee(employeeId);
        updatedEmployee.setCin("654321");
        Personne updatedPersonne = new Personne();
        updatedPersonne.setIdPersonne(personneId);
        updatedPersonne.setNom("Jane");
        updatedPersonne.setPrenom("Doe");
        updatedPersonne.setEmail("jane.doe@example.com");
        updatedPersonne.setNumTel("0987654321");
        updatedPersonne.setMotDePasse("newpassword");
        updatedPersonne.setRole(Role.client);
        updatedEmployee.setPersonne(updatedPersonne);

        // Mock the repository method calls
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));
        when(personneRepository.findById(personneId)).thenReturn(Optional.of(existingPersonne));
        when(personneRepository.save(any(Personne.class))).thenReturn(updatedPersonne);
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        // Call the service method
        Employee result = employeeService.updateEmployee(updatedEmployee);

        // Verify the repository method calls
        verify(employeeRepository, times(1)).findById(employeeId);
        verify(personneRepository, times(1)).findById(personneId);
        verify(personneRepository, times(1)).save(any(Personne.class));
        verify(employeeRepository, times(1)).save(any(Employee.class));

        // Assertions
        assertNotNull(result);
        assertEquals(updatedEmployee, result);
        assertEquals(updatedPersonne, result.getPersonne());
    }

    @Test
    void testCountAllEmployees() {
        // Prepare test data
        long expectedCount = 5L;

        // Mock the repository method call
        when(employeeRepository.count()).thenReturn(expectedCount);

        // Call the service method
        long result = employeeService.countAllEmployees();

        // Verify the repository method call
        verify(employeeRepository, times(1)).count();

        // Assertions
        assertEquals(expectedCount, result);
    }

    @Test
    void testCheckIfRoleExists() {
        // Prepare test data
        String role = "client";
        Role employeeRole = Role.client;
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());

        // Mock the repository method call
        when(employeeRepository.findEmployeeByRole(employeeRole)).thenReturn(employees);

        // Call the service method
        boolean result = employeeService.CheckIfRoleExists(role);

        // Verify the repository method call
        verify(employeeRepository, times(1)).findEmployeeByRole(employeeRole);

        // Assertions
        assertTrue(result);
    }

    // Add more test methods as needed...
}
