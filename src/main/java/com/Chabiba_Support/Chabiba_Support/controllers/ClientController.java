package com.Chabiba_Support.Chabiba_Support.controllers;

import com.Chabiba_Support.Chabiba_Support.models.Client;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.models.Role;
import com.Chabiba_Support.Chabiba_Support.requests.ClientRequestDTO;
import com.Chabiba_Support.Chabiba_Support.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Client>> getAllClients() {

        List<Client> clients = clientService.findAllClients();
        System.out.println(clients.get(0).getNomEntreprise());
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Client> addClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/controllers/client/add").toUriString());
        Personne personne = new Personne();
        personne.setNom(clientRequestDTO.getNom());
        personne.setPrenom(clientRequestDTO.getPrenom());
        personne.setEmail(clientRequestDTO.getEmail());
        personne.setNumTel(clientRequestDTO.getNumTel());
        personne.setMotDePasse(clientRequestDTO.getMotDePasse());
        personne.setRole(Role.client);

        return ResponseEntity.created(uri).body(clientService.addClient(personne,clientRequestDTO.getNomEntreprise()));
    }

    @GetMapping("/findPersonne/{id}")
    public Client getClientBYIdPersonne (@PathVariable("id") Long id) {
        Client client = clientService.findClientByIdPersonne(id);
        return client;
    }

    @GetMapping("/find/{id}")
    public Client getClientBYId (@PathVariable("id") Long id) {
        Client client = clientService.findClientById(id);
        return client;
    }


    @PutMapping("/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {

        Client updateClient = clientService.updateClient(client);
        return new ResponseEntity<>(updateClient, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteClient(@PathVariable Long id) {
        Client client = clientService.findClientById(id);
        clientService.deleteClient(client);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/{nom}")
    public ResponseEntity<List<Client>> getClientByNomLikeIgnoreCase(@PathVariable String nom) {
        List<Client> clients = clientService.findByNomLikeIgnoreCase(nom);
        return  new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countAllClient() {
        long count = clientService.countAllClients();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }












}
