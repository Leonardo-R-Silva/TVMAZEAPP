package br.com.lrdsilva.tvmazeapp.helpers;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import br.com.lrdsilva.tvmazeapp.adapters.GridAdapter;

public class DrawableTarget {

    private GridAdapter.ViewHolder viewHolder;
    private Bitmap bitmap;
    private View toggleButton;

    public DrawableTarget(GridAdapter.ViewHolder viewHolder,View toggleButton){

        this.viewHolder = viewHolder;
        this.toggleButton = toggleButton;
    }

    private void handleDrawable(Bitmap bitmap)
    {
        BitmapDrawable bdrawable = new BitmapDrawable(bitmap);
        toggleButton.setBackgroundDrawable(bdrawable);
    }
    public Target getTarget()
    {
        return createTarget();

    }
    private Target createTarget()
    {
            Target targetDrawable = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                handleDrawable(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                System.out.println(e.toString());
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        return targetDrawable;
    }

}
