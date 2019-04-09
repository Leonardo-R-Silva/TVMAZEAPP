package br.com.lrdsilva.tvmazeapp.helpers;

import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.widget.ToggleButton;

import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


import java.util.ArrayList;
import java.util.List;

import br.com.lrdsilva.tvmazeapp.R;
import br.com.lrdsilva.tvmazeapp.activities.ActivityDetails;
import br.com.lrdsilva.tvmazeapp.adapters.GridAdapter;
import br.com.lrdsilva.tvmazeapp.models.Show;



public class HelperButtons {

    private Context context;
    private GridAdapter.ViewHolder viewHolder;
    private List<Show> showsList;
    private boolean isFavorited;

    public HelperButtons(Context context, GridAdapter.ViewHolder viewHolder, List<Show>showsList)
    {
        this.context = context;
        this.viewHolder = viewHolder;
        this.showsList = showsList;
    }

    public HelperButtons(Context context, GridAdapter.ViewHolder viewHolder, List<Show>showsList,boolean isFavorited)
    {

        this.context = context;
        this.viewHolder = viewHolder;
        this.showsList = showsList;
        this.isFavorited = isFavorited;
    }
    public void onShowClick()
    {
        int showListPos = viewHolder.getAdapterPosition();
        Log.d("pos",""+ showListPos);
        Intent intent = new Intent(context, ActivityDetails.class);
        intent.putExtra("id",showsList.get(showListPos).getId());
        intent.putExtra("title", showsList.get(showListPos).getName());
        intent.putExtra("summary",showsList.get(showListPos).getSummary());
        intent.putExtra("genre",showsList.get(showListPos).getGenres().toString());
        intent.putExtra("premiered",showsList.get(showListPos).getPremiered());
        intent.putExtra("showListPos",showListPos);
        intent.putExtra("isFavorite", viewHolder.isChecked);
        boolean hasImage = showsList.get(showListPos).getImage()!=null?true:false;
        if(hasImage)
            intent.putExtra("img",showsList.get(showListPos).getImage().getMedium());
        else
            intent.putExtra("img","R.drawable.placeholder");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    public void onFavoriteClick(ToggleButton favorito)
    {

        if(favorito.isChecked()) {
            HawkOperations insertData = new HawkOperations(context, showsList.get(viewHolder.getAdapterPosition()).getId());
            viewHolder.isChecked = true;
            insertData.insertDataFavorites("favorites");
            GridAdapter.adapter.oldFavoriteShows = new ArrayList<>();
            GridAdapter.adapter.oldFavoriteShows.addAll(GridAdapter.adapter.favoriteShows);
            GridAdapter.adapter.favoriteShows = new HawkOperations(context).getFavorites("favorites");
            Hawk.destroy();
        }else {
            viewHolder.isChecked = false;
            HawkOperations deleteData = new HawkOperations(context,showsList.get(viewHolder.getAdapterPosition()).getId());
            deleteData.deleteDataFavorites("favorites");
            GridAdapter.adapter.oldFavoriteShows = new ArrayList<>();
            GridAdapter.adapter.oldFavoriteShows.addAll(GridAdapter.adapter.favoriteShows);
            GridAdapter.adapter.favoriteShows = new HawkOperations(context).getFavorites("favorites");
            Hawk.destroy();
        }

    }



}
