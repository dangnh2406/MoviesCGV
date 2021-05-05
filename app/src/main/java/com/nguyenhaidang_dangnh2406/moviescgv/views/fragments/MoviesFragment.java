package com.nguyenhaidang_dangnh2406.moviescgv.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nguyenhaidang_dangnh2406.moviescgv.R;
import com.nguyenhaidang_dangnh2406.moviescgv.adapters.MoviesAdapter;
import com.nguyenhaidang_dangnh2406.moviescgv.interfaces.ApiMovies;
import com.nguyenhaidang_dangnh2406.moviescgv.interfaces.GetCountingStar;
import com.nguyenhaidang_dangnh2406.moviescgv.model.movies.Movies;
import com.nguyenhaidang_dangnh2406.moviescgv.model.movies.Result;
import com.nguyenhaidang_dangnh2406.moviescgv.presenters.MovieApi;
import com.nguyenhaidang_dangnh2406.moviescgv.views.activitys.DetailMovieActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviesFragment extends Fragment implements View.OnClickListener {
    private ApiMovies mApiMovies;
    private List<Result> mResultListMovies;
    private RecyclerView mRecyclerView;
    private MoviesAdapter moviesAdapter;
    private RecyclerView.LayoutManager mlayoutManager;
    private GetCountingStar mGetCountingStar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MoviesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoviesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoviesFragment newInstance(String param1, String param2) {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static MoviesFragment newInstance() {
        MoviesFragment fragment = new MoviesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        callMovies(view);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.row_movies_cardview:
                int id_movie = (int) v.getTag();
                Intent intent = new Intent(getContext(), DetailMovieActivity.class);
                intent.putExtra("id_movie",id_movie);
                startActivity(intent);

                break;
            default:
                break;
        }

    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.fragment_movies_recycler_view);
        mlayoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(mlayoutManager);
        mRecyclerView.setHasFixedSize(true);

        moviesAdapter = new MoviesAdapter(getContext(), mResultListMovies, this);
        mRecyclerView.setAdapter(moviesAdapter);

        mRecyclerView.setOnClickListener(this);

    }

    private void callMovies(View view) {

        mApiMovies = MovieApi.getMovies().create(ApiMovies.class);
        mApiMovies.getListMovie().enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (response.isSuccessful()) {
                    mResultListMovies = response.body().getResults();
                    Toast.makeText(getContext(), "Hello guys! ", Toast.LENGTH_SHORT).show();
                    initView(view);
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Toast.makeText(getContext(), "Vui long kiem tra ket noi internet!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (!fragment.isAdded()){
            try {
                getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_movies,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }catch (Exception e){

            }
        }else {
            transaction.show(fragment);
        }
    }

}