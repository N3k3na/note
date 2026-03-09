package com.example.restservice.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "feuille")
public class Feuille {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "idcandidat", nullable = false)
    private Long idCandidat;
    
    @Column(name = "idfeuille", nullable = false)
    private Long idFeuille;
    
    @ManyToOne
    @JoinColumn(name = "idcandidat", insertable = false, updatable = false)
    private Candidat candidat;
    
    @OneToMany(mappedBy = "feuille")
    private List<Note> notes;
    
    public Feuille() {}
    
    public Feuille(Long idCandidat, Long idFeuille) {
        this.idCandidat = idCandidat;
        this.idFeuille = idFeuille;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getIdCandidat() {
        return idCandidat;
    }
    
    public void setIdCandidat(Long idCandidat) {
        this.idCandidat = idCandidat;
    }
    
    public Long getIdFeuille() {
        return idFeuille;
    }
    
    public void setIdFeuille(Long idFeuille) {
        this.idFeuille = idFeuille;
    }
    
    public Candidat getCandidat() {
        return candidat;
    }
    
    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }
    
    public List<Note> getNotes() {
        return notes;
    }
    
    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}