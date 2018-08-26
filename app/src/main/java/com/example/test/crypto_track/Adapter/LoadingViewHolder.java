package com.example.test.crypto_track.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.test.crypto_track.R;

public class LoadingViewHolder extends RecyclerView.ViewHolder {
    public ProgressBar progressBar;
    public LoadingViewHolder(View itemView) {
        super(itemView);

        progressBar=(ProgressBar)itemView.findViewById(R.id.progress_bar);
    }
}
