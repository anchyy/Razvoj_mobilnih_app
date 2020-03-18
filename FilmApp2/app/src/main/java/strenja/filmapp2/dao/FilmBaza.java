package strenja.filmapp2.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import strenja.filmapp2.model.Film;

@Database(entities = {Film.class}, version = 1, exportSchema = false)
//@TypeConverters({DateConverter.class})
public abstract class FilmBaza extends RoomDatabase {

    public abstract FilmDAO FilmDAO();
    private static FilmBaza INSTANCE;

    public static  FilmBaza getBaza (Context context){
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), FilmBaza.class, "film-baza").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
}
