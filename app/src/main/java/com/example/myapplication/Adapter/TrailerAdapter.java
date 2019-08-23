package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Models.MovieTrailerModel;
import com.example.myapplication.R;
import com.example.myapplication.databinding.TrailerItemsBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>{
    Context mContext;
    List<MovieTrailerModel.ResultsBean> trailers;
    public TrailerAdapter(Context context, List<MovieTrailerModel.ResultsBean> movieTrailerModels, TrailerClickListener trailerClickListener){
        mContext=context;
        trailers=movieTrailerModels;
        this.trailerClickListener=trailerClickListener;
    }
    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        TrailerItemsBinding trailerItemsBinding = DataBindingUtil.inflate(inflater, R.layout.trailer_items,parent,false);
        return new TrailerViewHolder( trailerItemsBinding );

    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        String video="http://img.youtube.com/vi/"+trailers.get( position ).getKey()+"/0.jpg";
        Picasso.with(mContext).load(video).into(holder.trailerItemsBinding.trailerImage);

    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }
    final private TrailerClickListener trailerClickListener;
    public interface TrailerClickListener{
        void onTrailerClick(String Key);
    }
    public class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TrailerItemsBinding trailerItemsBinding;
        public TrailerViewHolder(TrailerItemsBinding binding) {
            super( binding.getRoot() );
            itemView.setOnClickListener( this );
            trailerItemsBinding=binding;
        }

        @Override
        public void onClick(View view) {
            int pos=getAdapterPosition();
            trailerClickListener.onTrailerClick( trailers.get( pos ).getKey() );
        }
    }
}
