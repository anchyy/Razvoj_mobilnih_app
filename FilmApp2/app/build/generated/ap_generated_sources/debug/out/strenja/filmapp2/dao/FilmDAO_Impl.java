package strenja.filmapp2.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker.Observer;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import strenja.filmapp2.model.Film;

@SuppressWarnings("unchecked")
public final class FilmDAO_Impl implements FilmDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfFilm;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfFilm;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfFilm;

  public FilmDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFilm = new EntityInsertionAdapter<Film>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `film`(`id`,`Naslov`,`redatelj`,`datumIzlaska`,`zanr`,`putanjaSlika`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Film value) {
        stmt.bindLong(1, value.getId());
        if (value.getNaslov() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNaslov());
        }
        if (value.getRedatelj() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getRedatelj());
        }
        if (value.getDatumIzlaska() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDatumIzlaska());
        }
        if (value.getZanr() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getZanr());
        }
        if (value.getPutanjaSlika() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getPutanjaSlika());
        }
      }
    };
    this.__deletionAdapterOfFilm = new EntityDeletionOrUpdateAdapter<Film>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `film` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Film value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfFilm = new EntityDeletionOrUpdateAdapter<Film>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `film` SET `id` = ?,`Naslov` = ?,`redatelj` = ?,`datumIzlaska` = ?,`zanr` = ?,`putanjaSlika` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Film value) {
        stmt.bindLong(1, value.getId());
        if (value.getNaslov() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNaslov());
        }
        if (value.getRedatelj() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getRedatelj());
        }
        if (value.getDatumIzlaska() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDatumIzlaska());
        }
        if (value.getZanr() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getZanr());
        }
        if (value.getPutanjaSlika() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getPutanjaSlika());
        }
        stmt.bindLong(7, value.getId());
      }
    };
  }

  @Override
  public void dodajNoviFilm(Film film) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfFilm.insert(film);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void obrisiFilm(Film film) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfFilm.handle(film);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void promjeniFilm(Film film) {
    __db.beginTransaction();
    try {
      __updateAdapterOfFilm.handle(film);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Film>> dohvatiFilmove() {
    final String _sql = "select * from film order by datumIzlaska";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<Film>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<Film> compute() {
        if (_observer == null) {
          _observer = new Observer("film") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfNaslov = _cursor.getColumnIndexOrThrow("Naslov");
          final int _cursorIndexOfRedatelj = _cursor.getColumnIndexOrThrow("redatelj");
          final int _cursorIndexOfDatumIzlaska = _cursor.getColumnIndexOrThrow("datumIzlaska");
          final int _cursorIndexOfZanr = _cursor.getColumnIndexOrThrow("zanr");
          final int _cursorIndexOfPutanjaSlika = _cursor.getColumnIndexOrThrow("putanjaSlika");
          final List<Film> _result = new ArrayList<Film>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Film _item;
            _item = new Film();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpNaslov;
            _tmpNaslov = _cursor.getString(_cursorIndexOfNaslov);
            _item.setNaslov(_tmpNaslov);
            final String _tmpRedatelj;
            _tmpRedatelj = _cursor.getString(_cursorIndexOfRedatelj);
            _item.setRedatelj(_tmpRedatelj);
            final String _tmpDatumIzlaska;
            _tmpDatumIzlaska = _cursor.getString(_cursorIndexOfDatumIzlaska);
            _item.setDatumIzlaska(_tmpDatumIzlaska);
            final String _tmpZanr;
            _tmpZanr = _cursor.getString(_cursorIndexOfZanr);
            _item.setZanr(_tmpZanr);
            final String _tmpPutanjaSlika;
            _tmpPutanjaSlika = _cursor.getString(_cursorIndexOfPutanjaSlika);
            _item.setPutanjaSlika(_tmpPutanjaSlika);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }
}
