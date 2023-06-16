//package com.Chabiba_Support.Chabiba_Support.controllers;
//
//
//import com.Chabiba_Support.Chabiba_Support.exception.RapportNotFoundException;
//import com.Chabiba_Support.Chabiba_Support.models.Demande;
//import com.Chabiba_Support.Chabiba_Support.models.Employee;
//import com.Chabiba_Support.Chabiba_Support.models.Rapport;
//import com.Chabiba_Support.Chabiba_Support.requests.DemandeRequestDTO;
//import com.Chabiba_Support.Chabiba_Support.requests.RapportRequestDTO;
//import com.Chabiba_Support.Chabiba_Support.services.DemandeService;
//import com.Chabiba_Support.Chabiba_Support.services.EmployeeService;
//import com.Chabiba_Support.Chabiba_Support.services.RapportService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//@RestController
//@CrossOrigin(origins = "*")
//@RequestMapping("/api/rapport")
//public class RapportController {
//
//	private final RapportService rapportService;
//	private final ModelMapper modelMapper;
//	private final DemandeService demandeService;
//	private final EmployeeService employeeService;
//
//	@Autowired
//	public RapportController(RapportService rapportService, ModelMapper modelMapper, DemandeService demandeService, EmployeeService employeeService) {
//		this.rapportService = rapportService;
//		this.modelMapper = modelMapper;
//		this.demandeService = demandeService;
//		this.employeeService = employeeService;
//	}
//
//	@GetMapping("/all")
//	public List<Rapport> getAllRapports() {
//		return rapportService.getAllRapports();
//	}
//
//	@GetMapping("/getByDemandeId/{idDemande}")
//	public List<Rapport> getRapportsByDemandeId(@PathVariable Long idDemande) {
//		return rapportService.getRapportsByDemandeId(idDemande);
//	}
//
//
//	@GetMapping("/getone/{idRapport}")
//	public Rapport getRapportByIdRapport(@PathVariable Long idRapport) {
//		return rapportService.getRapportByIdRapport(idRapport);
//	}
//
//	/*@PostMapping("/add")
//	public ResponseEntity<String> createRapport(@RequestParam("file") MultipartFile file, @RequestParam("rapport") String rapportJson) throws IOException {
//		RapportRequestDTO rapportDTO = new ObjectMapper().readValue(rapportJson, RapportRequestDTO.class);
//		Rapport rapport = modelMapper.map(rapportDTO, Rapport.class);
//		if (!file.isEmpty()) {
//			String filePath = "C:\\Users\\issam\\Desktop\\BackendNew\\uploads" + file.getOriginalFilename();
//			file.transferTo(new File(filePath));
//			rapport.setFilePath(filePath);
//		}
//		rapportService.saveRapport(rapport);
//		return ResponseEntity.ok("Rapport created successfully");
//	}*/
//
//
//	@PostMapping(value = {"/add"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	public ResponseEntity<String> createRapport(@RequestPart("file") MultipartFile file, @RequestPart("rapport") String rapportJson) throws IOException {
//		// Convert demandeJson (String) to RapportRequestDTO
//		RapportRequestDTO rapportDTO = new ObjectMapper().readValue(rapportJson, RapportRequestDTO.class);
//		Rapport rapport = new Rapport();
//		rapport.setDate(rapportDTO.getDate());
//		rapport.setContenu(rapportDTO.getContenu());
//		////Get a EMployee and Demande Objects/////////
//		Employee employee = employeeService.findEmployeeById(rapportDTO.getIdEmployee());
//		Demande demande = demandeService.findById(rapportDTO.getIdDemande());
//		rapport.setEmployee(employee);
//		rapport.setDemande(demande);
//
//		//rapport.setDocumentR(file.getBytes());
//		rapport.setIdRapport(null);
//		if (!file.isEmpty()) {
//
//			String filePath = "src/main/uploads" + file.getOriginalFilename();
//
//
//			file.transferTo(new File(filePath));
//			rapport.setFilePath(filePath);
//		}
//		rapportService.saveRapport(rapport);
//		return ResponseEntity.ok("Rapport created succusfully");
//	}
//	@DeleteMapping("/delete/{idRapport}")
//	public ResponseEntity<?> deleteRapport(@PathVariable Long idRapport) {
//		try {
//			rapportService.deleteRapport(idRapport);
//			return ResponseEntity.ok().build();
//		} catch (RapportNotFoundException e) {
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//	@PutMapping("/update/{id}")
//	public ResponseEntity<String> updateRapport(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("rapport") String rapportJson, @PathVariable("id") long id) throws IOException {
//		Rapport currentRapport = rapportService.getRapportByIdRapport(id);
//		RapportRequestDTO rapportDTO = new ObjectMapper().readValue(rapportJson, RapportRequestDTO.class);
//
//		modelMapper.map(rapportDTO, currentRapport);
//
//		if (file != null && !file.isEmpty()) {
//			String filePath = "/path/to/save/file/" + file.getOriginalFilename();
//			file.transferTo(new File(filePath));
//			currentRapport.setFilePath(filePath);
//		}
//
//		rapportService.saveRapport(currentRapport);
//		return ResponseEntity.ok("Rapport updated successfully");
//	}
//
//
//}
