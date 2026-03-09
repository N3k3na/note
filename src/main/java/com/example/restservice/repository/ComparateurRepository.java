package com.example.restservice.repository;

import com.example.restservice.entity.Comparateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ComparateurRepository extends JpaRepository<Comparateur, Long> {
    
    @Query("SELECT c FROM Comparateur c WHERE c.comparateur = :comparateur")
    Optional<Comparateur> findByComparateur(@Param("comparateur") String comparateur);
}