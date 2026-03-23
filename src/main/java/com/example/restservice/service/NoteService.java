package com.example.restservice.service;

import com.example.restservice.entity.*;
import com.example.restservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private CandidatRepository candidatRepository;
    
    @Autowired
    private MatiereRepository matiereRepository;
    
    @Autowired
    private FeuilleRepository feuilleRepository;
    
    @Autowired
    private ProfRepository profRepository;
    
    @Autowired
    private NoteRepository noteRepository;
    
    @Autowired
    private ParametreRepository parametreRepository;
    
    @Autowired
    private ComparateurRepository comparateurRepository;
    
    @Autowired
    private ResolutionRepository resolutionRepository;
    
    // Récupérer tous les candidats
    public List<Candidat> getAllCandidats() {
        return candidatRepository.findAll();
    }
    
    // Récupérer toutes les matières
    public List<Matiere> getAllMatieres() {
        return matiereRepository.findAll();
    }
    
    // Récupérer tous les professeurs
    public List<Prof> getAllProfs() {
        return profRepository.findAll();
    }
    
    // Sauvegarder une note
    @Transactional
    public Note saveNote(Long idCandidat, Long idMatiere, Long idProf, BigDecimal noteValue) {
        
        // Vérifier si une feuille existe déjà pour ce candidat et cette matière
        Optional<Feuille> feuilleOpt = feuilleRepository.findByIdCandidatAndIdMatiere(idCandidat, idMatiere);
        
        Feuille feuille;
        if (feuilleOpt.isPresent()) {
            feuille = feuilleOpt.get();
        } else {
            // Créer une nouvelle feuille
            feuille = new Feuille(idCandidat, idMatiere);
            feuille = feuilleRepository.save(feuille);
        }
        
        // Vérifier si ce professeur a déjà noté cette feuille
        if (noteRepository.existsByIdFeuilleAndIdProf(feuille.getId(), idProf)) {
            throw new RuntimeException("Ce professeur a déjà noté cette feuille");
        }
        
        // Créer et sauvegarder la note
        Note note = new Note(feuille.getId(), idProf, noteValue);
        return noteRepository.save(note);
    }
    
    // Calculer la note réelle d'un candidat pour une matière
    public BigDecimal calculerNoteReelle(Long idCandidat, Long idMatiere) {
        
        // Récupérer toutes les notes du candidat pour cette matière
        List<Note> notes = noteRepository.findByIdCandidatAndIdMatiere(idCandidat, idMatiere);
        
        if (notes.isEmpty()) {
            return BigDecimal.ZERO;
        }
        
        // Extraire les valeurs des notes
        List<BigDecimal> valeursNotes = new ArrayList<>();
        for (Note note : notes) {
            valeursNotes.add(note.getNote());
        }
        
        // Vérifier si toutes les notes sont identiques
        boolean allNotesIdentiques = true;
        BigDecimal premiereNote = valeursNotes.get(0);
        for (BigDecimal note : valeursNotes) {
            if (note.compareTo(premiereNote) != 0) {
                allNotesIdentiques = false;
                break;
            }
        }
        
        if (allNotesIdentiques) {
            return premiereNote;
        }
        
        // Calculer la différence selon la formule donnée
        BigDecimal difference = calculerDifference(valeursNotes);
        
        // Récupérer les paramètres pour cette matière
        List<Parametre> parametres = parametreRepository.findByIdMatiereOrderByValeurAsc(idMatiere);
        
        // Déterminer quelle note prendre selon les paramètres
        return appliquerRegle(valeursNotes, difference, parametres);
    }
    
    // Calculer la différence entre les notes
    private BigDecimal calculerDifference(List<BigDecimal> notes) {
        BigDecimal difference = BigDecimal.ZERO;
        int n = notes.size();
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                difference = difference.add(
                    notes.get(i).subtract(notes.get(j)).abs()
                );
            }
        }
        
        return difference;
    }
    
    // Appliquer la règle de calcul selon les paramètres
    private BigDecimal appliquerRegle(List<BigDecimal> notes, BigDecimal difference, 
                                       List<Parametre> parametres) {
        
        if (parametres.isEmpty()) {
            return calculerMoyenne(notes);
        }
        
        // Rechercher un paramètre qui correspond exactement à la différence
        for (Parametre param : parametres) {
            Comparateur comparateur = param.getComparateur();
            BigDecimal valeurSeuil = param.getValeur();
            
            if (comparateur.getComparateur().equals("egal") && 
                difference.compareTo(valeurSeuil) == 0) {
                return appliquerResolution(notes, param.getResolution());
            }
        }
        
        // Si aucun paramètre ne correspond exactement, trouver le plus proche
        Parametre parametreLePlusProche = trouverParametreLePlusProche(difference, parametres);
        
        if (parametreLePlusProche != null) {
            return appliquerResolution(notes, parametreLePlusProche.getResolution());
        }
        
        // Par défaut, retourner la moyenne
        return calculerMoyenne(notes);
    }
    
    // Trouver le paramètre le plus proche de la différence donnée
    private Parametre trouverParametreLePlusProche(BigDecimal difference, List<Parametre> parametres) {
        if (parametres.isEmpty()) {
            return null;
        }
        
        Parametre parametreChoisi = null;
        BigDecimal ecartMinimal = null;
        
        for (Parametre param : parametres) {
            BigDecimal valeurSeuil = param.getValeur();
            BigDecimal ecart = difference.subtract(valeurSeuil).abs();
            
            // Si la différence est exactement au milieu entre deux valeurs
            // On prend la plus petite valeur (déjà géré par l'ordre de parcours)
            
            if (ecartMinimal == null || ecart.compareTo(ecartMinimal) < 0) {
                ecartMinimal = ecart;
                parametreChoisi = param;
            } else if (ecart.compareTo(ecartMinimal) == 0) {
                // En cas d'égalité d'écart (ex: différence = 6.5 avec valeurs 6 et 7)
                // On prend la plus petite valeur
                if (param.getValeur().compareTo(parametreChoisi.getValeur()) < 0) {
                    parametreChoisi = param;
                }
            }
        }
        
        return parametreChoisi;
    }
    
    // Appliquer la résolution (maximum, minimum, moyenne) sur les notes
    private BigDecimal appliquerResolution(List<BigDecimal> notes, Resolution resolution) {
        switch (resolution.getAPrendre()) {
            case "maximum":
                return notes.stream().max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
            case "minimum":
                return notes.stream().min(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
            case "moyenne":
                return calculerMoyenne(notes);
            default:
                return calculerMoyenne(notes);
        }
    }
    
    // Calculer la moyenne des notes
    private BigDecimal calculerMoyenne(List<BigDecimal> notes) {
        BigDecimal somme = notes.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return somme.divide(BigDecimal.valueOf(notes.size()), 2, RoundingMode.HALF_UP);
    }
}