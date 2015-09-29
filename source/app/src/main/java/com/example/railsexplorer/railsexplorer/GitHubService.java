package com.example.railsexplorer.railsexplorer;

import android.app.IntentService;
import android.content.Intent;

import com.example.railsexplorer.railsexplorer.model.CommitEntry;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class GitHubService extends IntentService {

    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final Gson gson = new Gson();

    public GitHubService() {
        super("GitHubService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ((RailsExplorerApp)getApplication()).getBus().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((RailsExplorerApp)getApplication()).getBus().unregister(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Request request = new Request.Builder()
                .url("https://api.github.com/repos/rails/rails/commits")
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            String responseString = response.body().string();
            CommitEntry[] commitEntries = gson.fromJson(responseString, CommitEntry[].class);
            // TODO filter commitEntries by author here
            ((RailsExplorerApp)getApplication()).getBus().post(new CommitObjectMessage(commitEntries));
        } catch (Exception e) {
            ((RailsExplorerApp)getApplication()).getBus().post(new CommitObjectMessage(null));
        }
    }
}
