package com.nguyenhaidang_dangnh2406.moviescgv.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhaidang_dangnh2406.moviescgv.R;
import com.nguyenhaidang_dangnh2406.moviescgv.data.FavoriteDatabase;
import com.nguyenhaidang_dangnh2406.moviescgv.interfaces.GetCountingStar;
import com.nguyenhaidang_dangnh2406.moviescgv.model.fravorites.MovieFavorites;
import com.nguyenhaidang_dangnh2406.moviescgv.model.movies.Result;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHoder> {
    private Context mContext;
    private List<Result> mResultList;
    private View.OnClickListener mOnClickListener;
    private MovieFavorites mMovieFavorites;
    private List<MovieFavorites> mMovieFavoritesList;
    private Result idmovieFavorites;
    private GetCountingStar mGetCountingStar;

    public MoviesAdapter(Context mContext, List<Result> mResultList, View.OnClickListener mOnClickListener) {
        this.mContext = mContext;
        this.mResultList = mResultList;
        this.mOnClickListener = mOnClickListener;
        this.mGetCountingStar = (GetCountingStar) mContext;
    }


    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(mContext).inflate(R.layout.row_movies,parent,false);

        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        holder.mRating_txt.setText(mResultList.get(position).getVoteAverage().toString()+"/10");
        holder.mRelease_txt.setText(mResultList.get(position).getReleaseDate());
        holder.mTitlleMovie_txt.setText(mResultList.get(position).getTitle());
        holder.mOverview_txt.setText(mResultList.get(position).getOverview());
        holder.mFavorite_img.setTag(position);
        Picasso.get().load("http://image.tmdb.org/t/p/w500" +mResultList.get(position).getPosterPath()).into(holder.mPoster_img);
        idmovieFavorites = mResultList.get(position);
       if (FavoriteDatabase.getInstance(mContext).favoriteDAO().isCheckedMovieExit(idmovieFavorites.getId().toString())){
           holder.mFavorite_img.setImageResource(R.drawable.icons8_heart_red);
       }else {
           holder.mFavorite_img.setImageResource(R.drawable.icons8_heart_blacks);
       }

        holder.mCardView.setTag(mResultList.get(position).getId());
        holder.mCardView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        if (mResultList == null){
            return 0;
        }else{
            return mResultList.size();
        }
    }

    public class ViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitlleMovie_txt;
        private TextView mRelease_txt;
        private TextView mRating_txt;
        private TextView mOverview_txt;
        private ImageView mPoster_img;
        private ImageView mFavorite_img;
        private CardView mCardView;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            mOverview_txt = itemView.findViewById(R.id.row_movies_overview_txt);
            mTitlleMovie_txt = itemView.findViewById(R.id.row_movies_title_txt);
            mRelease_txt = itemView.findViewById(R.id.row_movies_release_date_txt);
            mRating_txt = itemView.findViewById(R.id.row_movies_rating_txt);
            mPoster_img = itemView.findViewById(R.id.row_movies_poster_img);
            mFavorite_img = itemView.findViewById(R.id.row_movies_ic_favorite_img);
            mCardView = itemView.findViewById(R.id.row_movies_cardview);

            mFavorite_img.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int location = (int) v.getTag();

            if (mMovieFavorites == null){
                mMovieFavorites = new MovieFavorites();
            }

            mMovieFavorites.setImg(mResultList.get(location).getPosterPath());
            mMovieFavorites.setIdMovie(mResultList.get(location).getId().toString());
            mMovieFavorites.setOverview(mResultList.get(location).getOverview());
            mMovieFavorites.setReleaseData(mResultList.get(location).getReleaseDate());
            mMovieFavorites.setTitleMovie(mResultList.get(location).getTitle());

            if(checkFavorite(mMovieFavorites)){
                mFavorite_img.setImageResource(R.drawable.icons8_heart_red);
                FavoriteDatabase.getInstance(mContext).favoriteDAO().insertFavorite(mMovieFavorites);
                mGetCountingStar.getCoutingStar( FavoriteDatabase.getInstance(mContext).favoriteDAO().getAllListFavorite().size());

            }else {
                mFavorite_img.setImageResource(R.drawable.icons8_heart_blacks);
                FavoriteDatabase.getInstance(mContext).favoriteDAO().deleteMovieFavorite(mMovieFavorites.getIdMovie());
                mGetCountingStar.getCoutingStar( FavoriteDatabase.getInstance(mContext).favoriteDAO().getAllListFavorite().size());

            }
        }

        private boolean checkFavorite(MovieFavorites movieFavorites){
            mMovieFavoritesList = FavoriteDatabase.getInstance(mContext).favoriteDAO()
                    .isCheckMovieExit(movieFavorites.getIdMovie());

            return mMovieFavoritesList != null &&  mMovieFavoritesList.isEmpty();
        }

    }
    public void addData(List<MovieFavorites> list){
        mMovieFavoritesList = list;
        notifyDataSetChanged();
    }
}
