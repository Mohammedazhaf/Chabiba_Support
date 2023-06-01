package com.Chabiba_Support.Chabiba_Support.repositories;


import com.Chabiba_Support.Chabiba_Support.models.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface PersonneRepository extends JpaRepository<Personne, Long> {

    Optional<Personne> findByEmail(String email);
}
