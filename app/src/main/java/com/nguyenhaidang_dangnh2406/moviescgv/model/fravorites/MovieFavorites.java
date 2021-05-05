package com.nguyenhaidang_dangnh2406.moviescgv.model.fravorites;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_movie")
public class MovieFavorites {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String idMovie;
    private String titleMovie;
    private String releaseData;
    private String overview;
    private String img;

    public MovieFavorites() {
    }

    public MovieFavorites(String idMovie, String titleMovie, String releaseData, String overview, String img) {
        this.idMovie = idMovie;
        this.titleMovie = titleMovie;
        this.releaseData = releaseData;
        this.overview = overview;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitleMovie() {
        return titleMovie;
    }

    public void setTitleMovie(String titleMovie) {
        this.titleMovie = titleMovie;
    }

    public String getReleaseData() {
        return releaseData;
    }

    public void setReleaseData(String releaseData) {
        this.releaseData = releaseData;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
