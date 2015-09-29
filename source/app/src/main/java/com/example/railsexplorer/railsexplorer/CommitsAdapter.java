package com.example.railsexplorer.railsexplorer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.railsexplorer.railsexplorer.model.CommitEntry;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CommitsAdapter extends RecyclerView.Adapter<CommitsAdapter.ViewHolder> {

    private List<CommitEntry> mItems;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.commit_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        if (mItems == null) {
            return;
        }
        CommitEntry commitEntry = mItems.get(position);
        viewHolder.authorName.setText(commitEntry.commit.author.name);
        viewHolder.commitSha.setText(commitEntry.sha);
        viewHolder.commitMessage.setText(commitEntry.commit.message);
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    public void setItems(List<CommitEntry> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.author_name)
        TextView authorName;

        @Bind(R.id.commit_sha)
        TextView commitSha;

        @Bind(R.id.commit_message)
        TextView commitMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
