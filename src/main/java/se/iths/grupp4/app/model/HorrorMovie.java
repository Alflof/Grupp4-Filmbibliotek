package se.iths.grupp4.app.model;

public class HorrorMovie extends Movie {

    public HorrorMovie() {
    }

    public HorrorMovie(int id, String title) {
        super(id, title);
    }

    @Override
    public String category() {
        return "Skräck";
    }
}