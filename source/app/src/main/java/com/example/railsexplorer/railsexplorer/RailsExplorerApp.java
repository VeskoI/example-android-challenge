package com.example.railsexplorer.railsexplorer;

import android.app.Application;

import dagger.ObjectGraph;

public class RailsExplorerApp extends Application {

    private static ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        objectGraph = ObjectGraph.create(Modules.list());
    }

    public void inject(Object target) {
        objectGraph.inject(target);
    }
}
