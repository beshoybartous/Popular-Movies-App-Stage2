package com.example.myapplication.Network;

import com.example.myapplication.Models.MovieTrailerModel;
import com.example.myapplication.Models.MoviesModel;
import com.example.myapplication.Models.ReviewModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface apiInterface {
    @GET("/3/movie/{category}")
    Call<MoviesModel> getMoviesJson(
            @Path("category") String category,
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("/3/movie/{movie_id}/videos")
    Call<MovieTrailerModel> getTrailerJson(
            @Path("movie_id") int id,
            @Query( "api_key" )String apikey,
            @Query( "language" )String language
    );
    @GET("/3/movie/{movie_id}/reviews")
    Call<ReviewModel> getReviewJson(
            @Path("movie_id") int id,
            @Query( "api_key" )String apikey,
            @Query( "language" )String language,
            @Query( "page" )int page
    );


}
