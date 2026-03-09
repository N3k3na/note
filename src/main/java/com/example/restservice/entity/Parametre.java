package com.example.restservice.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "parametre")
public class Parametre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "idmatiere", nullable = false)
    private Long idMatiere;
    
    @Column(name = "valeur", nullable = false, precision = 10, scale = 2)
    private BigDecimal valeur;
    
    @Column(name = "idcomparateur", nullable = false)
    private Long idComparateur;
    
    @Column(name = "idresolution", nullable = false)
    private Long idResolution;
    
    @ManyToOne
    @JoinColumn(name = "idmatiere", insertable = false, updatable = false)
    private Matiere matiere;
    
    @ManyToOne
    @JoinColumn(name = "idcomparateur", insertable = false, updatable = false)
    private Comparateur comparateur;
    
    @ManyToOne
    @JoinColumn(name = "idresolution", insertable = false, updatable = false)
    private Resolution resolution;
    
    public Parametre() {}
    
    public Parametre(Long idMatiere, BigDecimal valeur, Long idComparateur, Long idResolution) {
        this.idMatiere = idMatiere;
        this.valeur = valeur;
        this.idComparateur = idComparateur;
        this.idResolution = idResolution;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getIdMatiere() {
        return idMatiere;
    }
    
    public void setIdMatiere(Long idMatiere) {
        this.idMatiere = idMatiere;
    }
    
    public BigDecimal getValeur() {
        return valeur;
    }
    
    public void setValeur(BigDecimal valeur) {
        this.valeur = valeur;
    }
    
    public Long getIdComparateur() {
        return idComparateur;
    }
    
    public void setIdComparateur(Long idComparateur) {
        this.idComparateur = idComparateur;
    }
    
    public Long getIdResolution() {
        return idResolution;
    }
    
    public void setIdResolution(Long idResolution) {
        this.idResolution = idResolution;
    }
    
    public Matiere getMatiere() {
        return matiere;
    }
    
    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
    
    public Comparateur getComparateur() {
        return comparateur;
    }
    
    public void setComparateur(Comparateur comparateur) {
        this.comparateur = comparateur;
    }
    
    public Resolution getResolution() {
        return resolution;
    }
    
    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }
}