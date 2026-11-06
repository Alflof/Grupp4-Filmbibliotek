package se.iths.grupp4.app.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.iths.grupp4.app.model.HorrorMovie;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HorrorMovieStorageTest {

    @TempDir
    Path tempDir;
    HorrorMovieStorage horrorMovieStorage;

    @BeforeEach
    void beforeEach() {
        Path file = tempDir.resolve("horror storage test.json");
        horrorMovieStorage = new HorrorMovieStorage(file);
    }

    @Test
    void savedMovieCanBeFoundById() throws IOException {
        horrorMovieStorage.save(new HorrorMovie(1, "Horror movie test"));
        HorrorMovie actualMovie = horrorMovieStorage.findById(1);
        assertEquals(1, actualMovie.getId());
        assertEquals("Horror movie test", actualMovie.getTitle());
    }

    @Test
    void saveIgnoresDuplicateId() throws IOException {
        horrorMovieStorage.save(new HorrorMovie(1, "Horror movie test"));
        horrorMovieStorage.save(new HorrorMovie(1, "Horror movie test 2"));
        List<HorrorMovie> horrorList = horrorMovieStorage.findAll();
        assertEquals(1, horrorList.size());
        assertEquals("Horror movie test", horrorList.get(0).getTitle());
    }

    @Test
    void findAllReturnsEmptyWhenNothingSaved() throws IOException {
        assertTrue(horrorMovieStorage.findAll().isEmpty());
    }

    @Test
    void findByIdReturnsNullWhenIdNotSaved() throws IOException {
        horrorMovieStorage.save(new HorrorMovie(1, "Horror movie test"));
        assertNull(horrorMovieStorage.findById(2));
    }
}