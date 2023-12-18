package com.example.controllers;

import com.example.services.InsertionArticle;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;
import org.springframework.stereotype.Service;
import org.bson.Document;

@Controller
@RequestMapping("/inserer")
public class InsertionController {

    @Autowired
    private InsertionArticle insertionArticle;

    @GetMapping("/insertPage")
    public String afficherPageInsertion() {
        return "insertPage";
    }

    @PostMapping("/inserer")
    public String insererDonnee(@RequestParam String Namebar, @RequestParam int PriceUniquebar) {
        insertionArticle.insererArticle(Namebar, PriceUniquebar);
        return "redirect:/insertPage.html";
    }
}
