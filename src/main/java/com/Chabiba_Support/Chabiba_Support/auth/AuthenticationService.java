package com.Chabiba_Support.Chabiba_Support.auth;

import com.Chabiba_Support.Chabiba_Support.config.JwtService;
import com.Chabiba_Support.Chabiba_Support.models.Client;
import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.models.Role;
import com.Chabiba_Support.Chabiba_Support.repositories.ClientRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.EmployeeRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private  final PersonneRepository personneRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        Personne personne1 = new Personne();
        if(request.getRole().equals("Responsable")){
            var personne = Personne.builder()
                    .nom(request.getNom())
                    .prenom(request.getPrenom())
                    .numTel(request.getNumTel())
                    .email(request.getEmail())
                    .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
                    .role(Role.Responsable)
                    .build();
            personne1 = personne;
            Personne savedPersonne = personneRepository.save(personne);
            Employee employee = new Employee(savedPersonne, request.getCin());
            employeeRepository.save(employee);
        }
        else if(request.getRole().equals("Secretaire")){
            var personne = Personne.builder()
                    .nom(request.getNom())
                    .prenom(request.getPrenom())
                    .numTel(request.getNumTel())
                    .email(request.getEmail())
                    .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
                    .role(Role.Secretaire)
                    .build();
            personne1 = personne;
            Personne savedPersonne = personneRepository.save(personne);
            Employee employee = new Employee(savedPersonne, request.getCin());
            employeeRepository.save(employee);
        }
        else if(request.getRole().equals("Technicien")){
            var personne = Personne.builder()
                    .nom(request.getNom())
                    .prenom(request.getPrenom())
                    .numTel(request.getNumTel())
                    .email(request.getEmail())
                    .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
                    .role(Role.Technicien)
                    .build();
            personne1 = personne;
            Personne savedPersonne = personneRepository.save(personne);
            Employee employee = new Employee(savedPersonne, request.getCin());
            employeeRepository.save(employee);
        }else if(request.getRole().equals("client")){
            var personne = Personne.builder()
                    .nom(request.getNom())
                    .prenom(request.getPrenom())
                    .numTel(request.getNumTel())
                    .email(request.getEmail())
                    .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
                    .role(Role.client)
                    .build();
            personne1 = personne;
            Personne savedPersonne = personneRepository.save(personne);
            Client client = new Client(request.getNomEntreprise(),savedPersonne);
            clientRepository.save(client);
        }
          var jwtToken = jwtService.generateToken(personne1);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       request.getEmail(),
                       request.getMotDePasse()
               )
       );
       var personne = personneRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(personne);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
