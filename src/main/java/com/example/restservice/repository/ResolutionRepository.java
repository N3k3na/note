package com.example.restservice.repository;

import com.example.restservice.entity.Resolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ResolutionRepository extends JpaRepository<Resolution, Long> {
    // Correction: utiliser le nom exact du champ "aPrendre" (avec 'a' minuscule)
    @Query("SELECT r FROM Resolution r WHERE r.aPrendre = :aPrendre")
    Optional<Resolution> findByAPrendre(@Param("aPrendre") String aPrendre);
}