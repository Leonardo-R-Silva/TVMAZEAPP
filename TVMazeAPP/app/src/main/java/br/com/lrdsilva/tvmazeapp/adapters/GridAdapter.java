package br.com.lrdsilva.tvmazeapp.adapters;

import android.content.Context;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;


import com.squareup.picasso.Picasso;


import br.com.lrdsilva.tvmazeapp.R;

import br.com.lrdsilva.tvmazeapp.activities.ActivityBase;

import br.com.lrdsilva.tvmazeapp.helpers.HelperButtons;
import br.com.lrdsilva.tvmazeapp.models.Show;

import java.util.ArrayList;



public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private ArrayList<Show> showsList;
    private Context context;
    public ArrayList<Integer> favoriteShows = new ArrayList<>();
        public ArrayList<Integer> oldFavoriteShows = favoriteShows;
    public static GridAdapter adapter;
    private ViewHolder currentViewHolder;

    public GridAdapter(Context context, ArrayList<Show> showsList, ArrayList<Integer> favoriteShow) {

        this.showsList=showsList;
        this.context=context;
        adapter = this;
        favoriteShows =ActivityBase.activityBaseSharedInstance.initFavoritos();

    }

    @NonNull
    @Override
    public GridAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GridAdapter.ViewHolder viewHolder, int i) {

        viewHolder.favorite.setBackgroundResource(R.drawable.favorite_details_toggle);
        setViewHolder(viewHolder);
        viewHolder.favorite.setActivated(false);
        if(favoriteShows != null && favoriteShows.contains(showsList.get(i).getId())) {
            viewHolder.favorite.setChecked(true);
            viewHolder.isChecked = true;
            viewHolder.favorite.setActivated(true);
        }
        else {

            viewHolder.favorite.setBackgroundResource(R.drawable.favorite_details_toggle);
            viewHolder.favorite.setChecked(false);
            viewHolder.isChecked = false;
            viewHolder.favorite.setActivated(true);
         }

        if (showsList.get(i).getImage() != null) {
            Picasso.get()
                    .load(showsList.get(i).getImage().getMedium())

                    .placeholder(R.drawable.placeholder)
                    .into(viewHolder.imgShowPoster);

        } else {
            Picasso.get()
                    .load(R.drawable.placeholder)
                    .into(viewHolder.imgShowPoster);

        }


        onShowClick(viewHolder);
        onFavoriteChecked(viewHolder);
        viewHolder.txvGenero.setText(showsList.get(i).getGenres().toString());
        viewHolder.txvTitulo.setText(showsList.get(i).getName());

    }

    public int getItemCount() {
        return showsList.size();
    }

    private void setViewHolder(ViewHolder viewHolder)
    {
        this.currentViewHolder = viewHolder;
    }

    public ViewHolder getViewHolder()
    {
        return currentViewHolder;
    }

    public void onShowClick(@NonNull final GridAdapter.ViewHolder viewHolder){

        viewHolder.imgShowPoster.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new HelperButtons(context,viewHolder,showsList,viewHolder.favorite.isChecked()).onShowClick();

            }
        });

    }

    public void onFavoriteChecked(@NonNull final GridAdapter.ViewHolder viewHolder)
    {
            viewHolder.favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                new HelperButtons(context, viewHolder, showsList).onFavoriteClick(viewHolder.favorite);

            }
        });
    }


    public void appendContentToList(ArrayList<Show> shows)
    {
        int lastPos =  getItemCount();
        showsList.addAll(shows);
        notifyItemRangeInserted(lastPos,showsList.size());


    }

    public void changeToggleNow()
    {
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imgShowPoster;
        private TextView txvTitulo;
        private TextView txvGenero;
        private ToggleButton favorite;
        public boolean isChecked;

        public ViewHolder(View view)
        {
            super(view);

            imgShowPoster = (ImageView) view.findViewById(R.id.showposter);
            txvTitulo = (TextView) view.findViewById(R.id.showtitulo);
            txvGenero = (TextView) view.findViewById(R.id.showgenero);
            favorite = (ToggleButton) view.findViewById(R.id.favorito);
            isChecked = false;

        }


    }


}
