package com.example.railsexplorer.railsexplorer;

import android.app.Application;

import com.squareup.otto.Bus;

public class RailsExplorerApp extends Application {

    private static MainThreadBus bus;

    @Override
    public void onCreate() {
        super.onCreate();

        bus = new MainThreadBus();
    }

    public Bus getBus() {
        return bus;
    }
}
