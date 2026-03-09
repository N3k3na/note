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
    public Note saveNote(Note note) {
        // Vérifier si la feuille existe, sinon la créer
        Feuille feuille = feuilleRepository.findByIdCandidatAndIdFeuille(
            note.getIdFeuille().longValue(), // Note: idFeuille dans Note correspond à l'ID de la feuille
            note.getIdFeuille() // À adapter selon votre logique métier
        );
        
        if (feuille == null) {
            feuille = new Feuille(
                note.getIdFeuille(), // À adapter : ceci devrait être l'ID du candidat
                note.getIdFeuille()   // Ceci devrait être l'ID de la feuille
            );
            feuille = feuilleRepository.save(feuille);
        }
        
        return noteRepository.save(note);
    }
    
    // Calculer la note réelle d'un candidat pour une matière
    public BigDecimal calculerNoteReelle(Long idCandidat, Long idMatiere) {
        // Récupérer toutes les feuilles du candidat
        List<Feuille> feuilles = feuilleRepository.findByIdCandidat(idCandidat);
        
        if (feuilles.isEmpty()) {
            return BigDecimal.ZERO;
        }
        
        // Récupérer toutes les notes pour ces feuilles
        List<BigDecimal> notes = new ArrayList<>();
        for (Feuille feuille : feuilles) {
            List<Note> notesFeuille = noteRepository.findNotesByFeuilleId(feuille.getId());
            for (Note note : notesFeuille) {
                notes.add(note.getNote());
            }
        }
        
        if (notes.isEmpty()) {
            return BigDecimal.ZERO;
        }
        
        // Vérifier si toutes les notes sont identiques
        boolean allNotesIdentiques = true;
        BigDecimal premiereNote = notes.get(0);
        for (BigDecimal note : notes) {
            if (note.compareTo(premiereNote) != 0) {
                allNotesIdentiques = false;
                break;
            }
        }
        
        if (allNotesIdentiques) {
            return premiereNote;
        }
        
        // Calculer la différence selon la formule donnée
        BigDecimal difference = calculerDifference(notes);
        
        // Récupérer les paramètres pour cette matière
        List<Parametre> parametres = parametreRepository.findByIdMatiereOrderByValeurAsc(idMatiere);
        
        // Déterminer quelle note prendre selon les paramètres
        return appliquerRegle(notes, difference, parametres);
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
        
        for (Parametre param : parametres) {
            Comparateur comparateur = param.getComparateur();
            BigDecimal valeurSeuil = param.getValeur();
            Resolution resolution = param.getResolution();
            
            boolean conditionRemplie = false;
            
            switch (comparateur.getComparateur()) {
                case "inf":
                    conditionRemplie = difference.compareTo(valeurSeuil) < 0;
                    break;
                case "infEgal":
                    conditionRemplie = difference.compareTo(valeurSeuil) <= 0;
                    break;
                case "sup":
                    conditionRemplie = difference.compareTo(valeurSeuil) > 0;
                    break;
                case "supEgal":
                    conditionRemplie = difference.compareTo(valeurSeuil) >= 0;
                    break;
            }
            
            if (conditionRemplie) {
                switch (resolution.getAPrendre()) {
                    case "maximum":
                        return notes.stream().max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
                    case "minimum":
                        return notes.stream().min(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
                    case "moyenne":
                        return calculerMoyenne(notes);
                }
            }
        }
        
        // Par défaut, retourner la moyenne
        return calculerMoyenne(notes);
    }
    
    // Calculer la moyenne des notes
    private BigDecimal calculerMoyenne(List<BigDecimal> notes) {
        BigDecimal somme = notes.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return somme.divide(BigDecimal.valueOf(notes.size()), 2, RoundingMode.HALF_UP);
    }
    
    // Vérifier si une note existe déjà
    public boolean noteExists(Long idFeuille, Long idProf) {
        return noteRepository.existsByIdFeuilleAndIdProf(idFeuille, idProf);
    }
}