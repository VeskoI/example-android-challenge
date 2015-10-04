package com.example.railsexplorer.railsexplorer;

final public class Modules {
    static Object[] list() {
        return new Object[] {
                new NetworkModule()
        };
    }

    private Modules() {
        // No instances.
    }
}
