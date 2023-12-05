package com.example.controllers;

import com.mongodb.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.stereotype.Service;
import org.bson.Document;
import redis.clients.jedis.Jedis;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rechercher")
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

    @PostMapping("/rechercher")
    public String rechercherDonnee(@RequestParam String nom) {
        rechercheArticle.rechercherArticleRedis(nom);
        return "redirect:/searchPage.html";
    }
}

