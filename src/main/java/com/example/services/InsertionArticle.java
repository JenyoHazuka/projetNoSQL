package com.example.services;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.stereotype.Service;
import org.bson.Document;

    @Service
    public class InsertionArticle {
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
