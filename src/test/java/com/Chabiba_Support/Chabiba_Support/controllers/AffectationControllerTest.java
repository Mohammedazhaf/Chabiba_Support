package com.Chabiba_Support.Chabiba_Support.controllers;

import com.Chabiba_Support.Chabiba_Support.models.Affectation;
import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.requests.AffectationRequestDTO;
import com.Chabiba_Support.Chabiba_Support.services.AffectationService;
import com.Chabiba_Support.Chabiba_Support.services.DemandeService;
import com.Chabiba_Support.Chabiba_Support.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class AffectationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AffectationService affectationService;

    @MockBean
    private DemandeService demandeService;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void testGetAllAffectations() throws Exception {
        List<Affectation> affectations = new ArrayList<>(); // Create sample affectations

        when(affectationService.getAll()).thenReturn(affectations);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/affectation/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(print());
    }

    @Test
    void testCreateAffectation() throws Exception {
        AffectationRequestDTO affectationDTO = new AffectationRequestDTO();
        affectationDTO.setIdEmployee(1L);
        affectationDTO.setIdDemande(1L);
        affectationDTO.setMission("Sample Mission");
        affectationDTO.setDelaiDate(new Date(2023,5,6));

        Employee employee = new Employee(); // Create a sample employee
        Demande demande = new Demande(); // Create a sample demande

        when(employeeService.findEmployeeById(1L)).thenReturn(employee);
        when(demandeService.findById(1L)).thenReturn(demande);
        when(affectationService.saveAffectation(any(Affectation.class))).thenReturn(new Affectation());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/affectation/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idEmployee\": 1, \"idDemande\": 1, \"mission\": \"Sample Mission\", \"delaiDate\": \"2023-06-30\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("The affectation was created"))
                .andDo(print());
    }

    @Test
    void testUpdateAffectation() throws Exception {
        AffectationRequestDTO affectationDTO = new AffectationRequestDTO();
        affectationDTO.setIdEmployee(1L);
        affectationDTO.setIdDemande(1L);
        affectationDTO.setMission("Updated Mission");
        affectationDTO.setDelaiDate(new Date(2023,5,6));

        Employee employee = new Employee(); // Create a sample employee
        Demande demande = new Demande(); // Create a sample demande
        Affectation affectation = new Affectation(); // Create a sample affectation

        when(affectationService.getAffectationById(1L)).thenReturn(affectation);
        when(employeeService.findEmployeeById(1L)).thenReturn(employee);
        when(demandeService.findById(1L)).thenReturn(demande);
        when(affectationService.saveAffectation(any(Affectation.class))).thenReturn(affectation);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/affectation/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idEmployee\": 1, \"idDemande\": 1, \"mission\": \"Updated Mission\", \"delaiDate\": \"2023-07-15\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("The affectation has updated"))
                .andDo(print());
    }
}
