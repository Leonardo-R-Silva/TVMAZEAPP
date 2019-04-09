package br.com.lrdsilva.tvmazeapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;


import java.util.ArrayList;

import br.com.lrdsilva.tvmazeapp.R;



import br.com.lrdsilva.tvmazeapp.network.RetrofitBuilder;

public class ActivityFavoriteShows extends ActivityBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_shows);
        activityBaseSharedInstance = this;
        retrofit = new RetrofitBuilder();
        initGallery();
        initDrawerMenu();
        objClass = getClass();
        ArrayList<Integer> favorite = initFavoritos();
        //TODO: Not a good way to do it, slows down Init
        if(favorite != null)
            for(int i =0; i<favorite.size(); i++)
            {
                fetchData(favorite.get(i));
            }


    }

}





