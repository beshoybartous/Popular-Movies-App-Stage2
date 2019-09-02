package com.example.myapplication.Repository;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.DataBase.MoviesDao;
import com.example.myapplication.DataBase.MoviesDataBase;
import com.example.myapplication.Models.MoviesDB;
import com.example.myapplication.Models.MoviesModel;
import com.example.myapplication.Network.RetrofitClient;
import com.example.myapplication.Network.apiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {
    //api data
    private final static String API_KEY="";
    private final static String LANGUAGE="en-US";
    private static int PAGE=1;

    private MoviesDao moviesDao;

    public MoviesRepository(Application application){
        MoviesDataBase moviesDataBase=MoviesDataBase.getInstance( application );
        moviesDao=moviesDataBase.moviesDao();
    }

    // fetch list of movies from api
    public LiveData<MoviesModel> fetchMoviesOnline(String CATEGORY) {
        final MutableLiveData<MoviesModel> OnlineMovies=new MutableLiveData<>();

        /**
         * Calling JSON
         */
        apiInterface api = RetrofitClient.getApiService();

        Call<MoviesModel> call = api.getMoviesJson( CATEGORY, API_KEY, LANGUAGE, PAGE );

        /**
         * Enqueue Callback will be call when get response...
         */

        call.enqueue( new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {


                if (response.isSuccessful()) {
                    /**
                     * Got Successfully
                     */

                    MoviesModel results = response.body();
                    OnlineMovies.setValue( results );


                }
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {

            }
        } );
        return OnlineMovies;
    }



    //data base
    public void Insert(MoviesDB movie){ new InsertAsync( moviesDao ).execute( movie );}
    public void Update(MoviesDB movie){new UpdateAsync( moviesDao ).execute( movie );}
    public void Delete(MoviesDB movie){new DeleteAsync( moviesDao ).execute( movie );}
    public void DeleteAll(){new DeleteAllAsync( moviesDao ).execute( );}
    //get list of movies from data base
    public LiveData<List<MoviesDB>>fetchMoviesOffnline(){return moviesDao.getAllMovies();}
    //check existance of movie in db
    public List<Integer> checkMovie(int movie_id) {
        return moviesDao.checkMovie( movie_id );
    }
    //get size of data base
    public List<MoviesDB>getMovie(int id){
        return moviesDao.getMovie( id );
    }
    private static class InsertAsync extends AsyncTask<MoviesDB,Void,Void> {
        private MoviesDao movDao;
        public InsertAsync(MoviesDao movDao) {
            this.movDao = movDao;
        }

        @Override
        protected Void doInBackground(MoviesDB... movies) {
            movDao.Insert( movies[0] );
            return null;
        }
    }
    private static class UpdateAsync extends AsyncTask<MoviesDB,Void,Void>{
        private MoviesDao movDao;
        public UpdateAsync(MoviesDao movDao) {
            this.movDao = movDao;
        }

        @Override
        protected Void doInBackground(MoviesDB... movies) {
            movDao.Update( movies[0] );
            return null;
        }
    }
    private static class DeleteAsync extends AsyncTask<MoviesDB,Void,Void>{
        private MoviesDao movDao;
        public DeleteAsync(MoviesDao movDao) {
            this.movDao = movDao;
        }

        @Override
        protected Void doInBackground(MoviesDB... movies) {
            movDao.Delete( movies[0] );
            return null;
        }
    }
    private static class DeleteAllAsync extends AsyncTask<Void,Void,Void>{
        private MoviesDao movDao;
        public DeleteAllAsync(MoviesDao movDao) {
            this.movDao = movDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            movDao.DeleteALLNotes();
            return null;
        }
    }


}
