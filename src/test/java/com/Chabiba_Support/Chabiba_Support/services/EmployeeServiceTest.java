package com.Chabiba_Support.Chabiba_Support.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Chabiba_Support.Chabiba_Support.exception.EmployeeNotFoundException;
import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.models.Role;
import com.Chabiba_Support.Chabiba_Support.repositories.EmployeeRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmployeeService.class})
@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {
    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private PersonneRepository personneRepository;

    /**
     * Method under test: {@link EmployeeService#addEmployee(Personne, String, String, String)}
     */
    @Test
    void testAddEmployee() {
        Employee employee = new Employee();
        when(employeeRepository.save((Employee) any())).thenReturn(employee);
        when(personneRepository.save((Personne) any()))
                .thenReturn(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        Personne personne = new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire);

        assertSame(employee, employeeService.addEmployee(personne, "Cin", "iloveyou", "Speciality"));
        verify(employeeRepository).save((Employee) any());
        verify(personneRepository).save((Personne) any());
        verify(passwordEncoder).encode((CharSequence) any());
        assertEquals("secret", personne.getPassword());
    }

    /**
     * Method under test: {@link EmployeeService#addEmployee(Personne, String, String, String)}
     */
    @Test
    void testAddEmployee2() {
        when(employeeRepository.save((Employee) any())).thenReturn(new Employee());
        when(personneRepository.save((Personne) any()))
                .thenReturn(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        when(passwordEncoder.encode((CharSequence) any())).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class,
                () -> employeeService.addEmployee(
                        new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire), "Cin", "iloveyou",
                        "Speciality"));
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link EmployeeService#findAllEmployees()}
     */
    @Test
    void testFindAllEmployees() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> actualFindAllEmployeesResult = employeeService.findAllEmployees();
        assertSame(employeeList, actualFindAllEmployeesResult);
        assertTrue(actualFindAllEmployeesResult.isEmpty());
        verify(employeeRepository).findAll();
    }

    /**
     * Method under test: {@link EmployeeService#findAllEmployees()}
     */
    @Test
    void testFindAllEmployees2() {
        when(employeeRepository.findAll()).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> employeeService.findAllEmployees());
        verify(employeeRepository).findAll();
    }

    /**
     * Method under test: {@link EmployeeService#deleteEmployee(Employee)}
     */
    @Test
    void testDeleteEmployee2() {
        doNothing().when(employeeRepository).delete((Employee) any());
        doNothing().when(personneRepository).deleteById((Long) any());
        when(personneRepository.findById((Long) any())).thenReturn(
                Optional.of(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire)));

        Employee employee = new Employee();
        employee.setPersonne(new Personne());
        employeeService.deleteEmployee(employee);
        verify(employeeRepository).delete((Employee) any());
        verify(personneRepository).findById((Long) any());
        verify(personneRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link EmployeeService#deleteEmployee(Employee)}
     */
    @Test
    void testDeleteEmployee3() {
        doNothing().when(employeeRepository).delete((Employee) any());
        doThrow(new RuntimeException()).when(personneRepository).deleteById((Long) any());
        when(personneRepository.findById((Long) any())).thenReturn(
                Optional.of(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire)));

        Employee employee = new Employee();
        employee.setPersonne(new Personne());
        assertThrows(RuntimeException.class, () -> employeeService.deleteEmployee(employee));
        verify(employeeRepository).delete((Employee) any());
        verify(personneRepository).findById((Long) any());
        verify(personneRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link EmployeeService#deleteEmployee(Employee)}
     */
    @Test
    void testDeleteEmployee4() {
        doNothing().when(employeeRepository).delete((Employee) any());
        Personne personne = mock(Personne.class);
        when(personne.getIdPersonne()).thenThrow(new RuntimeException());
        Optional<Personne> ofResult = Optional.of(personne);
        doNothing().when(personneRepository).deleteById((Long) any());
        when(personneRepository.findById((Long) any())).thenReturn(ofResult);

        Employee employee = new Employee();
        employee.setPersonne(new Personne());
        assertThrows(RuntimeException.class, () -> employeeService.deleteEmployee(employee));
        verify(employeeRepository).delete((Employee) any());
        verify(personneRepository).findById((Long) any());
        verify(personne).getIdPersonne();
    }

    /**
     * Method under test: {@link EmployeeService#findEmployeeByIdPersonne(Long)}
     */
    @Test
    void testFindEmployeeByIdPersonne() {
        Employee employee = new Employee();
        when(employeeRepository.findEmployeeByPersonne((Personne) any())).thenReturn(Optional.of(employee));
        when(personneRepository.findById((Long) any())).thenReturn(
                Optional.of(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire)));
        assertSame(employee, employeeService.findEmployeeByIdPersonne(1L));
        verify(employeeRepository).findEmployeeByPersonne((Personne) any());
        verify(personneRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link EmployeeService#findEmployeeByIdPersonne(Long)}
     */
    @Test
    void testFindEmployeeByIdPersonne2() {
        when(employeeRepository.findEmployeeByPersonne((Personne) any())).thenReturn(Optional.of(new Employee()));
        when(personneRepository.findById((Long) any())).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> employeeService.findEmployeeByIdPersonne(1L));
        verify(personneRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link EmployeeService#findEmployeeByIdPersonne(Long)}
     */
    @Test
    void testFindEmployeeByIdPersonne3() {
        when(employeeRepository.findEmployeeByPersonne((Personne) any())).thenReturn(Optional.empty());
        when(personneRepository.findById((Long) any())).thenReturn(
                Optional.of(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire)));
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployeeByIdPersonne(1L));
        verify(employeeRepository).findEmployeeByPersonne((Personne) any());
        verify(personneRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link EmployeeService#findByNomLikeIgnoreCase(String)}
     */
    @Test
    void testFindByNomLikeIgnoreCase() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        when(employeeRepository.findByNomLikeIgnoreCase((String) any())).thenReturn(employeeList);
        List<Employee> actualFindByNomLikeIgnoreCaseResult = employeeService.findByNomLikeIgnoreCase("Nom");
        assertSame(employeeList, actualFindByNomLikeIgnoreCaseResult);
        assertTrue(actualFindByNomLikeIgnoreCaseResult.isEmpty());
        verify(employeeRepository).findByNomLikeIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link EmployeeService#findByNomLikeIgnoreCase(String)}
     */
    @Test
    void testFindByNomLikeIgnoreCase2() {
        when(employeeRepository.findByNomLikeIgnoreCase((String) any())).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> employeeService.findByNomLikeIgnoreCase("Nom"));
        verify(employeeRepository).findByNomLikeIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link EmployeeService#updateEmployee(Employee)}
     */
    @Test
    void testUpdateEmployee2() {
        Employee employee = new Employee();
        when(employeeRepository.save((Employee) any())).thenReturn(employee);
        when(employeeRepository.findById((Long) any())).thenReturn(Optional.of(new Employee()));
        when(personneRepository.save((Personne) any()))
                .thenReturn(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        when(personneRepository.findById((Long) any())).thenReturn(
                Optional.of(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire)));
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        Employee employee1 = new Employee();
        employee1.setPersonne(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        assertSame(employee, employeeService.updateEmployee(employee1));
        verify(employeeRepository).save((Employee) any());
        verify(employeeRepository).findById((Long) any());
        verify(personneRepository).save((Personne) any());
        verify(personneRepository).findById((Long) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link EmployeeService#updateEmployee(Employee)}
     */
    @Test
    void testUpdateEmployee3() {
        when(employeeRepository.save((Employee) any())).thenReturn(new Employee());
        when(employeeRepository.findById((Long) any())).thenReturn(Optional.of(new Employee()));
        when(personneRepository.save((Personne) any()))
                .thenReturn(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        when(personneRepository.findById((Long) any())).thenReturn(
                Optional.of(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire)));
        when(passwordEncoder.encode((CharSequence) any())).thenThrow(new RuntimeException());

        Employee employee = new Employee();
        employee.setPersonne(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        assertThrows(RuntimeException.class, () -> employeeService.updateEmployee(employee));
        verify(employeeRepository).findById((Long) any());
        verify(personneRepository).findById((Long) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link EmployeeService#updateEmployee(Employee)}
     */
    @Test
    void testUpdateEmployee4() {
        Employee employee = mock(Employee.class);
        doThrow(new EmployeeNotFoundException("An error occurred")).when(employee).setCin((String) any());
        doThrow(new EmployeeNotFoundException("An error occurred")).when(employee).setPersonne((Personne) any());
        Optional<Employee> ofResult = Optional.of(employee);
        when(employeeRepository.save((Employee) any())).thenReturn(new Employee());
        when(employeeRepository.findById((Long) any())).thenReturn(ofResult);
        when(personneRepository.save((Personne) any()))
                .thenReturn(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        when(personneRepository.findById((Long) any())).thenReturn(
                Optional.of(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire)));
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        Employee employee1 = new Employee();
        employee1.setPersonne(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.updateEmployee(employee1));
        verify(employeeRepository).findById((Long) any());
        verify(employee).setCin((String) any());
    }

    /**
     * Method under test: {@link EmployeeService#updateEmployee(Employee)}
     */
    @Test
    void testUpdateEmployee5() {
        when(employeeRepository.save((Employee) any())).thenReturn(new Employee());
        when(employeeRepository.findById((Long) any())).thenReturn(Optional.empty());
        new EmployeeNotFoundException("An error occurred");
        new EmployeeNotFoundException("An error occurred");
        when(personneRepository.save((Personne) any()))
                .thenReturn(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        when(personneRepository.findById((Long) any())).thenReturn(
                Optional.of(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire)));
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        Employee employee = new Employee();
        employee.setPersonne(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        assertThrows(RuntimeException.class, () -> employeeService.updateEmployee(employee));
        verify(employeeRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link EmployeeService#countAllEmployees()}
     */
    @Test
    void testCountAllEmployees() {
        when(employeeRepository.count()).thenReturn(3L);
        assertEquals(3L, employeeService.countAllEmployees());
        verify(employeeRepository).count();
    }

    /**
     * Method under test: {@link EmployeeService#countAllEmployees()}
     */
    @Test
    void testCountAllEmployees2() {
        when(employeeRepository.count()).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> employeeService.countAllEmployees());
        verify(employeeRepository).count();
    }

    /**
     * Method under test: {@link EmployeeService#CheckIfRoleExists(String)}
     */
    @Test
    void testCheckIfRoleExists() {
        employeeService.CheckIfRoleExists("Responsable");
    }


    /**
     * Method under test: {@link EmployeeService#findEmployeeById(Long)}
     */
    @Test
    void testFindEmployeeById() {
        Employee employee = new Employee();
        when(employeeRepository.findEmployeeById((Long) any())).thenReturn(employee);
        assertSame(employee, employeeService.findEmployeeById(1L));
        verify(employeeRepository).findEmployeeById((Long) any());
    }

    /**
     * Method under test: {@link EmployeeService#findEmployeeById(Long)}
     */
    @Test
    void testFindEmployeeById2() {
        when(employeeRepository.findEmployeeById((Long) any())).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> employeeService.findEmployeeById(1L));
        verify(employeeRepository).findEmployeeById((Long) any());
    }
}

//package com.Chabiba_Support.Chabiba_Support.services;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//
//import com.Chabiba_Support.Chabiba_Support.exception.EmployeeNotFoundException;
//import com.Chabiba_Support.Chabiba_Support.models.Employee;
//import com.Chabiba_Support.Chabiba_Support.models.Personne;
//import com.Chabiba_Support.Chabiba_Support.models.Role;
//import com.Chabiba_Support.Chabiba_Support.repositories.EmployeeRepository;
//import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
//import static org.mockito.Mockito.*;
//class EmployeeServiceTest {
//
//    @Mock
//    private EmployeeRepository employeeRepository;
//
//    @Mock
//    private PersonneRepository personneRepository;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    private EmployeeService employeeService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testAddEmployee() {
//        // Create a Personne object
//        Personne personne = new Personne();
//        personne.setIdPersonne(1L);
//        personne.setNom("John");
//        personne.setPrenom("Doe");
//        personne.setEmail("john.doe@example.com");
//        personne.setNumTel("123456789");
//        personne.setMotDePasse("password");
//        personne.setRole(Role.client);
//
//        // Create an Employee object
//        Employee employee = new Employee(personne, "1234567890");
//
//        // Configure the mock repository to return the saved employee
//        Mockito.when(personneRepository.save(Mockito.any(Personne.class))).thenReturn(personne);
//        Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);
//
//        // Call the service method
//        Employee result = employeeService.addEmployee(personne, "1234567890");
//
//        // Verify the result
//        Assertions.assertEquals(employee, result);
//    }
//
//    @Test
//    void testFindAllEmployees() {
//        // Create a list of employees
//        List<Employee> employees = new ArrayList<>();
//        employees.add(new Employee());
//        employees.add(new Employee());
//
//        // Configure the mock repository to return the list of employees
//        Mockito.when(employeeRepository.findAll()).thenReturn(employees);
//
//        // Call the service method
//        List<Employee> result = employeeService.findAllEmployees();
//
//        // Verify the result
//        Assertions.assertEquals(employees, result);
//    }
//
//    @Test
//    void testDeleteEmployee() {
//        // Create an employee object
//        Employee employee = new Employee();
//        employee.setPersonne(new Personne());
//        employee.getPersonne().setIdPersonne(1L);
//
//        // Configure the mock repository to return the employee
//        Mockito.when(personneRepository.findById(employee.getPersonne().getIdPersonne())).thenReturn(Optional.of(employee.getPersonne()));
//        Mockito.when(employeeRepository.findEmployeeByPersonne(employee.getPersonne())).thenReturn(Optional.of(employee));
//
//        // Call the service method
//        employeeService.deleteEmployee(employee);
//
//        // Verify that the delete methods were called on the repositories
//        Mockito.verify(employeeRepository).delete(employee);
//        Mockito.verify(personneRepository).deleteById(employee.getPersonne().getIdPersonne());
//    }
//
//    @Test
//    void testFindEmployeeByIdPersonne() {
//        // Create a personne object
//        Personne personne = new Personne();
//        personne.setIdPersonne(1L);
//
//        // Create an employee object
//        Employee employee = new Employee(personne, "1234567890");
//
//        // Configure the mock repository to return the employee
//        Mockito.when(personneRepository.findById(personne.getIdPersonne())).thenReturn(Optional.of(personne));
//        Mockito.when(employeeRepository.findEmployeeByPersonne(personne)).thenReturn(Optional.of(employee));
//
//        // Call the service method
//        Employee result = employeeService.findEmployeeByIdPersonne(personne.getIdPersonne());
//
//        // Verify the result
//        Assertions.assertEquals(employee, result);
//    }
//
//
//    @Test
//    void testFindByNomLikeIgnoreCase() {
//        // Prepare test data
//        String nom = "John";
//        List<Employee> employees = new ArrayList<>();
//        employees.add(new Employee());
//        employees.add(new Employee());
//        when(employeeRepository.findByNomLikeIgnoreCase(nom)).thenReturn(employees);
//
//        // Call the service method
//        List<Employee> result = employeeService.findByNomLikeIgnoreCase(nom);
//
//        // Verify the repository method call
//        verify(employeeRepository, times(1)).findByNomLikeIgnoreCase(nom);
//
//        // Assertions
//        assertNotNull(result);
//        assertEquals(employees.size(), result.size());
//    }
//
//    @Test
//    void testUpdateEmployee() {
//        // Prepare test data
//        Long employeeId = 1L;
//        Long personneId = 2L;
//        String cin = "123456";
//        Employee existingEmployee = new Employee();
//        existingEmployee.setIdEmployee(employeeId);
//        existingEmployee.setCin(cin);
//        Personne existingPersonne = new Personne();
//        existingPersonne.setIdPersonne(personneId);
//        existingPersonne.setNom("John");
//        existingPersonne.setPrenom("Doe");
//        existingPersonne.setEmail("john.doe@example.com");
//        existingPersonne.setNumTel("1234567890");
//        existingPersonne.setMotDePasse("password");
//        existingPersonne.setRole(Role.client);
//        existingEmployee.setPersonne(existingPersonne);
//
//        Employee updatedEmployee = new Employee();
//        updatedEmployee.setIdEmployee(employeeId);
//        updatedEmployee.setCin("654321");
//        Personne updatedPersonne = new Personne();
//        updatedPersonne.setIdPersonne(personneId);
//        updatedPersonne.setNom("Jane");
//        updatedPersonne.setPrenom("Doe");
//        updatedPersonne.setEmail("jane.doe@example.com");
//        updatedPersonne.setNumTel("0987654321");
//        updatedPersonne.setMotDePasse("newpassword");
//        updatedPersonne.setRole(Role.client);
//        updatedEmployee.setPersonne(updatedPersonne);
//
//        // Mock the repository method calls
//        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));
//        when(personneRepository.findById(personneId)).thenReturn(Optional.of(existingPersonne));
//        when(personneRepository.save(any(Personne.class))).thenReturn(updatedPersonne);
//        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);
//
//        // Call the service method
//        Employee result = employeeService.updateEmployee(updatedEmployee);
//
//        // Verify the repository method calls
//        verify(employeeRepository, times(1)).findById(employeeId);
//        verify(personneRepository, times(1)).findById(personneId);
//        verify(personneRepository, times(1)).save(any(Personne.class));
//        verify(employeeRepository, times(1)).save(any(Employee.class));
//
//        // Assertions
//        assertNotNull(result);
//        assertEquals(updatedEmployee, result);
//        assertEquals(updatedPersonne, result.getPersonne());
//    }
//
//    @Test
//    void testCountAllEmployees() {
//        // Prepare test data
//        long expectedCount = 5L;
//
//        // Mock the repository method call
//        when(employeeRepository.count()).thenReturn(expectedCount);
//
//        // Call the service method
//        long result = employeeService.countAllEmployees();
//
//        // Verify the repository method call
//        verify(employeeRepository, times(1)).count();
//
//        // Assertions
//        assertEquals(expectedCount, result);
//    }
//
//    @Test
//    void testCheckIfRoleExists() {
//        // Prepare test data
//        String role = "client";
//        Role employeeRole = Role.client;
//        List<Employee> employees = new ArrayList<>();
//        employees.add(new Employee());
//        employees.add(new Employee());
//
//        // Mock the repository method call
//        when(employeeRepository.findEmployeeByRole(employeeRole)).thenReturn(employees);
//
//        // Call the service method
//        boolean result = employeeService.CheckIfRoleExists(role);
//
//        // Verify the repository method call
//        verify(employeeRepository, times(1)).findEmployeeByRole(employeeRole);
//
//        // Assertions
//        assertTrue(result);
//    }
//
//    // Add more test methods as needed...
//}
