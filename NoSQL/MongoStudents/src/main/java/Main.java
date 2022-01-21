import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.bson.Document;

import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, CsvValidationException {

        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("local");
        MongoCollection<Document> collection = database.getCollection("Students");
        collection.drop();

        CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/mongo.csv"));

        String[] nextLine;
        while ((nextLine = csvReader.readNext()) != null) {
            if (nextLine != null) {
                Document document = new Document()
                    .append("Name", nextLine[0])
                    .append("Age", nextLine[1])
                    .append("Courses", nextLine[2]);
                collection.insertOne(document);
            }
        }

        System.out.println("Total students count: " +
                collection.countDocuments());

        System.out.println("Students count more than 40 age: " +
                collection.countDocuments(Filters.gt("Age", "40")));

        System.out.println("Most young student's name: " +
                collection.find().sort(Sorts.ascending("Age")).first().getString("Name"));

        System.out.println("Elder student's courses: " +
                collection.find().sort(Sorts.descending("Age")).first().getString("Courses"));

    }
}
