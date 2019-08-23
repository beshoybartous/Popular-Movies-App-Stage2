package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Models.MovieTrailerModel;
import com.example.myapplication.Models.MoviesDB;
import com.example.myapplication.Models.MoviesModel;
import com.example.myapplication.Models.ReviewModel;
import com.example.myapplication.R;
import com.example.myapplication.databinding.DetailItemsBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MoviesInfoViewHolder>  {
    private Context mContext;
    private MoviesModel.ResultsBean mAPIMovies;
    private MoviesDB mDBMovies;
    TrailerAdapter trailerAdapter;
    ReviewAdapter reviewAdapter;
    List<MovieTrailerModel.ResultsBean> mTrailers;
    List<ReviewModel.ResultsBean>mReviews;
    boolean mConnection;
    public DetailAdapter(Context context)
    {
        mContext=context;
        mTrailers=new ArrayList<>(  );
        mReviews=new ArrayList<>(  );
    }
    public void setAPIMovies(MoviesModel.ResultsBean resultsBean){
        mAPIMovies=resultsBean;
    }
    public void setDBMovies(MoviesDB moviesDB){
        mDBMovies=moviesDB;
    }
    public void setmTrailers(List<MovieTrailerModel.ResultsBean> mTrailers) {
        this.mTrailers = mTrailers;
    }

    public void setmReviews(List<ReviewModel.ResultsBean> mReviews) {
        this.mReviews = mReviews;
    }
    public void setConnection(boolean connection){
        mConnection=connection;
    }
    @Override
    public MoviesInfoViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        DetailItemsBinding detailItemsBinding = DataBindingUtil.inflate(inflater, R.layout.detail_items,parent,false);
        return new MoviesInfoViewHolder( detailItemsBinding );
    }

    @Override
    public void onBindViewHolder( MoviesInfoViewHolder holder, int position) {
        if (mAPIMovies != null) {
            String[] releaseYear = mAPIMovies.getRelease_date().split( "-", 3 );
            Picasso.with( mContext ).load( mAPIMovies.getPoster_path() ).into( holder.detailItemsBinding.selectedMovieImage );
            holder.detailItemsBinding.movieTitle.setText( mAPIMovies.getTitle() );
            holder.detailItemsBinding.movieReleaseDate.setText( releaseYear[0] );
            holder.detailItemsBinding.movieRate.setText( String.valueOf( mAPIMovies.getVote_average() ) + "/10" );
            holder.detailItemsBinding.movieOverview.setText( mAPIMovies.getOverview() );
        }
        else{
            String[] releaseYear = mDBMovies.getRelease_date().split( "-", 3 );
            Picasso.with( mContext ).load( mDBMovies.getPoster_path() ).into( holder.detailItemsBinding.selectedMovieImage );
            holder.detailItemsBinding.movieTitle.setText( mDBMovies.getTitle() );
            holder.detailItemsBinding.movieReleaseDate.setText( releaseYear[0] );
            holder.detailItemsBinding.movieRate.setText( String.valueOf( mDBMovies.getVote_average() ) + "/10" );
            holder.detailItemsBinding.movieOverview.setText( mDBMovies.getOverview() );
        }
        if(mConnection){
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( mContext, LinearLayoutManager.HORIZONTAL, false );
            RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager( mContext, LinearLayoutManager.HORIZONTAL, false );

            holder.detailItemsBinding.rvTrailer.setLayoutManager( layoutManager );
            holder.detailItemsBinding.rvReview.setLayoutManager( layoutManager2 );
            reviewAdapter = new ReviewAdapter( mContext, mReviews );
            holder.detailItemsBinding.rvReview.setAdapter( reviewAdapter );
            trailerAdapter = new TrailerAdapter( mContext, mTrailers, (new TrailerAdapter.TrailerClickListener() {
                @Override
                public void onTrailerClick(String Key) {

                    Intent intent = new Intent( Intent.ACTION_VIEW );
                    intent.setData( Uri.parse( "https://www.youtube.com/watch?v=" + Key ) );
                    mContext.startActivity( intent );

                }
            }) );
            holder.detailItemsBinding.rvTrailer.setAdapter( trailerAdapter );
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class MoviesInfoViewHolder extends RecyclerView.ViewHolder  {
        DetailItemsBinding detailItemsBinding;
        public MoviesInfoViewHolder(final DetailItemsBinding binding) {
            super( binding.getRoot() );
            this.detailItemsBinding=binding;
        }

    }
}
