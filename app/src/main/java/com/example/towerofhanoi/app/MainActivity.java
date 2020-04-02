package com.example.towerofhanoi.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.example.towerofhanoi.R;
import com.example.towerofhanoi.fragment.FragmentMenu;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private FragmentMenu menu;

    @SuppressWarnings("deprecation")
    private void setLocale(Locale locale){
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            configuration.setLocale(locale);
        } else{
            configuration.locale=locale;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            getApplicationContext().createConfigurationContext(configuration);
        } else {
            resources.updateConfiguration(configuration,displayMetrics);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

        AppCompatDelegate.setDefaultNightMode(InitApplication.getInstance(this).isNightModeEnabled()?
                AppCompatDelegate.MODE_NIGHT_YES:AppCompatDelegate.MODE_NIGHT_NO);

        setLocale(new Locale(InitApplication.getInstance(this).getLanguage()));

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        menu = new FragmentMenu();

        getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, menu).commit();
    }

    private static void debug(String msg) {
        Log.d("AAAAAAAAAAAAA", msg);
    }
}
