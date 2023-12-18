package com.example.services;

import com.mongodb.client.*;
import org.bson.Document;
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

        String valeur = jedis.hget("recherche", nom);

        if (valeur != null) {
            System.out.println("Valeur récupérée : " + valeur);
            stockerDansRedis(nom, valeur);
        } else {
            System.out.println("Aucun résultat trouvé pour : " + nom);
        }
    }

    private void stockerDansRedis(String nom, String resultat) {
        try {
            jedis.hset("recherche", nom, resultat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
