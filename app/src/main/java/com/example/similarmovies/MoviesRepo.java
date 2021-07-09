package com.example.similarmovies;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.similarmovies.models.Movie;
import com.example.similarmovies.models.Similar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class MoviesRepo {

    public List<Movie> movieList;
    public static final String BASE_URL = "https://tastedive.com/api/";


    public MutableLiveData<List<Movie>> getMovies(String movieName) {
        MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<Similar> call = api.getMovies(movieName);
        call.enqueue(new Callback<Similar>() {

            @Override
            public void onResponse(Call<Similar> call, Response<Similar> response) {
                movieList = response.body().getResults().getMovies();
                mutableLiveData.setValue(movieList);
            }

            @Override
            public void onFailure(Call<Similar> call, Throwable t) {
                Log.d("Failure: ", t.getMessage());
            }

        });
        return mutableLiveData;
    }

    public interface ApiInterface {
        @GET("similar")
        Call<Similar> getMovies(@Query("q") String movieName);
    }
}
