package com.Chabiba_Support.Chabiba_Support.controllers;

import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.models.Rapport;
import com.Chabiba_Support.Chabiba_Support.requests.EmployeeRequestDTO;
import com.Chabiba_Support.Chabiba_Support.requests.RapportRequestDTO;
import com.Chabiba_Support.Chabiba_Support.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/respo/employee", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.findAllEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee addedEmployee = employeeService.addEmployee(employee.getPersonne(), employee.getCin(), employee.getPersonne().getPassword(),employee.getSpeciality());
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/respo/employee/find/" + addedEmployee.getPersonne().getIdPersonne()).toUriString());
		return ResponseEntity.created(uri).body(addedEmployee);
	}
	/*@PostMapping("/add")
	public ResponseEntity<Employee> addEmployee(@RequestPart("employee") EmployeeRequestDTO employeeDTO, @RequestPart("file") MultipartFile file) {
		// Extract employee data from the DTO
		Personne personne = new Personne();
		personne.setNom(employeeDTO.getNom());
		personne.setPrenom(employeeDTO.getPrenom());
		// Set other properties of the personne entity

		Employee employee = new Employee();
		employee.setPersonne(personne);
		employee.setCin(employeeDTO.getCin());
		// Set other properties of the employee entity

		// Save the employee and personne entities
		Employee addedEmployee = employeeService.addEmployee(employee.getPersonne(), employee.getCin(), employee.getPersonne().getPassword());

		// Upload the file and set the profilPicture path
		if (file != null && !file.isEmpty()) {
			try {
				String fileName = file.getOriginalFilename();
				String filePath = "src/main/uploads/" + fileName; // Set the file path as per your requirement
				File destFile = new File(filePath);
				file.transferTo(destFile);

				// Update the profilPicture path of the associated personne entity
				addedEmployee.getPersonne().setProfilPicture(filePath);

				// Save the updated employee with the file path
				employeeService.saveEmployee(addedEmployee);
			} catch (IOException e) {
				// Handle file upload exception
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		}

		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/respo/employee/find/" + addedEmployee.getPersonne().getIdPersonne()).toUriString());
		return ResponseEntity.created(uri).body(addedEmployee);
	}*/
	/*@PostMapping("/add")
	public ResponseEntity<Employee> addEmployee(@RequestPart("employee") EmployeeRequestDTO employeeDTO,
												@RequestPart("file") MultipartFile file) {
		// Create Personne object
		Personne personne = new Personne();
		personne.setNom(employeeDTO.getNom());
		personne.setPrenom(employeeDTO.getPrenom());

		// Create Employee object
		Employee employee = new Employee();
		employee.setCin(employeeDTO.getCin());
		employee.setPersonne(personne);

		// Save Employee and handle file upload
		Employee addedEmployee = employeeService.addEmployee(employee, file);

		// Create the URI for the created resource
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/respo/employee/find/" + addedEmployee.getPersonne().getIdPersonne())
				.toUriString());

		return ResponseEntity.created(uri).body(addedEmployee);
	}*/

	@GetMapping("/find/{id}")
	public ResponseEntity<Employee> getEmployeeByIdPersonne(@PathVariable("id") Long id) {
		Employee employee = employeeService.findEmployeeByIdPersonne(id);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		Employee updatedEmployee = employeeService.updateEmployee(employee);
		return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		Employee employee = employeeService.findEmployeeByIdPersonne(id);
		employeeService.deleteEmployee(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/search/{nom}")
	public ResponseEntity<List<Employee>> getEmployeeByNomLikeIgnoreCase(@PathVariable String nom) {
		List<Employee> employees = employeeService.findByNomLikeIgnoreCase(nom);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/count")
	public ResponseEntity<Long> countAllUser() {
		long count = employeeService.countAllEmployees();
		return new ResponseEntity<>(count, HttpStatus.OK);
	}
}
