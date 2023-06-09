package com.Chabiba_Support.Chabiba_Support.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Chabiba_Support.Chabiba_Support.config.JwtService;
import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.models.Role;
import com.Chabiba_Support.Chabiba_Support.repositories.ClientRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.EmployeeRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class AuthenticationControllerTest {
    /**
     * Method under test: {@link AuthenticationController#register(AuthenticationRequest)}
     */
    @Test
    void testRegister() {
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        ClientRepository clientRepository = mock(ClientRepository.class);
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        JwtService jwtService = new JwtService();
        AuthenticationController authenticationController = new AuthenticationController(
                new AuthenticationService(personneRepository, clientRepository, employeeRepository, passwordEncoder, jwtService,
                        new ProviderManager(new ArrayList<>())));
        authenticationController.register(new AuthenticationRequest("jane.doe@example.org", "Mot De Passe"));
    }

    /**
     * Method under test: {@link AuthenticationController#register(AuthenticationRequest)}
     */
    @Test
    void testRegister2() {
        ArrayList<AuthenticationProvider> authenticationProviderList = new ArrayList<>();
        authenticationProviderList.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(authenticationProviderList);
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        ClientRepository clientRepository = mock(ClientRepository.class);
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationController authenticationController = new AuthenticationController(
                new AuthenticationService(personneRepository, clientRepository, employeeRepository, passwordEncoder,
                        new JwtService(), authenticationManager));
        authenticationController.register(new AuthenticationRequest("jane.doe@example.org", "Mot De Passe"));
    }

    /**
     * Method under test: {@link AuthenticationController#register(AuthenticationRequest)}
     */
    @Test
    void testRegister3() {
        AuthenticationService authenticationService = mock(AuthenticationService.class);
        when(authenticationService.authenticate((AuthenticationRequest) any()))
                .thenReturn(new AuthenticationResponse("ABC123"));
        AuthenticationController authenticationController = new AuthenticationController(authenticationService);
        ResponseEntity<AuthenticationResponse> actualRegisterResult = authenticationController
                .register(new AuthenticationRequest("jane.doe@example.org", "Mot De Passe"));
        assertTrue(actualRegisterResult.hasBody());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
        assertEquals(200, actualRegisterResult.getStatusCodeValue());
        verify(authenticationService).authenticate((AuthenticationRequest) any());
    }

    /**
     * Method under test: {@link AuthenticationController#register(RegisterRequest)}
     */
    @Test
    void testRegister4() {
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        ClientRepository clientRepository = mock(ClientRepository.class);
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        JwtService jwtService = new JwtService();
        AuthenticationController authenticationController = new AuthenticationController(
                new AuthenticationService(personneRepository, clientRepository, employeeRepository, passwordEncoder,
                        jwtService, new ProviderManager(new ArrayList<>())));
        authenticationController.register(new RegisterRequest("Nom", "Prenom", "jane.doe@example.org", "Num Tel",
                "Mot De Passe", "Role", "Nom Entreprise", "Cin"));
    }

    /**
     * Method under test: {@link AuthenticationController#register(RegisterRequest)}
     */
    @Test
    void testRegister5() {
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        ClientRepository clientRepository = mock(ClientRepository.class);
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        JwtService jwtService = new JwtService();
        AuthenticationController authenticationController = new AuthenticationController(
                new AuthenticationService(personneRepository, clientRepository, employeeRepository, passwordEncoder,
                        jwtService, new ProviderManager(new ArrayList<>())));

        RegisterRequest registerRequest = new RegisterRequest("Nom", "Prenom", "jane.doe@example.org", "Num Tel",
                "Mot De Passe", "Role", "Nom Entreprise", "Cin");
        registerRequest.setRole((String) "Responsable");
        authenticationController.register(registerRequest);
    }

    /**
     * Method under test: {@link AuthenticationController#register(RegisterRequest)}
     */
    @Test
    void testRegister6() {
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        ClientRepository clientRepository = mock(ClientRepository.class);
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        JwtService jwtService = new JwtService();
        AuthenticationController authenticationController = new AuthenticationController(
                new AuthenticationService(personneRepository, clientRepository, employeeRepository, passwordEncoder,
                        jwtService, new ProviderManager(new ArrayList<>())));

        RegisterRequest registerRequest = new RegisterRequest("Nom", "Prenom", "jane.doe@example.org", "Num Tel",
                "Mot De Passe", "Role", "Nom Entreprise", "Cin");
        registerRequest.setRole((String) "Secretaire");
        authenticationController.register(registerRequest);
    }

    /**
     * Method under test: {@link AuthenticationController#register(RegisterRequest)}
     */
    @Test
    void testRegister7() {
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        ClientRepository clientRepository = mock(ClientRepository.class);
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        JwtService jwtService = new JwtService();
        AuthenticationController authenticationController = new AuthenticationController(
                new AuthenticationService(personneRepository, clientRepository, employeeRepository, passwordEncoder,
                        jwtService, new ProviderManager(new ArrayList<>())));

        RegisterRequest registerRequest = new RegisterRequest("Nom", "Prenom", "jane.doe@example.org", "Num Tel",
                "Mot De Passe", "Role", "Nom Entreprise", "Cin");
        registerRequest.setRole((String) "Technicien");
        authenticationController.register(registerRequest);
    }

    /**
     * Method under test: {@link AuthenticationController#register(RegisterRequest)}
     */
    @Test
    void testRegister8() {
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        ClientRepository clientRepository = mock(ClientRepository.class);
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        JwtService jwtService = new JwtService();
        AuthenticationController authenticationController = new AuthenticationController(
                new AuthenticationService(personneRepository, clientRepository, employeeRepository, passwordEncoder,
                        jwtService, new ProviderManager(new ArrayList<>())));

        RegisterRequest registerRequest = new RegisterRequest("Nom", "Prenom", "jane.doe@example.org", "Num Tel",
                "Mot De Passe", "Role", "Nom Entreprise", "Cin");
        registerRequest.setRole((String) "client");
        authenticationController.register(registerRequest);
    }

    /**
     * Method under test: {@link AuthenticationController#register(RegisterRequest)}
     */
    @Test
    void testRegister9() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ArrayList<AuthenticationProvider> authenticationProviderList = new ArrayList<>();
        authenticationProviderList.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(authenticationProviderList);
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        ClientRepository clientRepository = mock(ClientRepository.class);
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationController authenticationController = new AuthenticationController(
                new AuthenticationService(personneRepository, clientRepository, employeeRepository, passwordEncoder,
                        new JwtService(), authenticationManager));
        ResponseEntity<AuthenticationResponse> actualRegisterResult = authenticationController
                .register(new RegisterRequest("Nom", "Prenom", "jane.doe@example.org", "Num Tel", "Mot De Passe", "Role",
                        "Nom Entreprise", "Cin"));
        assertTrue(actualRegisterResult.hasBody());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
        assertEquals(200, actualRegisterResult.getStatusCodeValue());
    }

    /**
     * Method under test: {@link AuthenticationController#register(RegisterRequest)}
     */
    @Test
    void testRegister10() {
        AuthenticationController authenticationController = new AuthenticationController(null);
        authenticationController.register(new RegisterRequest("Nom", "Prenom", "jane.doe@example.org", "Num Tel",
                "Mot De Passe", "Role", "Nom Entreprise", "Cin"));
    }

    /**
     * Method under test: {@link AuthenticationController#register(RegisterRequest)}
     */
    @Test
    void testRegister11() {
        AuthenticationService authenticationService = mock(AuthenticationService.class);
        when(authenticationService.register((RegisterRequest) any())).thenReturn(new AuthenticationResponse("ABC123"));
        AuthenticationController authenticationController = new AuthenticationController(authenticationService);
        ResponseEntity<AuthenticationResponse> actualRegisterResult = authenticationController
                .register(new RegisterRequest("Nom", "Prenom", "jane.doe@example.org", "Num Tel", "Mot De Passe", "Role",
                        "Nom Entreprise", "Cin"));
        assertTrue(actualRegisterResult.hasBody());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
        assertEquals(200, actualRegisterResult.getStatusCodeValue());
        verify(authenticationService).register((RegisterRequest) any());
    }

    /**
     * Method under test: {@link AuthenticationController#register(RegisterRequest)}
     */
    @Test
    void testRegister12() {
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        when(personneRepository.save((Personne) any()))
                .thenReturn(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.save((Employee) any())).thenReturn(new Employee());

        ArrayList<AuthenticationProvider> authenticationProviderList = new ArrayList<>();
        authenticationProviderList.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(authenticationProviderList);
        ClientRepository clientRepository = mock(ClientRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationController authenticationController = new AuthenticationController(
                new AuthenticationService(personneRepository, clientRepository, employeeRepository, passwordEncoder,
                        new JwtService(), authenticationManager));

        RegisterRequest registerRequest = new RegisterRequest("Nom", "Prenom", "jane.doe@example.org", "Num Tel",
                "Mot De Passe", "Role", "Nom Entreprise", "Cin");
        registerRequest.setRole((String) "Responsable");
        ResponseEntity<AuthenticationResponse> actualRegisterResult = authenticationController.register(registerRequest);
        assertTrue(actualRegisterResult.hasBody());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
        assertEquals(200, actualRegisterResult.getStatusCodeValue());
        verify(personneRepository).save((Personne) any());
        verify(employeeRepository).save((Employee) any());
    }

    /**
     * Method under test: {@link AuthenticationController#register(RegisterRequest)}
     */
    @Test
    void testRegister13() {
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        when(personneRepository.save((Personne) any()))
                .thenReturn(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.save((Employee) any())).thenReturn(new Employee());

        ArrayList<AuthenticationProvider> authenticationProviderList = new ArrayList<>();
        authenticationProviderList.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(authenticationProviderList);
        ClientRepository clientRepository = mock(ClientRepository.class);
        AuthenticationController authenticationController = new AuthenticationController(
                new AuthenticationService(personneRepository, clientRepository, employeeRepository,
                        new BCryptPasswordEncoder(), null, authenticationManager));

        RegisterRequest registerRequest = new RegisterRequest("Nom", "Prenom", "jane.doe@example.org", "Num Tel",
                "Mot De Passe", "Role", "Nom Entreprise", "Cin");
        registerRequest.setRole((String) "Responsable");
        authenticationController.register(registerRequest);
    }

    /**
     * Method under test: {@link AuthenticationController#register(RegisterRequest)}
     */
    @Test
    void testRegister14() {
        PersonneRepository personneRepository = mock(PersonneRepository.class);
        when(personneRepository.save((Personne) any()))
                .thenReturn(new Personne(1L, "Personne", "Personne1", "foo", "S1", "Motdepasse", Role.Secretaire));
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.save((Employee) any())).thenReturn(new Employee());
        JwtService jwtService = mock(JwtService.class);
        when(jwtService.generateToken((UserDetails) any())).thenReturn("ABC123");

        ArrayList<AuthenticationProvider> authenticationProviderList = new ArrayList<>();
        authenticationProviderList.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(authenticationProviderList);
        ClientRepository clientRepository = mock(ClientRepository.class);
        AuthenticationController authenticationController = new AuthenticationController(
                new AuthenticationService(personneRepository, clientRepository, employeeRepository,
                        new BCryptPasswordEncoder(), jwtService, authenticationManager));

        RegisterRequest registerRequest = new RegisterRequest("Nom", "Prenom", "jane.doe@example.org", "Num Tel",
                "Mot De Passe", "Role", "Nom Entreprise", "Cin");
        registerRequest.setRole((String) "Responsable");
        ResponseEntity<AuthenticationResponse> actualRegisterResult = authenticationController.register(registerRequest);
        assertTrue(actualRegisterResult.hasBody());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
        assertEquals(200, actualRegisterResult.getStatusCodeValue());
        assertEquals("ABC123", actualRegisterResult.getBody().getToken());
        verify(personneRepository).save((Personne) any());
        verify(employeeRepository).save((Employee) any());
        verify(jwtService).generateToken((UserDetails) any());
    }
}

