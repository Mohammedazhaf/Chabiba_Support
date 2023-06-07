package com.Chabiba_Support.Chabiba_Support.services;

import com.Chabiba_Support.Chabiba_Support.exception.ClientNotFoundException;
import com.Chabiba_Support.Chabiba_Support.models.Client;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.models.Role;
import com.Chabiba_Support.Chabiba_Support.repositories.ClientRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;
    private final PersonneRepository personneRepository;
    private  final PasswordEncoder passwordEncoder;

    public Client addClient(Personne personne, String nomEntreprise) {
        Personne savedPersonne = personneRepository.save(personne);
        Client client = new Client(nomEntreprise,savedPersonne);
        return clientRepository.save(client);
    }

    //la liste de tout les clients
    public List<Client> findAllClients() {
        log.info("Fetching all clients");
        return clientRepository.findAll();
    }

    //supprimer un client
    public void deleteClient(Client client) {
        Personne personne = personneRepository.findById(client.getPersonne().getIdPersonne()).orElse(null);
        clientRepository.delete(client);  // may be can be changed With the next ligne
        personneRepository.deleteById(personne.getIdPersonne());
    }

    public Client findClientByIdPersonne(Long idPersonne) {

        Personne findedPersonne = personneRepository.findById(idPersonne).orElse(null);

        return clientRepository.findClientByPersonne(findedPersonne).orElseThrow(() -> new ClientNotFoundException("Client by id " + idPersonne + " was not found"));
    }

    public List<Client> findByNomLikeIgnoreCase(String nom) {
        return clientRepository.findByNomLikeIgnoreCase(nom);
    }

    public long countAllClients() {
        return clientRepository.count();
    }

    public Client updateClient(Client client) {//////Have a things
        Long clientId = client.getIdClient();
        Long personneId = client.getPersonne().getIdPersonne();
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with ID: " + clientId));

        // Update the necessary fields
        existingClient.setNomEntreprise(client.getNomEntreprise());
        Personne personne = personneRepository.findById(personneId).orElseThrow(() -> new RuntimeException("Personne not found with ID: " + personneId));

        personne.setNom(client.getPersonne().getNom());
        personne.setPrenom(client.getPersonne().getPrenom());
        personne.setEmail(client.getPersonne().getEmail());
        personne.setNumTel(client.getPersonne().getNumTel());
        personne.setMotDePasse(passwordEncoder.encode(client.getPersonne().getMotDePasse()));
        personne.setRole(Role.client);

        Personne updatedPersonne= personneRepository.save(personne);

        existingClient.setPersonne(updatedPersonne);

        // Save the updated client
        return clientRepository.save(existingClient);
    }
    public Client getClientByID(Long id){
        return clientRepository.getById(id);
    }
    public Client findClientById(Long id){
        return clientRepository.findClientById(id);
    }
}
