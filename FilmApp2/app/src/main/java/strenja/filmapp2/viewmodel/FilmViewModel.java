package strenja.filmapp2.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import strenja.filmapp2.dao.FilmBaza;
import strenja.filmapp2.dao.FilmDAO;
import strenja.filmapp2.model.Film;

public class FilmViewModel extends AndroidViewModel {

    FilmDAO filmDAO;
    private Film film;
    private LiveData<List<Film>> filmovi;

    public void setFilm(Film film) {
        this.film = film;
    }

    public Film getFilm() {
        return film;
    }

    public FilmViewModel(Application application) {
        super(application);
        filmDAO = FilmBaza.getBaza(application.getApplicationContext()).FilmDAO();

    }
    public LiveData<List<Film>> dohvatiFilmove(){
        filmovi = filmDAO.dohvatiFilmove();
        return filmovi;
    }
    public void dodajNoviFilm() {

        filmDAO.dodajNoviFilm(film);
    }

    public void promjeniFilm() {

        filmDAO.promjeniFilm(film);
    }

    public void obrisiOsobu() {

        filmDAO.obrisiFilm(film);
    }





}
