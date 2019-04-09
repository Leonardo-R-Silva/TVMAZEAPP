package br.com.lrdsilva.tvmazeapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Toolbar;


import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import br.com.lrdsilva.tvmazeapp.R;
import br.com.lrdsilva.tvmazeapp.adapters.GridAdapter;
import br.com.lrdsilva.tvmazeapp.helpers.DrawableTarget;
import br.com.lrdsilva.tvmazeapp.helpers.HawkOperations;

public class ActivityDetails extends AppCompatActivity {

    TextView titleDetails;
    ImageView posterDetails;
    TextView summarydetails;
    TextView genreDetails;
    TextView premieredDetails;
    ToggleButton favoriteDetails;
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initLayout();
        getData();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
               finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void getData()
    {
        Intent get = getIntent();
        titleDetails.setText(get.getStringExtra("title"));
        summarydetails.setText(Html.fromHtml(get.getStringExtra("summary")));
        genreDetails.setText(get.getStringExtra("genre"));
        premieredDetails.setText(get.getStringExtra("premiered"));
        Picasso.get()
                .load(get.getStringExtra("img"))
                .placeholder(R.drawable.placeholder)
                .into(posterDetails);
    }

    protected void initLayout()
    {
        Intent get = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        titleDetails = findViewById(R.id.detailstitle);
        posterDetails =  findViewById(R.id.detailsshowposter);
        favoriteDetails = findViewById(R.id.favoritedetails);
        summarydetails =  findViewById(R.id.detailssummary);
        genreDetails = findViewById(R.id.detailstgenre);
        premieredDetails =  findViewById(R.id.detailspremiered);

        favoriteDetails.setText(null);
        favoriteDetails.setTextOff(null);
        favoriteDetails.setTextOn(null);
        favoriteDetails.setBackgroundResource(R.drawable.favorite_details_toggle);
        favoriteDetails.setActivated(false);
        favoriteDetails.setChecked(get.getBooleanExtra("isFavorite",false));
        favoriteDetails.setActivated(true);
        favoriteDetails.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent get = getIntent();

                if(isChecked){

                    new HawkOperations(context,get.getIntExtra("id",0)).insertDataFavorites("favorites");
                    GridAdapter.adapter.oldFavoriteShows = new ArrayList<>();
                    GridAdapter.adapter.oldFavoriteShows.addAll(GridAdapter.adapter.favoriteShows);
                    GridAdapter.adapter.favoriteShows = new HawkOperations(context).getFavorites("favorites");


                }else {

                    new HawkOperations(context, get.getIntExtra("id", 0)).deleteDataFavorites("favorites");
                    GridAdapter.adapter.oldFavoriteShows = new ArrayList<>();
                    GridAdapter.adapter.oldFavoriteShows.addAll(GridAdapter.adapter.favoriteShows);
                    GridAdapter.adapter.favoriteShows = new HawkOperations(context).getFavorites("favorites");

                }
            }
        });
    }
}
