package strenja.filmapp2.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(tableName = "film")

public class Film implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @NonNull
    @ColumnInfo(name = "Naslov")
    private String naslov;
    private String redatelj;
    private String datumIzlaska;
    private String zanr;
    private String putanjaSlika;

}
