package com.Chabiba_Support.Chabiba_Support.auth;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
public class PersonneToken extends Personne{
	private Personne personne;
	private AuthenticationResponse token;
	public PersonneToken(Personne personne,AuthenticationResponse token){
		super(personne);
		this.token = token;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public AuthenticationResponse getToken() {
		return token;
	}

	public void setToken(AuthenticationResponse token) {
		this.token = token;
	}
}
