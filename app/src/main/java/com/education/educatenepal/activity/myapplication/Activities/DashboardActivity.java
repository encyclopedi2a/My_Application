package com.education.educatenepal.activity.myapplication.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.education.educatenepal.activity.myapplication.R;
import com.education.educatenepal.activity.myapplication.classes.DisclaimerDialogBox;
import com.education.educatenepal.activity.myapplication.classes.EntryAdapter;
import com.education.educatenepal.activity.myapplication.classes.EntryItem;
import com.education.educatenepal.activity.myapplication.classes.PreferenceSettingValueProvider;
import com.education.educatenepal.activity.myapplication.classes.SectionItem;
import com.education.educatenepal.activity.myapplication.fragments.FacebookLikeFragment;
import com.education.educatenepal.activity.myapplication.fragments.HomePageFragment;
import com.education.educatenepal.activity.myapplication.fragments.ViewPagerFragment;
import com.education.educatenepal.activity.myapplication.interfaces.Item;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;
    private Toolbar toolbar;
    private TextView toolbartitle;
    private ArrayList<Item> items;
    private boolean checkbox_preference;
    private String getCurrentActivityStatua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        toolbartitle = (TextView) findViewById(R.id.titletool);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle(null);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        getCurrentActivityStatua = "active";
        HomePageFragment homePageFragment = new HomePageFragment();
        ft.replace(R.id.content_frame, homePageFragment, "HOME_FRAGMENT");
        ft.commit();
        // set a custom shadow that overlays the main content when the drawer opens
        // mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        //setting up the listview with arrayitem
        items = new ArrayList<Item>();
        if (!new PreferenceSettingValueProvider(getApplicationContext()).provideSharedPreferenceValue()) {
            String englishListArray[] = getResources().getStringArray(R.array.englishListArray);
            for (String array : englishListArray)
                if (array.trim().equals("Universities in Nepal") || array.trim().equals("Edu-Nepal"))
                    items.add(new SectionItem(array));
                else
                    items.add(new EntryItem(array));
        } else {
            String nepaliListArray[] = getResources().getStringArray(R.array.nepaliListArray);
            for (String array : nepaliListArray)
                if (array.trim().equals("मुख्य पृष्ठ") || array.trim().equals("एजु -नेपाल"))
                    items.add(new SectionItem(array));
                else
                    items.add(new EntryItem(array));
        }
        EntryAdapter adapter = new EntryAdapter(getApplicationContext(), items, checkbox_preference);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        //ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                if (!new PreferenceSettingValueProvider(getApplicationContext()).provideSharedPreferenceValue()) {
                    toolbartitle.setText("EDU-NEPAL");
                } else {
                    toolbartitle.setText("एजु -नेपाल");
                }
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(DashboardActivity.this, SettingPreferenceActivity.class);
                startActivityForResult(intent, 0);
                DashboardActivity.this.finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            Bundle args = new Bundle();
            args.putString("position", String.valueOf(position));
            switch (position) {
                case 0:
                    getCurrentActivityStatua = "active";
                    HomePageFragment homePageFragment = new HomePageFragment();
                    ft.replace(R.id.content_frame, homePageFragment, "HOME_FRAGMENT");
                    ft.commit();
                    break;
                case 2:
                    getCurrentActivityStatua = "passive";
                    ViewPagerFragment tuActivity = new ViewPagerFragment();
                    tuActivity.setArguments(args);
                    ft.replace(R.id.content_frame, tuActivity);
                    ft.commit();
                    break;
                case 3:
                    getCurrentActivityStatua = "passive";
                    ViewPagerFragment puActivity = new ViewPagerFragment();
                    puActivity.setArguments(args);
                    ft.replace(R.id.content_frame, puActivity);
                    ft.commit();
                    break;
                case 4:
                    getCurrentActivityStatua = "passive";
                    ViewPagerFragment purActivity = new ViewPagerFragment();
                    purActivity.setArguments(args);
                    ft.replace(R.id.content_frame, purActivity);
                    ft.commit();
                    break;
                case 5:
                    getCurrentActivityStatua = "passive";
                    ViewPagerFragment kuActivity = new ViewPagerFragment();
                    kuActivity.setArguments(args);
                    ft.replace(R.id.content_frame, kuActivity);
                    ft.commit();
                    break;
                case 6:
                    getCurrentActivityStatua = "passive";
                    ViewPagerFragment lbuActivity = new ViewPagerFragment();
                    lbuActivity.setArguments(args);
                    ft.replace(R.id.content_frame, lbuActivity);
                    ft.commit();
                    break;
                case 7:
                    getCurrentActivityStatua = "passive";
                    ViewPagerFragment muActivity = new ViewPagerFragment();
                    muActivity.setArguments(args);
                    ft.replace(R.id.content_frame, muActivity);
                    ft.commit();
                    break;
                case 9:
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,
                            "Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                    break;
                case 10:
                    getCurrentActivityStatua = "passive";
                    FacebookLikeFragment facebookLikeFragment = new FacebookLikeFragment();
                    ft.replace(R.id.content_frame, facebookLikeFragment);
                    ft.commit();
                    break;

                case 11:
                    Intent intent = new Intent(DashboardActivity.this, SettingPreferenceActivity.class);
                    startActivityForResult(intent, 0);
                    DashboardActivity.this.finish();
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    break;
                case 12:
                    new DisclaimerDialogBox(DashboardActivity.this).showDialog();
                    break;
            }
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }
    }

    private void selectItem(int position) {
        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        toolbartitle.setText(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (getCurrentActivityStatua.equals("active")) {
            finish();
        } else {
            getCurrentActivityStatua = "active";
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            HomePageFragment homePageFragment = new HomePageFragment();
            ft.replace(R.id.content_frame, homePageFragment, "HOME_FRAGMENT");
            ft.commit();
        }
    }
}




