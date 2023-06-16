package com.Chabiba_Support.Chabiba_Support.controllers;

import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.services.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/personnes")
public class PersonneController {
	private final PersonneService personneService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public PersonneController(PersonneService personneService) {
		this.personneService = personneService;
	}

	@GetMapping
	public ResponseEntity<List<Personne>> getAllPersonnes() {
		List<Personne> personnes = personneService.getAllPersonnes();
		return new ResponseEntity<>(personnes, HttpStatus.OK);
	}
	@GetMapping("/image/{filename}")
	public ResponseEntity<Resource> getImage(@PathVariable String filename) throws IOException, MalformedURLException {
		String uploadDirectory = "src/main/uploads/avatars";
		Path filePath = Paths.get(uploadDirectory, filename);
		Resource resource = new UrlResource(filePath.toUri());

		if (resource.exists()) {
			return ResponseEntity.ok()
					.contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type
					.body(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<Personne> getPersonneById(@PathVariable("id") Long id) {
		Optional<Personne> personne = personneService.getPersonneById(id);
		return personne.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<Personne> createPersonne(@RequestBody Personne personne) {
		Personne createdPersonne = personneService.createPersonne(personne);
		return new ResponseEntity<>(createdPersonne, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Personne> updatePersonne(@PathVariable("id") Long id, @RequestBody Personne personne) {
		personne.setIdPersonne(id);
		personne.setMotDePasse(passwordEncoder.encode(personne.getMotDePasse()));
		Personne updatedPersonne = personneService.updatePersonne(personne);
		return new ResponseEntity<>(updatedPersonne, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePersonne(@PathVariable("id") Long id) {
		personneService.deletePersonne(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
