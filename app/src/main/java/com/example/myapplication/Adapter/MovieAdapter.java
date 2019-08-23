package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Models.MoviesDB;
import com.example.myapplication.Models.MoviesModel;
import com.example.myapplication.R;
import com.example.myapplication.databinding.MoviesItemsBinding;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    private static final String POPULAR_MOVIES="Popular Movies";
    private static final String TOP_RATED_MOVIES="Top Rated Movies";
    private static final String FAVORITE_MOVIES="Favorite Movies";
    private String mCategory;
    private Context mContext;
    private  List<MoviesModel.ResultsBean> mPopularMovies,mTopRatedMovies;
    private  ArrayList<MoviesDB> mFavoriteMovies;

    final private ListItemClickListiner listItemClickListiner;

    public MovieAdapter(Context context,ListItemClickListiner listener){
        mContext=context;
        listItemClickListiner=listener;
        mPopularMovies=new ArrayList<>(  );
        mTopRatedMovies=new ArrayList<>(  );
        mFavoriteMovies=new ArrayList<>(  );
        mCategory="";

    }

    public void setPopularMovies(List<MoviesModel.ResultsBean>resultsBeans){
        mPopularMovies=resultsBeans;
    }
    public void setmTopRatedMovies(List<MoviesModel.ResultsBean>resultsBeans){
        mTopRatedMovies=resultsBeans;
    }
    public void setDBMovies(ArrayList<MoviesDB>dbMovies){
        mFavoriteMovies=dbMovies;
    }
    public void setCategory(String Category){
        mCategory=Category;
    }
    @Override
    public MovieViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        MoviesItemsBinding moviesItemsBinding = DataBindingUtil.inflate(inflater, R.layout.movies_items,parent,false);
        return new MovieViewHolder( moviesItemsBinding );
    }

    @Override
    public void onBindViewHolder( MovieViewHolder holder, int position) {
        if(mCategory.equals( POPULAR_MOVIES ) &&mPopularMovies!=null) {
            Picasso.with( mContext ).load( mPopularMovies.get( position ).getPoster_path() ).into( holder.moviesItemsBinding.moviesPoster );
            holder.moviesItemsBinding.moviesTitle.setText( mPopularMovies.get( position ).getTitle() );

        }
        else if(mCategory.equals( TOP_RATED_MOVIES )&&mTopRatedMovies!=null){
            Picasso.with( mContext ).load( mTopRatedMovies.get( position ).getPoster_path() ).into( holder.moviesItemsBinding.moviesPoster );
            holder.moviesItemsBinding.moviesTitle.setText( mTopRatedMovies.get( position ).getTitle() );
        }
        else if(mCategory.equals( FAVORITE_MOVIES )&&mFavoriteMovies!=null){
                Picasso.with( mContext ).load( mFavoriteMovies.get( position ).getPoster_path() ).into( holder.moviesItemsBinding.moviesPoster );
                holder.moviesItemsBinding.moviesTitle.setText( mFavoriteMovies.get( position ).getTitle() );
        }
    }

    @Override
    public int getItemCount() {
        if(mCategory.equals( POPULAR_MOVIES )) {
             return mPopularMovies.size();
         }
         if(mCategory.equals( TOP_RATED_MOVIES )) {
             return mTopRatedMovies.size();
         }
        if(mCategory.equals( FAVORITE_MOVIES )) {
            return mFavoriteMovies.size();
        }
             return 0;
    }
    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        MoviesItemsBinding moviesItemsBinding;
        public MovieViewHolder(MoviesItemsBinding binding) {
            super( binding.getRoot() );
            this.moviesItemsBinding=binding;
            binding.getRoot().setOnClickListener( this );
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            int movieId;
            if(mCategory.equals( POPULAR_MOVIES )) {
                movieId=mPopularMovies.get( position ).getId();
            }
            else if(mCategory.equals( TOP_RATED_MOVIES )) {
                movieId=mTopRatedMovies.get( position ).getId();
            }

            else{
                movieId=mFavoriteMovies.get( position ).getId();
            }
                listItemClickListiner.onListItemClick( position,movieId );
        }
    }

    public interface ListItemClickListiner{
        void onListItemClick(int clickedItemIndex,int MovieID);
    }

}
