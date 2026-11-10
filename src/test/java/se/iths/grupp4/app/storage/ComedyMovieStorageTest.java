package se.iths.grupp4.app.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.iths.grupp4.app.model.ComedyMovie;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComedyMovieStorageTest {

    @TempDir
    Path tempDir;

    @Test
    void saveAndFindById() throws IOException {
        ComedyMovieStorage storage = new ComedyMovieStorage(tempDir.resolve("comedy.json"));
        storage.save(new ComedyMovie(1, "Superbad"));

        ComedyMovie found = storage.findById(1);

        assertEquals(1, found.getId());
        assertEquals("Superbad", found.getTitle());
    }

    @Test
    void duplicateIdIsNotSaved() throws IOException {
        ComedyMovieStorage storage = new ComedyMovieStorage(tempDir.resolve("comedy.json"));
        storage.save(new ComedyMovie(1, "Superbad"));
        storage.save(new ComedyMovie(1, "The Hangover"));

        List<ComedyMovie> movies = storage.findAll();

        assertEquals(1, movies.size());
        assertEquals("Superbad", movies.get(0).getTitle());
    }

    @Test
    void findAllWithoutSavingGivesEmptyList() throws IOException {
        ComedyMovieStorage storage = new ComedyMovieStorage(tempDir.resolve("comedy.json"));

        List<ComedyMovie> movies = storage.findAll();

        assertTrue(movies.isEmpty());
    }

    @Test
    void findByMissingIdGivesNull() throws IOException {
        ComedyMovieStorage storage = new ComedyMovieStorage(tempDir.resolve("comedy.json"));
        storage.save(new ComedyMovie(1, "Suerbad"));

        ComedyMovie found = storage.findById(99);

        assertNull(found);
    }
}
