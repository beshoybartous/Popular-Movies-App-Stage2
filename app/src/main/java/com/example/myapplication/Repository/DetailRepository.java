package com.example.myapplication.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.Models.MovieTrailerModel;
import com.example.myapplication.Models.ReviewModel;
import com.example.myapplication.Network.RetrofitClient;
import com.example.myapplication.Network.apiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRepository {


    public static String API_KEY="";
    public static String LANGUAGE="en-US";
    public static int PAGE=1;


    public DetailRepository(Application application)
    {
    }
    MutableLiveData<MovieTrailerModel> movieTrailerList=new MutableLiveData<>();
    public MutableLiveData<MovieTrailerModel> getTrailer(int id){
        apiInterface api = RetrofitClient.getApiService();

        //String ID=String.valueOf( id )+VIDEOS;
        Call<MovieTrailerModel> call=api.getTrailerJson( id,API_KEY,LANGUAGE );

        call.enqueue( new Callback<MovieTrailerModel>() {
            @Override
            public void onResponse(Call<MovieTrailerModel> call, Response<MovieTrailerModel> response) {
                if(response.isSuccessful()){
                    MovieTrailerModel results = response.body();
                    movieTrailerList.setValue( results );
                }
            }

            @Override
            public void onFailure(Call<MovieTrailerModel> call, Throwable t) {

            }
        } );

        return movieTrailerList;
    }

    MutableLiveData<ReviewModel>movieReviewList=new MutableLiveData<>();
    public MutableLiveData<ReviewModel> getReview(int id){
        apiInterface api = RetrofitClient.getApiService();

        //String ID=String.valueOf( id )+VIDEOS;
        Call<ReviewModel> call=api.getReviewJson( id,API_KEY,LANGUAGE,PAGE );

        call.enqueue( new Callback<ReviewModel>() {
            @Override
            public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
                if(response.isSuccessful()){
                    ReviewModel results = response.body();
                    movieReviewList.setValue( results );
                }
            }

            @Override
            public void onFailure(Call<ReviewModel> call, Throwable t) {

            }
        } );

        return movieReviewList;
    }



}
