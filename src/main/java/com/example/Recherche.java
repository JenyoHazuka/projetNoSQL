package main.java.com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Recherche {

    public class Article {
        private int id;

        public Article(int id) {
            this.id = id;
        }

        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    @Service
    public class RechercheArticle {
        public void rechercheArticle(Article article) {
            String connectionString = "mongodb://localhost:27017";
            try (MongoClient mongoClient = MongoClients.create(connectionString)) {
                List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
                // Ajoutez votre logique de recherche MongoDB ici
                System.out.println("main.java.com.example.Recherche d'article effectuée pour l'id : " + article.getId());
            }
        }
    }

    @Controller
    @RequestMapping("/")
    public class RechercheController {
        @Autowired
        private RechercheArticle rechercheArticle;

        @PostMapping("/recherche")
        @ResponseBody
        public String rechercherArticle(@RequestParam int id) {
            Article article = new Article(id);
            rechercheArticle.rechercheArticle(article);
            return "main.java.com.example.Recherche effectuée";
        }
    }
}