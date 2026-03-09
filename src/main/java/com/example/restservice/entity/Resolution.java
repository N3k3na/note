package com.example.restservice.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "resolution")
public class Resolution {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "a_prendre", nullable = false, length = 20)
    private String aPrendre;
    
    @OneToMany(mappedBy = "resolution")
    private List<Parametre> parametres;
    
    public Resolution() {}
    
    public Resolution(String aPrendre) {
        this.aPrendre = aPrendre;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getAPrendre() {
        return aPrendre;
    }
    
    public void setAPrendre(String aPrendre) {
        this.aPrendre = aPrendre;
    }
    
    public List<Parametre> getParametres() {
        return parametres;
    }
    
    public void setParametres(List<Parametre> parametres) {
        this.parametres = parametres;
    }
}