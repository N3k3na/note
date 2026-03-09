package com.example.restservice.controller;

import com.example.restservice.entity.*;
import com.example.restservice.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    /**
     * Page d'accueil du module notes
     */
    @GetMapping("")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("notes/index");
        modelAndView.addObject("titre", "Gestion des Notes");
        modelAndView.addObject("sousTitre", "Système de gestion des notes avec règles de calcul");
        return modelAndView;
    }

    /**
     * Page pour insérer une note
     */
    @GetMapping("/inserer")
    public ModelAndView showInsertForm() {
        List<Candidat> candidats = noteService.getAllCandidats();
        List<Matiere> matieres = noteService.getAllMatieres();
        List<Prof> profs = noteService.getAllProfs();
        
        ModelAndView modelAndView = new ModelAndView("notes/inserer");
        modelAndView.addObject("note", new Note());
        modelAndView.addObject("candidats", candidats);
        modelAndView.addObject("matieres", matieres);
        modelAndView.addObject("profs", profs);
        modelAndView.addObject("titre", "Insérer une Note");
        return modelAndView;
    }
    
    /**
     * Traiter l'insertion d'une note
     */
    @PostMapping("/inserer")
    public ModelAndView insertNote(@RequestParam("idCandidat") Long idCandidat,
                                  @RequestParam("idMatiere") Long idMatiere,
                                  @RequestParam("idProf") Long idProf,
                                  @RequestParam("note") BigDecimal noteValue,
                                  @RequestParam("idFeuille") Long idFeuille,
                                  RedirectAttributes redirectAttributes) {
        
        // Créer et sauvegarder la note
        Note note = new Note(idFeuille, idProf, noteValue);
        // Note: Vous devrez peut-être ajuster la logique pour lier la matière à la feuille
        
        noteService.saveNote(note);
        
        redirectAttributes.addFlashAttribute("successMessage", 
            "Note insérée avec succès !");
        
        return new ModelAndView("redirect:/notes/inserer");
    }
    
    /**
     * Page pour consulter la note réelle d'un candidat
     */
    @GetMapping("/consulter")
    public ModelAndView showConsultForm() {
        List<Candidat> candidats = noteService.getAllCandidats();
        List<Matiere> matieres = noteService.getAllMatieres();
        
        ModelAndView modelAndView = new ModelAndView("notes/consulter");
        modelAndView.addObject("candidats", candidats);
        modelAndView.addObject("matieres", matieres);
        modelAndView.addObject("titre", "Consulter la Note Réelle");
        return modelAndView;
    }
    
    /**
     * Traiter la consultation de la note réelle
     */
    @PostMapping("/consulter")
    public ModelAndView consulterNote(@RequestParam("idCandidat") Long idCandidat,
                                     @RequestParam("idMatiere") Long idMatiere,
                                     RedirectAttributes redirectAttributes) {
        
        BigDecimal noteReelle = noteService.calculerNoteReelle(idCandidat, idMatiere);
        
        // Récupérer les informations du candidat et de la matière
        Candidat candidat = noteService.getAllCandidats().stream()
            .filter(c -> c.getId().equals(idCandidat))
            .findFirst().orElse(null);
        
        Matiere matiere = noteService.getAllMatieres().stream()
            .filter(m -> m.getId().equals(idMatiere))
            .findFirst().orElse(null);
        
        redirectAttributes.addFlashAttribute("noteReelle", noteReelle);
        redirectAttributes.addFlashAttribute("candidat", candidat);
        redirectAttributes.addFlashAttribute("matiere", matiere);
        redirectAttributes.addFlashAttribute("idCandidat", idCandidat);
        redirectAttributes.addFlashAttribute("idMatiere", idMatiere);
        
        return new ModelAndView("redirect:/notes/resultat");
    }
    
    /**
     * Page de résultat de la note réelle
     */
    @GetMapping("/resultat")
    public ModelAndView showResultat() {
        ModelAndView modelAndView = new ModelAndView("notes/resultat");
        modelAndView.addObject("titre", "Résultat du Calcul");
        return modelAndView;
    }
}