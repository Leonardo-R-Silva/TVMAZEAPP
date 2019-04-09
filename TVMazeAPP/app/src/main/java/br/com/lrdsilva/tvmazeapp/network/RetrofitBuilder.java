package br.com.lrdsilva.tvmazeapp.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.lrdsilva.tvmazeapp.activities.ActivityAllShows;
import br.com.lrdsilva.tvmazeapp.activities.ActivityBase;
import br.com.lrdsilva.tvmazeapp.adapters.GridAdapter;
import br.com.lrdsilva.tvmazeapp.helpers.NetworkingStatus;
import br.com.lrdsilva.tvmazeapp.interfaces.TvMazeApiEndPoints;
import br.com.lrdsilva.tvmazeapp.models.Show;
import br.com.lrdsilva.tvmazeapp.models.Shows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitBuilder {

    public int page = 0;

    public RetrofitBuilder()
    {

    }

    private Retrofit buildRetrofit(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TvMazeApiEndPoints.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        return  retrofit;
    }

    //@Query by page number
    public void getPage() {

        Retrofit retrofit = buildRetrofit();
        final Context context = ActivityBase.activityBaseSharedInstance.getApplicationContext();

        TvMazeApiEndPoints tvMazeApiEndPoints = retrofit.create(TvMazeApiEndPoints.class);
        if (!new NetworkingStatus(context).isNetworkAvailable()) {
            Toast.makeText(context, "Network not available!", Toast.LENGTH_SHORT).show();
            return;
        }
        Call<List<Show>> call = tvMazeApiEndPoints.getAllShows(page);
        call.enqueue(new Callback<List<Show>>() {

            @Override
            public void onResponse(Call<List<Show>> call, Response<List<Show>> response) {

                if (response.isSuccessful()) {

                    List<Show> shows = response.body();
                    ActivityBase.activityBaseSharedInstance.initGridAdapter((ArrayList<Show>) shows);
                    ActivityBase.activityBaseSharedInstance.hitBottom = false;
                    Toast.makeText(context,"Displaying Content...", Toast.LENGTH_LONG).show();

                } else {
                    switch (response.code()) {

                        case 404:

                            Toast.makeText(context, "Page not Found", Toast.LENGTH_SHORT).show();
                            break;

                        case 408:

                            Toast.makeText(context, "Timeout Request", Toast.LENGTH_SHORT).show();
                            break;

                        case 500:

                            Toast.makeText(context, "Internal Server Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Show>> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT);

            }
        });
    }
    //@Query by String
    public void searchByName(String name)
    {
        ActivityBase.activityBaseSharedInstance.initGallery();
        Retrofit retrofit = buildRetrofit();
        final Context context = ActivityBase.activityBaseSharedInstance.getApplicationContext();
        if(!new NetworkingStatus(context).isNetworkAvailable())
        {
            Toast.makeText(context, "Network not available!", Toast.LENGTH_SHORT).show();
            return;
        }

        TvMazeApiEndPoints tvMazeApiEndPoints = retrofit.create(TvMazeApiEndPoints.class);

        Call<List<Shows>> call = tvMazeApiEndPoints.getShowsWithName(name);

        call.enqueue(new Callback<List<Shows>>() {

            @Override
            public void onResponse(Call<List<Shows>> call, Response<List<Shows>> response) {

                ArrayList<Show> onlyShow = new ArrayList<Show>();
                if(response.isSuccessful())
                    for(Shows s : response.body())
                    {

                        onlyShow.add(s.getShow());
                        if(onlyShow.get(0) != null&& ActivityBase.activityBaseSharedInstance.tvShowsGalleryView.getAdapter() != null) {

                            GridAdapter gridAdapter = new GridAdapter(context, (ArrayList<Show>) onlyShow, ActivityBase.activityBaseSharedInstance.initFavoritos());
                            ActivityBase.activityBaseSharedInstance.tvShowsGalleryView.setAdapter(gridAdapter);
                            Toast.makeText(context,"Displaying Content...", Toast.LENGTH_LONG).show();
                        }
                    }
                else
                {
                    switch(response.code())
                    {
                        case 404:

                            Toast.makeText(context, "Page not Found", Toast.LENGTH_SHORT).show();
                            break;

                        case 408:

                            Toast.makeText(context, "Timeout Request", Toast.LENGTH_SHORT).show();
                            break;

                        case 500:

                            Toast.makeText(context, "Internal Server Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Shows>> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage().toString(), Toast.LENGTH_SHORT);
            }
        });
    }
    //@Query by id
    public void getShowById(int id)
    {
        Retrofit retrofit = buildRetrofit();
        final Context context = ActivityBase.activityBaseSharedInstance.getApplicationContext();

        TvMazeApiEndPoints tvMazeApiEndPoints = retrofit.create(TvMazeApiEndPoints.class);
        if (!new NetworkingStatus(context).isNetworkAvailable()) {
            Toast.makeText(context, "Network not available!", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<Show> call = tvMazeApiEndPoints.getById(id);

        call.enqueue(new Callback<Show>() {
            @Override
            public void onResponse(Call<Show> call, Response<Show> response) {

                if (response.isSuccessful()) {

                    List<Show> shows = new ArrayList<>();
                    shows.add(response.body());
                    ActivityBase.activityBaseSharedInstance.initGridAdapter((ArrayList<Show>) shows);
                    ActivityBase.activityBaseSharedInstance.hitBottom = false;


                } else {
                    switch (response.code()) {

                        case 404:

                            Toast.makeText(context, "Page not Found", Toast.LENGTH_SHORT).show();
                            break;

                        case 408:

                            Toast.makeText(context, "Timeout Request", Toast.LENGTH_SHORT).show();
                            break;

                        case 500:

                            Toast.makeText(context, "Internal Server Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<Show> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage().toString(), Toast.LENGTH_SHORT);

            }
        });
    }
}
