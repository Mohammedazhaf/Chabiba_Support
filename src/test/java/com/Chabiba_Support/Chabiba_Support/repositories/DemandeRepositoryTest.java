package com.Chabiba_Support.Chabiba_Support.repositories;

import com.Chabiba_Support.Chabiba_Support.models.Client;
import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.models.Etat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DemandeRepositoryTest {

    @Mock
    private DemandeRepository demandeRepository;

    @Test
    public void testFindByEtat() {
        Etat etat = Etat.EnAttente;
        Demande demande = new Demande();
        demande.setEtat(etat);

        when(demandeRepository.findByEtat(etat)).thenReturn(Collections.singletonList(demande));

        List<Demande> result = demandeRepository.findByEtat(etat);

        assertEquals(Collections.singletonList(demande), result);
    }

    @Test
    public void testFindByClientIdPersonne() {
        Long idPersonne = 1L;
        Demande demande = new Demande();
        Client client = new Client("ABC Company");
//        demande.setClientIdPersonne(idPersonne);
        demande.setClient(client);
        when(demandeRepository.findByClientIdPersonne(idPersonne)).thenReturn(Collections.singletonList(demande));

        List<Demande> result = demandeRepository.findByClientIdPersonne(idPersonne);

        assertEquals(Collections.singletonList(demande), result);
    }
}
