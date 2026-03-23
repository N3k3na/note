package com.example.restservice.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "feuille")
public class Feuille {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "idcandidat", nullable = false)
    private Long idCandidat;
    
    @Column(name = "idmatiere", nullable = false)
    private Long idMatiere;
    
    @ManyToOne
    @JoinColumn(name = "idcandidat", insertable = false, updatable = false)
    private Candidat candidat;
    
    @ManyToOne
    @JoinColumn(name = "idmatiere", insertable = false, updatable = false)
    private Matiere matiere;
    
    @OneToMany(mappedBy = "feuille")
    private List<Note> notes;
    
    public Feuille() {}
    
    public Feuille(Long idCandidat, Long idMatiere) {
        this.idCandidat = idCandidat;
        this.idMatiere = idMatiere;
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
    
    public Long getIdMatiere() {
        return idMatiere;
    }
    
    public void setIdMatiere(Long idMatiere) {
        this.idMatiere = idMatiere;
    }
    
    public Candidat getCandidat() {
        return candidat;
    }
    
    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }
    
    public Matiere getMatiere() {
        return matiere;
    }
    
    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
    
    public List<Note> getNotes() {
        return notes;
    }
    
    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}