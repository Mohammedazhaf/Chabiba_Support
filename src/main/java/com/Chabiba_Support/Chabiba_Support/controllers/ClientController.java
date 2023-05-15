package com.Chabiba_Support.Chabiba_Support.controllers;

import com.Chabiba_Support.Chabiba_Support.models.Client;
import com.Chabiba_Support.Chabiba_Support.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
@CrossOrigin("*")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients () {
        List<Client> clients = clientService.findAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Client>addClient(@RequestBody Client client) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/controllers/client/add").toUriString());
        return ResponseEntity.created(uri).body(clientService.addClient(client));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Client> getClientBYIdPersonne (@PathVariable("id") Long id) {
        Client client = clientService.findClientByIdPersonne(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {

        Client updateClient = clientService.updateClient(client);
        return new ResponseEntity<>(updateClient, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteClient(@PathVariable Long id) {
        Client client = clientService.findClientByIdPersonne(id);
        clientService.deleteClient(client);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Client>> getClientByNomLikeIgnoreCase(@RequestParam("nom") String nom) {
        List<Client> clients = clientService.findByNomLikeIgnoreCase(nom);
        return  new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countAllClient() {
        long count = clientService.countAllClients();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }











}
