package com.Chabiba_Support.Chabiba_Support.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.models.Role;
import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PersonneService.class})
@ExtendWith(SpringExtension.class)
class PersonneServiceTest {
    @MockBean
    private PersonneRepository personneRepository;

    @Autowired
    private PersonneService personneService;

    /**
     * Method under test: {@link PersonneService#getAllPersonnes()}
     */
    @Test
    void testGetAllPersonnes() {
        ArrayList<Personne> personneList = new ArrayList<>();
        when(personneRepository.findAll()).thenReturn(personneList);
        List<Personne> actualAllPersonnes = personneService.getAllPersonnes();
        assertSame(personneList, actualAllPersonnes);
        assertTrue(actualAllPersonnes.isEmpty());
        verify(personneRepository).findAll();
    }

    /**
     * Method under test: {@link PersonneService#getPersonneById(Long)}
     */
    @Test
    void testGetPersonneById() {
        Optional<Personne> ofResult = Optional
                .of(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        when(personneRepository.findById((Long) any())).thenReturn(ofResult);
        Optional<Personne> actualPersonneById = personneService.getPersonneById(1L);
        assertSame(ofResult, actualPersonneById);
        assertTrue(actualPersonneById.isPresent());
        verify(personneRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PersonneService#createPersonne(Personne)}
     */
    @Test
    void testCreatePersonne() {
        Personne personne = new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire);

        when(personneRepository.save((Personne) any())).thenReturn(personne);
        assertSame(personne, personneService
                .createPersonne(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire)));
        verify(personneRepository).save((Personne) any());
    }

    /**
     * Method under test: {@link PersonneService#updatePersonne(Personne)}
     */
    @Test
    void testUpdatePersonne() {
        Personne personne = new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire);

        when(personneRepository.save((Personne) any())).thenReturn(personne);
        assertSame(personne, personneService
                .updatePersonne(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire)));
        verify(personneRepository).save((Personne) any());
    }

    /**
     * Method under test: {@link PersonneService#deletePersonne(Long)}
     */
    @Test
    void testDeletePersonne() {
        doNothing().when(personneRepository).deleteById((Long) any());
        personneService.deletePersonne(1L);
        verify(personneRepository).deleteById((Long) any());
    }
}

