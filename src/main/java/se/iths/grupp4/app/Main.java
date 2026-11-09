package se.iths.grupp4.app;

import se.iths.grupp4.app.model.HorrorMovie;
import se.iths.grupp4.app.storage.HorrorMovieStorage;
import se.iths.grupp4.app.storage.MovieStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static MovieStorage<HorrorMovie> horrorStorage =
            new HorrorMovieStorage(Path.of("data", "horror-movies.json"));

    static void main() {
        List<HorrorMovie> horrorMovies = List.of(
            new HorrorMovie(1, "The Thing"),
            new HorrorMovie(2, "Backrooms"),
            new HorrorMovie(3, "Talk To Me"),
            new HorrorMovie(4, "Heretic"),
            new HorrorMovie(5, "Cube")
        );

        for (HorrorMovie movie : horrorMovies){
            try {
                horrorStorage.save(movie);
            } catch (IOException e) {
                IO.println("Fel: " + e.getMessage());
            }
        }
    }
}