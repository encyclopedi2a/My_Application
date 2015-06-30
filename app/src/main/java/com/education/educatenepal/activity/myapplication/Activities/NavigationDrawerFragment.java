package com.education.educatenepal.activity.myapplication.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.education.educatenepal.activity.myapplication.R;
import com.education.educatenepal.activity.myapplication.classes.EntryAdapter;
import com.education.educatenepal.activity.myapplication.classes.EntryItem;
import com.education.educatenepal.activity.myapplication.classes.PreferenceSettingValueProvider;
import com.education.educatenepal.activity.myapplication.classes.SectionItem;
import com.education.educatenepal.activity.myapplication.fragments.HomePageFragment;
import com.education.educatenepal.activity.myapplication.fragments.ViewPagerFragment;
import com.education.educatenepal.activity.myapplication.interfaces.Item;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.ArrayList;

/**
 * Fragment used for managing interactions for and presentation of a navigation drawer.
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * design guidelines</a> for a complete explanation of the behaviors implemented here.
 */
public class NavigationDrawerFragment extends Fragment implements AdapterView.OnItemClickListener {

    /**
     * Remember the position of the selected item.
     */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    /**
     * Per the design guidelines, you should show the drawer on launch until the user manually
     * expands it. This shared preference tracks this.
     */
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private NavigationDrawerCallbacks mCallbacks;

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private View mFragmentContainerView;
    private int mCurrentSelectedPosition = 0;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;
    private ArrayList<Item> items;
    private boolean checkbox_preference;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private FacebookCallback<LoginResult> facebookCallback = new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken token = loginResult.getAccessToken();
            Toast.makeText(getActivity().getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
            Profile profile = Profile.getCurrentProfile();
            if (profile != null) {

            }
        }

        @Override
        public void onCancel() {
            Toast.makeText(getActivity().getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(FacebookException e) {
            // Toast.makeText(getActivity().getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
        }
    };

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialise the facebook sdk
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        // Read in the flag indicating whether or not the user has demonstrated awareness of the
        // drawer. See PREF_USER_LEARNED_DRAWER for details.
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }


        // Select either the default item (0) or the last selected item.
        selectItem(mCurrentSelectedPosition);
        //This will return the checkbox value from preference setting
        new PreferenceSettingValueProvider(getActivity().getApplicationContext()).provideSharedPreferenceValue();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Indicate that this fragment would like to influence the set of actions in the action bar.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, null);

        // Other app specific specializatio
        loginButton = (LoginButton) view.findViewById(R.id.loginButton);
        loginButton.setReadPermissions("user_friends", "public_profile");
        // If using in a fragment
        loginButton.setFragment(this);
        // Other app specific specialization
        mDrawerListView = (ListView) view.findViewById(R.id.drawerListView);
        mDrawerListView.setBackgroundColor(Color.WHITE);
        mDrawerListView.setOnItemClickListener(this);
        items = new ArrayList<Item>();
        if (!new PreferenceSettingValueProvider(getActivity().getApplicationContext()).provideSharedPreferenceValue()) {
            items.add(new EntryItem("Home"));
            items.add(new SectionItem("Universities in Nepal"));
            items.add(new EntryItem("Tribhuvan University"));
            items.add(new EntryItem("Pokhara University"));
            items.add(new EntryItem("Purbanchal University"));
            items.add(new EntryItem("Kathmandu University"));
            items.add(new EntryItem("Lumbini Bauddha University"));
            items.add(new EntryItem("Mahendra University"));
            items.add(new SectionItem("Edu-Nepal"));
            items.add(new EntryItem("Share this app"));
            items.add(new EntryItem("Like us on facebook"));
            items.add(new EntryItem("Settings"));
            items.add(new EntryItem("Disclaimer "));
        } else {
            items.add(new EntryItem("मुख्य पृष्ठ"));
            items.add(new SectionItem("नेपालमा विश्वविद्यालय"));
            items.add(new EntryItem("त्रिभुवन विश्वविद्यालय"));
            items.add(new EntryItem("पोखरा विश्वविद्यालय"));
            items.add(new EntryItem("पुर्बन्चल विश्वविद्यालय"));
            items.add(new EntryItem("काठमाडौँ  विश्वविद्यालय"));
            items.add(new EntryItem("लुम्बिनी बुद्ध विश्वविद्यालय"));
            items.add(new EntryItem("महेन्द्र विश्वविद्यालय"));
            items.add(new SectionItem("एजु -नेपाल"));
            items.add(new EntryItem("यो याप शेयर गर्नुहोस्"));
            items.add(new EntryItem("फेसबुकमा  लाइक गर्नुहोस्"));
            items.add(new EntryItem("सेटिङ्ग"));
            items.add(new EntryItem("डीसक्लेमर"));
        }
        EntryAdapter adapter = new EntryAdapter(getActivity().getApplicationContext(), items, checkbox_preference);
        mDrawerListView.setAdapter(adapter);
        return view;
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the navigation drawer and the action bar app icon.
        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),                    /* host Activity */
                mDrawerLayout,                    /* DrawerLayout object */
                R.drawable.ic_drawer,             /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }
                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!new PreferenceSettingValueProvider(getActivity().getApplicationContext()).provideSharedPreferenceValue()) {
                    getActionBar().setTitle("Edu-Nepal");
                } else {
                    getActionBar().setTitle("एजु -नेपाल");
                }
                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

        // If the user hasn't 'learned' about the drawer, open it to introduce them to the drawer,
        // per the navigation drawer design guidelines.
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }

        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void selectItem(int position) {
        mCurrentSelectedPosition = position;
        if (mDrawerListView != null) {
            mDrawerListView.setItemChecked(position, true);
        }
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Per the navigation drawer design guidelines, updates the action bar to show the global app
     * 'context', rather than just what's in the current screen.
     */
    private void showGlobalContextActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle(R.string.app_name);
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        switch (position) {
            case 0:
                DashboardActivity.getCurrentActivityStatua = "active";
                HomePageFragment homePageFragment = new HomePageFragment();
                ft.replace(R.id.container, homePageFragment, "HOME_FRAGMENT");
                ft.commit();
                break;
            case 2:
                DashboardActivity.getCurrentActivityStatua = "passive";
                ViewPagerFragment tuActivity = new ViewPagerFragment();
                ft.replace(R.id.container, tuActivity);
                ft.commit();
                break;
            case 3:
                DashboardActivity.getCurrentActivityStatua = "passive";
                ViewPagerFragment puActivity = new ViewPagerFragment();
                ft.replace(R.id.container, puActivity);
                ft.commit();
                break;
            case 4:
                DashboardActivity.getCurrentActivityStatua = "passive";
                ViewPagerFragment purActivity = new ViewPagerFragment();
                ft.replace(R.id.container, purActivity);
                ft.commit();
                break;
            case 5:
                DashboardActivity.getCurrentActivityStatua = "passive";
                ViewPagerFragment kuActivity = new ViewPagerFragment();
                ft.replace(R.id.container, kuActivity);
                ft.commit();
                break;
            case 6:
                DashboardActivity.getCurrentActivityStatua = "passive";
                ViewPagerFragment lbuActivity = new ViewPagerFragment();
                ft.replace(R.id.container, lbuActivity);
                ft.commit();
                break;
            case 7:
                DashboardActivity.getCurrentActivityStatua = "passive";
                ViewPagerFragment muActivity = new ViewPagerFragment();
                ft.replace(R.id.container, muActivity);
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

                break;

            case 11:
                Intent intent = new Intent(getActivity(), SettingPreferenceActivity.class);
                startActivityForResult(intent, 0);
                getActivity().finish();
                break;
            case 12:
                break;
        }
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public static interface NavigationDrawerCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onNavigationDrawerItemSelected(int position);
    }

    ////////////////

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, requestCode, data);
    }


    /////////////////

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.loginButton);
        loginButton.setReadPermissions("user_friends", "public_profile");
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, facebookCallback);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken token = loginResult.getAccessToken();
                Toast.makeText(getActivity().getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
                Profile profile = Profile.getCurrentProfile();
                if (profile != null) {

                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(getActivity().getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
            }

        });
    }
}
