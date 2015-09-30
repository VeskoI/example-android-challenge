package com.example.railsexplorer.railsexplorer;

import android.app.IntentService;
import android.content.Intent;

import com.example.railsexplorer.railsexplorer.model.CommitEntry;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class GitHubService extends IntentService {

    private static final String RAILS_REPO_COMMITS_URL = "https://api.github.com/repos/rails/rails/commits";
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
                .url(RAILS_REPO_COMMITS_URL)
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            String responseString = response.body().string();
            CommitEntry[] commitEntries = gson.fromJson(responseString, CommitEntry[].class);
            List<CommitEntry> result = sortCommitsByAuthor(commitEntries);
            ((RailsExplorerApp)getApplication()).getBus().post(new CommitObjectMessage(result));
        } catch (Exception e) {
            ((RailsExplorerApp)getApplication()).getBus().post(new CommitObjectMessage(null));
        }
    }

    /**
     * @return Sorted list of commits, such that it has only the LAST commit of each author.
     */
    private List<CommitEntry> sortCommitsByAuthor(CommitEntry[] commitEntries) {
        HashMap<String, CommitEntry> map = new LinkedHashMap<>();
        for (CommitEntry commitEntry : commitEntries) {
            String authorEmail = commitEntry.commit.author.email;
            if (!map.containsKey(authorEmail)) {
                map.put(authorEmail, commitEntry);
            }
        }

        return new ArrayList<>(map.values());
    }
}
