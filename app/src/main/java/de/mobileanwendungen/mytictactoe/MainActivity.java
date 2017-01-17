package de.mobileanwendungen.mytictactoe;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class MainActivity extends AppCompatActivity {

    private int counter;
    private int xPosition = 0;
    private int yPosition = 0;
    private Gameboard gameboard = null;
    private String spielerX = "X";
    private String spielerO = "O";
    private boolean spielende = false;
    private RadioButton radioButtonO;
    private RadioButton radioButtonX;
    private int gamecounter = 0;
    private int unentschieden = 0;
    private int winX = 0;
    private int winO = 0;
    Socket client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameboard = new Gameboard();
        counter = 0;
        radioButtonO = (RadioButton)findViewById(R.id.radio_O);
        radioButtonX = (RadioButton)findViewById(R.id.radio_X);
        radioButtonO.setChecked(true);
        radioButtonX.setChecked(false);
        System.out.println(gamecounter);
        new Thread(new Runnable() {
            @Override
            public void run() {
                tcp();
            }
        }).start();

    }

    public void tcp(){
        //Socket client;

        try {
            client = new Socket("192.168.2.113", 60123);
            PrintWriter out1 = new PrintWriter(client.getOutputStream(), true);
            PrintWriter out2 = new PrintWriter(client.getOutputStream(), true);
            PrintWriter out3 = new PrintWriter(client.getOutputStream(), true);
            PrintWriter out4 = new PrintWriter(client.getOutputStream(), true);

            System.out.println("server gefunden :)");
            out1.println(unentschieden);
            out1.flush();


            out2.println(winX);
            out2.flush();


            out3.println(winO);
            out3.flush();


            out4.println(gamecounter);
            out4.flush();


           /*InputStreamReader input1 = new InputStreamReader(client.getInputStream());

            BufferedReader in1 = new BufferedReader(input1);


            String s1 = in1.readLine();
            System.out.println("vom Server " + s1);
           */
        }
        catch (UnknownHostException e){
            System.out.println("Unknown Error");
            e.printStackTrace();
        }
        catch (IOException e){
            System.out.println("IO Error");
            e.printStackTrace();
        }
    }


    /**
     * Aufruf der Reset methode
     * @param view
     */
    public void resetClick(View view){
        System.out.println(unentschieden);
        System.out.println(winO);
        System.out.println(winX);
        System.out.println(gamecounter);
        new Thread(new Runnable() {
            @Override
            public void run() {
                tcp();
            }
        }).start();
        reset();
    }

    public void statistikClick(View view){
        Intent spielStaende = new Intent(MainActivity.this, StatistikActivity.class);
        spielStaende.putExtra("gesamt", gamecounter);
        spielStaende.putExtra("xWin", winX);
        spielStaende.putExtra("oWin", winO);
        spielStaende.putExtra("unentschieden", unentschieden);
        startActivity(spielStaende);
    }

    /**
     * Wechselt zwischen den RadioButtons, je nachdem welcher Spieler am Zug ist.
     * @param view
     */
    public void onRadioButtonChanged(View view){
        if(counter%2 == 1 ){
            radioButtonO.setChecked(true);
        }
        else if(counter%2 == 0){
            radioButtonX.setChecked(true);
        }
    }



    /**
     * Setzt je nachdem welcher Spieler am Zug ist, das entsprechende Zeichen an der gewaehlten Position
     * @param view
     */
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
                onRadioButtonChanged(view);
            }
            else{
                gameboard.setzeZeichen(xPosition,yPosition,spielerO);
                butt.setText(spielerO);
                onRadioButtonChanged(view);
            }

            spielende = pruefeEnde();

            if(!spielende){
                counter++;
            }

        }

    }

    /**
     * Ueberprueft, ob das Spiel zu ende ist
     * @return
     */
    private boolean pruefeEnde(){
        if(gameboard.isWinner() && counter%2 == 1){
            ermittleGewinner(true, spielerX);
            return true;
        }
        else if(gameboard.isWinner() && counter%2 == 0){
            ermittleGewinner(true,spielerO);
            return true;
        }
        else if(counter >= 8){
            ermittleGewinner(false, spielerX);
            return true;
        }

        return false;
    }

    /**
     * Ermittelt, welcher spieler gewonnen hat oder ob ein Unendschieden vorliegt
     * @param status
     * @param spieler
     */
    private void ermittleGewinner(boolean status, String spieler){
        if(status == true && counter %2 == 1){
            spieler = spieler + " hat Gewonnen!";
            winX++;
            gamecounter++;
        }
        else if(status == true && counter%2 == 0){
            spieler = spieler + " hat Gewonnen!";
            winO++;
            gamecounter++;
        }
        else if(status == false){
            spieler = "Uendschieden";
            unentschieden++;
            gamecounter++;
        }

        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context,spieler, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER|Gravity.CENTER,0,0);
        toast.show();
    }

    /**
     * Leert das Spielfeld fuer eine neue Runde
     * Setzt alle noetigen Parameter auf den Ursprung
     */
    private void reset(){
        int[] idList = {R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9,};

        TextView reset;
        for (int item : idList){
            reset = (Button)findViewById(item);
            reset.setText("");
        }
        spielende = false;
        counter = 0;
        radioButtonO.setChecked(true);
        radioButtonX.setChecked(false);
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
