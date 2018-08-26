package com.example.test.crypto_track.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.crypto_track.Interface.ILoadMore;
import com.example.test.crypto_track.MainActivity;
import com.example.test.crypto_track.Model.CoinModel;
import com.example.test.crypto_track.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.test.crypto_track.R.*;

public class CoinAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ILoadMore iLoadMore;
    Activity activity;
    boolean isLoading;
    List<CoinModel> items;

    int visibleThreshhold = 5, lastVisibleItem, totalItemCount;

    public CoinAdapter(RecyclerView recyclerView, MainActivity mainActivity, List<CoinModel> items) {
        this.activity = activity;
        this.items = items;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshhold)) {

                    if (iLoadMore != null) {
                        iLoadMore.onLoadMore();
                        isLoading = true;
                    }
                }
            }
        });

    }

    public void setiLoadMore(ILoadMore iLoadMore) {
        this.iLoadMore = iLoadMore;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layout = LayoutInflater.from(activity);
        View v = layout.inflate(R.layout.coin_layout, parent, false);

        return new CoinViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        CoinModel item = items.get(position);
        CoinViewHolder holderItem = (CoinViewHolder) holder;

        holderItem.icon_name.setText(item.getName());
        holderItem.icon_symbol.setText(item.getSymbol());
        holderItem.icon_price.setText(item.getPrice_usd());
        holderItem.one_hour_change.setText(item.getPrice_change_1hr() + "%");
        holderItem.twenty_hours_change.setText(item.getPrice_change_24hr() + "%");
        holderItem.seven_days_change.setText(item.getPrice_change_7d() + "%");

        Picasso.get().load(new StringBuilder("https://res.cloudinary.com/dxi90ksom/image/upload/")
                .append(item.getSymbol()
                        .toLowerCase())
                        .append(".png")
                        .toString())
                .into(holderItem.icon_icon);

        holderItem.one_hour_change.setTextColor(item.getPrice_change_1hr().contains("-") ? Color.parseColor("#FF0000")
                : Color.parseColor("#32CD32"));

        holderItem.twenty_hours_change.setTextColor(item.getPrice_change_1hr().contains("-") ? Color.parseColor("#FF0000")
                : Color.parseColor("#32CD32"));

        holderItem.seven_days_change.setTextColor(item.getPrice_change_1hr().contains("-") ? Color.parseColor("#FF0000")
                : Color.parseColor("#32CD32"));

    }

    @Override
    public int getItemCount() {

        Log.i("List Size", items.toString());

        return 0;
//        return items.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    public void updateData(List<CoinModel> coinModels) {

        this.items = coinModels;
        notifyDataSetChanged();

    }
}
