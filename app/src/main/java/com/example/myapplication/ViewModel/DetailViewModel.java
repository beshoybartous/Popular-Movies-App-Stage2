package com.example.myapplication.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.Models.MovieTrailerModel;
import com.example.myapplication.Models.ReviewModel;
import com.example.myapplication.Repository.DetailRepository;

public class DetailViewModel extends AndroidViewModel {
    DetailRepository detailRepository;
    LiveData<MovieTrailerModel>trailers;
    public DetailViewModel(@NonNull Application application) {
        super( application );
        detailRepository =new DetailRepository( application );
    }
    public MutableLiveData<MovieTrailerModel> getTrailer(int id){
        return detailRepository.getTrailer( id );
    }
    public MutableLiveData<ReviewModel>getReview(int id){
        return detailRepository.getReview( id );
    }
}
