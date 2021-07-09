package com.example.similarmovies;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.similarmovies.models.Movie;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private MoviesRepo moviesRepo=new MoviesRepo();
    private MutableLiveData<List<Movie>> movieList=null;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Movie>> getMovies(String movieName){
        movieList=moviesRepo.getMovies(movieName);
        return movieList;
    }


}
