package com.Chabiba_Support.Chabiba_Support.repositories;

import com.Chabiba_Support.Chabiba_Support.models.Personne;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonneRepositoryTest {

    @Mock
    private PersonneRepository personneRepository;

    @Test
    public void testFindByEmail() {
        // Mocking the repository behavior
        String email = "test@example.com";
        Personne personne = new Personne();
        personne.setEmail(email);
        when(personneRepository.findByEmail(email)).thenReturn(Optional.of(personne));

        // Calling the repository method
        Optional<Personne> result = personneRepository.findByEmail(email);

        // Verifying the repository method was called
        verify(personneRepository, times(1)).findByEmail(email);

        // Asserting the result
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(email, result.get().getEmail());
    }
}
