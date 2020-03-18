package strenja.filmapp2.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import strenja.filmapp2.model.Film;

@Dao
public interface FilmDAO {

    @Query("select * from film order by datumIzlaska")
    LiveData<List<Film>> dohvatiFilmove();

    @Insert
    void dodajNoviFilm (Film film);

    @Update
    void promjeniFilm(Film film);


    @Delete
    void obrisiFilm (Film film);

}
