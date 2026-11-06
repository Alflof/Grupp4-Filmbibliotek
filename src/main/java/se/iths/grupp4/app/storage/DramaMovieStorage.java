package se.iths.grupp4.app.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.grupp4.app.model.DramaMovie;
import se.iths.grupp4.app.model.Movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DramaMovieStorage implements MovieStorage<DramaMovie> {
    private Path filePath;
    ObjectMapper mapper = new ObjectMapper();


    public DramaMovieStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(DramaMovie movie) throws IOException {
        if (findById(movie.getId()) != null) {
            return;
        }
        List<DramaMovie> dramaMovies = findAll();
        dramaMovies.add(movie);
        mapper.writeValue(filePath.toFile(), dramaMovies);
    }

    @Override
    public DramaMovie findById(int id) throws IOException {
        List<DramaMovie> dramaMovies = findAll();
        for (DramaMovie movie : dramaMovies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public List<DramaMovie> findAll() throws IOException {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }
        return mapper.readValue(filePath.toFile(), new TypeReference<List<DramaMovie>>() {
        });
    }
}
