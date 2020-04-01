package com.example.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Entities.Coin;
import com.example.app.Entities.CoinLoreResponse;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //boolean to decide which view to show
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //bundles to save necessary data
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.detail_container) != null) {
            mTwoPane = true;
        }

        RecyclerView mRecyclerView = findViewById(R.id.rvList);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Gson gson = new Gson();
        CoinLoreResponse response = gson.fromJson(CoinLoreResponse.json, CoinLoreResponse.class);
        List<Coin> coins = response.getData();

        RecyclerView.Adapter mAdapter = new CoinAdapter(this, coins, mTwoPane);
        mRecyclerView.setAdapter(mAdapter);
    }
}

