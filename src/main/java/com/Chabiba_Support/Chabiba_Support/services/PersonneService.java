package com.Chabiba_Support.Chabiba_Support.services;

import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {
	private final PersonneRepository personneRepository;

	@Autowired
	public PersonneService(PersonneRepository personneRepository) {
		this.personneRepository = personneRepository;
	}

	public List<Personne> getAllPersonnes() {
		return personneRepository.findAll();
	}

	public Optional<Personne> getPersonneById(Long id) {
		return personneRepository.findById(id);
	}

	public Personne createPersonne(Personne personne) {
		return personneRepository.save(personne);
	}

	public Personne updatePersonne(Personne personne) {
		return personneRepository.save(personne);
	}

	public void deletePersonne(Long id) {
		personneRepository.deleteById(id);
	}
}
