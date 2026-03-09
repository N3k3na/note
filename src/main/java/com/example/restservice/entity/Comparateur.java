package com.example.restservice.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "comparateur")
public class Comparateur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "comparateur", nullable = false, length = 10)
    private String comparateur;
    
    @OneToMany(mappedBy = "comparateur")
    private List<Parametre> parametres;
    
    public Comparateur() {}
    
    public Comparateur(String comparateur) {
        this.comparateur = comparateur;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getComparateur() {
        return comparateur;
    }
    
    public void setComparateur(String comparateur) {
        this.comparateur = comparateur;
    }
    
    public List<Parametre> getParametres() {
        return parametres;
    }
    
    public void setParametres(List<Parametre> parametres) {
        this.parametres = parametres;
    }
}