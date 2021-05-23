package com.orzechowski.lab2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "phones")
public class Phones {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @NonNull
    @ColumnInfo(name = "producent")
    private String producent;

    @NonNull
    @ColumnInfo(name = "model")
    private String model;

    @NonNull
    @ColumnInfo(name = "android_wersja")
    private String wersja;

    @NonNull
    @ColumnInfo(name = "strona_www")
    private String strona;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getProducent() {
        return producent;
    }

    public void setProducent(@NonNull String producent) {
        this.producent = producent;
    }

    @NonNull
    public String getModel() {
        return model;
    }

    public void setModel(@NonNull String model) {
        this.model = model;
    }

    @NonNull
    public String getWersja() {
        return wersja;
    }

    public void setWersja(@NonNull String wersja) {
        this.wersja = wersja;
    }

    @NonNull
    public String getStrona() {
        return strona;
    }

    public void setStrona(@NonNull String strona) {
        this.strona = strona;
    }

    public Phones(@NonNull String producent, @NonNull String model, @NonNull String wersja,
                  @NonNull String strona)
    {
        this.producent = producent;
        this.model = model;
        this.wersja = wersja;
        this.strona = strona;
    }
}
