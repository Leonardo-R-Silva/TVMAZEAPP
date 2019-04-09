package br.com.lrdsilva.tvmazeapp.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import br.com.lrdsilva.tvmazeapp.R;
import br.com.lrdsilva.tvmazeapp.adapters.GridAdapter;
import br.com.lrdsilva.tvmazeapp.helpers.HawkOperations;
import br.com.lrdsilva.tvmazeapp.helpers.NetworkingStatus;
import br.com.lrdsilva.tvmazeapp.models.Show;
import br.com.lrdsilva.tvmazeapp.network.RetrofitBuilder;

public class ActivityBase extends AppCompatActivity {

    //Set public for static acess
    public RecyclerView tvShowsGalleryView;

    //UI elements
    private DrawerLayout rootDrawer;
    private ActionBarDrawerToggle rootDrawerToogle;
    private NavigationView menuDrawer;
    private TextView noConnection;
    private Button retry;

    //API Request
    protected RetrofitBuilder retrofit;

    //Static instance
    public static ActivityBase activityBaseSharedInstance;

    //Check variables
    public boolean isFirstTime = true;
    public boolean hitBottom =false;
    protected Object objClass;
    private boolean shouldAllowBack = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_shows);

    }

    public void fetchData()
    {

        if(new NetworkingStatus(this).isNetworkAvailable()){

            getMoreItems();
        }
        else{

            noConnection.setVisibility(View.VISIBLE);
            retry.setVisibility(View.VISIBLE);
        }
    }

    public void fetchData(@NonNull String name)
    {
        if(new NetworkingStatus(this).isNetworkAvailable()){

            retrofit.searchByName(name);
        }
        else{

            noConnection.setVisibility(View.VISIBLE);
            retry.setVisibility(View.VISIBLE);
        }
    }

    public void fetchData(int id)
    {
        if(new NetworkingStatus(this).isNetworkAvailable()){

            retrofit.getShowById(id);
        }
        else{

            noConnection.setVisibility(View.VISIBLE);
            retry.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {

        if(GridAdapter.adapter != null && !isFavoritesTheSame(GridAdapter.adapter.favoriteShows,GridAdapter.adapter.oldFavoriteShows))
        {
            GridAdapter.adapter.changeToggleNow();

        }
        super.onResume();

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if(rootDrawerToogle.onOptionsItemSelected(item)){

            return true;

        }


        return false;
    }

    private boolean isFavoritesTheSame(ArrayList<Integer> oldFavorites, ArrayList<Integer> newFavorites)
    {
        if (oldFavorites == null && newFavorites == null){

            Log.d("URL", "true");
            return true;
        }

        if((oldFavorites == null || newFavorites == null
                || oldFavorites.size() != newFavorites.size())){
            Log.d("URL", "false");
            return false;
        }
        Log.d("URL", ""+oldFavorites.equals(newFavorites));
        return oldFavorites.equals(newFavorites);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return super.onCreateOptionsMenu(menu);
    }

    public void initGallery()
    {

        tvShowsGalleryView = (RecyclerView) findViewById(R.id.tvshowsgallery);
        retry = (Button) findViewById(R.id.retryButton);
        noConnection = (TextView) findViewById(R.id.No_Conection_Text);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("test", "Retry");
                if(new NetworkingStatus(getApplicationContext()).isNetworkAvailable()){
                    getMoreItems();
                    noConnection.setVisibility(View.GONE);
                    retry.setVisibility(View.GONE);
                }
            }
        });
        noConnection.setVisibility(View.GONE);
        retry.setVisibility(View.GONE);
        tvShowsGalleryView.setHasFixedSize(false);
        tvShowsGalleryView.setNestedScrollingEnabled(false);

        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        tvShowsGalleryView.setLayoutManager(gridLayoutManager);

        if(tvShowsGalleryView.getAdapter() == null){
            tvShowsGalleryView.setAdapter(new GridAdapter(getApplicationContext(),new ArrayList<Show>(),new ArrayList<Integer>()));
        }

    }

    private void getMoreItems() {

        if (isFirstTime)
        {
            retrofit.getPage();
        }
        tvShowsGalleryView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);

                if (!tvShowsGalleryView.canScrollVertically(1 )&& !isFirstTime && !hitBottom){
                    retrofit.page += 1;
                    Toast.makeText(getApplicationContext(),"Loading...", Toast.LENGTH_LONG).show();
                    retrofit.getPage();
                    hitBottom = true;


                }

            }
        });
    }

    public void initDrawerMenu()
    {

        //DrawerCode
        rootDrawer = (DrawerLayout) findViewById(R.id.rootdrawer);
        rootDrawerToogle = new ActionBarDrawerToggle(this, rootDrawer,R.string.openDrawer,R.string.closeDrawer);

        menuDrawer = (NavigationView) findViewById(R.id.menudrawer) ;
        rootDrawer.addDrawerListener(rootDrawerToogle);

        menuDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int menuItemId =menuItem.getItemId();

                switch (menuItemId)
                {
                    case R.id.showsdrawer:
                        if(!objClass.equals(ActivityAllShows.class)){
                            Intent allShows = new Intent(getApplicationContext(), ActivityAllShows.class);
                            startActivity(allShows);
                        }
                        break;
                    case R.id.favoritesdrawer:
                        if(!objClass.equals(ActivityFavoriteShows.class)){
                            Intent favorite = new Intent(getApplicationContext(), ActivityFavoriteShows.class);
                            startActivity(favorite);
                        }
                        break;
                    case R.id.leavedrawer:
                        finish();
                        System.exit(0);
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });
        rootDrawerToogle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        menuDrawer.setItemIconTintList(null);

    }

    public void  initGridAdapter(ArrayList<Show> show){
        if(show.get(0) != null && isFirstTime) {

            GridAdapter gridAdapter = new GridAdapter(getApplicationContext(),  show, initFavoritos());
            tvShowsGalleryView.setAdapter(gridAdapter);
            isFirstTime=false;

        }else
        {

            GridAdapter gridAdapter= (GridAdapter) tvShowsGalleryView.getAdapter();
            GridAdapter.adapter.favoriteShows = initFavoritos();
            gridAdapter.appendContentToList(show);

        }
    }

    //TODO: Update favorites toggle on both activities
    @Override
    public void onBackPressed() {
        if (!shouldAllowBack) {

        } else {
            super.onBackPressed();
        }
    }


    public ArrayList<Integer> initFavoritos()
    {
        return new HawkOperations(getApplicationContext()).getFavorites("favorites");
    }
}
