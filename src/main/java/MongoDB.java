import java.util.logging.Logger;
import java.util.logging.Level;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.MongoCollection;

public class MongoDB {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = new MongoClient("127.0.0.1")) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("mongodb_test");

            MongoIterable<String> listCollectionsNames = mongoDatabase.listCollectionNames();

            System.out.println("Collections list:");
            for (String collectionsNames : listCollectionsNames) {
                System.out.println(collectionsNames);
            }
            System.out.println();

            MongoCollection<Document> restaurants = mongoDatabase.getCollection("restaurants");

            System.out.println("Restaurants list:");
            for (Document restaurant : restaurants.find()) {
                String name = restaurant.getString("name");
                String cuisine = restaurant.getString("cuisine");
                Document address = restaurant.get("address", Document.class);
                String street = (address != null) ? address.getString("street") : "";
                String building = (address != null) ? address.getString("building") : "";

                System.out.println(" Name: " + name);
                System.out.println("  Cuisine: " + cuisine);
                System.out.println("  Grades: ");
                System.out.println("  Address: ");
                System.out.println("   Street: " + street);
                System.out.println("   Building: " + building);
            }
        }

        System.out.println("Finish!");
    }
}

