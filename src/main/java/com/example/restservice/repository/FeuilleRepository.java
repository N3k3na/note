package com.example.restservice.repository;

import com.example.restservice.entity.Feuille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FeuilleRepository extends JpaRepository<Feuille, Long> {
    List<Feuille> findByIdCandidat(Long idCandidat);
    
    @Query("SELECT f FROM Feuille f WHERE f.idCandidat = :idCandidat AND f.idFeuille = :idFeuille")
    Feuille findByIdCandidatAndIdFeuille(@Param("idCandidat") Long idCandidat, @Param("idFeuille") Long idFeuille);
    
    @Query("SELECT DISTINCT f.idFeuille FROM Feuille f WHERE f.idCandidat = :idCandidat")
    List<Long> findDistinctIdFeuilleByIdCandidat(@Param("idCandidat") Long idCandidat);
}