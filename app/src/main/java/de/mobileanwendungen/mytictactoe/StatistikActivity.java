package de.mobileanwendungen.mytictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StatistikActivity extends AppCompatActivity {

    private String ges;
    private String x;
    private String o;
    private String un;
    private int gesamt;
    private int xWin;
    private int oWin;
    private int unentschie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik);
        TextView gesamtspiele = (TextView)findViewById(R.id.gesamt);
        TextView unent = (TextView)findViewById(R.id.unentschieden);
        TextView winnerX = (TextView)findViewById(R.id.xWin);
        TextView winnerO = (TextView)findViewById(R.id.oWin);

        Bundle extras = getIntent().getExtras();

        gesamt = extras.getInt("gesamt");
        xWin = extras.getInt("xWin");
        oWin = extras.getInt("oWin");
        unentschie = extras.getInt("unentschieden");

        ges = Integer.toString(gesamt);
        gesamtspiele.setText(gesamtspiele.getText() + " " + ges);

        un = Integer.toString(unentschie);
        unent.setText(unent.getText() + " " + un);

        x = Integer.toString(xWin);
        winnerX.setText(winnerX.getText() + " " + x);

        o = Integer.toString(oWin);
        winnerO.setText(winnerO.getText() + " " + o);

        //TODO 2 Buttons fuer speichern und laden implementieren

    }

    public void speichernClick(View view){
        //do something
    }

    public void ladenClick(View view){
        //do something
    }
}
