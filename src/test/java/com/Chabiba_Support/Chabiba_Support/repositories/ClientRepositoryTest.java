package com.Chabiba_Support.Chabiba_Support.repositories;

import com.Chabiba_Support.Chabiba_Support.models.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class ClientRepositoryTest {

    @Mock
    private ClientRepository clientRepository;

    @Test
    public void testFindClientByIdPersonne() {
        Long idPersonne = 1L;
        Client client = new Client();
        client.setIdPersonne(idPersonne);

        when(clientRepository.findClientByIdPersonne(idPersonne)).thenReturn(Optional.of(client));

        Optional<Client> result = clientRepository.findClientByIdPersonne(idPersonne);

//        assertNotEquals(Optional.of(client), result);
        assertEquals(Optional.of(client), result);
    }

    @Test
    public void testFindClientByNomEntreprise() {
        String nomEntreprise = "ABC Company";
        List<Client> clients = List.of(new Client(), new Client());

        when(clientRepository.findClientByNomEntreprise(nomEntreprise)).thenReturn(clients);

        List<Client> result = clientRepository.findClientByNomEntreprise(nomEntreprise);

        assertEquals(clients, result);
    }

    @Test
    public void testFindByNomLikeIgnoreCase() {
        String nom = "John";
        List<Client> clients = List.of(new Client(), new Client());

        when(clientRepository.findByNomLikeIgnoreCase(nom)).thenReturn(clients);

        List<Client> result = clientRepository.findByNomLikeIgnoreCase(nom);

        assertEquals(clients, result);
    }
}
