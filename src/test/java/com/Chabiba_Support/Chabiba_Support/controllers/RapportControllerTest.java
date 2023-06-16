package com.Chabiba_Support.Chabiba_Support.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.Chabiba_Support.Chabiba_Support.exception.RapportNotFoundException;
import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.models.Rapport;
import com.Chabiba_Support.Chabiba_Support.repositories.ClientRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.DemandeRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.EmployeeRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;
import com.Chabiba_Support.Chabiba_Support.requests.RapportRequestDTO;
import com.Chabiba_Support.Chabiba_Support.services.DemandeService;
import com.Chabiba_Support.Chabiba_Support.services.EmployeeService;
import com.Chabiba_Support.Chabiba_Support.services.RapportService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
@ExtendWith(MockitoExtension.class)
class RapportControllerTest {


    @Mock
    private RapportService rapportService;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private DemandeService demandeService;

    @InjectMocks
    private RapportController rapportController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createRapport_shouldSaveRapportAndReturnSuccessResponse() throws IOException {
        // Mocking the dependencies and request data
        MultipartFile file = new MockMultipartFile("file", "test.txt", MediaType.TEXT_PLAIN_VALUE, "file content".getBytes());
        String rapportJson = "{\"date\":\"2023-06-13T12:00:00Z\", \"contenu\":\"Rapport content\", \"idEmployee\":1, \"idDemande\":1}";

        RapportRequestDTO rapportDTO = new RapportRequestDTO();
        rapportDTO.setDate(new Date(2023,6,13));
        rapportDTO.setContenu("Rapport content");
        rapportDTO.setIdEmployee(1L);
        rapportDTO.setIdDemande(1L);

        Employee employee = new Employee();
        employee.setIdEmployee(1L);

        Demande demande = new Demande();
        demande.setIdDemande(1L);

        // Call the method under test
        ResponseEntity<String> response = rapportController.createRapport(file, rapportJson);

        // Verify the interactions
        verify(rapportService, times(0)).saveRapport(any(Rapport.class));

        // Assertions
        assertEquals(ResponseEntity.ok("Rapport created successfully"), response);
    }




    /**
     * Method under test: {@link RapportController#getAllRapports()}
     */
    @Test
    void testGetAllRapports2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        RapportService rapportService = mock(RapportService.class);
        ArrayList<Rapport> rapportList = new ArrayList<>();
        when(rapportService.getAllRapports()).thenReturn(rapportList);
        ModelMapper modelMapper = new ModelMapper();
        DemandeService demandeService = new DemandeService(mock(DemandeRepository.class), mock(PersonneRepository.class),
                mock(ClientRepository.class));

        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        List<Rapport> actualAllRapports = (new RapportController(rapportService, modelMapper, demandeService,
                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder()))).getAllRapports();
        assertSame(rapportList, actualAllRapports);
        assertTrue(actualAllRapports.isEmpty());
        verify(rapportService).getAllRapports();
    }

    /**
     * Method under test: {@link RapportController#getRapportsByDemandeId(Long)}
     */
    @Test
    void testGetRapportsByDemandeId2() throws RapportNotFoundException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        RapportService rapportService = mock(RapportService.class);
        ArrayList<Rapport> rapportList = new ArrayList<>();
        when(rapportService.getRapportsByDemandeId((Long) any())).thenReturn(rapportList);
        ModelMapper modelMapper = new ModelMapper();
        DemandeService demandeService = new DemandeService(mock(DemandeRepository.class), mock(PersonneRepository.class),
                mock(ClientRepository.class));

        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        List<Rapport> actualRapportsByDemandeId = (new RapportController(rapportService, modelMapper, demandeService,
                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder())))
                .getRapportsByDemandeId(1L);
        assertSame(rapportList, actualRapportsByDemandeId);
        assertTrue(actualRapportsByDemandeId.isEmpty());
        verify(rapportService).getRapportsByDemandeId((Long) any());
    }

    /**
     * Method under test: {@link RapportController#getRapportByIdRapport(Long)}
     */
    @Test
    void testGetRapportByIdRapport2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        RapportService rapportService = mock(RapportService.class);
        Rapport rapport = new Rapport();
        when(rapportService.getRapportByIdRapport((Long) any())).thenReturn(rapport);
        ModelMapper modelMapper = new ModelMapper();
        DemandeService demandeService = new DemandeService(mock(DemandeRepository.class), mock(PersonneRepository.class),
                mock(ClientRepository.class));

        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        assertSame(rapport,
                (new RapportController(rapportService, modelMapper, demandeService,
                        new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder())))
                        .getRapportByIdRapport(1L));
        verify(rapportService).getRapportByIdRapport((Long) any());
    }

//    /**
//     * Method under test: {@link RapportController#createRapport(MultipartFile, String)}
//     */
//    @Test
//    void testCreateRapport() throws IOException {
//        // TODO: Complete this test.
//        //   Diffblue AI was unable to find a test
//
//        RapportService rapportService = new RapportService();
//        ModelMapper modelMapper = new ModelMapper();
//        DemandeService demandeService = new DemandeService(mock(DemandeRepository.class), mock(PersonneRepository.class),
//                mock(ClientRepository.class));
//
//        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
//        PersonneRepository personneRepository = mock(PersonneRepository.class);
//        RapportController rapportController = new RapportController(rapportService, modelMapper, demandeService,
//                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder()));
//        rapportController.createRapport(
//                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), "Rapport Json");
//    }
//
//    /**
//     * Method under test: {@link RapportController#createRapport(MultipartFile, String)}
//     */
//    @Test
//    void testCreateRapport2() throws IOException {
//        // TODO: Complete this test.
//        //   Diffblue AI was unable to find a test
//
//        RapportService rapportService = new RapportService();
//        ModelMapper modelMapper = new ModelMapper();
//        DemandeService demandeService = new DemandeService(mock(DemandeRepository.class), mock(PersonneRepository.class),
//                mock(ClientRepository.class));
//
//        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
//        PersonneRepository personneRepository = mock(PersonneRepository.class);
//        RapportController rapportController = new RapportController(rapportService, modelMapper, demandeService,
//                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder()));
//        rapportController
//                .createRapport(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), null);
//    }
//
//    /**
//     * Method under test: {@link RapportController#createRapport(MultipartFile, String)}
//     */
//    @Test
//    void testCreateRapport3() throws IOException {
//        // TODO: Complete this test.
//        //   Diffblue AI was unable to find a test
//
//        RapportService rapportService = new RapportService();
//        ModelMapper modelMapper = new ModelMapper();
//        DemandeService demandeService = new DemandeService(mock(DemandeRepository.class), mock(PersonneRepository.class),
//                mock(ClientRepository.class));
//
//        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
//        PersonneRepository personneRepository = mock(PersonneRepository.class);
//        RapportController rapportController = new RapportController(rapportService, modelMapper, demandeService,
//                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder()));
//        rapportController
//                .createRapport(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), "foo");
//    }

//    /**
//     * Method under test: {@link RapportController#createRapport(MultipartFile, String)}
//     */
//    @Test
//    void testCreateRapport4() throws IOException {
//        // TODO: Complete this test.
//        //   Diffblue AI was unable to find a test
//
//        RapportService rapportService = new RapportService();
//        ModelMapper modelMapper = new ModelMapper();
//        DemandeService demandeService = new DemandeService(mock(DemandeRepository.class), mock(PersonneRepository.class),
//                mock(ClientRepository.class));
//
//        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
//        PersonneRepository personneRepository = mock(PersonneRepository.class);
//        RapportController rapportController = new RapportController(rapportService, modelMapper, demandeService,
//                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder()));
//        rapportController
//                .createRapport(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), "42");
//    }

//    /**
//     * Method under test: {@link RapportController#createRapport(MultipartFile, String)}
//     */
//    @Test
//    void testCreateRapport5() throws IOException {
//        // TODO: Complete this test.
//        //   Diffblue AI was unable to find a test
//
//        RapportService rapportService = new RapportService();
//        ModelMapper modelMapper = new ModelMapper();
//        DemandeService demandeService = new DemandeService(mock(DemandeRepository.class), mock(PersonneRepository.class),
//                mock(ClientRepository.class));
//
//        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
//        PersonneRepository personneRepository = mock(PersonneRepository.class);
//        RapportController rapportController = new RapportController(rapportService, modelMapper, demandeService,
//                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder()));
//        rapportController
//                .createRapport(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), "");
//    }


    /**
     * Method under test: {@link RapportController#deleteRapport(Long)}
     */
    @Test
    void testDeleteRapport2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        RapportService rapportService = mock(RapportService.class);
        doNothing().when(rapportService).deleteRapport((Long) any());
        ModelMapper modelMapper = new ModelMapper();
        DemandeService demandeService = new DemandeService(mock(DemandeRepository.class), mock(PersonneRepository.class),
                mock(ClientRepository.class));

        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        ResponseEntity<?> actualDeleteRapportResult = (new RapportController(rapportService, modelMapper, demandeService,
                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder()))).deleteRapport(1L);
        assertNull(actualDeleteRapportResult.getBody());
        assertEquals(200, actualDeleteRapportResult.getStatusCodeValue());
        assertTrue(actualDeleteRapportResult.getHeaders().isEmpty());
        verify(rapportService).deleteRapport((Long) any());
    }

    /**
     * Method under test: {@link RapportController#deleteRapport(Long)}
     */
    @Test
    void testDeleteRapport3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        RapportService rapportService = mock(RapportService.class);
        doThrow(new RapportNotFoundException("An error occurred")).when(rapportService).deleteRapport((Long) any());
        ModelMapper modelMapper = new ModelMapper();
        DemandeService demandeService = new DemandeService(mock(DemandeRepository.class), mock(PersonneRepository.class),
                mock(ClientRepository.class));

        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        ResponseEntity<?> actualDeleteRapportResult = (new RapportController(rapportService, modelMapper, demandeService,
                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder()))).deleteRapport(1L);
        assertNull(actualDeleteRapportResult.getBody());
        assertEquals(404, actualDeleteRapportResult.getStatusCodeValue());
        assertTrue(actualDeleteRapportResult.getHeaders().isEmpty());
        verify(rapportService).deleteRapport((Long) any());
    }

//    /**
//     * Method under test: {@link RapportController#updateRapport(MultipartFile, String, long)}
//     */
//    @Test
//    void testUpdateRapport() throws IOException {
//        // TODO: Complete this test.
//        //   Diffblue AI was unable to find a test
//
//        RapportService rapportService = new RapportService();
//        ModelMapper modelMapper = new ModelMapper();
//        DemandeService demandeService = new DemandeService(mock(DemandeRepository.class), mock(PersonneRepository.class),
//                mock(ClientRepository.class));
//
//        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
//        PersonneRepository personneRepository = mock(PersonneRepository.class);
//        RapportController rapportController = new RapportController(rapportService, modelMapper, demandeService,
//                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder()));
//        rapportController.updateRapport(
//                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), "Rapport Json", 1L);
//    }

//    /**
//     * Method under test: {@link RapportController#updateRapport(MultipartFile, String, long)}
//     */
//    @Test
//    void testUpdateRapport2() throws IOException {
//        // TODO: Complete this test.
//        //   Diffblue AI was unable to find a test
//
//        RapportService rapportService = mock(RapportService.class);
//        when(rapportService.getRapportByIdRapport((Long) any())).thenReturn(new Rapport());
//        ModelMapper modelMapper = new ModelMapper();
//        DemandeService demandeService = new DemandeService(mock(DemandeRepository.class), mock(PersonneRepository.class),
//                mock(ClientRepository.class));
//
//        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
//        PersonneRepository personneRepository = mock(PersonneRepository.class);
//        RapportController rapportController = new RapportController(rapportService, modelMapper, demandeService,
//                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder()));
//        rapportController.updateRapport(
//                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), "Rapport Json", 1L);
//    }

//    /**
//     * Method under test: {@link RapportController#updateRapport(MultipartFile, String, long)}
//     */
//    @Test
//    void testUpdateRapport3() throws IOException {
//        // TODO: Complete this test.
//        //   Diffblue AI was unable to find a test
//
//        RapportService rapportService = mock(RapportService.class);
//        when(rapportService.getRapportByIdRapport((Long) any())).thenReturn(new Rapport());
//        ModelMapper modelMapper = new ModelMapper();
//        DemandeService demandeService = new DemandeService(mock(DemandeRepository.class), mock(PersonneRepository.class),
//                mock(ClientRepository.class));
//
//        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
//        PersonneRepository personneRepository = mock(PersonneRepository.class);
//        RapportController rapportController = new RapportController(rapportService, modelMapper, demandeService,
//                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder()));
//        rapportController.updateRapport(
//                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), "42", 1L);
//    }

    /**
     * Method under test: {@link RapportController#updateRapport(MultipartFile, String, long)}
     */
//    @Test
//    void testUpdateRapport4() throws IOException {
//        // TODO: Complete this test.
//        //   Diffblue AI was unable to find a test
//
//        RapportService rapportService = mock(RapportService.class);
//        when(rapportService.getRapportByIdRapport((Long) any())).thenReturn(new Rapport());
//        ModelMapper modelMapper = new ModelMapper();
//        DemandeService demandeService = new DemandeService(mock(DemandeRepository.class), mock(PersonneRepository.class),
//                mock(ClientRepository.class));
//
//        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
//        PersonneRepository personneRepository = mock(PersonneRepository.class);
//        RapportController rapportController = new RapportController(rapportService, modelMapper, demandeService,
//                new EmployeeService(employeeRepository, personneRepository, new BCryptPasswordEncoder()));
//        rapportController
//                .updateRapport(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), "", 1L);
//    }
}

