package se.iths.grupp4.app;

import se.iths.grupp4.app.model.DramaMovie;
import se.iths.grupp4.app.model.HorrorMovie;
import se.iths.grupp4.app.storage.DramaMovieStorage;
import se.iths.grupp4.app.storage.HorrorMovieStorage;
import se.iths.grupp4.app.storage.MovieStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {

    private static MovieStorage<HorrorMovie> horrorStorage =
            new HorrorMovieStorage(Path.of("data", "horror-movies.json"));

    private static MovieStorage<DramaMovie> dramaStorage =
            new DramaMovieStorage(Path.of("data", "drama-movies.json"));

    static void main() {
        List<HorrorMovie> horrorMovies = List.of(
                new HorrorMovie(1, "The Thing"),
                new HorrorMovie(2, "Backrooms"),
                new HorrorMovie(3, "Talk To Me"),
                new HorrorMovie(4, "Heretic"),
                new HorrorMovie(5, "Cube")
        );

        for (HorrorMovie movie : horrorMovies) {
            try {
                horrorStorage.save(movie);
            } catch (IOException e) {
                IO.println("Fel: " + e.getMessage());
            }
        }

        List<DramaMovie> dramaMovies = List.of(
                new DramaMovie(1, "Manchester by the Sea"),
                new DramaMovie(2, "Short Term 12"),
                new DramaMovie(3, "Leave No Trace"),
                new DramaMovie(4, "En oväntad vänskap"),
                new DramaMovie(5, "The notebook")
        );
        for (DramaMovie movie : dramaMovies) {
            try {
                dramaStorage.save(movie);
            } catch (IOException e) {
                IO.println(e.getMessage());
            }
        }

    }
}