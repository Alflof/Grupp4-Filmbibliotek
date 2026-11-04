package se.iths.grupp4.app.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.grupp4.app.model.HorrorMovie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class HorrorMovieStorage implements MovieStorage<HorrorMovie>{

    ObjectMapper mapper = new ObjectMapper();
    private Path horrorMovieFilePath;

    public HorrorMovieStorage(Path filePath){
        this.horrorMovieFilePath = filePath;
    }

    @Override
    public void save(HorrorMovie movie) throws IOException {
        //anropa findById(). Finns id redan, gör ingenting.
        // Annars: anropa findAll(), lägg till filmen i listan och skriv hela listan till fil.
        // Vid första anropet skapas filen

        HorrorMovie horrorMovie = findById(movie.getId());

        if (horrorMovie == null){
            //anropa findAll(), lägg till filmen i listan och skriv hela listan till fil.
            // Vid första anropet skapas filen
            List<HorrorMovie> horrorList = findAll();
            horrorList.add(movie);
            //skriv till fil

        } else {
            //gör ingenting
        }
    }

    @Override
    public HorrorMovie findById(int id) throws IOException { //anropa findAll() - om id:t saknas, returnera null
        List<HorrorMovie> horrorList = findAll();
        for (HorrorMovie horrorMovie : horrorList){
            if (horrorMovie.getId() == id) {
                return horrorMovie;
            }
        }
        return null;
    }

    @Override
    public List<HorrorMovie> findAll() throws IOException { //läs listan; saknas filen, returnera en tom lista
        List<HorrorMovie> horrorList;
        if (Files.exists(horrorMovieFilePath)) {
            horrorList = mapper.readValue(horrorMovieFilePath.toFile(),
                    new TypeReference<List<HorrorMovie>>() {});
            return horrorList;
        } else {
            return List.of();
        }
    }
}
