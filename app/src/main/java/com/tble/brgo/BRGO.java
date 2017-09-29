package com.tble.brgo;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

import layout.BRGOFragment;
import layout.Calendar;
import layout.Handbook;
import layout.News;
import layout.Planner;
import layout.StudentID;
import layout.Websites;
import layout.About;

public class BRGO extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brgo);

        //set initial fragment to be news
        News initial = new News();
        FragmentTransaction transfer = getSupportFragmentManager().beginTransaction();
        transfer.replace(R.id.fragmentcontainer, initial).commit();

        //setup Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindow().setStatusBarColor(Color.parseColor("#1995AD"));

        //setup side menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemTextAppearance(R.style.itemFont);
        Menu menu = navigationView.getMenu();
        menu.clear();
        menu.add(0, R.id.nav_News, Menu.NONE, "News").setCheckable(true).setChecked(true);
        menu.add(0, R.id.nav_Calendar, Menu.NONE, "Calendar");
        menu.add(0, R.id.nav_Websites, Menu.NONE, "Websites");
        menu.add(0, R.id.nav_Handbook, Menu.NONE, "School Handbook");
        menu.add(0, R.id.nav_Planner, Menu.NONE, "Homework Planner");
        menu.add(0, R.id.nav_ID, Menu.NONE, "Student ID");
        menu.add(0, R.id.nav_About, Menu.NONE, "About");
        menu.setGroupCheckable(0,true, true);

        updateTitle();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.brgo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Update the title of the navigation bar based on what school has been selected
    public void updateTitle(){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        getSupportActionBar().setTitle(Switcher.intToString(sharedPref.getInt("School", 14273)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("School", Switcher.idToInt(id)).commit();
        updateTitle();

        //Use BRGOFragment class to provide casting for all fragments and access to new instance
        List<Fragment> list = getSupportFragmentManager().getFragments();
        Fragment oldFragment = getSupportFragmentManager().getFragments().get(list.size() - 1);
        Fragment newFragment = (Fragment)((BRGOFragment)oldFragment).updateInstance();
        getSupportFragmentManager().beginTransaction().detach(oldFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentcontainer, newFragment).commit();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) { //Handles switching between view
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = Switcher.idToFragment(id);
        FragmentTransaction transfer = getSupportFragmentManager().beginTransaction();
        transfer.replace(R.id.fragmentcontainer,fragment).addToBackStack("tag").commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
