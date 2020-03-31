package com.example.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.app.Entities.Coin;

import java.text.NumberFormat;

public class DetailFragment extends Fragment {
    public static final String ARG_ITEM_ID = "item_id";
    private Coin mCoin;

    public DetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments().containsKey(ARG_ITEM_ID)) {
            mCoin = Coin.getCoin(getArguments().getString(ARG_ITEM_ID));
            this.getActivity().setTitle(mCoin.getName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        if(mCoin != null) {
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            ((TextView) rootView.findViewById(R.id.tvID)).setText(mCoin.getId());
            ((TextView) rootView.findViewById(R.id.tvSymbol)).setText(mCoin.getSymbol());
            ((TextView) rootView.findViewById(R.id.tvName)).setText(mCoin.getName());
            ((TextView) rootView.findViewById(R.id.tvNameId)).setText(mCoin.getNameid());
            ((TextView) rootView.findViewById(R.id.tvRank)).setText(mCoin.getRank());
            ((TextView) rootView.findViewById(R.id.tvPrice)).setText(formatter.format(mCoin.getPriceUsd()));
            ((TextView) rootView.findViewById(R.id.tvChange1hField)).setText(String.valueOf(mCoin.getPercentChange1h()) + " %");
            ((TextView) rootView.findViewById(R.id.tvChange24hField)).setText(String.valueOf(mCoin.getPercentChange24h()) + " %");
            ((TextView) rootView.findViewById(R.id.tvChange7dField)).setText(String.valueOf(mCoin.getPercentChange7d()) + " %");
            ((TextView) rootView.findViewById(R.id.tvMarketcapField)).setText(formatter.format(mCoin.getMarketCapUsd()));
            ((TextView) rootView.findViewById(R.id.tvVolumeField)).setText(formatter.format(mCoin.getVolume24()));
            ((TextView) rootView.findViewById(R.id.tvVolume24A)).setText(formatter.format(mCoin.getVolume24a()));
            ((TextView) rootView.findViewById(R.id.tvTSupply)).setText(formatter.format(mCoin.getCsupply()));
            ((TextView) rootView.findViewById(R.id.tvMSupply)).setText(formatter.format(mCoin.getTsupply()));
            ((TextView) rootView.findViewById(R.id.tvMSupply)).setText(formatter.format(mCoin.getMsupply()));

            ((ImageView) rootView.findViewById(R.id.ivSearch)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchCoin(mCoin.getName());
                }
            });
        }
        return rootView;
    }

    private void searchCoin(String name) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + name));
        startActivity(intent);
    }
}