package main.java.space.harbour.java.hw10;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import main.java.space.harbour.java.hw7.Movie;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public final class MovieDatabaseExecutor {
    private MongoDatabase database;
    private MongoClient client;
    private MongoCollection<Document> collection;

    private final String host = "localhost";
    private final int port = 27107;

    private String collectionName = "movies";
    private String dbName = "java-homework";

    public MovieDatabaseExecutor() {
        client = new MongoClient(host, port);
        database = client.getDatabase(dbName);
        collection  = database.getCollection(collectionName);
    }

    public void execInsertOne(final Movie m) {
        collection.insertOne(Document.parse(m.toJsonString()));
    }

    public boolean execDeleteOne(final Bson bson) {
        try {
            DeleteResult result = collection.deleteOne(bson);
            if (result.wasAcknowledged()) {
                return true;
            }
        } catch (MongoException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public void execDeleteAll() {
        collection.drop();
    }

    public List<Movie> execQuery(final BasicDBObject query) {
        List<Movie> movies = new ArrayList<>();
        FindIterable<Document> documents = collection.find(query);

        documents.forEach((Block<Document>) doc -> {
            Movie movie = new Movie();
            movie.getJsonFromFile(doc.toJson());
            movies.add(movie);
        });
        return movies;
    }

    public void close() {
        client.close();
    }
}
