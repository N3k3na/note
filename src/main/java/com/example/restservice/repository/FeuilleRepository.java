package com.example.restservice.repository;

import com.example.restservice.entity.Feuille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FeuilleRepository extends JpaRepository<Feuille, Long> {
    
    // Trouver toutes les feuilles d'un candidat
    List<Feuille> findByIdCandidat(Long idCandidat);
    
    // Trouver toutes les feuilles pour une matière
    List<Feuille> findByIdMatiere(Long idMatiere);
    
    // Trouver une feuille spécifique d'un candidat pour une matière
    @Query("SELECT f FROM Feuille f WHERE f.idCandidat = :idCandidat AND f.idMatiere = :idMatiere")
    Optional<Feuille> findByIdCandidatAndIdMatiere(@Param("idCandidat") Long idCandidat, @Param("idMatiere") Long idMatiere);
    
    // Vérifier si un candidat a déjà une feuille pour une matière
    boolean existsByIdCandidatAndIdMatiere(Long idCandidat, Long idMatiere);
    
    // Trouver toutes les matières évaluées pour un candidat
    @Query("SELECT DISTINCT f.idMatiere FROM Feuille f WHERE f.idCandidat = :idCandidat")
    List<Long> findDistinctIdMatiereByIdCandidat(@Param("idCandidat") Long idCandidat);
}