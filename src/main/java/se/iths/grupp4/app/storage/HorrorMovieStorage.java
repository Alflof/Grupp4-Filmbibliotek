package se.iths.grupp4.app.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.grupp4.app.model.HorrorMovie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class HorrorMovieStorage implements MovieStorage<HorrorMovie> {

    ObjectMapper objectMapper = new ObjectMapper();
    private Path horrorMovieFilePath;

    public HorrorMovieStorage(Path filePath) {
        this.horrorMovieFilePath = filePath;
    }

    @Override
    public List<HorrorMovie> findAll() throws IOException {
        if (Files.exists(horrorMovieFilePath) && Files.size(horrorMovieFilePath) > 0) {
            return objectMapper.readValue(horrorMovieFilePath.toFile(),
                    new TypeReference<>() {
                    });
        } else {
            return new ArrayList<>(); //new array list istället för path of säkrare?
        }
    }

    @Override
    public HorrorMovie findById(int id) throws IOException {
        List<HorrorMovie> horrorList = findAll();
        for (HorrorMovie horrorMovie : horrorList) {
            if (horrorMovie.getId() == id) {
                return horrorMovie;
            }
        }
        return null;
    }

    @Override
    public void save(HorrorMovie movie) throws IOException {
        HorrorMovie horrorMovie = findById(movie.getId());
        if (horrorMovie == null) {
            List<HorrorMovie> horrorList = findAll();
            horrorList.add(movie);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(horrorMovieFilePath.toFile(), horrorList);
            IO.println("Skrev till fil " + horrorList);
        } else {
            IO.println("Filmen finns redan i biblioteket.");
        }
    }
}
