package com.example.restservice.repository;

import com.example.restservice.entity.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {
    List<Matiere> findByLibelleContainingIgnoreCase(String libelle);
}