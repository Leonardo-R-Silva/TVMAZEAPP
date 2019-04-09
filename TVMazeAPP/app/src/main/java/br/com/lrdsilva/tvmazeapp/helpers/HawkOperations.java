package br.com.lrdsilva.tvmazeapp.helpers;

import android.content.Context;
import android.util.Log;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.Parser;

import java.util.ArrayList;



public class HawkOperations {


    private Context context;
    private Integer showId;

    public HawkOperations(Context context, Integer showId)
    {
        this.context = context;
        this.showId = showId;

        Hawk.init(context).build();


    }
    private Context contex;
    public HawkOperations(Context context)
    {
        this.contex = context;
        Hawk.init(context).build();
    }


    public void insertDataFavorites(String key)
    {
        String hashCode = ""+key.hashCode();
        if(Hawk.contains(hashCode))
        {
            ArrayList<Integer> favoritesList = Hawk.get(hashCode);
            if(!favoritesList.contains(showId))
                favoritesList.add(showId);
            Hawk.put(hashCode,favoritesList);

        }else{

            ArrayList<Integer> favoritesList = new ArrayList<>();
            favoritesList.add(showId);
            Hawk.put(hashCode,favoritesList);
        }
    }

    public void deleteDataFavorites(String key) {
        String hashCode = "" + key.hashCode();

        if (Hawk.contains(hashCode)) {

            ArrayList<Integer> favoritesList = Hawk.get(hashCode);
            favoritesList.remove(showId);
            Hawk.put(hashCode, favoritesList);
        }

    }

    public ArrayList<Integer> getFavorites(String key)
    {
        String hashCode = "" + key.hashCode();
        ArrayList<Integer> favoritesList = Hawk.get(hashCode);
        return  favoritesList;
    }


}
