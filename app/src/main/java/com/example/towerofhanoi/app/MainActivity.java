package com.example.towerofhanoi.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.example.towerofhanoi.R;
import com.example.towerofhanoi.fragment.FragmentMenu;
import com.example.towerofhanoi.fragment.FragmentScores;
import com.example.towerofhanoi.init.InitApplication;

public class MainActivity extends AppCompatActivity {

    private FragmentMenu menuFragment;
    private FragmentScores scoresFragment;

    private InitApplication initApplication;

    public void toggleNightMode() {

        initApplication.setNightModeEnabled(!initApplication.isNightModeEnabled());

        getSupportFragmentManager().beginTransaction().hide(menuFragment).commit();

        Intent intent = getIntent();
        finish();
        startActivity(intent);
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

        setContentView(R.layout.activity_main);

        menuFragment = new FragmentMenu(this);
        scoresFragment = new FragmentScores(this);

        // Passage au fragment
        FragmentManager fm = getSupportFragmentManager();

        getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, scoresFragment).hide(scoresFragment).commit();

        getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, menuFragment).commit();
    }

    public FragmentMenu getMenuFragment() {
        return menuFragment;
    }

    public FragmentScores getScoresFragment() {
        return scoresFragment;
    }

    private static void debug(String msg) {
        Log.d("AAAAAAAAAAAAA", msg);
    }
}
