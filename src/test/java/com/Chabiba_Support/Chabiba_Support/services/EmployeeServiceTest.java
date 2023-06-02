package com.Chabiba_Support.Chabiba_Support.services;

import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.repositories.EmployeeRepository;
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

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddEmployee() {
        // Create a sample employee
        Employee employee = new Employee("John Doe");
        employee.setNom("John Doe");

        // Configure the mock repository
        when(employeeRepository.save(employee)).thenReturn(employee);

        // Call the service method
        Employee savedEmployee = employeeService.addEmployee(employee);

        // Verify the repository method was called and returned the expected result
        verify(employeeRepository, times(1)).save(employee);
        assertEquals(employee, savedEmployee);
    }

    @Test
    public void testFindAllEmployees() {
        // Create a list of sample employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John Doe"));
        employees.add(new Employee("Jane Smith"));

        // Configure the mock repository
        when(employeeRepository.findAll()).thenReturn(employees);

        // Call the service method
        List<Employee> result = employeeService.findAllEmployees();

        // Verify the repository method was called and returned the expected result
        verify(employeeRepository, times(1)).findAll();
        assertEquals(employees, result);
    }

    @Test
    public void testFindEmployeeByIdPersonne() {
        // Create a sample employee
        long idPersonne = 1;
        Employee employee = new Employee("John Doe");
        employee.setIdPersonne(idPersonne);

        // Configure the mock repository
        when(employeeRepository.findEmployeeByIdPersonne(idPersonne)).thenReturn(Optional.of(employee));

        // Call the service method
        Employee result = employeeService.findEmployeeByIdPersonne(idPersonne);

        // Verify the repository method was called and returned the expected result
        verify(employeeRepository, times(1)).findEmployeeByIdPersonne(idPersonne);
        assertEquals(employee, result);
    }

    @Test
    public void testDeleteEmployee() {
        // Create a sample employee
        Employee employee = new Employee("John Doe");
        employee.setNom("John Doe");

        // Call the service method
        employeeService.deleteEmployee(employee);

        // Verify the repository method was called
        verify(employeeRepository, times(1)).delete(employee);
    }


}
