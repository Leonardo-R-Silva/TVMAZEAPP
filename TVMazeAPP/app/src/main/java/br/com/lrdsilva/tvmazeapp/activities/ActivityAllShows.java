package br.com.lrdsilva.tvmazeapp.activities;

import android.os.Bundle;

import br.com.lrdsilva.tvmazeapp.R;

import br.com.lrdsilva.tvmazeapp.network.RetrofitBuilder;

public class ActivityAllShows extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_shows);
        activityBaseSharedInstance = this;
        retrofit = new RetrofitBuilder();
        initGallery();
        initDrawerMenu();
        objClass = getClass();
        fetchData();


    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}

