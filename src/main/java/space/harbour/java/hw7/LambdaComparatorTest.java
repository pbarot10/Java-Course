package main.java.space.harbour.java.hw7;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LambdaComparatorTest {
    private LambdaComparator lc;
    private final String jsonFile1 = "BladeRunner.json";
    private final String jsonFile2 = "TheShawshankRedemption.json";
    private final String jsonFile3 = "TheGodfather.json";
    private List<Movie> movie;
    private Actor actor;
    private Director director;

    @Before
    public void setUp() throws Exception {
        lc = new LambdaComparator();
        movie = new ArrayList<>();
        movie.add(new Movie().getJsonFromFile(jsonFile1));
        movie.add(new Movie().getJsonFromFile(jsonFile2));
        movie.add(new Movie().getJsonFromFile(jsonFile3));
    }

    @After
    public void tearDown() throws Exception {
        lc = null;
        movie = null;
        actor = null;
        director = null;
    }

    @Test
    public void testRatingsComparator() {
        lc = new LambdaComparator();
        movie = new ArrayList<>();
        movie.add(new Movie().getJsonFromFile(jsonFile1));
        movie.add(new Movie().getJsonFromFile(jsonFile2));
        movie.add(new Movie().getJsonFromFile(jsonFile3));

        assertEquals("Blade Runner", movie.get(0).getTitle());
        assertEquals("The Shawshank Redemption", movie.get(1).getTitle());
        assertEquals("The Godfather", movie.get(2).getTitle());

        lc.sortByRatings(movie);

        assertEquals("The Shawshank Redemption", movie.get(2).getTitle());
        assertEquals("The Godfather", movie.get(1).getTitle());
        assertEquals("Blade Runner", movie.get(0).getTitle());
    }

    @Test
    public void testRuntimeComparator() {
        lc = new LambdaComparator();
        movie = new ArrayList<>();
        movie.add(new Movie().getJsonFromFile(jsonFile1));
        movie.add(new Movie().getJsonFromFile(jsonFile2));
        movie.add(new Movie().getJsonFromFile(jsonFile3));

        lc.sortByLength(movie);

        assertEquals("The Shawshank Redemption", movie.get(1).getTitle());
        assertEquals("The Godfather", movie.get(2).getTitle());
        assertEquals("Blade Runner", movie.get(0).getTitle());
    }

    @Test
    public void testSortByReleaseYear() {
        lc = new LambdaComparator();
        movie = new ArrayList<>();
        movie.add(new Movie().getJsonFromFile(jsonFile1));
        movie.add(new Movie().getJsonFromFile(jsonFile2));
        movie.add(new Movie().getJsonFromFile(jsonFile3));

        lc.sortByReleaseYear(movie);

        assertEquals("The Shawshank Redemption", movie.get(2).getTitle());
        assertEquals("The Godfather", movie.get(0).getTitle());
        assertEquals("Blade Runner", movie.get(1).getTitle());
    }

    @Test
    public void testFilterByActor() {
        lc = new LambdaComparator();
        movie = new ArrayList<>();
        movie.add(new Movie().getJsonFromFile(jsonFile1));
        movie.add(new Movie().getJsonFromFile(jsonFile2));
        movie.add(new Movie().getJsonFromFile(jsonFile3));

        actor = new Actor();
        actor.setName("Harrison Ford");
        movie = lc.filterByActor(movie, actor);

        assertEquals(1, movie.size());
        assertEquals("Blade Runner", movie.get(0).getTitle());
    }

    @Test
    public void testFilterByGenre() {
        lc = new LambdaComparator();
        movie = new ArrayList<>();
        movie.add(new Movie().getJsonFromFile(jsonFile1));
        movie.add(new Movie().getJsonFromFile(jsonFile2));
        movie.add(new Movie().getJsonFromFile(jsonFile3));

        movie = lc.filterByGenre(movie, "Crime");

        assertEquals("The Godfather", movie.get(0).getTitle());
        assertEquals(1, movie.size());
    }

    @Test
    public void testFilterByDirector() {
        lc = new LambdaComparator();
        movie = new ArrayList<>();
        movie.add(new Movie().getJsonFromFile(jsonFile1));
        movie.add(new Movie().getJsonFromFile(jsonFile2));
        movie.add(new Movie().getJsonFromFile(jsonFile3));

        director = new Director();
        director.setName("Frank Darabont");
        movie = lc.filterByDirector(movie, director);

        assertEquals("The Shawshank Redemption", movie.get(0).getTitle());
        assertEquals(1, movie.size());
    }
}
