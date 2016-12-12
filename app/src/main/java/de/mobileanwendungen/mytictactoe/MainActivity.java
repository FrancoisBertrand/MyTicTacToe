package de.mobileanwendungen.mytictactoe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int counter;
    private int xPosition = 0;
    private int yPosition = 0;
    private Gameboard gameboard = null;
    private String spielerX = "X";
    private String spielerO = "O";
    private boolean spielende = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().hide();
        gameboard = new Gameboard();
        counter = 0;
    }






    /**
     * Verhindert das Drehen der View
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
