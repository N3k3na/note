package com.example.restservice.repository;

import com.example.restservice.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    
    List<Note> findByIdFeuille(Long idFeuille);
    
    List<Note> findByIdProf(Long idProf);
    
    @Query("SELECT n FROM Note n WHERE n.idFeuille IN " +
           "(SELECT f.id FROM Feuille f WHERE f.idCandidat = :idCandidat)")
    List<Note> findByIdCandidat(@Param("idCandidat") Long idCandidat);
    
    @Query("SELECT n FROM Note n WHERE n.idFeuille = :idFeuille")
    List<Note> findNotesByFeuilleId(@Param("idFeuille") Long idFeuille);
    
    boolean existsByIdFeuilleAndIdProf(Long idFeuille, Long idProf);
}