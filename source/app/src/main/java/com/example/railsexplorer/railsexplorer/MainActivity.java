package com.example.railsexplorer.railsexplorer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.railsexplorer.railsexplorer.model.CommitEntry;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final Gson gson = new Gson();

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.empty_view)
    TextView mEmptyView;

    private CommitsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new CommitsAdapter();
        mRecyclerView.setAdapter(mAdapter);

        new GetCommitsTask().execute();
    }

    private class GetCommitsTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            Request request = new Request.Builder()
                    .url("https://api.github.com/repos/rails/rails/commits")
                    .build();

            try {
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                CommitEntry[] commitEntries = gson.fromJson(s, CommitEntry[].class);
                mAdapter.setItems(Arrays.asList(commitEntries));
                mEmptyView.setVisibility(View.GONE);
            } catch (Exception e) {
                mRecyclerView.setVisibility(View.GONE);
                mEmptyView.setText(e.getMessage());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
