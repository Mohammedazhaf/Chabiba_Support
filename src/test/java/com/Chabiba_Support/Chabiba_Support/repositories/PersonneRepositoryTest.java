package com.Chabiba_Support.Chabiba_Support.repositories;

import com.Chabiba_Support.Chabiba_Support.models.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class PersonneRepositoryTest {

    @Autowired
    private PersonneRepository personneRepository;

    @Test
    public void testFindByEmail() {
        // Create test data
        Personne personne = new Personne();
        personne.setEmail("test@example.com");
        personne.setMotDePasse("example");
        personne.setRole(Role.client);
        personneRepository.save(personne);

        // Call the repository method
        Personne foundPersonne = personneRepository.findByEmail("test@example.com").orElse(null);

        // Assert the result
        assertNotNull(foundPersonne);
        assertEquals("test@example.com", foundPersonne.getEmail());
    }
}