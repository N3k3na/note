package com.example.restservice.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "candidat")
public class Candidat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nom", nullable = false, length = 100)
    private String nom;
    
    @Column(name = "prenom", nullable = false, length = 100)
    private String prenom;
    
    @OneToMany(mappedBy = "candidat")
    private List<Feuille> feuilles;
    
    public Candidat() {}
    
    public Candidat(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public List<Feuille> getFeuilles() {
        return feuilles;
    }
    
    public void setFeuilles(List<Feuille> feuilles) {
        this.feuilles = feuilles;
    }
}