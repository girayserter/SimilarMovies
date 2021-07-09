package com.example.similarmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.similarmovies.databinding.ActivityMainBinding;
import com.example.similarmovies.models.Movie;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    MoviesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.rcvSimilars.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MoviesAdapter();
        binding.rcvSimilars.setAdapter(adapter);


        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        binding.search.setOnClickListener(v -> {
            String movieName=binding.txtMovieName.getText().toString();
            viewModel.getMovies(movieName).observe(this, new Observer<List<Movie>>() {
                @Override
                public void onChanged(List<Movie> teamList) {
                    adapter.addMovieList(teamList);
                    adapter.notifyDataSetChanged();
                }
            });
        });
    }
}