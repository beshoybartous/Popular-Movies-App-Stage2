package com.example.myapplication.UI;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Adapter.DetailAdapter;
import com.example.myapplication.ViewModel.DetailViewModel;
import com.example.myapplication.Models.MovieTrailerModel;
import com.example.myapplication.Models.MoviesDB;
import com.example.myapplication.Models.MoviesModel;
import com.example.myapplication.Models.ReviewModel;
import com.example.myapplication.R;
import com.example.myapplication.databinding.DetailActivityBinding;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    DetailActivityBinding detailBinding;
    private final static String NO_CONNECTION="Your are Offline";
    private final static String ADDED_TO_FAVORITE_LIST="Added To Favorite List";
    private final static String REMOVED_FROM_FAVORITE_LIST="Removed From Favorite List";

    private final static String SELECTED_MOVIES="Api Movie";
    private static final String FAVORITE_MOVIES="Favorite Movies";
    private final static String MOVIE_FOUND_IN_DB="movie in DB";

    private final static int UNFAVORITE_MOVIE=2;
    DetailViewModel detailViewModel;

    DetailAdapter detailAdapter;
    MoviesModel.ResultsBean apiMovie;
    MoviesDB dbMovie;
    boolean noConnection;
    List<MovieTrailerModel.ResultsBean> trailers;
    List<ReviewModel.ResultsBean>reviews;
    boolean mConnection;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        detailBinding= DataBindingUtil.setContentView( this,R.layout.detail_activity );
        setSupportActionBar( (Toolbar) detailBinding.toolbar );
        getSupportActionBar().setTitle( "" );

        final Drawable upArrow = getResources().getDrawable(R.drawable.back_button);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager=new LinearLayoutManager( this,LinearLayoutManager.VERTICAL,false );
        detailBinding.rvMovieInfo.setLayoutManager( layoutManager );
        detailAdapter =new DetailAdapter(DetailActivity.this);
        detailViewModel = ViewModelProviders.of( this ).get( DetailViewModel.class );
        if(savedInstanceState==null){
            noConnection=getIntent().getBooleanExtra( NO_CONNECTION,false );

            if(noConnection) {
            dbMovie =  getIntent().getExtras().getParcelable( FAVORITE_MOVIES );
            detailAdapter.setDBMovies( dbMovie );
            setBackground( dbMovie.getPoster_path() );
            mConnection=false;
            detailAdapter.setConnection( !noConnection );
            detailBinding.rvMovieInfo.setAdapter( detailAdapter );

        }
        else {
            mConnection=true;
            detailAdapter.setConnection( !noConnection );
            dbMovie = getIntent().getExtras().getParcelable( FAVORITE_MOVIES );
            apiMovie = (MoviesModel.ResultsBean) getIntent().getExtras().getParcelable( SELECTED_MOVIES );
            if (dbMovie != null) {
                detailAdapter.setDBMovies( dbMovie );
                setBackground( dbMovie.getPoster_path() );
                setTrailers( dbMovie.getId() );
                setReviews( dbMovie.getId() );

            } else {
                detailAdapter.setAPIMovies( apiMovie );
                setBackground( apiMovie.getPoster_path() );
                setTrailers( apiMovie.getId() );
                setReviews( apiMovie.getId() );
            }
        }
        }
        else{
            apiMovie=savedInstanceState.getParcelable( SELECTED_MOVIES );
            dbMovie=savedInstanceState.getParcelable( FAVORITE_MOVIES );
            noConnection=savedInstanceState.getBoolean( NO_CONNECTION,false );
            if(apiMovie!=null){
                mConnection=true;
                detailAdapter.setConnection( !noConnection );
                detailAdapter.setAPIMovies( apiMovie );
                setBackground( apiMovie.getPoster_path() );
                setTrailers( apiMovie.getId() );
                setReviews( apiMovie.getId() );

            }
            else{
                if(noConnection){
                    detailAdapter.setDBMovies( dbMovie );
                    setBackground( dbMovie.getPoster_path() );
                    detailBinding.rvMovieInfo.setAdapter( detailAdapter );
                    mConnection=false;
                    detailAdapter.setConnection( !noConnection );
                }
                else{
                    mConnection=true;
                    detailAdapter.setConnection( !noConnection );
                    detailAdapter.setDBMovies( dbMovie );
                    setBackground( dbMovie.getPoster_path() );
                    setTrailers( dbMovie.getId() );
                    setReviews( dbMovie.getId() );
                }
            }
        }
    }
    private void setTrailers(final int movieID){
        detailViewModel.getTrailer( movieID ).observe( this, new Observer<MovieTrailerModel>() {
            @Override
            public void onChanged(MovieTrailerModel movieTrailerModel) {
                trailers = movieTrailerModel.getResults();
                detailAdapter.setmTrailers( trailers );
                detailBinding.rvMovieInfo.setAdapter( detailAdapter );

            }
        } );
    }
    private void setReviews(int movieID){
        detailViewModel.getReview( movieID ).observe( this, new Observer<ReviewModel>() {
            @Override
            public void onChanged(ReviewModel reviewModel) {
                reviews = reviewModel.getResults();
                detailAdapter.setmReviews( reviews );
                detailBinding.rvMovieInfo.setAdapter( detailAdapter );

            }
        } );
    }
    private void setBackground(String iamgeURI){
        Picasso.with( this).load(iamgeURI)
                .into( new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            detailBinding.parentLayout.setBackground( new BitmapDrawable( bitmap ) );
                        }
                    }
                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }
                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }
    @Override
    protected void onStart() {
        detailBinding.blurLayout.startBlur();
        super.onStart();
    }

    @Override
    protected void onStop() {
        detailBinding.blurLayout.pauseBlur();

        super.onStop();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate( R.menu.favorite_movie,menu );
        if(getIntent().getBooleanExtra( MOVIE_FOUND_IN_DB,false)){

            menu.findItem( R.id.favorite_movie ).setIcon( R.drawable.favorite );
        }
        else{
            menu.findItem( R.id.favorite_movie ).setIcon( R.drawable.not_favorite );

        }
        return true;
    }
    private Intent putMovie(){
        Intent intent=new Intent(  );
        if(apiMovie!=null) {
            intent.putExtra( SELECTED_MOVIES, (Parcelable) apiMovie );
        }
        else{
            intent.putExtra( FAVORITE_MOVIES, (Parcelable) dbMovie );
            intent.putExtra( NO_CONNECTION,true );
        }
        return intent;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if(checkSelfPermission( Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(permissions, 101);
        }

        if (checkSelfPermission( Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            if (item.getItemId() == R.id.favorite_movie &&
                    item.getIcon().getConstantState().equals( getResources().getDrawable( R.drawable.not_favorite ).getConstantState() )) {

                item.setIcon( ContextCompat.getDrawable( this, R.drawable.favorite ) );
                Toast.makeText( DetailActivity.this,ADDED_TO_FAVORITE_LIST,Toast.LENGTH_SHORT ).show();
                setResult( RESULT_OK, putMovie() );
                return true;
            } else if (item.getItemId() == R.id.favorite_movie) {
                item.setIcon( ContextCompat.getDrawable( this, R.drawable.not_favorite ) );
                setResult( UNFAVORITE_MOVIE, putMovie() );
                Toast.makeText( DetailActivity.this,REMOVED_FROM_FAVORITE_LIST,Toast.LENGTH_SHORT ).show();
                return true;
            }
        }
        return super.onOptionsItemSelected( item );
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(apiMovie!=null){
            outState.putParcelable( SELECTED_MOVIES, (Parcelable) apiMovie );
        }
        else{
            if(noConnection){
                outState.putBoolean( NO_CONNECTION,noConnection );
            }
            outState.putParcelable( FAVORITE_MOVIES, (Parcelable) dbMovie );
        }
        super.onSaveInstanceState( outState );
    }
}
