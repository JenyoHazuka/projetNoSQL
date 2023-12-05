package com.example.controllers;

import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
class RechercheArticle {

    private  Jedis jedis;



    private void initRedis(){
        jedis = new Jedis("redis://default:kQ6comKgexGLxxsxx2EJfcs5s9Ig7cp2@redis-13334.c304.europe-west1-2.gce.cloud.redislabs.com:13334");
    }

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

    public void rechercherArticleRedis(String nom) {
        if(jedis==null){
            initRedis();
        }
            jedis.set("cle", "valeur");
            String valeur = jedis.get("searchbar");
            System.out.println("Valeur récupérée : " + valeur);
    }

    private void stockerDansRedis(String nom, String resultat) {
        try {
            // Stocker dans Redis
            jedis.hset("recherche", nom, resultat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
