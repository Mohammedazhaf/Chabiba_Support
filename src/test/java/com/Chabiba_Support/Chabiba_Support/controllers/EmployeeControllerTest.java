package com.Chabiba_Support.Chabiba_Support.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.models.Role;
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

class EmployeeControllerTest {
    /**
     * Method under test: {@link EmployeeController#getAllEmployees()}
     */
    @Test
    void testGetAllEmployees() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

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
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EmployeeService employeeService = mock(EmployeeService.class);
        when(employeeService.findAllEmployees()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Employee>> actualAllEmployees = (new EmployeeController(employeeService)).getAllEmployees();
        assertTrue(actualAllEmployees.hasBody());
        assertEquals(200, actualAllEmployees.getStatusCodeValue());
        assertTrue(actualAllEmployees.getHeaders().isEmpty());
        verify(employeeService).findAllEmployees();
    }


    /**
     * Method under test: {@link EmployeeController#getEmployeeByIdPersonne(Long)}
     */
    @Test
    void testGetEmployeeByIdPersonne() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.findEmployeeByPersonne((Personne) any())).thenReturn(Optional.of(new Employee()));
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        when(personneRepository.findById((Long) any())).thenReturn(
                Optional.of(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire)));
        ResponseEntity<Employee> actualEmployeeByIdPersonne = (new EmployeeController(
                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder())))
                .getEmployeeByIdPersonne(1L);
        assertTrue(actualEmployeeByIdPersonne.hasBody());
        assertTrue(actualEmployeeByIdPersonne.getHeaders().isEmpty());
        assertEquals(200, actualEmployeeByIdPersonne.getStatusCodeValue());
        verify(employeeRepository).findEmployeeByPersonne((Personne) any());
        verify(personneRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link EmployeeController#getEmployeeByIdPersonne(Long)}
     */
    @Test
    void testGetEmployeeByIdPersonne3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EmployeeService employeeService = mock(EmployeeService.class);
        when(employeeService.findEmployeeByIdPersonne((Long) any())).thenReturn(new Employee());
        ResponseEntity<Employee> actualEmployeeByIdPersonne = (new EmployeeController(employeeService))
                .getEmployeeByIdPersonne(1L);
        assertTrue(actualEmployeeByIdPersonne.hasBody());
        assertTrue(actualEmployeeByIdPersonne.getHeaders().isEmpty());
        assertEquals(200, actualEmployeeByIdPersonne.getStatusCodeValue());
        verify(employeeService).findEmployeeByIdPersonne((Long) any());
    }

    /**
     * Method under test: {@link EmployeeController#updateEmployee(Employee)}
     */
    @Test
    void testUpdateEmployee2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class org.springframework.security.core.GrantedAuthority]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:734)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class org.springframework.security.core.GrantedAuthority]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:734)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `org.springframework.security.core.GrantedAuthority` (no Creators, like default constructor, exist): abstract types either need to be mapped to concrete types, have custom deserializer, or contain additional type information
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 256] (through reference chain: com.Chabiba_Support.Chabiba_Support.models.Employee["personne"]->com.Chabiba_Support.Chabiba_Support.models.Personne["authorities"]->java.util.ImmutableCollections$List12[1])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1909)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:408)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1354)
        //       at com.fasterxml.jackson.databind.deser.AbstractDeserializer.deserialize(AbstractDeserializer.java:274)
        //       at com.fasterxml.jackson.databind.deser.std.CollectionDeserializer._deserializeFromArray(CollectionDeserializer.java:359)
        //       at com.fasterxml.jackson.databind.deser.std.CollectionDeserializer.deserialize(CollectionDeserializer.java:272)
        //       at com.fasterxml.jackson.databind.deser.std.CollectionDeserializer.deserialize(CollectionDeserializer.java:28)
        //       at com.fasterxml.jackson.databind.deser.impl.SetterlessProperty.deserializeAndSet(SetterlessProperty.java:134)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:392)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.impl.MethodProperty.deserializeAndSet(MethodProperty.java:129)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:392)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:734)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)
        //   See https://diff.blue/R013 to resolve this issue.

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
     * Method under test: {@link EmployeeController#deleteEmployee(Long)}
     */
    @Test
    void testDeleteEmployee2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        Employee employee = new Employee();
        employee.setPersonne(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        Optional<Employee> ofResult = Optional.of(employee);
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        doNothing().when(employeeRepository).delete((Employee) any());
        when(employeeRepository.findEmployeeByPersonne((Personne) any())).thenReturn(ofResult);
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        doNothing().when(personneRepository).deleteById((Long) any());
        when(personneRepository.findById((Long) any())).thenReturn(
                Optional.of(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire)));
        ResponseEntity<Map<String, Boolean>> actualDeleteEmployeeResult = (new EmployeeController(
                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder()))).deleteEmployee(1L);
        assertEquals(1, actualDeleteEmployeeResult.getBody().size());
        assertTrue(actualDeleteEmployeeResult.hasBody());
        assertEquals(200, actualDeleteEmployeeResult.getStatusCodeValue());
        assertTrue(actualDeleteEmployeeResult.getHeaders().isEmpty());
        verify(employeeRepository).findEmployeeByPersonne((Personne) any());
        verify(employeeRepository).delete((Employee) any());
        verify(personneRepository, atLeast(1)).findById((Long) any());
        verify(personneRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link EmployeeController#deleteEmployee(Long)}
     */
    @Test
    void testDeleteEmployee5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EmployeeService employeeService = mock(EmployeeService.class);
        when(employeeService.findEmployeeByIdPersonne((Long) any())).thenReturn(new Employee());
        doNothing().when(employeeService).deleteEmployee((Employee) any());
        ResponseEntity<Map<String, Boolean>> actualDeleteEmployeeResult = (new EmployeeController(employeeService))
                .deleteEmployee(1L);
        assertEquals(1, actualDeleteEmployeeResult.getBody().size());
        assertTrue(actualDeleteEmployeeResult.hasBody());
        assertEquals(200, actualDeleteEmployeeResult.getStatusCodeValue());
        assertTrue(actualDeleteEmployeeResult.getHeaders().isEmpty());
        verify(employeeService).findEmployeeByIdPersonne((Long) any());
        verify(employeeService).deleteEmployee((Employee) any());
    }

    /**
     * Method under test: {@link EmployeeController#getEmployeeByNomLikeIgnoreCase(String)}
     */
    @Test
    void testGetEmployeeByNomLikeIgnoreCase() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

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
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

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
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

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
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EmployeeService employeeService = mock(EmployeeService.class);
        when(employeeService.countAllEmployees()).thenReturn(3L);
        ResponseEntity<Long> actualCountAllUserResult = (new EmployeeController(employeeService)).countAllUser();
        assertEquals(3L, actualCountAllUserResult.getBody().longValue());
        assertEquals(200, actualCountAllUserResult.getStatusCodeValue());
        assertTrue(actualCountAllUserResult.getHeaders().isEmpty());
        verify(employeeService).countAllEmployees();
    }
}

