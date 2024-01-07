package com.example.controllers;

import com.example.services.RechercheArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ResponseBody
    public List<String> rechercherDonnee(@RequestParam String nom, Model model) {
        rechercheArticle.rechercherArticleRedis(nom);
        List<String> resultats = rechercheArticle.rechercherArticleMongoDB(nom);
        return resultats;
    }


}