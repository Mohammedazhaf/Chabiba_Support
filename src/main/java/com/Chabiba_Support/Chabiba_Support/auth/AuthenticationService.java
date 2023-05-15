package com.Chabiba_Support.Chabiba_Support.auth;

import com.Chabiba_Support.Chabiba_Support.config.JwtService;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.models.Role;
import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Chabiba_Support.Chabiba_Support.auth.PersonneToken;
@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private PersonneToken personneToken;
    private  final PersonneRepository repository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {

        var personne = Personne.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .numTel(request.getNumTel())
                .email(request.getEmail())
                .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
                .role(Role.client)
                .build();
            repository.save(personne);

          var jwtToken = jwtService.generateToken(personne);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public PersonneToken authenticate(AuthenticationRequest request) {

       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       request.getEmail(),
                       request.getMotDePasse()
               )
       );
       var personne = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(personne);
		personneToken = new PersonneToken(personne,AuthenticationResponse.builder()
				.token(jwtToken)
				.build());
		personneToken.setIdPersonne(personne.getIdPersonne());
		personneToken.setMotDePasse("");
        return
				personneToken;
    }
}
