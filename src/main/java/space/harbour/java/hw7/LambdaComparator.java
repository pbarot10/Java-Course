package main.java.space.harbour.java.hw7;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class LambdaComparator {
    public void sortByReleaseYear(final List<Movie> m) {
        m.sort(Comparator.comparingInt(Movie::getYear));
    }

    public void sortByRatings(final List<Movie> m) {
        m.sort(Comparator.comparing(movie -> Arrays.stream(movie.getRatings())
                .filter(rating -> "Internet Movie Database"
                    .equals(rating.getSource())).findAny()
                        .get().getValue()));
        System.out.println();
    }

    public void sortByLength(final List<Movie> m) {
        m.sort(Comparator.comparingInt(Movie::getRuntime));
    }

    public List<Movie> filterByDirector(final List<Movie> m,
                                        final Director director) {
        return m.stream()
                .filter(movie -> movie.getDirectors()
                        .getName().equals(director.getName()))
                .collect(Collectors.toList());
    }

    public List<Movie> filterByActor(final List<Movie> m, final Actor actor) {
        return m.stream()
                .filter(movie -> Arrays.stream(movie.getActors())
                        .anyMatch(a -> actor.getName().equals(a.getName())))
                .collect(Collectors.toList());
    }

    public List<Movie> filterByGenre(final List<Movie> m, final String genre) {
        return m.stream()
                .filter(movie -> Arrays.stream(movie
                        .getGenres()).anyMatch(genre::equals))
                .collect(Collectors.toList());
    }
}
