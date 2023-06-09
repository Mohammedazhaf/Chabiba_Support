package com.Chabiba_Support.Chabiba_Support.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Chabiba_Support.Chabiba_Support.config.JwtService;
import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.models.Role;
import com.Chabiba_Support.Chabiba_Support.repositories.ClientRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.EmployeeRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthenticationService.class})
@ExtendWith(SpringExtension.class)
class AuthenticationServiceTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private PersonneRepository personneRepository;

    /**
     * Method under test: {@link AuthenticationService#register(RegisterRequest)}
     */
    @Test
    void testRegister() {
        when(jwtService.generateToken((UserDetails) any())).thenReturn("ABC123");
        assertEquals("ABC123", authenticationService.register(new RegisterRequest("Nom", "Prenom", "jane.doe@example.org",
                "Num Tel", "Mot De Passe", "Role", "Nom Entreprise", "Cin")).getToken());
        verify(jwtService).generateToken((UserDetails) any());
    }

    /**
     * Method under test: {@link AuthenticationService#register(RegisterRequest)}
     */
    @Test
    void testRegister2() {
        when(personneRepository.save((Personne) any()))
                .thenReturn(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        when(employeeRepository.save((Employee) any())).thenReturn(new Employee());
        when(jwtService.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        assertEquals("ABC123",
                authenticationService
                        .register(new RegisterRequest("Nom", "Prenom", "jane.doe@example.org", "Num Tel", "Mot De Passe",
                                "Responsable", "Nom Entreprise", "Cin"))
                        .getToken());
        verify(personneRepository).save((Personne) any());
        verify(employeeRepository).save((Employee) any());
        verify(jwtService).generateToken((UserDetails) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link AuthenticationService#register(RegisterRequest)}
     */
    @Test
    void testRegister3() {
        when(personneRepository.save((Personne) any()))
                .thenReturn(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        when(employeeRepository.save((Employee) any())).thenReturn(new Employee());
        when(jwtService.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        authenticationService.register(new RegisterRequest());
    }

    /**
     * Method under test: {@link AuthenticationService#register(RegisterRequest)}
     */
    @Test
    void testRegister4() {

        when(personneRepository.save((Personne) any()))
                .thenReturn(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        when(employeeRepository.save((Employee) any())).thenReturn(new Employee());
        when(jwtService.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        authenticationService.register(null);
    }

    /**
     * Method under test: {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate() throws AuthenticationException {
        when(personneRepository.findByEmail((String) any()))
                .thenReturn(Optional.of(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire)));
        when(jwtService.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        assertEquals("ABC123",
                authenticationService.authenticate(new AuthenticationRequest("jane.doe@example.org", "Mot De Passe"))
                        .getToken());
        verify(personneRepository).findByEmail((String) any());
        verify(jwtService).generateToken((UserDetails) any());
        verify(authenticationManager).authenticate((Authentication) any());
    }

    /**
     * Method under test: {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate2() throws AuthenticationException {  when(personneRepository.findByEmail((String) any())).thenReturn(Optional.empty());
        when(jwtService.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        authenticationService.authenticate(new AuthenticationRequest("jane.doe@example.org", "Mot De Passe"));
    }

    /**
     * Method under test: {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate3() throws AuthenticationException {
        when(personneRepository.findByEmail((String) any())).thenReturn(
                Optional.of(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire)));
        when(jwtService.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        authenticationService.authenticate(null);
    }
}

