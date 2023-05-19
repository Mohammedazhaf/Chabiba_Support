package com.Chabiba_Support.Chabiba_Support.repositories;

import com.Chabiba_Support.Chabiba_Support.models.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaireRepository  extends JpaRepository<Commentaire, Long> {

    List<Commentaire> findByDemandeIdPersonne(Long idDemande);

    List<Commentaire> findByEtoile(int etoile);

    List<Commentaire> findByClienrIdPersonne(Long idClient);

    Commentaire save(Commentaire commentaire);

    void delete(Commentaire commentaire);

    List<Commentaire> findAll();

}
