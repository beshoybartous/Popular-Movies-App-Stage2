package com.example.myapplication.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.Models.MoviesDB;
import com.example.myapplication.Models.MoviesModel;
import com.example.myapplication.Repository.MoviesRepository;

import java.util.List;


public class MoviesViewModel extends AndroidViewModel {
    private final static String POPULAR_CATEGORY="popular";
    private final static String TOP_RATED_CATEGORY="top_rated";
    private MoviesRepository moviesRepository;
    //list of movies online and database
    private LiveData<MoviesModel> popularMovies;
    private LiveData<MoviesModel> topRatedMovies;
    private LiveData<List<MoviesDB>> OfflineMovies;

    public MoviesViewModel( Application application) {
        super( application );
        moviesRepository=new MoviesRepository( application );
        OfflineMovies=moviesRepository.fetchMoviesOffnline();
        popularMovies=moviesRepository.fetchMoviesOnline( POPULAR_CATEGORY );
        topRatedMovies=moviesRepository.fetchMoviesOnline( TOP_RATED_CATEGORY );

    }

    //fetch list of movies from api
    public LiveData<MoviesModel> fetchPopularMovies( )
    {
        if(popularMovies.getValue()==null){
            popularMovies=moviesRepository.fetchMoviesOnline( POPULAR_CATEGORY );
        }
        return popularMovies;
    }

    public LiveData<MoviesModel> fetchTopRatedMovies()
    {
        if(topRatedMovies.getValue()==null){
            topRatedMovies=moviesRepository.fetchMoviesOnline( TOP_RATED_CATEGORY );
        }
        return topRatedMovies;
    }

    //database
    public void Insert(MoviesDB movie){ moviesRepository.Insert( movie );}
    public void Update(MoviesDB movie){moviesRepository.Update( movie );}
    public void Delete(MoviesDB movie){moviesRepository.Delete( movie );}
    public void DeleteAll(){moviesRepository.DeleteAll();}
    //get list of movies from data base
    public LiveData<List<MoviesDB>> fetchFavoriteMovies(){return OfflineMovies;}
    //check existance of movie in db
    public List<Integer> checkMovie(int movie_id) {
        return moviesRepository.checkMovie(movie_id);
    }
    //get size of data base
    public List<MoviesDB>getMovie(int id){return moviesRepository.getMovie( id );}
    }
