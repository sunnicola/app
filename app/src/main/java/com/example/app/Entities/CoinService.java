package com.example.app.Entities;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoinService {

    //create call method to get all 100 coins
    // Add a @Get Annotation with the tickers endpoint
    //Use https://square.github.io/retrofit/ for guidance on using Retrofit

    @GET("/api/tickers")

    Call<CoinLoreResponse> getCoins();

}
