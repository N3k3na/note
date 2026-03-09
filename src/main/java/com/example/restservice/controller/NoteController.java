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

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("notes/index");
        modelAndView.addObject("titre", "Gestion des Notes");
        modelAndView.addObject("sousTitre", "Système de gestion des notes avec règles de calcul");
        return modelAndView;
    }

    @GetMapping("/inserer")
    public ModelAndView showInsertForm() {
        List<Candidat> candidats = noteService.getAllCandidats();
        List<Matiere> matieres = noteService.getAllMatieres();
        List<Prof> profs = noteService.getAllProfs();
        
        ModelAndView modelAndView = new ModelAndView("notes/inserer");
        modelAndView.addObject("candidats", candidats);
        modelAndView.addObject("matieres", matieres);
        modelAndView.addObject("profs", profs);
        modelAndView.addObject("titre", "Insérer une Note");
        return modelAndView;
    }
    
    @PostMapping("/inserer")
    public ModelAndView insertNote(@RequestParam("idCandidat") Long idCandidat,
                                  @RequestParam("idMatiere") Long idMatiere,
                                  @RequestParam("idProf") Long idProf,
                                  @RequestParam("note") BigDecimal noteValue,
                                  RedirectAttributes redirectAttributes) {
        
        try {
            noteService.saveNote(idCandidat, idMatiere, idProf, noteValue);
            redirectAttributes.addFlashAttribute("successMessage", "Note insérée avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        
        return new ModelAndView("redirect:/notes/inserer");
    }
    
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
    
    @GetMapping("/resultat")
    public ModelAndView showResultat() {
        ModelAndView modelAndView = new ModelAndView("notes/resultat");
        modelAndView.addObject("titre", "Résultat du Calcul");
        return modelAndView;
    }
}