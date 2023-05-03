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
    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

    public Client findClientByIdPersonne(Long idPersonne) {
        return clientRepository.findClientByIdPersonne(idPersonne).orElseThrow(() -> new ClientNotFoundException("Client by id " + idPersonne + " was not found"));
    }

    public List<Client> findByNomLikeIgnoreCase(String nom) {
        return clientRepository.findByNomLikeIgnoreCase(nom);
    }

    public long countAllClients() {
        return clientRepository.count();
    }

    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }







}
