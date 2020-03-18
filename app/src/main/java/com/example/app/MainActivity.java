package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
   public static final String EXTRA_MESSAGE = "au.edu.unsw.infs3634.beers.MESSAGE";

   //BOOLEAN TO DETERMINE WHICH LAYOUT IS USED
    Boolean inWide;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = findViewById(R.id.rvList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        CoinAdapter.RecyclerViewClickListener listener = new CoinAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                launchDetailActivity(position);
            }
        };
        mAdapter = new CoinAdapter(Coin.getCoins(), listener);
        mRecyclerView.setAdapter(mAdapter);


        //LAYOUT CHOSEN DEPENDS ON WHETHER SCROLVIEW2 IS IN VIEW (AKA 900DP> LAYOUT)
        inWide = findViewById(R.id.scrollView2) != null;

        Intent intent = getIntent();
        int value = intent.getIntExtra("message", 0);

        // FRAGMENT
        // calling fragment from an activity
        FragmentManager manager = getSupportFragmentManager();
        // 'instruction' method that executes the fragment and makes fragment appear on screen
        FragmentTransaction transaction = manager.beginTransaction();
        //create fragment object
        Fragment fragment = new DetailFragment();
        //creating bundle
        Bundle arguments = new Bundle();
        arguments.putBoolean("inWide", inWide);
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction().replace(R.id.scrollView2, fragment).commit();
        transaction.replace(R.id.scrollView2, fragment);
        transaction.commit();
    }

    private void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_MESSAGE, position);
        startActivity(intent);
    }
}
