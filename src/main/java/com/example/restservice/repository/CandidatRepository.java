package com.example.restservice.repository;

import com.example.restservice.entity.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Long> {
    List<Candidat> findByNomContainingIgnoreCase(String nom);
    List<Candidat> findByPrenomContainingIgnoreCase(String prenom);
    List<Candidat> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom);
}