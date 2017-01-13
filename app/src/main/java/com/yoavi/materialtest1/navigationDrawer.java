package com.yoavi.materialtest1;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class navigationDrawer extends Fragment {

    public static final String PREF_FILE_NAME="AppData";
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private boolean mUserLearnerDrawer;
    private boolean mFromSavedInstanceSate;
    public static final String KEY_USER_LEARNED_DRAWER="user_learned_drawer";
    private View containerView;
    private Context context;

    public navigationDrawer() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnerDrawer=Boolean.valueOf(readFromPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,"false"));
        if (savedInstanceState!=null){
            mFromSavedInstanceSate=true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout=inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        return layout;
    }

    public void setUp(int fragment_id, DrawerLayout drawerlayout, final Toolbar toolbar) {
        containerView=getActivity().findViewById(fragment_id);
        mDrawerLayout = drawerlayout;
        mDrawerToggle= new ActionBarDrawerToggle(getActivity(),drawerlayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mFromSavedInstanceSate){
                    mFromSavedInstanceSate=true;
                    saveToPreferences(getActivity(),PREF_FILE_NAME,mUserLearnerDrawer+"");
                    getActivity().invalidateOptionsMenu();
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

        };
        if(!mUserLearnerDrawer&&!mFromSavedInstanceSate){
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public static void saveToPreferences(Context context,String preference_name,String preference_value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(preference_name,preference_value);
        editor.apply();
    }

    public static String readFromPreferences(Context context,String preference_name,String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(preference_name,defaultValue);
    }
}
