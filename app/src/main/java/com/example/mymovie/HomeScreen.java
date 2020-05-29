package com.example.mymovie;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mymovie.Fragment.HomeFragment;
import com.example.mymovie.Fragment.Maps;
import com.example.mymovie.Fragment.MovieMemoirFragment;
import com.example.mymovie.Fragment.MovieSearchFragment;
import com.example.mymovie.Fragment.ReportsFragment;
import com.example.mymovie.Fragment.WatchlistFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);       //adding the toolbar
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nv);

        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String identify=bundle.getString("identify");
        if(!identify.isEmpty()){
            SharedPreferences sharedPref= getSharedPreferences("User", Context.MODE_PRIVATE);
            SharedPreferences.Editor spEditor = sharedPref.edit();
            spEditor.putString("identify", identify);
            spEditor.apply();
        }
        //these two lines of code show the navicon drawer icon top left hand side
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        replaceFragment(new HomeFragment());
        navigationView.setNavigationItemSelectedListener(this);



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.addMessage:
                replaceFragment(new AddFragment());
                break;
            case R.id.displayMessage:
                replaceFragment(new ViewFragment());
                break;
            case R.id.home:
                replaceFragment(new HomeFragment());
                break;
            case R.id.movieSearch:
                replaceFragment(new MovieSearchFragment());
                break;
            case R.id.movieMemoir:
                replaceFragment(new MovieMemoirFragment());
                break;
            case R.id.reports:
                replaceFragment(new ReportsFragment());
                break;
            case R.id.watchList:
                replaceFragment(new WatchlistFragment());
                break;
            case R.id.maps:
                replaceFragment(new Maps());
                break;
        }    //this code closes the drawer after you selected an item from the menu, otherwise stay open
             drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void replaceFragment(Fragment nextFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, nextFragment);
        fragmentTransaction.commit();
    }
}
