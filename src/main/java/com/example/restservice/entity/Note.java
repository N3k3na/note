package com.example.restservice.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "note")
public class Note {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "idfeuille", nullable = false)
    private Long idFeuille;
    
    @Column(name = "idprof", nullable = false)
    private Long idProf;
    
    @Column(name = "note", nullable = false, precision = 4, scale = 2)
    private BigDecimal note;
    
    @ManyToOne
    @JoinColumn(name = "idfeuille", insertable = false, updatable = false)
    private Feuille feuille;
    
    @ManyToOne
    @JoinColumn(name = "idprof", insertable = false, updatable = false)
    private Prof prof;
    
    public Note() {}
    
    public Note(Long idFeuille, Long idProf, BigDecimal note) {
        this.idFeuille = idFeuille;
        this.idProf = idProf;
        this.note = note;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getIdFeuille() {
        return idFeuille;
    }
    
    public void setIdFeuille(Long idFeuille) {
        this.idFeuille = idFeuille;
    }
    
    public Long getIdProf() {
        return idProf;
    }
    
    public void setIdProf(Long idProf) {
        this.idProf = idProf;
    }
    
    public BigDecimal getNote() {
        return note;
    }
    
    public void setNote(BigDecimal note) {
        this.note = note;
    }
    
    public Feuille getFeuille() {
        return feuille;
    }
    
    public void setFeuille(Feuille feuille) {
        this.feuille = feuille;
    }
    
    public Prof getProf() {
        return prof;
    }
    
    public void setProf(Prof prof) {
        this.prof = prof;
    }
}