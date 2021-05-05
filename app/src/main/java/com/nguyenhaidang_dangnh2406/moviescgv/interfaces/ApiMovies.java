package com.nguyenhaidang_dangnh2406.moviescgv.interfaces;

import com.nguyenhaidang_dangnh2406.moviescgv.model.movie_detail.ListMember;
import com.nguyenhaidang_dangnh2406.moviescgv.model.movie_detail.MoviesDetail;
import com.nguyenhaidang_dangnh2406.moviescgv.model.movies.Movies;
import com.nguyenhaidang_dangnh2406.moviescgv.model.movies.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiMovies {

    @GET("3/movie/popular?api_key=414ffc7cfe79b04554b68edfa48428d3&language=en-US&page=1")
    Call<Movies> getListMovie();

    @GET("3/movie/{id}?api_key=414ffc7cfe79b04554b68edfa48428d3")
    Call<MoviesDetail> getDetailMovie(@Path("id") int id);

    @GET("3/movie/{id_movie}/credits?api_key=414ffc7cfe79b04554b68edfa48428d3")
    Call<ListMember> getListMemberMovie(@Path("id_movie") int id);

}
