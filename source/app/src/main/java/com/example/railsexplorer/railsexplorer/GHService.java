package com.example.railsexplorer.railsexplorer;

import com.example.railsexplorer.railsexplorer.model.CommitEntry;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

public interface GHService {
    @GET("/repos/rails/rails/commits")
    Call<List<CommitEntry>> getRailsCommits();
}
