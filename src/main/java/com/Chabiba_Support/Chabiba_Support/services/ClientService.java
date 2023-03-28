package com.Chabiba_Support.Chabiba_Support.services;

import com.Chabiba_Support.Chabiba_Support.exception.ClientNotFoundException;
import com.Chabiba_Support.Chabiba_Support.models.Client;
import com.Chabiba_Support.Chabiba_Support.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    //la liste de tout les clients
    public List<Client> findAllClients() {
        log.info("Fetching all clients");
        return clientRepository.findAll();
    }

    //supprimer un client
    public void deleteClient(Long idPersonne){
        clientRepository.deleteClientById(idPersonne);
    }

    public Client findClientById(Long idPersonne) {
        return clientRepository.findClientById(idPersonne).orElseThrow(() -> new ClientNotFoundException("Client by id " + idPersonne + " was not found"));
    }

    public Client updateClient(Long idPersonne, String nom, String prenom, String numTel, String email,  String nomEntreprise) {
        Client client = clientRepository.findClientById(idPersonne).orElse(null);
        if (client == null) {
            return null;
        }
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setNumTel(numTel);
        client.setEmail(email);
        client.setNomEntreprise(nomEntreprise);

        return clientRepository.save(client);
    }







}
