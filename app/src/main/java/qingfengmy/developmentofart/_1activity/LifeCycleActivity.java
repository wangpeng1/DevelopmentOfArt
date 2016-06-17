package qingfengmy.developmentofart._1activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import qingfengmy.developmentofart.R;

public class LifeCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        if (savedInstanceState != null) {
            String test = (String) savedInstanceState.get("test");
            Log.e("aaa", "onCreate:" + test);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String test = "------------";
        Log.e("aaa", "onSaveInstanceState:" + test);
        outState.putString("test", test);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String test = (String) savedInstanceState.get("test");
        Log.e("aaa", "onRestoreInstanceState:" + test);
    }
}
