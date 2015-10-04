package com.example.railsexplorer.railsexplorer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.MoshiConverterFactory;
import retrofit.Retrofit;

@Module(
        injects = {
                MainActivity.class
        }
)
public class NetworkModule {

    private static final String BASE_URL = "https://api.github.com";

    @Provides
    @Singleton
    GitHubService providesGitHubService(Retrofit retrofit) {
        return retrofit.create(GitHubService.class);
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }
}
