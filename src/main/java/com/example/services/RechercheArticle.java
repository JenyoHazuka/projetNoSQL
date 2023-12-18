package com.example.services;

import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class RechercheArticle {

    public void rechercherArticleMongoDB(String nom) {
        String connectionString = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("ProjetNOsql");
            MongoCollection<Document> collection = database.getCollection("ListeArticle");

            FindIterable<Document> result = collection.find(new Document("nom", nom));
            for (Document document : result) {
                System.out.println("Résultat de la recherche : " + document.toJson());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Jedis jedis;

    private void initRedis() {
        jedis = new Jedis("redis://default:kQ6comKgexGLxxsxx2EJfcs5s9Ig7cp2@redis-13334.c304.europe-west1-2.gce.cloud.redislabs.com:13334");
    }

    public void rechercherArticleRedis(String nom) {
        if (jedis == null) {
            initRedis();
        }
        try {
            String valeur = jedis.hget("recherche", nom);

            if (valeur != null) {
                System.out.println("Valeur récupérée : " + valeur);
            } else {
                System.out.println("Aucun résultat trouvé pour : " + nom);
                String resultatRecherche = effectuerRecherche(nom);
                stockerDansRedis(nom, resultatRecherche);
                System.out.println("Résultat stocké dans Redis : " + nom + " => " + resultatRecherche);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stockerDansRedis(String nom, String resultat) {
        try {
            jedis.hset("ListeArticle", nom, resultat);
            jedis.expire("ListeArticle", 3600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String effectuerRecherche(String nom) {

        return "Résultat de la recherche pour " + nom;
    }
}