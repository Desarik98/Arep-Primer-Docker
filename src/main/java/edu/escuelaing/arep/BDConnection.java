package edu.escuelaing.arep;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;

public class BDConnection {
    private MongoClientURI uri;
    private MongoClient client;
    private MongoDatabase database;

    public BDConnection() {
        uri = new MongoClientURI("mongodb://localhost:27017/db");
        client = new MongoClient(uri);
        database = client.getDatabase("db");
    }

    public void insertMessage(String message){
        MongoCollection<Document> collection = database.getCollection("Messages");
        Document document = new Document();
        document.append("mensaje",message);
        document.append("fecha", new Date().toString());
        collection.insertOne(document);
    }

    public String getData(){
        MongoCollection<Document> collection = database.getCollection("Messages");
        String message = "";
        FindIterable<Document> iterable = collection.find();
        for (Document document: iterable) {
            message += "<tr><td>" + document.get("mensaje").toString() + "</td><td>" + document.get("fecha").toString() + "</td></tr>";
        }
        System.out.println(message);
        return message;
    }
}