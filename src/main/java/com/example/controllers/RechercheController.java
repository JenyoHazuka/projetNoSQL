package com.example.controllers;

import com.example.services.RechercheArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


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
        rechercheArticle.rechercherArticleMongoDB(nom);
        return "redirect:/searchPage.html";
    }
}