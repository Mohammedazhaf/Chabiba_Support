package com.Chabiba_Support.Chabiba_Support.controllers;

import com.Chabiba_Support.Chabiba_Support.models.*;
import com.Chabiba_Support.Chabiba_Support.requests.RapportRequestDTO;
import com.Chabiba_Support.Chabiba_Support.services.DemandeService;
import com.Chabiba_Support.Chabiba_Support.services.EmployeeService;
import com.Chabiba_Support.Chabiba_Support.services.RapportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RapportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RapportService rapportService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private DemandeService demandeService;

    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRapports() throws Exception {
        // Mock the RapportService's getAllRapports method
        List<Rapport> mockRapports = Arrays.asList(new Rapport(), new Rapport());
        when(rapportService.getAllRapports()).thenReturn(mockRapports);

        // Perform the GET request
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/rapport/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Extract the response body and convert it to a list of Rapport objects
        String responseBody = mvcResult.getResponse().getContentAsString();
        List<Rapport> responseRapports = new ObjectMapper().readValue(responseBody, List.class);

        // Assert that the response contains the expected number of Rapport objects
        assertThat(responseRapports).hasSize(mockRapports.size());
    }

    @Test
    public void testCreateRapport() throws Exception {
        // Mock the dependencies
        when(rapportService.saveRapport(any())).thenReturn(new Rapport());
        when(employeeService.findEmployeeById(any())).thenReturn(new Employee());
        when(demandeService.findById(any())).thenReturn(new Demande());

        // Create a RapportRequestDTO
        RapportRequestDTO rapportRequestDTO = new RapportRequestDTO();
        rapportRequestDTO.setDate(new Date(2023,2,6));
        rapportRequestDTO.setContenu("Test Rapport");
        rapportRequestDTO.setIdEmployee(1L);
        rapportRequestDTO.setIdDemande(1L);

        // Create a sample file
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        // Convert RapportRequestDTO to JSON
        String rapportJson = new ObjectMapper().writeValueAsString(rapportRequestDTO);

        // Perform the POST request
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.multipart("/api/rapport/add")
                        .file(file)
                        .param("rapport", rapportJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Assert the response
        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isEqualTo("Rapport created successfully");

        // Verify that the saveRapport method was called
        verify(rapportService, times(1)).saveRapport(any());
    }

    @Test
    public void testDeleteRapport() throws Exception {

        // Create a rapport with ID 1L and insert it into the database
        Rapport rapport = new Rapport();
        rapport.setIdRapport(1L);
        rapport.setContenu("Rapport content");
        rapport.setDate(new Date(2023,6,1));
        Employee employee = new Employee();
        employee.setIdEmployee(1L);
        employee.setCin("FA21232");
        Personne personne = new Personne(1L,"personne","personne","2131","email@email.com","motdepasse", Role.Responsable);
        employee.setPersonne(personne);
        Client client = new Client(1L,"Entreprise",personne);
        Demande demande = new Demande(1L,ResponseResponsable.pending,false,new Date(2023,6,1),"titre",Etat.EnCours,Service.Developpement,"100","message",Type.Urgent,client);


        rapport.setEmployee(employee);
        rapport.setDemande(demande);

        rapportService.saveRapport(rapport);

        // Perform the DELETE request
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/rapport/delete/{idRapport}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Assert the response
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);

        // Verify that the deleteRapport method was called
        verify(rapportService, times(1)).deleteRapport(1L);
    }

    @Test
    public void testUpdateRapport() throws Exception {
        // Mock the dependencies
        when(rapportService.getRapportByIdRapport(any())).thenReturn(new Rapport());

        // Create a RapportRequestDTO
        RapportRequestDTO rapportRequestDTO = new RapportRequestDTO();
        rapportRequestDTO.setDate(new Date(2023,6,1));
        rapportRequestDTO.setContenu("Updated Rapport");

        // Create a sample file
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, Updated World!".getBytes()
        );

        // Convert RapportRequestDTO to JSON
        String rapportJson = new ObjectMapper().writeValueAsString(rapportRequestDTO);

        // Perform the PUT request
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.multipart("/api/rapport/update/{id}", 1L)
                        .file(file)
                        .param("rapport", rapportJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Assert the response
        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).isEqualTo("Demande updated successfully");

        // Verify that the getRapportByIdRapport and saveRapport methods were called
        verify(rapportService, times(1)).getRapportByIdRapport(1L);
        verify(rapportService, times(1)).saveRapport(any());
    }
}
