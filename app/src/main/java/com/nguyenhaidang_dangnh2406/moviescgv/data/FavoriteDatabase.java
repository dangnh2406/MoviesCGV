package com.nguyenhaidang_dangnh2406.moviescgv.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nguyenhaidang_dangnh2406.moviescgv.interfaces.FavoriteDAO;
import com.nguyenhaidang_dangnh2406.moviescgv.model.fravorites.MovieFavorites;

@Database(entities = MovieFavorites.class , version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {
    public static final String DATA_NAME ="favorite.db";
    private static FavoriteDatabase instance;

    public static synchronized FavoriteDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context,FavoriteDatabase.class,DATA_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract FavoriteDAO favoriteDAO();
}
