package com.nguyenhaidang_dangnh2406.moviescgv.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nguyenhaidang_dangnh2406.moviescgv.model.fravorites.MovieFavorites;

import java.util.List;

@Dao
public interface FavoriteDAO {

    @Insert
    public void insertFavorite(MovieFavorites movieFavorites);

    @Query("select * from favorite_movie")
    public List<MovieFavorites> getAllListFavorite();

    @Query("select * from favorite_movie where idMovie = :id")
    public List<MovieFavorites> isCheckMovieExit(String id);

    @Query("select exists (select 1 from favorite_movie where idMovie = :id)")
    public boolean isCheckedMovieExit(String id);

    @Query("delete from favorite_movie where idMovie = :id")
    public void deleteMovieFavorite(String id);
}
