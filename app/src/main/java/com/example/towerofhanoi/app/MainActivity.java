package com.example.towerofhanoi.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.towerofhanoi.R;
import com.example.towerofhanoi.fragment.Fragment;
import com.example.towerofhanoi.fragment.FragmentGame;
import com.example.towerofhanoi.fragment.FragmentMenu;
import com.example.towerofhanoi.fragment.FragmentScores;
import com.example.towerofhanoi.init.InitApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements com.example.towerofhanoi.app.FragmentManager {

    private HashMap<String, Fragment> fragments;

    private Fragment currentFragment;

    private InitApplication initApplication;

    private static void debug(String msg) {
        Log.d("MAIN", msg);
    }

    public MainActivity() {
        currentFragment = null;
        fragments = new HashMap<>();
    }

    @Override
    public void onBackPressed() {
        boolean relayBack = true;

        if (currentFragment != null) {
            relayBack = currentFragment.onBackPressed();
        }
        if (relayBack) {
            super.onBackPressed();
        }
    }

    public void toggleNightMode() {
        initApplication.setNightModeEnabled(!initApplication.isNightModeEnabled());

        getSupportFragmentManager().beginTransaction().hide(currentFragment).commit();

        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private void addFragment(Fragment f) {
        fragments.put(f.getName().toLowerCase(), f);
        getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, f).hide(f).commit();
    }

    private void initFragments() {
        addFragment(new FragmentMenu(this, this,  MENU));
        addFragment(new FragmentScores(this, this, SCORES));
        addFragment(new FragmentGame(this, this, GAME));

        setFragment(MENU);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initApplication = InitApplication.getInstance(this);

        // Transition entre les activités
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

        // Fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // NightMode
        if (initApplication.isNightModeEnabled()) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }

        super.onCreate(savedInstanceState);

        initFragments();

        setContentView(R.layout.activity_main);
    }

    @Override
    public void setFragment(String s) {
        Fragment newFragment = fragments.get(s.toLowerCase());

        if (newFragment != null) {
            if (currentFragment != null) {
                currentFragment.onStop();
                getSupportFragmentManager().beginTransaction().hide(currentFragment).show(newFragment).commit();
                newFragment.onStart();
            } else {
                getSupportFragmentManager().beginTransaction().show(newFragment).commit();
                newFragment.onStart();
            }
            currentFragment = newFragment;
        }
    }

    @Override
    public List<String> getFragments() {
        List<String> fragmentKeys = new ArrayList<>();

        Iterator<String> keys = fragments.keySet().iterator();

        while (keys.hasNext()) {
            fragmentKeys.add(keys.next());
        }

        return fragmentKeys;
    }
}
