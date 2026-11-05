package se.iths.grupp4.app.storage;

import se.iths.grupp4.app.model.DramaMovie;

import java.io.IOException;
import java.util.List;

public interface MovieStorage<T> {

    void save(T movie) throws IOException;

    void save(DramaMovie movie) throws IOException;

    T findById(int id) throws IOException;

    List<T> findAll() throws IOException;

}
