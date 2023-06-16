package com.Chabiba_Support.Chabiba_Support.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Chabiba_Support.Chabiba_Support.models.Client;
import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.models.Role;
import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;
import com.Chabiba_Support.Chabiba_Support.services.PersonneService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PersonneController.class})
@ExtendWith(SpringExtension.class)
class PersonneControllerTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersonneController personneController;

    @MockBean
    private PersonneService personneService;

    /**
     * Method under test: {@link PersonneController#getAllPersonnes()}
     */
    @Test
    void testGetAllPersonnes() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PersonneRepository personneRepository = mock(PersonneRepository.class);
        when(personneRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Personne>> actualAllPersonnes = (new PersonneController(
                new PersonneService(personneRepository))).getAllPersonnes();
        assertTrue(actualAllPersonnes.hasBody());
        assertEquals(200, actualAllPersonnes.getStatusCodeValue());
        assertTrue(actualAllPersonnes.getHeaders().isEmpty());
        verify(personneRepository).findAll();
    }

    /**
     * Method under test: {@link PersonneController#getAllPersonnes()}
     */
    @Test
    void testGetAllPersonnes3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PersonneService personneService = mock(PersonneService.class);
        when(personneService.getAllPersonnes()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Personne>> actualAllPersonnes = (new PersonneController(personneService)).getAllPersonnes();
        assertTrue(actualAllPersonnes.hasBody());
        assertEquals(200, actualAllPersonnes.getStatusCodeValue());
        assertTrue(actualAllPersonnes.getHeaders().isEmpty());
        verify(personneService).getAllPersonnes();
    }

    /**
     * Method under test: {@link PersonneController#getImage(String)}
     */
    @Test
    void testGetImage() throws IOException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ResponseEntity<Resource> actualImage = (new PersonneController(
                new PersonneService(mock(PersonneRepository.class)))).getImage("foo.txt");
        assertNull(actualImage.getBody());
        assertEquals(404, actualImage.getStatusCodeValue());
        assertTrue(actualImage.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link PersonneController#getPersonneById(Long)}
     */
    @Test
    void testGetPersonneById() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PersonneRepository personneRepository = mock(PersonneRepository.class);
        when(personneRepository.findById((Long) any())).thenReturn(
                Optional.of(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire)));
        ResponseEntity<Personne> actualPersonneById = (new PersonneController(new PersonneService(personneRepository)))
                .getPersonneById(1L);
        assertTrue(actualPersonneById.hasBody());
        assertTrue(actualPersonneById.getHeaders().isEmpty());
        assertEquals(200, actualPersonneById.getStatusCodeValue());
        verify(personneRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PersonneController#getPersonneById(Long)}
     */
    @Test
    void testGetPersonneById2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PersonneRepository personneRepository = mock(PersonneRepository.class);
        when(personneRepository.findById((Long) any())).thenReturn(Optional.empty());
        ResponseEntity<Personne> actualPersonneById = (new PersonneController(new PersonneService(personneRepository)))
                .getPersonneById(1L);
        assertNull(actualPersonneById.getBody());
        assertEquals(404, actualPersonneById.getStatusCodeValue());
        assertTrue(actualPersonneById.getHeaders().isEmpty());
        verify(personneRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PersonneController#getPersonneById(Long)}
     */
    @Test
    void testGetPersonneById4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PersonneService personneService = mock(PersonneService.class);
        when(personneService.getPersonneById((Long) any())).thenReturn(
                Optional.of(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire)));
        ResponseEntity<Personne> actualPersonneById = (new PersonneController(personneService)).getPersonneById(1L);
        assertTrue(actualPersonneById.hasBody());
        assertTrue(actualPersonneById.getHeaders().isEmpty());
        assertEquals(200, actualPersonneById.getStatusCodeValue());
        verify(personneService).getPersonneById((Long) any());
    }

    /**
     * Method under test: {@link PersonneController#createPersonne(Personne)}
     */
    @Test
    void testCreatePersonne() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.JsonMappingException: Cannot invoke "com.Chabiba_Support.Chabiba_Support.models.Personne.getIdPersonne()" because "this.personne" is null (through reference chain: com.Chabiba_Support.Chabiba_Support.models.Personne["client"]->com.Chabiba_Support.Chabiba_Support.models.Client["personneId"])
        //       at com.fasterxml.jackson.databind.JsonMappingException.wrapWithPath(JsonMappingException.java:402)
        //       at com.fasterxml.jackson.databind.JsonMappingException.wrapWithPath(JsonMappingException.java:361)
        //       at com.fasterxml.jackson.databind.ser.std.StdSerializer.wrapAndThrow(StdSerializer.java:316)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:782)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:733)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4624)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3869)
        //   java.lang.NullPointerException: Cannot invoke "com.Chabiba_Support.Chabiba_Support.models.Personne.getIdPersonne()" because "this.personne" is null
        //       at com.Chabiba_Support.Chabiba_Support.models.Client.getPersonneId(Client.java:34)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:689)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:733)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4624)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3869)
        //   See https://diff.blue/R013 to resolve this issue.

        PersonneRepository personneRepository = mock(PersonneRepository.class);
        when(personneRepository.save((Personne) any()))
                .thenReturn(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        PersonneController personneController = new PersonneController(new PersonneService(personneRepository));
        Personne personne = new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire);

        ResponseEntity<Personne> actualCreatePersonneResult = personneController.createPersonne(personne);
        assertEquals(personne, actualCreatePersonneResult.getBody());
        assertTrue(actualCreatePersonneResult.getHeaders().isEmpty());
        assertEquals(201, actualCreatePersonneResult.getStatusCodeValue());
        verify(personneRepository).save((Personne) any());
    }


    /**
     * Method under test: {@link PersonneController#createPersonne(Personne)}
     */
    @Test
    void testCreatePersonne3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.JsonMappingException: Cannot invoke "com.Chabiba_Support.Chabiba_Support.models.Personne.getIdPersonne()" because "this.personne" is null (through reference chain: com.Chabiba_Support.Chabiba_Support.models.Personne["client"]->com.Chabiba_Support.Chabiba_Support.models.Client["personneId"])
        //       at com.fasterxml.jackson.databind.JsonMappingException.wrapWithPath(JsonMappingException.java:402)
        //       at com.fasterxml.jackson.databind.JsonMappingException.wrapWithPath(JsonMappingException.java:361)
        //       at com.fasterxml.jackson.databind.ser.std.StdSerializer.wrapAndThrow(StdSerializer.java:316)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:782)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:733)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4624)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3869)
        //   java.lang.NullPointerException: Cannot invoke "com.Chabiba_Support.Chabiba_Support.models.Personne.getIdPersonne()" because "this.personne" is null
        //       at com.Chabiba_Support.Chabiba_Support.models.Client.getPersonneId(Client.java:34)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:689)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:733)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4624)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3869)
        //   See https://diff.blue/R013 to resolve this issue.

        PersonneService personneService = mock(PersonneService.class);
        when(personneService.createPersonne((Personne) any()))
                .thenReturn(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        PersonneController personneController = new PersonneController(personneService);
        Personne personne = new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire);

        ResponseEntity<Personne> actualCreatePersonneResult = personneController.createPersonne(personne);
        assertEquals(personne, actualCreatePersonneResult.getBody());
        assertTrue(actualCreatePersonneResult.getHeaders().isEmpty());
        assertEquals(201, actualCreatePersonneResult.getStatusCodeValue());
        verify(personneService).createPersonne((Personne) any());
    }

    /**
     * Method under test: {@link PersonneController#deletePersonne(Long)}
     */
    @Test
    void testDeletePersonne() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PersonneRepository personneRepository = mock(PersonneRepository.class);
        doNothing().when(personneRepository).deleteById((Long) any());
        ResponseEntity<Void> actualDeletePersonneResult = (new PersonneController(
                new PersonneService(personneRepository))).deletePersonne(1L);
        assertNull(actualDeletePersonneResult.getBody());
        assertEquals(204, actualDeletePersonneResult.getStatusCodeValue());
        assertTrue(actualDeletePersonneResult.getHeaders().isEmpty());
        verify(personneRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link PersonneController#deletePersonne(Long)}
     */
    @Test
    void testDeletePersonne3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        PersonneService personneService = mock(PersonneService.class);
        doNothing().when(personneService).deletePersonne((Long) any());
        ResponseEntity<Void> actualDeletePersonneResult = (new PersonneController(personneService)).deletePersonne(1L);
        assertNull(actualDeletePersonneResult.getBody());
        assertEquals(204, actualDeletePersonneResult.getStatusCodeValue());
        assertTrue(actualDeletePersonneResult.getHeaders().isEmpty());
        verify(personneService).deletePersonne((Long) any());
    }

//    /**
//     * Method under test: {@link PersonneController#updatePersonne(Long, Personne)}
//     */
//    @Test
//    void testUpdatePersonne() throws Exception {
//        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.put("/personnes/{id}", 1L)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        Personne personne = new Personne();
//        personne.setClient(new Client());
//        personne.setEmail("jane.doe@example.org");
//        personne.setEmployee(new Employee());
//        personne.setIdPersonne(1L);
//        personne.setMotDePasse("Mot De Passe");
//        personne.setNom("Nom");
//        personne.setNumTel("Num Tel");
//        personne.setPrenom("Prenom");
//        personne.setProfilPicture("Profil Picture");
//        personne.setRole(Role.Secretaire);
//        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
//                .content((new ObjectMapper()).writeValueAsString(personne));
//        MockMvcBuilders.standaloneSetup(personneController).build().perform(requestBuilder);
//    }
}

