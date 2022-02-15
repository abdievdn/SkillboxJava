import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.lt;
import static java.lang.invoke.MethodHandles.lookup;

public class Main {

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");
        MongoCollection<Document> shops = database.getCollection("Shops");
        MongoCollection<Document> items = database.getCollection("Items");

        String shopName;
        String itemName;
        boolean exit = false;
        while (!exit) {
            System.out.println("Use nums commands: " +
                    "add shop - 1, " +
                    "add item - 2, " +
                    "add item in shop - 3, " +
                    "show statistics - 4, " +
                    "exit - 0");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    System.out.print("Input shop: ");
                    shopName = scanner.nextLine();
                    Document shop = new Document()
                            .append("Name", shopName)
                            .append("Items", new ArrayList<String>());
                    shops.insertOne(shop);
                    break;
                case "2":
                    System.out.print("Input item: ");
                    itemName = scanner.nextLine();
                    Document item = new Document().append("Name", itemName);
                    System.out.print("Input item's price: ");
                    String price = scanner.nextLine();
                    item.append("Price", Integer.valueOf(price));
                    items.insertOne(item);
                    break;
                case "3":
                    System.out.print("Insert item in shop. \n Input item: ");
                    itemName = scanner.nextLine();
                    System.out.print(" Input shop: ");
                    shopName = scanner.nextLine();
                    Document query = new Document().append("Name", shopName);
                    Document update = new Document("$push", new Document("Items", itemName));
                    shops.updateOne(query, update, new UpdateOptions().upsert(true));
                    break;
                case "4":
                    System.out.println("Statistics.");
                    AggregateIterable<Document> aggregateIterable = shops.aggregate(
                            List.of(Aggregates.
                                            lookup("Items", "Items", "Name", "Statistics"),
                                            unwind("$Statistics"),
                                            group("$Name",
                                                    sum("count", 1),
                                                    sum("countLessThanHundred", new BasicDBObject("$lt", Arrays.asList("$Statistics.Price", "100"))),
                                                    avg("avgPrice", "$Statistics.Price"),
                                                    min("minPrice", "$Statistics.Name"),
                                                    max("maxPrice", "$Statistics.Name"))));
                    for(Document doc: aggregateIterable){
                        System.out.println("Shop: " + doc.get("_id") +
                                "\n Items count: " + doc.get("count") +
                                "\n Items count less than hundred: " + doc.get("countLessThanHundred") +
                                "\n Average price items: " + doc.get("avgPrice") +
                                "\n Min price item: " + doc.get("minPrice") +
                                "\n Max price item: " + doc.get("maxPrice"));
                    }
                    break;
                case "0":
                    System.out.println("Exit program!");
                    exit = true;
                    break;
                default:
                    break;
            }
        }
    }
}
