package com.example.railsexplorer.railsexplorer;

import com.example.railsexplorer.railsexplorer.model.CommitEntry;

import java.util.List;

public class CommitObjectMessage {

    public List<CommitEntry> entries;

    public CommitObjectMessage(List<CommitEntry> entries) {
        this.entries = entries;
    }
}
