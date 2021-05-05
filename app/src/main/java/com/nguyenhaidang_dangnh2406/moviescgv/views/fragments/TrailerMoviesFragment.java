package com.nguyenhaidang_dangnh2406.moviescgv.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.nguyenhaidang_dangnh2406.moviescgv.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrailerMoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrailerMoviesFragment extends Fragment {
    private WebView mWebViewTrailer;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrailerMoviesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment TrailerMoviesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrailerMoviesFragment newInstance(String param1) {
        TrailerMoviesFragment fragment = new TrailerMoviesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trailer_movies, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        mWebViewTrailer = view.findViewById(R.id.fragment_trailer_web_view);

        mWebViewTrailer.getSettings().setLoadsImagesAutomatically(true);
        mWebViewTrailer.getSettings().setJavaScriptEnabled(true);
        mWebViewTrailer.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebViewTrailer.loadUrl(mParam1);
    }
}