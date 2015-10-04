package com.example.railsexplorer.railsexplorer;

import com.example.railsexplorer.railsexplorer.model.CommitEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by vesko on 10/4/2015.
 */
public class Utils {

    private Utils() {
        // Not to be instantiated.
        throw new AssertionError();
    }
    /**
     * @return Sorted list of commits, such that it has only the LAST commit of each author.
     */
    public static List<CommitEntry> ensureOneCommitPerAuthor(List<CommitEntry> commitEntries) {
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
