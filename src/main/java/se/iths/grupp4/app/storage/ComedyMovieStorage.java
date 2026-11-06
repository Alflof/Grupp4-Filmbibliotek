package se.iths.grupp4.app.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.grupp4.app.model.ComedyMovie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ComedyMovieStorage implements MovieStorage<ComedyMovie> {

    private Path file;
    private ObjectMapper mapper = new ObjectMapper();

    public ComedyMovieStorage(Path file){
        this.file = file;
    }

    @Override
    public List<ComedyMovie> findAll() throws IOException {
        if (!Files.exists(file) || Files.size(file) == 0) {
            return new ArrayList<>();
        }
        return mapper.readValue(file.toFile(), new TypeReference<List<ComedyMovie>>() {});
    }

    @Override
    public ComedyMovie findById(int id) throws IOException {
        for (ComedyMovie movie : findAll()) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public void save(ComedyMovie movie) throws IOException {
        if (findById(movie.getId()) != null){
            return;
        }
        List<ComedyMovie> movies = findAll();
        movies.add(movie);
        mapper.writerWithDefaultPrettyPrinter().writeValue(file.toFile(), movies);
    }

}
