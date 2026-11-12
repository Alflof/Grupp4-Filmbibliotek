package se.iths.grupp4.app;

import se.iths.grupp4.app.model.ComedyMovie;
import se.iths.grupp4.app.model.DramaMovie;
import se.iths.grupp4.app.model.HorrorMovie;
import se.iths.grupp4.app.model.Movie;
import se.iths.grupp4.app.storage.ComedyMovieStorage;
import se.iths.grupp4.app.storage.DramaMovieStorage;
import se.iths.grupp4.app.storage.HorrorMovieStorage;
import se.iths.grupp4.app.storage.MovieStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static MovieStorage<HorrorMovie> horrorStorage =
            new HorrorMovieStorage(Path.of("data", "horror-movies.json"));

    private static MovieStorage<DramaMovie> dramaStorage =
            new DramaMovieStorage(Path.of("data", "drama-movies.json"));

    private static MovieStorage<ComedyMovie> comedyStorage =
            new ComedyMovieStorage(Path.of("data", "comedy-movie.json"));

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

        List<ComedyMovie> comedyMovies = List.of(
                new ComedyMovie(1, "Superbad"),
                new ComedyMovie(2, "The Hangover"),
                new ComedyMovie(3, "Step Brothers"),
                new ComedyMovie(4, "Dumb and Dumber"),
                new ComedyMovie(5, "Mean Girls")
        );

        for (ComedyMovie movie : comedyMovies) {
            try {
                comedyStorage.save(movie);
            } catch (IOException e) {
                IO.println("Fel: " + e.getMessage());
            }

        }
        boolean keepRunning = true;
        while (keepRunning) {

            String choice = IO.readln("\n1.Visa filmer i en viss kategori\n" +
                    "2.Visa alla filmer\n" + "3.Avsluta\n" + "Välj ett alternativ: ");
            String categoryChoice;

            switch (choice) {
                case "1" -> {
                    categoryChoice = IO.readln("\n1.Skräck\n2.Drama\n3.Komedi\nVälj en kategori: ");
                    printCategory(categoryChoice);
                }
                case "2" -> printAll();
                case "3" -> keepRunning = false;
                default -> IO.println("Ogiltigt val, försök igen");
            }
        }

    }

    static void printCategory(String categoryChoice) {
        try {
            if (categoryChoice.equals("1")) {
                List<HorrorMovie> allMovies = horrorStorage.findAll();
                for (Movie movie : allMovies) {
                    IO.println(movie.getId() + ". " + movie.getTitle() + " - " + movie.category());
                }
            }
            if (categoryChoice.equals("2")) {
                List<DramaMovie> allMovies = dramaStorage.findAll();
                for (Movie movie : allMovies) {
                    IO.println(movie.getId() + ". " + movie.getTitle() + " - " + movie.category());
                }
            }
            if (categoryChoice.equals("3")) {
                List<ComedyMovie> allMovies = comedyStorage.findAll();
                for (Movie movie : allMovies) {
                    IO.println(movie.getId() + ". " + movie.getTitle() + " - " + movie.category());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static void printAll() {
        List<Movie> allMovies = new ArrayList<>();
        try {
            for (Movie movie : horrorStorage.findAll()) {
                allMovies.add(movie);
            }
            for (Movie movie : dramaStorage.findAll()) {
                allMovies.add(movie);
            }
            for (Movie movie : comedyStorage.findAll()) {
                allMovies.add(movie);
            }
            for (Movie movie : allMovies) {
                IO.println(movie.getId() + ". " + movie.getTitle() + " - " + movie.category());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
