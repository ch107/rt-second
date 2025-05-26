package crudpractice;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class MyMongoDB {
    public static void main(String[] args) {
        String uri = "mongodb://myuser:mypassword@localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("testdb");
            MongoCollection<Document> collection = database.getCollection("users");

            // Create
            Document user = new Document("name", "Alice").append("age", 25);
            collection.insertOne(user);

            // Read
            for (Document doc : collection.find()) {
                System.out.println(doc.toJson());
            }

            // Update
            collection.updateOne(Filters.eq("name", "Alice"),
                    new Document("$set", new Document("age", 26)));

            // Delete
            //collection.deleteOne(Filters.eq("name", "Alice"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
