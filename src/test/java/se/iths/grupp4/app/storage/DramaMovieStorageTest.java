package se.iths.grupp4.app.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.iths.grupp4.app.model.DramaMovie;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DramaMovieStorageTest {
    @TempDir
    Path tempDir;
    DramaMovieStorage dramaMovieStorage;

    @BeforeEach
    void setUp() {
        Path file = tempDir.resolve("drama-storage-test.json");
        dramaMovieStorage = new DramaMovieStorage(file);

    }

    @Test
    void shouldSaveAndFindMovie() {
        DramaMovie dramaMovie = new DramaMovie(1, "test");
        try {
            dramaMovieStorage.save(dramaMovie);
            dramaMovie = dramaMovieStorage.findById(1);
            assertEquals(1, dramaMovie.getId());
            assertEquals("test", dramaMovie.getTitle());
        } catch (IOException e) {
            IO.println(e.getMessage());
        }


    }

    @Test
    void shouldNotSaveDuplicatedId() {
        DramaMovie dramaMovie1 = new DramaMovie(1, "test1");
        DramaMovie dramaMovie2 = new DramaMovie(1, "test2");
        try {
            dramaMovieStorage.save(dramaMovie1);
            dramaMovieStorage.save(dramaMovie2);
            List<DramaMovie> dramaMovies = dramaMovieStorage.findAll();
            assertEquals(1, dramaMovies.size());
            assertEquals("test1", dramaMovies.get(0).getTitle());
        } catch (IOException e) {
            IO.println(e.getMessage());
        }

    }

    @Test
    void shouldReturnEmptyWhenFileNotExist() {
        try {
            List<DramaMovie> dramaMovies = dramaMovieStorage.findAll();
            assertTrue(dramaMovies.isEmpty());
        } catch (IOException e) {
            IO.println(e.getMessage());
        }

    }

    @Test
    void shouldReturnNullWhenIdNotExist() {
        DramaMovie dramaMovie1 = new DramaMovie(1, "test1");
        try {
            dramaMovieStorage.save(dramaMovie1);
            assertNull(dramaMovieStorage.findById(2));

        } catch (IOException e) {
            IO.println(e.getMessage());
        }

    }
}