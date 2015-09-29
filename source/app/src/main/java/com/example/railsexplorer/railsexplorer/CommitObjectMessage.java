package com.example.railsexplorer.railsexplorer;

import com.example.railsexplorer.railsexplorer.model.CommitEntry;

public class CommitObjectMessage {

    public CommitEntry[] entries;

    public CommitObjectMessage(CommitEntry[] entries) {
        this.entries = entries;
    }
}
