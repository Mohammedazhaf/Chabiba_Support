package com.Chabiba_Support.Chabiba_Support.controllers;

import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.services.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< Updated upstream
import org.springframework.security.crypto.password.PasswordEncoder;
=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
	@Autowired
	private PasswordEncoder passwordEncoder;
=======
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
		String uploadDirectory = "src/main/uploads/avatars";
=======
		String uploadDirectory = System.getProperty("user.dir") + "/uploads/avatars/";
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
	@GetMapping("/document/{filename}")
	public ResponseEntity<Resource> getDocument(@PathVariable String filename) throws IOException, MalformedURLException {
		String uploadDirectory = System.getProperty("user.dir") + "/uploads/documents/";
		Path filePath = Paths.get(uploadDirectory, filename);
		Resource resource = new UrlResource(filePath.toUri());

		if (resource.exists()) {
			return ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_PDF) // Set the appropriate content type
					.body(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/rapport-document/{filename}")
	public ResponseEntity<Resource> getRapportDocument(@PathVariable String filename) throws IOException, MalformedURLException {
		String uploadDirectory = System.getProperty("user.dir") + "/uploads/rapport-documents/";
		Path filePath = Paths.get(uploadDirectory, filename);
		Resource resource = new UrlResource(filePath.toUri());

		if (resource.exists()) {
			return ResponseEntity.ok()
					.contentType(MediaType.ALL) // Set the appropriate content type
					.body(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
		personne.setMotDePasse(passwordEncoder.encode(personne.getMotDePasse()));
=======
>>>>>>> Stashed changes
		Personne updatedPersonne = personneService.updatePersonne(personne);
		return new ResponseEntity<>(updatedPersonne, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePersonne(@PathVariable("id") Long id) {
		personneService.deletePersonne(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
