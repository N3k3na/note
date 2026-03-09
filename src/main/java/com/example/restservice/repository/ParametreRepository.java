package com.example.restservice.repository;

import com.example.restservice.entity.Parametre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ParametreRepository extends JpaRepository<Parametre, Long> {
    
    List<Parametre> findByIdMatiere(Long idMatiere);
    
    @Query("SELECT p FROM Parametre p WHERE p.idMatiere = :idMatiere " +
           "ORDER BY p.valeur ASC")
    List<Parametre> findByIdMatiereOrderByValeurAsc(@Param("idMatiere") Long idMatiere);
}