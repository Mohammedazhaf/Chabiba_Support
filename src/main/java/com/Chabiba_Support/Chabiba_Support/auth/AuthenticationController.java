package com.Chabiba_Support.Chabiba_Support.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {
	private PersonneToken personneToken;
    private  final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return  ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<PersonneToken> register(
            @RequestBody AuthenticationRequest request
    ) {

       return ResponseEntity.ok(service.authenticate(request));
    }
	@GetMapping("/validateToken")
	public boolean validateToken(Authentication authentication) {
		return authentication != null && authentication.isAuthenticated();
	}
}
