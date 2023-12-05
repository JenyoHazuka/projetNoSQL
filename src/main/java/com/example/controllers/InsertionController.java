package com.example.controllers;

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
        return "insertPage"; // Utilisez le nom du fichier HTML sans l'extension
    }

    @PostMapping("/inserer")
    public String insererDonnee(@RequestParam String Namebar, @RequestParam int PriceUniquebar) {
        insertionArticle.insererArticle(Namebar, PriceUniquebar);
        return "redirect:/insertPage.html"; // Redirige correctement vers la page d'insertion
    }

    @Service
    public static class InsertionArticle {
        public void insererArticle(String nom, int prix) {
            String connectionString = "mongodb://localhost:27017";
            try (MongoClient mongoClient = MongoClients.create(connectionString)) {
                MongoDatabase database = mongoClient.getDatabase("ProjetNOsql");
                MongoCollection<Document> collection = database.getCollection("ListeArticle");

                Document document = new Document("nom", nom)
                        .append("prix", prix);

                collection.insertOne(document);

                System.out.println("Article inséré : Nom = " + nom + ", Prix = " + prix);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
