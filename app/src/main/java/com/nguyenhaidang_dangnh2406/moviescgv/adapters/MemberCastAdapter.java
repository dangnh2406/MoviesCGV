package com.nguyenhaidang_dangnh2406.moviescgv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhaidang_dangnh2406.moviescgv.R;
import com.nguyenhaidang_dangnh2406.moviescgv.model.movie_detail.Cast;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MemberCastAdapter extends RecyclerView.Adapter<MemberCastAdapter.ViewHoder> {
    private List<Cast> mCastList;
    private Context mContext;

    public MemberCastAdapter(List<Cast> mCastList, Context context) {
        this.mCastList = mCastList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_cast_crew , parent , false);

        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        holder.mNameCast.setText(mCastList.get(position).getName());
        if (mCastList != null){
            Picasso.get().load("http://image.tmdb.org/t/p/w500/"+mCastList.get(position).getProfilePath()).into(holder.mImageCast);
        }

    }

    @Override
    public int getItemCount() {
        if (mCastList == null){
            return 0;
        }else {
            return mCastList.size();
        }
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        private ImageView mImageCast;
        private TextView mNameCast;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);

            mNameCast = itemView.findViewById(R.id.row_cast_name_txt);
            mImageCast = itemView.findViewById(R.id.row_cast_image);
        }
    }
}
