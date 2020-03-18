package strenja.filmapp2.dao;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class FilmBaza_Impl extends FilmBaza {
  private volatile FilmDAO _filmDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `film` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Naslov` TEXT NOT NULL, `redatelj` TEXT, `datumIzlaska` TEXT, `zanr` TEXT, `putanjaSlika` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"f8f5b16a797623f8dd91315d5b290181\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `film`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsFilm = new HashMap<String, TableInfo.Column>(6);
        _columnsFilm.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsFilm.put("Naslov", new TableInfo.Column("Naslov", "TEXT", true, 0));
        _columnsFilm.put("redatelj", new TableInfo.Column("redatelj", "TEXT", false, 0));
        _columnsFilm.put("datumIzlaska", new TableInfo.Column("datumIzlaska", "TEXT", false, 0));
        _columnsFilm.put("zanr", new TableInfo.Column("zanr", "TEXT", false, 0));
        _columnsFilm.put("putanjaSlika", new TableInfo.Column("putanjaSlika", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFilm = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFilm = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFilm = new TableInfo("film", _columnsFilm, _foreignKeysFilm, _indicesFilm);
        final TableInfo _existingFilm = TableInfo.read(_db, "film");
        if (! _infoFilm.equals(_existingFilm)) {
          throw new IllegalStateException("Migration didn't properly handle film(strenja.filmapp2.model.Film).\n"
                  + " Expected:\n" + _infoFilm + "\n"
                  + " Found:\n" + _existingFilm);
        }
      }
    }, "f8f5b16a797623f8dd91315d5b290181", "dc03db6e7a73111463a8db125a37c734");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "film");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `film`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public FilmDAO FilmDAO() {
    if (_filmDAO != null) {
      return _filmDAO;
    } else {
      synchronized(this) {
        if(_filmDAO == null) {
          _filmDAO = new FilmDAO_Impl(this);
        }
        return _filmDAO;
      }
    }
  }
}
