package main.java.space.harbour.java.hw10;

/*import com.mongodb.BasicDBObject;
import main.java.space.harbour.java.hw7.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class TestMovieDatabaseExecutor {
    MovieDatabaseExecutor executor;
    @Before
    public void setUp() throws Exception {
        executor = new MovieDatabaseExecutor();
    }
    @After
    public void tearDown() throws Exception {
        executor.execDeleteAll();
        executor.close();
    }
    @Test
    public void testInsertOne() {
        executor.execInsertOne(new Movie().getJsonFromFile("BladeRunner.json"));
        assertEquals(1, executor.execQuery(new BasicDBObject()).size());
    }
    @Test
    public void testInsertMultiple() {
        executor.execInsertOne(new Movie().getJsonFromFile("BladeRunner.json"));
        executor.execInsertOne(new Movie().getJsonFromFile("TheShawshankRedemption.json"));
        executor.execInsertOne(new Movie().getJsonFromFile("TheGodfather.json"));
        assertEquals(1+2, executor.execQuery(new BasicDBObject()).size());
    }
    @Test
    public void testRetrieveOne() {
        executor.execInsertOne(new Movie().getJsonFromFile("BladeRunner.json"));
        executor.execInsertOne(new Movie().getJsonFromFile("TheShawshankRedemption.json"));
        executor.execInsertOne(new Movie().getJsonFromFile("TheGodfather.json"));
        BasicDBObject query = new BasicDBObject();
        query.put("Title", "Blade Runner");
        assertEquals(1, executor.execQuery(query).size());
    }
    
}
*/
