package com.example.similarmovies.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Similar {
    @SerializedName("Similar")
    @Expose
    private Results results = null;

    public Results getResults() {
        return results;
    }

    public void setResults(Results teams) {
        this.results = results;
    }
}
