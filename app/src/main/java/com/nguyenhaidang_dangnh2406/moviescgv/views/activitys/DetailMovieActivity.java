package com.nguyenhaidang_dangnh2406.moviescgv.views.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyenhaidang_dangnh2406.moviescgv.R;
import com.nguyenhaidang_dangnh2406.moviescgv.adapters.MemberCastAdapter;
import com.nguyenhaidang_dangnh2406.moviescgv.interfaces.ApiMovies;
import com.nguyenhaidang_dangnh2406.moviescgv.model.movie_detail.Cast;
import com.nguyenhaidang_dangnh2406.moviescgv.model.movie_detail.ListMember;
import com.nguyenhaidang_dangnh2406.moviescgv.model.movie_detail.MoviesDetail;
import com.nguyenhaidang_dangnh2406.moviescgv.presenters.MovieApi;
import com.nguyenhaidang_dangnh2406.moviescgv.views.fragments.SettingFragment;
import com.nguyenhaidang_dangnh2406.moviescgv.views.fragments.TrailerMoviesFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovieActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar mToolbar;
    private ImageView mPosterBackgroundMovie_img;
    private ImageView mPosterAvtMovie_img;
    private TextView mTitleMovie_txt;
    private TextView mReleaseDataMovie_txt;
    private TextView mRatingMovie_txt;
    private TextView mIntroduceMovie_txt;
    private TextView mIntroduce_txt;
    private TextView mCastCrew;
    private Button mTrailer_btn;
    private ApiMovies mApiMovies;
    private ProgressBar mProcessBar;
    private List<MoviesDetail> mMoviesDetailList;
    private RecyclerView mRecyclerView;
    private MemberCastAdapter mMemberCastAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Cast> mCastList;
    private String uri_movie = "http://image.tmdb.org/t/p/w500/";
    private Intent intent;
    private int id_movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        intent = getIntent();
        id_movie = intent.getIntExtra("id_movie", 0);

        callMovies();
        initView();
        setmToolbar();
        callCastMovie();
    }


    private void initView() {
        mToolbar = findViewById(R.id.detail_movie_toolbar);
        mIntroduceMovie_txt = findViewById(R.id.detail_movie_introduce_txt);
        mPosterAvtMovie_img = findViewById(R.id.detai_movie_poster_avt_img);
        mPosterBackgroundMovie_img = findViewById(R.id.detai_movie_poster_background_img);
        mRatingMovie_txt = findViewById(R.id.detail_movie_rating_txt);
        mReleaseDataMovie_txt = findViewById(R.id.detail_movie_release_date_txt);
        mTitleMovie_txt = findViewById(R.id.detail_movie_title_txt);
        mIntroduce_txt = findViewById(R.id.detail_movie_introduce);
        mCastCrew = findViewById(R.id.detail_movie_cast_crew);
        mProcessBar = findViewById(R.id.detail_movie_progress_bar);
        mRecyclerView = findViewById(R.id.detail_movie_recycler_view);
        mTrailer_btn = findViewById(R.id.detail_movie_preview_trailer_btn);
        mLayoutManager = new LinearLayoutManager(DetailMovieActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mMoviesDetailList = new ArrayList<>();
        mCastList = new ArrayList<>();

        mTrailer_btn.setOnClickListener(this);

        mProcessBar.setVisibility(View.VISIBLE);


    }

    private void setmToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Detail Movie");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void callMovies() {

        mApiMovies = MovieApi.getMovies().create(ApiMovies.class);
        mApiMovies.getDetailMovie(id_movie).enqueue(new Callback<MoviesDetail>() {
            @Override
            public void onResponse(Call<MoviesDetail> call, Response<MoviesDetail> response) {
                if (response.isSuccessful() && response != null) {
                    mMoviesDetailList = Collections.singletonList(response.body());
                }
                showDetailMovie();
                mProcessBar.setVisibility(View.INVISIBLE);
                Log.d("TAG",mMoviesDetailList.get(0).getHomepage());
            }

            @Override
            public void onFailure(Call<MoviesDetail> call, Throwable t) {

            }
        });
    }

    private void callCastMovie() {
        mApiMovies = MovieApi.getMovies().create(ApiMovies.class);
        mApiMovies.getListMemberMovie(id_movie).enqueue(new Callback<ListMember>() {
            @Override
            public void onResponse(Call<ListMember> call, Response<ListMember> response) {
                if (response.isSuccessful() && response != null) {
                    mCastList = response.body().getCast();

                    mMemberCastAdapter = new MemberCastAdapter(mCastList, DetailMovieActivity.this);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setHasFixedSize(true);
                    mRecyclerView.setAdapter(mMemberCastAdapter);

                }
            }

            @Override
            public void onFailure(Call<ListMember> call, Throwable t) {

            }
        });
    }

    private void showDetailMovie() {

        if (mMoviesDetailList != null) {
            mIntroduce_txt.setText("Introduce");
            mCastCrew.setText("Cast & Crew");
            mRatingMovie_txt.setText(mMoviesDetailList.get(0).getVoteCount().toString());
            mReleaseDataMovie_txt.setText(mMoviesDetailList.get(0).getReleaseDate());
            mTitleMovie_txt.setText(mMoviesDetailList.get(0).getTitle());
            mIntroduceMovie_txt.setText(mMoviesDetailList.get(0).getOverview());
            Picasso.get().load(uri_movie + mMoviesDetailList.get(0).getBackdropPath()).into(mPosterBackgroundMovie_img);
            Picasso.get().load(uri_movie + mMoviesDetailList.get(0).getPosterPath()).into(mPosterAvtMovie_img);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_movie_preview_trailer_btn:
                showWebTrailerMovie(TrailerMoviesFragment.newInstance(mMoviesDetailList.get(0).getHomepage()));
                break;
            default:
                break;
        }
    }

    private void showWebTrailerMovie(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()){
            try {
                getFragmentManager().beginTransaction();
                transaction.replace(R.id.detail_movie_frame_layout,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }catch (Exception e){

            }
        }else {
            transaction.show(fragment);
        }
    }
}