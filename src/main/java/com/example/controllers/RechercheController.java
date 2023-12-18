package com.example.controllers;

import com.example.services.RechercheArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/recherche")
public class RechercheController {

    private final RechercheArticle rechercheArticle;

    @Autowired
    public RechercheController(RechercheArticle rechercheArticle) {
        this.rechercheArticle = rechercheArticle;
    }

    @GetMapping("/searchPage")
    public String afficherPageRecherche() {
        return "searchPage";
    }


    @PostMapping("/recherche")
    public String rechercherDonnee(@RequestParam String nom) {
        rechercheArticle.rechercherArticleRedis(nom);
        return "redirect:/searchPage.html";
    }

    /*@PostMapping("/recherche")
    public String performSearch(@RequestParam("nom") String searchTerm, Model model) {
        String searchResult = "Résultats de la recherche pour '" + searchTerm + "': exemple1, exemple2, exemple3";

        model.addAttribute("searchResult", searchResult);

        return "redirect:/searchPage.html";
    }*/
}