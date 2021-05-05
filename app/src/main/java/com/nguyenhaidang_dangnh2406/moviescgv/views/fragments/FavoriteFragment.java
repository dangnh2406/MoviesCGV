package com.nguyenhaidang_dangnh2406.moviescgv.views.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyenhaidang_dangnh2406.moviescgv.R;
import com.nguyenhaidang_dangnh2406.moviescgv.adapters.FavoritesMovieAdapter;
import com.nguyenhaidang_dangnh2406.moviescgv.adapters.MoviesAdapter;
import com.nguyenhaidang_dangnh2406.moviescgv.data.FavoriteDatabase;
import com.nguyenhaidang_dangnh2406.moviescgv.interfaces.GetCountingStar;
import com.nguyenhaidang_dangnh2406.moviescgv.model.fravorites.MovieFavorites;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment  {
    private RecyclerView mRecyclerView;
    private List<MovieFavorites> mMovieFavoritesList;
    private FavoritesMovieAdapter mMoviesAdapter;
    private GetCountingStar mGetCountingStar;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        initView(view);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getView() != null) {
        loadData();
        }
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        try {
            mGetCountingStar = (GetCountingStar) activity;
        }catch (Exception e){

        }
    }

    private void initView(View view){
        mRecyclerView = view.findViewById(R.id.favorite_recycler_view);
        loadData();

    }

    private void loadData(){
        mMoviesAdapter = new FavoritesMovieAdapter(getContext(),mMovieFavoritesList);
        mMovieFavoritesList = FavoriteDatabase.getInstance(getContext()).favoriteDAO().getAllListFavorite();
        mMoviesAdapter.changeData(mMovieFavoritesList);
        mMoviesAdapter.notifyDataSetChanged();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mMoviesAdapter);
        mRecyclerView.setHasFixedSize(true);
        mGetCountingStar.getCoutingStar(mMovieFavoritesList.size());
    }


}