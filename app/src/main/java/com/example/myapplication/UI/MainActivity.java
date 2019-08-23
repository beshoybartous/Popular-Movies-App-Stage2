package com.example.myapplication.UI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.myapplication.Adapter.MovieAdapter;
import com.example.myapplication.ModelView.MoviesViewModel;
import com.example.myapplication.Models.MoviesDB;
import com.example.myapplication.Models.MoviesModel;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListiner{
    private static final String POPULAR_MOVIES="Popular Movies";
    private static final String TOP_RATED_MOVIES="Top Rated Movies";
    private static final String FAVORITE_MOVIES="Favorite Movies";
    private final static String NO_CONNECTION="Your are Offline";
    private final static String CONNECTION="Your are Online";
    private final static String SPINNER_POSITION="Spinner Position";
    private final static String RECYCLER_VIEW_POSITION="Recycler View Position";
    private final static String SELECTED_MOVIES="Api Movie";
    private final static String MOVIE_FOUND_IN_DB="movie in DB";


    //intent result
    private final static int REQUEST_CODE=1;
    private final static int UNFAVORITE_MOVIE=2;

    //data binidng
    ActivityMainBinding mainBinding;
    //spinner for sort
    private Spinner spinner;
    private int spinnerPostionIdex = -1;
    private final static int favSpinnerPosition=2;
    private MenuItem sortSpinner = null;
    private ArrayAdapter<CharSequence> spinnerAdapter;

    //ViewModel and Movies Adapter and RecyclerView
    private MoviesViewModel moviesViewModel;
    private MovieAdapter moviesAdapter;
   // private RecyclerView moviesRv;

    // list of movies from api and database
    List<MoviesModel.ResultsBean> moviesList;
    MoviesModel popularMovies,topRatedMovies;
    ArrayList<MoviesDB>favMoviesList;


    //boolean to check network state and broad cast receiver
    boolean noConetion;
    NetworkBroadCastReceiver networkBroadCastReceiver=new NetworkBroadCastReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        mainBinding= DataBindingUtil.setContentView( this,R.layout.activity_main );
        moviesList=new ArrayList<>(  );

        //init spinner adapter
        spinnerAdapter= ArrayAdapter.createFromResource( MainActivity.this,R.array.sort_items,android.R.layout.simple_spinner_item );
        spinnerAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        //find views and set recycler view and set orientation size
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mainBinding.rvMovies.setLayoutManager( new GridLayoutManager( MainActivity.this,2 ) );
        }
        else{
            mainBinding.rvMovies.setLayoutManager( new GridLayoutManager( MainActivity.this,4 ) );
        }

        //set ViewModel
        moviesViewModel= ViewModelProviders.of( MainActivity.this ).get( MoviesViewModel.class );

        //init Movie Adapter
        moviesAdapter=new MovieAdapter(MainActivity.this,MainActivity.this);
        //restore data if orientation changed
        if(savedInstanceState==null) {
            //get movies from api and db and set defult cateogry popular if online
            if(!noConetion){

                fetcPopularMovies();
                moviesAdapter.setCategory( POPULAR_MOVIES );
                fetchTopRatedMovies();
                fetchFavoriteMovies();
            }
            else {
                fetchFavoriteMovies();
            }
        }
        else{
            //get movies from savedinstance
            popularMovies = (MoviesModel) savedInstanceState.getParcelable( POPULAR_MOVIES );
            topRatedMovies = (MoviesModel) savedInstanceState.getParcelable( TOP_RATED_MOVIES );
            favMoviesList=savedInstanceState.getParcelableArrayList( FAVORITE_MOVIES );
            //if popularmovies not empty and there is connection set recycler view
            if(popularMovies!=null&&!noConetion) {

                moviesAdapter.setPopularMovies(  popularMovies.getResults() );
                moviesAdapter.setCategory( POPULAR_MOVIES );
                moviesAdapter.notifyDataSetChanged();
            }
            //if topRatedMovies not empty and there is connection set recycler view
            else if(topRatedMovies!=null&&!noConetion){
                moviesAdapter.setmTopRatedMovies(  topRatedMovies.getResults() );
                moviesAdapter.setCategory( TOP_RATED_MOVIES );
                moviesAdapter.notifyDataSetChanged();
            }
            //if favMoviesList not empty set recycler view
            else if(favMoviesList!=null){

                moviesAdapter.setDBMovies(  favMoviesList );
                moviesAdapter.setCategory( FAVORITE_MOVIES );
                moviesAdapter.notifyDataSetChanged();
            }
            //set recycler view position to old position and spinner
            mainBinding.rvMovies.getLayoutManager().scrollToPosition(savedInstanceState.getInt(RECYCLER_VIEW_POSITION));
            spinnerPostionIdex = savedInstanceState.getInt( SPINNER_POSITION, -1 );
        }
        mainBinding.rvMovies.setAdapter(moviesAdapter);


    }
//fetch movies list from api and set adapter
    private void fetcPopularMovies( ){
        moviesViewModel.fetchPopularMovies().observe( this, new Observer<MoviesModel>() {
            @Override
            public void onChanged(MoviesModel moviesModel) {
                popularMovies=moviesModel;
                moviesAdapter.setPopularMovies( popularMovies.getResults() );
                moviesAdapter.notifyDataSetChanged();
            }

        } );    }
    private void fetchTopRatedMovies( ){

        moviesViewModel.fetchTopRatedMovies().observe( this, new Observer<MoviesModel>() {
            @Override
            public void onChanged(MoviesModel moviesModel) {
                topRatedMovies=moviesModel;
                moviesAdapter.setmTopRatedMovies( topRatedMovies.getResults() );
                moviesAdapter.notifyDataSetChanged();
            }

        } );    }
    private void fetchFavoriteMovies( ){

        moviesViewModel.fetchFavoriteMovies().observe( this, new Observer<List<MoviesDB>>() {
            @Override
            public void onChanged(List<MoviesDB> moviesDB) {
                favMoviesList=new ArrayList<MoviesDB>( moviesDB );
                moviesAdapter.setDBMovies( favMoviesList );
                moviesAdapter.notifyDataSetChanged();
            }
        } );
    }
//check if movie exist in db
    public boolean MovieExist(final int MovieID){

        if(!moviesViewModel.checkMovie( MovieID ).isEmpty())
            return true;
        else
            return false;

    }
    @Override
    public void onListItemClick(int clickedItemIndex,int MovieID) {
        final Intent detailActivity = new Intent( MainActivity.this, DetailActivity.class );
        //check spinner postion to send movie from api or database
            if (spinner.getSelectedItem().equals( FAVORITE_MOVIES )) {
                MoviesDB moviesDB = moviesViewModel.getMovie( MovieID ).get( 0 );
                detailActivity.putExtra( FAVORITE_MOVIES, (Parcelable) moviesDB );
                if(noConetion){
                    Log.i( "favvv", String.valueOf( noConetion ) );
                    //if there is no connetion set no connection  to false
                    detailActivity.putExtra( NO_CONNECTION,true );
                }

                detailActivity.putExtra( MOVIE_FOUND_IN_DB,true );
                startActivityForResult(detailActivity,REQUEST_CODE);
            }
            else {
                 if (MovieExist( MovieID )) {
                     MoviesDB moviesDB = moviesViewModel.getMovie( MovieID ).get( 0 );
                     detailActivity.putExtra( FAVORITE_MOVIES, (Parcelable) moviesDB );
                     detailActivity.putExtra( MOVIE_FOUND_IN_DB, true );
                     if(noConetion){
                         detailActivity.putExtra( NO_CONNECTION,true );
                     }
                     startActivityForResult(detailActivity,REQUEST_CODE);
                 }
                else if (spinner.getSelectedItem().equals( POPULAR_MOVIES )) {

                     if(!noConetion){
                         MoviesModel.ResultsBean movie = popularMovies.getResults().get( clickedItemIndex );
                         detailActivity.putExtra( SELECTED_MOVIES, movie );
                         startActivityForResult(detailActivity,REQUEST_CODE);
                     }
                }
                    else {
                     if(!noConetion){
                         MoviesModel.ResultsBean movie = topRatedMovies.getResults().get( clickedItemIndex );
                         detailActivity.putExtra( SELECTED_MOVIES, movie );
                         startActivityForResult(detailActivity,REQUEST_CODE);
                     }
                }

            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate( R.menu.sort_menu,menu );
        sortSpinner = menu.findItem( R.id.sort_spinner);
        View spinnerView = sortSpinner.getActionView();
        if (spinnerView instanceof Spinner)
        {
            spinner = (Spinner) spinnerView;
            spinner.setAdapter(spinnerAdapter);
            //set spinner text if orientation changed
            if(spinnerPostionIdex!=-1){
                spinner.setSelection( spinnerPostionIdex );
            }
            spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    if(adapterView.getItemAtPosition( position ).equals( FAVORITE_MOVIES ))
                    {
                        if(position!=spinnerPostionIdex) {
                            fetchFavoriteMovies();
                            moviesAdapter.setCategory( FAVORITE_MOVIES );
                            mainBinding.rvMovies.setAdapter( moviesAdapter );
                            spinnerPostionIdex = position;
                        }
                    }
                    else{
                        //check the type of selected sort
                        if(adapterView.getItemAtPosition( position ).equals( POPULAR_MOVIES )){
                            //if the selected sort from spinner not as the same sort before selections get new data
                            if(position!=spinnerPostionIdex ) {
                                fetcPopularMovies(  );

                                mainBinding.rvMovies.setAdapter(moviesAdapter);
                                moviesAdapter.setCategory( POPULAR_MOVIES );
                            }

                        }
                        else if(adapterView.getItemAtPosition( position ).equals( TOP_RATED_MOVIES )){
                            if(position!=spinnerPostionIdex ) {
                                fetchTopRatedMovies(  );
                                moviesAdapter.setCategory( TOP_RATED_MOVIES );
                                mainBinding.rvMovies.setAdapter(moviesAdapter);
                            }

                        }
                        spinnerPostionIdex=position;
                    }
                    if(noConetion){
                        Toast.makeText( MainActivity.this,NO_CONNECTION,Toast.LENGTH_LONG ).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            } );

        }

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter=new IntentFilter( ConnectivityManager.CONNECTIVITY_ACTION );
        registerReceiver( networkBroadCastReceiver,filter );
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver( networkBroadCastReceiver );
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        MoviesModel.ResultsBean apiMovie;
        final MoviesDB dbMovie;

        if(requestCode==REQUEST_CODE &&resultCode!=RESULT_CANCELED) {
            //if there is no connection then we get movie from db
            if(data.getBooleanExtra( NO_CONNECTION,false )){
                dbMovie=data.getExtras().getParcelable( FAVORITE_MOVIES );
            }
            else{

                apiMovie=data.getExtras().getParcelable( SELECTED_MOVIES );
                int movie_id= apiMovie.getId();
                double vote_average= apiMovie.getVote_average();
                String poster_path= apiMovie.getPoster_path();
                String overview= apiMovie.getOverview();
                String release_date=apiMovie.getRelease_date();
                final String title= apiMovie.getTitle();
                dbMovie=new MoviesDB( vote_average, poster_path, overview, title, true ,"",release_date);
                dbMovie.setId( movie_id );
                dbMovie.setImagePath( "file://" + Environment.getExternalStorageDirectory().getAbsolutePath().toString()
                        + "/PopularMovies/" + title);
            }
            //if result ok then movie become favorite add it to database
            if(requestCode==REQUEST_CODE && resultCode==RESULT_OK) {
                if(!MovieExist( dbMovie.getId() )){

                    Picasso.with(MainActivity.this).load(dbMovie.getPoster_path()).into( new Target() {
                        @Override
                        public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                            new Thread(new Runnable() {

                                @RequiresApi(api = Build.VERSION_CODES.M)
                                @Override
                                public void run() {

                                    File file = new File( Environment.getExternalStorageDirectory().getPath() + "/PopularMovies/"
                                            +dbMovie.getTitle());
                                    try {
                                        file.mkdirs();
                                        if (file.exists()) {
                                            file.delete();
                                        }
                                        file.createNewFile();

                                        FileOutputStream ostream = new FileOutputStream(file);
                                        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
                                        ostream.flush();
                                        ostream.close();
                                        String base = Environment.getExternalStorageDirectory().getAbsolutePath().toString();
                                    } catch (IOException e) {
                                    }
                                }
                            }).start();
                        }
                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {

                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    } );

                    moviesViewModel.Insert( dbMovie );
                }
            }
            //else it become unfavorite remove it from db
            else if(requestCode==REQUEST_CODE&&resultCode==UNFAVORITE_MOVIE) {
                if (MovieExist( dbMovie.getId() )) {
                    File myDir = new File(Environment.getExternalStorageDirectory() + "/PopularMovies/");
                    new File( myDir,dbMovie.getTitle() ).delete();
                    moviesViewModel.Delete( dbMovie );
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        String spinnerValue=spinner.getSelectedItem().toString();
        outState.putString( "test1",spinnerValue );
        if (spinnerValue.equals( POPULAR_MOVIES )) {
            outState.putParcelable( POPULAR_MOVIES, popularMovies );
        }
        else if (spinnerValue.equals( TOP_RATED_MOVIES )) {

            outState.putParcelable( TOP_RATED_MOVIES, topRatedMovies );
        }
        else{

            outState.putParcelableArrayList( FAVORITE_MOVIES, favMoviesList );
        }
        outState.putInt( SPINNER_POSITION,spinner.getSelectedItemPosition() );
        GridLayoutManager layoutManager = ((GridLayoutManager)mainBinding.rvMovies.getLayoutManager());
        int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
        outState.putInt( RECYCLER_VIEW_POSITION,firstVisiblePosition );
        super.onSaveInstanceState( outState );
    }

    private class NetworkBroadCastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if(ConnectivityManager.CONNECTIVITY_ACTION.equals( intent.getAction() )){
                noConetion=intent.getBooleanExtra(
                        ConnectivityManager.EXTRA_NO_CONNECTIVITY,false

                );
            }
        }
    }
}
