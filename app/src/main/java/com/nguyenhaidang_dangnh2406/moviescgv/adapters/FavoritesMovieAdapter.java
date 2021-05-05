package com.nguyenhaidang_dangnh2406.moviescgv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhaidang_dangnh2406.moviescgv.R;
import com.nguyenhaidang_dangnh2406.moviescgv.model.fravorites.MovieFavorites;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoritesMovieAdapter extends RecyclerView.Adapter<FavoritesMovieAdapter.ViewHoderss>{
    private Context mContext;
    private List<MovieFavorites> mMovieFavoritesList;

    public FavoritesMovieAdapter(Context mContext, List<MovieFavorites> mMovieFavoritesList) {
        this.mContext = mContext;
        this.mMovieFavoritesList = mMovieFavoritesList;
    }

    @NonNull
    @Override
    public ViewHoderss onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_movies, parent, false);

        return new FavoritesMovieAdapter.ViewHoderss(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderss holder, int position) {
        holder.mRating_txt.setText(mMovieFavoritesList.get(position).getReleaseData());
        holder.mRelease_txt.setText(mMovieFavoritesList.get(position).getReleaseData());
        holder.mTitlleMovie_txt.setText(mMovieFavoritesList.get(position).getTitleMovie());
        holder.mOverview_txt.setText(mMovieFavoritesList.get(position).getOverview());
        holder.mFavorite_img.setTag(position);
        Picasso.get().load("http://image.tmdb.org/t/p/w500" + mMovieFavoritesList.get(position).getImg()).into(holder.mPoster_img);



    }



    @Override
    public int getItemCount() {
        if (mMovieFavoritesList == null) {
            return 0;
        } else {
            return mMovieFavoritesList.size();
        }
    }

    public class ViewHoderss extends RecyclerView.ViewHolder {
        private TextView mTitlleMovie_txt;
        private TextView mRelease_txt;
        private TextView mRating_txt;
        private TextView mOverview_txt;
        private ImageView mPoster_img;
        private ImageView mFavorite_img;


        public ViewHoderss(@NonNull View itemView) {
            super(itemView);
            mOverview_txt = itemView.findViewById(R.id.row_movies_overview_txt);
            mTitlleMovie_txt = itemView.findViewById(R.id.row_movies_title_txt);
            mRelease_txt = itemView.findViewById(R.id.row_movies_release_date_txt);
            mRating_txt = itemView.findViewById(R.id.row_movies_rating_txt);
            mPoster_img = itemView.findViewById(R.id.row_movies_poster_img);
            mFavorite_img = itemView.findViewById(R.id.row_movies_ic_favorite_img);

        }


    }
    public void changeData(List<MovieFavorites> list){
        mMovieFavoritesList = list;
        notifyDataSetChanged();
    }

}

