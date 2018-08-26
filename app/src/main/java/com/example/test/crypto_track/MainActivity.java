package com.example.test.crypto_track;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.test.crypto_track.Adapter.CoinAdapter;
import com.example.test.crypto_track.Interface.ILoadMore;
import com.example.test.crypto_track.Model.CoinModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    List<CoinModel> newItems;

    List<CoinModel> items = new ArrayList<>();
    CoinAdapter adapter;
    RecyclerView recyclerView;

    OkHttpClient client;
    Request request;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.rootLayout);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadFirst10Coin(0);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                items.clear();
                loadFirst10Coin(0);
                setUpAdapter();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.coinList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setUpAdapter();
    }

    private void setUpAdapter() {

        adapter = new CoinAdapter(recyclerView, MainActivity.this, items);
        recyclerView.setAdapter(adapter);
        adapter.setiLoadMore(new ILoadMore() {
            @Override
            public void onLoadMore() {

                if (items.size() <= 1000) {
                    loadNext10Coin(items.size());
                } else {
                    Toast.makeText(MainActivity.this, "Max Item is 1000", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadNext10Coin(int index) {

        client = new OkHttpClient();
        String apiUrl = "https://api.coinmarketcap.com/v1/ticker/?start=%d&limit=10";
        request = new Request.Builder().url(String.format(apiUrl, index))
                .build();

        swipeRefreshLayout.setRefreshing(true);
        client.newCall(request)
                .enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                Gson gson = new Gson();
                newItems = gson.fromJson(body, new TypeToken<List<CoinModel>>() {
                }.getType());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        items.addAll(newItems);
                        adapter.setLoaded();
                        adapter.updateData(items);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });

//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                items.addAll(newItems);
//                adapter.setLoaded();
//                adapter.updateData(items);
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
    }

    private void loadFirst10Coin(int index) {
        client = new OkHttpClient();
        request = new Request.Builder().url(String.format("https://api.coinmarketcap.com/v1/ticker/?start=%d&limit=10", index)).build();

        swipeRefreshLayout.setRefreshing(true);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                Gson gson = new Gson();
                newItems = gson.fromJson(body, new TypeToken<List<CoinModel>>() {
                }.getType());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("Data", newItems.toString());
                        adapter.updateData(newItems);
                    }
                });
            }
        });

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
