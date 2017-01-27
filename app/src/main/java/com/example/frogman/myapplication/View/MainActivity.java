package com.example.frogman.myapplication.View;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.frogman.myapplication.Presenter.GameLoop;
import com.example.frogman.myapplication.R;

public class MainActivity extends AppCompatActivity {
    private GameLoop gameLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int mUIFlag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LOW_PROFILE | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(mUIFlag);
        Point screenSize= new Point();
        this.getWindowManager().getDefaultDisplay().getRealSize(screenSize);
        setContentView(R.layout.activity_main);
        gameLoop = new GameLoop(this, screenSize);
        setContentView(gameLoop);
    }

    @Override
    protected void onPause(){
        super.onPause();
        gameLoop.pause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        gameLoop.resume();
    }
}
