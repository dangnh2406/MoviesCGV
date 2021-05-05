package com.nguyenhaidang_dangnh2406.moviescgv.presenters;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieApi {
    public static Retrofit retrofit;
    public static final String BASE_URL = "https://api.themoviedb.org/";

    public static Retrofit getMovies(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
