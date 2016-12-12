package de.mobileanwendungen.mytictactoe;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        gameboard = new Gameboard();
        counter = 0;
    }

    public void resetClick(View view){
        reset();
    }

    public void setSymbol(View view){
        Button butt = (Button)findViewById(view.getId());

        String content = (String) butt.getText();
        if(content == "" && !spielende){
            switch (butt.getId()) {
                case R.id.button_1:
                    xPosition = 0;
                    yPosition = 0;
                    break;
                case R.id.button_2:
                    xPosition = 0;
                    yPosition = 1;
                    break;
                case R.id.button_3:
                    xPosition = 0;
                    yPosition = 2;
                    break;
                case R.id.button_4:
                    xPosition = 1;
                    yPosition = 0;
                    break;
                case R.id.button_5:
                    xPosition = 1;
                    yPosition = 1;
                    break;
                case R.id.button_6:
                    xPosition = 1;
                    yPosition = 2;
                    break;
                case R.id.button_7:
                    xPosition = 2;
                    yPosition = 0;
                    break;
                case R.id.button_8:
                    xPosition = 2;
                    yPosition = 1;
                    break;
                case R.id.button_9:
                    xPosition = 2;
                    yPosition = 2;
                    break;
            }

            if(counter % 2 == 1){
                gameboard.setzeZeichen(xPosition,yPosition,spielerX);
                butt.setText(spielerX);
            }else{
                gameboard.setzeZeichen(xPosition,yPosition,spielerO);
                butt.setText(spielerO);
            }

            spielende = pruefeEnde();

            if(!spielende){
                counter++;
            }

        }

    }

    private boolean pruefeEnde(){
        if(gameboard.isWinner() && counter%2 == 1){
            ermittleGewinner(true, spielerX);
            return true;
        }
        else if(gameboard.isWinner() && counter%2 == 0){
            ermittleGewinner(true,spielerO);
            return true;
        }else if(counter >= 9){
            ermittleGewinner(false, "");
            return true;
        }

        return false;
    }

    private void ermittleGewinner(boolean status, String spieler){
        if(status == true && counter %2 == 1){
            spieler = spieler + " hat Gewonnen!";
        }else if(status == true && counter%2 == 0){
            spieler = spieler + " hat Gewonnen!";
        }else{
            spieler = spieler + "Uendschieden";
        }

        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context,spieler, Toast.LENGTH_LONG);
        toast.show();
    }

    private void reset(){
        int[] idList = {R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9,};

        TextView reset;
        for (int item : idList){
            reset = (Button)findViewById(item);
            reset.setText("");
        }
        spielende = false;
        counter = 0;
        gameboard.clear();
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
