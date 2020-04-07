package com.example.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Entities.Coin;
import com.example.app.Entities.CoinLoreResponse;
import com.example.app.Entities.CoinService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.detail_container) != null) {
            mTwoPane = true;
        }

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rvList);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Gson gson = new Gson();
        CoinLoreResponse jsonString = gson.fromJson(CoinLoreResponse.json, CoinLoreResponse.class);
        List<Coin> coin = jsonString.getData();

        final RecyclerView.Adapter mAdapter = new CoinAdapter(this, coin, mTwoPane);
        mRecyclerView.setAdapter(mAdapter);


        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.coinlore.net/api/tickers/").addConverterFactory(GsonConverterFactory.create()).build();
        CoinService service = retrofit.create(CoinService.class);
        Call<CoinLoreResponse> coinsCall = service.getCoins();
        Response<CoinLoreResponse> coinsResponse;
        coinsCall.enqueue(new Callback<CoinLoreResponse>() {
            @Override
            public void onResponse(Call<CoinLoreResponse> call, Response<CoinLoreResponse> response) {
                if(response.isSuccessful()) {
                    List<Coin> coins = response.body().getData();
                    ((CoinAdapter) mAdapter).setCoins(coins);
                } else {

                }
            }

            @Override
            public void onFailure(Call<CoinLoreResponse> call, Throwable t) {

            }
        });


    }
}

