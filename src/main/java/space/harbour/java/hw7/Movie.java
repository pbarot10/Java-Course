package main.java.space.harbour.java.hw7;

import main.java.space.harbour.java.hw4.Jsonable;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class Movie implements Jsonable {

    private String title;
    private String released;
    private String plot;
    private String poster;
    private String awards;
    private int year;
    private int runtime;
    private String[] genres;
    private String[] languages;
    private String[] countries;
    private Writer[] writers;
    private Actor[] actors;
    private Director directors;
    private Rating[] ratings;

    public String getTitle() {
        return title;
    }

    public String getReleased() {
        return released;
    }

    public String getPlot() {
        return plot;
    }

    public String getPoster() {
        return poster;
    }

    public String getAwards() {
        return awards;
    }

    public int getYear() {
        return year;
    }

    public int getRuntime() {
        return runtime;
    }

    public String[] getGenres() {
        return genres;
    }

    public String[] getLanguages() {
        return languages;
    }

    public String[] getCountries() {
        return countries;
    }

    public Writer[] getWriters() {
        return writers;
    }

    public Actor[] getActors() {
        return actors;
    }

    public Director getDirectors() {
        return directors;
    }

    public Rating[] getRatings() {
        return ratings;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonArrayBuilder genresBuilder = Json.createArrayBuilder();
        for (String genre: genres) {
            genresBuilder.add(genre);
        }

        JsonArrayBuilder languagesBuilder = Json.createArrayBuilder();
        for (String language: languages) {
            languagesBuilder.add(language);
        }

        JsonArrayBuilder countriesBuilder = Json.createArrayBuilder();
        for (String country: countries) {
            countriesBuilder.add(country);
        }

        JsonArrayBuilder actorsBuilder = Json.createArrayBuilder();
        for (Actor actor: actors) {
            actorsBuilder.add(actor.toJsonObject());
        }

        JsonArrayBuilder ratingsBuilder = Json.createArrayBuilder();
        for (Rating rating: ratings) {
            ratingsBuilder.add(rating.toJsonObject());
        }

        JsonArrayBuilder writersBuilder = Json.createArrayBuilder();
        for (Writer writer: writers) {
            writersBuilder.add(writer.toJsonObject());
        }

        return Json.createObjectBuilder()
                .add("Title", title)
                .add("Year", year)
                .add("Released", released)
                .add("Runtime", runtime)
                .add("Genres", genresBuilder)
                .add("Director", directors.toJsonObject())
                .add("Writers", writersBuilder)
                .add("Actors", actorsBuilder)
                .add("Plot", plot)
                .add("Languages", languagesBuilder)
                .add("Countries", countriesBuilder)
                .add("Awards", awards)
                .add("Poster", poster)
                .add("Ratings", ratingsBuilder)
                .build();
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }

    public void fromJson(final String json) {
        JsonReader jsonReader = Json.createReader(new StringReader(json));
        JsonObject jsonObject = jsonReader.readObject();

        this.title = jsonObject.getString("Title");
        this.year = jsonObject.getInt("Year");
        this.awards = jsonObject.getString("Awards");
        this.poster = jsonObject.getString("Poster");
        this.runtime = jsonObject.getInt("Runtime");
        this.released = jsonObject.getString("Released");
        this.plot = jsonObject.getString("Plot");

        this.directors = new Director();
        this.directors.fromJsonObject(jsonObject.getJsonObject("Director"));

        this.genres = new String[jsonObject.getJsonArray("Genres").size()];
        for (int i = 0; i < jsonObject.getJsonArray("Genres").size(); i++) {
            this.genres[i] = jsonObject.getJsonArray("Genres").getString(i);
        }

        this.countries = new String[jsonObject.
                getJsonArray("Countries").size()];
        for (int i = 0; i < jsonObject.
                getJsonArray("Countries").size(); i++) {
            this.countries[i] = jsonObject.
                    getJsonArray("Countries").getString(i);
        }

        this.languages = new String[jsonObject
                .getJsonArray("Languages").size()];
        for (int i = 0; i < jsonObject
                .getJsonArray("Languages").size(); i++) {
            this.languages[i] = jsonObject
                    .getJsonArray("Languages").getString(i);
        }

        this.writers = new Writer[jsonObject
                .getJsonArray("Writers").size()];
        for (int i = 0; i < jsonObject
                .getJsonArray("Writers").size(); i++) {
            this.writers[i] = new Writer();
            this.writers[i].fromJsonObject(jsonObject
                    .getJsonArray("Writers").getJsonObject(i));
        }

        this.actors = new Actor[jsonObject
                .getJsonArray("Actors").size()];
        for (int i = 0; i < jsonObject
                .getJsonArray("Actors").size(); i++) {
            this.actors[i] = new Actor();
            this.actors[i].fromJsonObject(jsonObject
                    .getJsonArray("Actors").getJsonObject(i));
        }

        this.ratings = new Rating[jsonObject
                .getJsonArray("Ratings").size()];
        for (int i = 0; i < jsonObject
                .getJsonArray("Ratings").size(); i++) {
            this.ratings[i] = new Rating();
            this.ratings[i].fromJsonObject(jsonObject
                    .getJsonArray("Ratings").getJsonObject(i));
        }
    }

    public Movie getJsonFromFile(final String filename) {
        String json = "";

        try {
            List<String> lines =
                    Files.readAllLines(
                            Paths.get(filename),
                            Charset.defaultCharset());

            for (String line : lines) {
                json += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (json.length() != 0) {
            fromJson(json);
        }
        return this;
    }
}
