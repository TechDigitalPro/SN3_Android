package com.rke.sn3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kongzue.tabbar.Tab;
import com.kongzue.tabbar.TabBarView;
import com.kongzue.tabbar.interfaces.OnTabChangeListener;
import com.rke.sn3.R;
import com.rke.sn3.common.Constant;
import com.rke.sn3.common.SN3Application;
import com.rke.sn3.fragment.HomeFragment;
import com.rke.sn3.fragment.NotificationFragment;
import com.rke.sn3.fragment.OrderServiceFragment;
import com.rke.sn3.fragment.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    private BottomNavigationView mBottomNav;

    private SN3Application appl;

    private TabBarView tabbar;

    private List<Fragment> fragments=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SN3Application.setLocaleSa(MainActivity.this);
        setContentView(R.layout.activity_main);
        appl = SN3Application.Me;
        tabbar = findViewById(R.id.tabbar);
        setupTab();
        Intent i = new Intent(MainActivity.this, LocationActivity.class);
        startActivity(i);
    }

    private void setupTab(){
        List<Tab> tabs = new ArrayList<>();
        tabs.add(new Tab(this, getResources().getString(R.string.home), R.mipmap.ic_home));
        tabs.add(new Tab(this, getResources().getString(R.string.search), R.mipmap.ic_search_below));
        tabs.add(new Tab(this, getResources().getString(R.string.notifications), R.mipmap.ic_notification));
        tabs.add(new Tab(this, getResources().getString(R.string.profile), R.mipmap.ic_profile_below));

        tabbar.setTab(tabs);
        tabbar.setOnTabChangeListener(new OnTabChangeListener() {
            @Override
            public void onTabChanged(View v, int index) {
                setTabSelection(index);
            }
        });

        fragments.add(new HomeFragment());
        fragments.add(new OrderServiceFragment());
        fragments.add(new NotificationFragment());
        fragments.add(new ProfileFragment());

        setTabSelection(0);
    }

    private void setTabSelection(int index){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        hideAllFragment(ft);
        if(!fragments.get(index).isAdded()){
            ft.add(R.id.layout_main, fragments.get(index));
            ft.show(fragments.get(index));
        }else{
            ft.show(fragments.get(index));
        }
        ft.commit();
    }
    private void hideAllFragment(FragmentTransaction ft){
        for (Fragment fragment : fragments) {
            if(fragment.isVisible()){
                ft.hide(fragment);
            }
        }
    }

}
