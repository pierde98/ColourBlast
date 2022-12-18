package it.unipg.pigdm.colourblast.View.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import it.unipg.pigdm.colourblast.R;
import it.unipg.pigdm.colourblast.View.MyView;
import it.unipg.pigdm.colourblast.View.Utils.Music;

public class LevelsActivity extends AppCompatActivity {

    private Button BottoneLivello1, BottoneLivello2, BottoneLivello3, BottoneLivello4, BottoneLivello5;
    private ImageButton BackButLivelli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //impostazioni Light/Dark mode
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.DarkTheme);
        else
            setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        BackButLivelli = findViewById(R.id.backButLevels);
        BackButLivelli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        BottoneLivello1 = findViewById(R.id.Livello1);
        BottoneLivello1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyView.getInstance().setMatrix(5, 4);
                openLevelsActivity();
                //MyView.getInstance().setLevelNumber(1);
            }
        });

        BottoneLivello2 = findViewById(R.id.Livello2);

        //abilito il bottone solo se il livello precedente è stato superato
        if(MyView.getInstance().readFile(this)<2){

            BottoneLivello2.setEnabled(false);
            BottoneLivello2.getBackground().setAlpha(120);

        }
        else{
            BottoneLivello2.setEnabled(true);
            BottoneLivello2.getBackground().setAlpha(255);
        }
        BottoneLivello2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyView.getInstance().setMatrix(8, 5);
                openLevelsActivity();
            }
        });

        BottoneLivello3 = findViewById(R.id.Livello3);

        //abilito il bottone solo se il livello precedente è stato superato
        if(MyView.getInstance().readFile(this)<3){
            BottoneLivello3.setEnabled(false);
            BottoneLivello3.getBackground().setAlpha(120);
        }
        else{
            BottoneLivello3.setEnabled(true);
            BottoneLivello3.getBackground().setAlpha(255);
        }
        BottoneLivello3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyView.getInstance().setMatrix(10, 6);
                openLevelsActivity();
            }
        });

        BottoneLivello4 = findViewById(R.id.Livello4);

        //abilito il bottone solo se il livello precedente è stato superato
        if(MyView.getInstance().readFile(this)<4){
            BottoneLivello4.setEnabled(false);
            BottoneLivello4.getBackground().setAlpha(120);
            //Toast.makeText(LevelsActivity.this, "Ultimo livello sbloccato " + MyView.getInstance().readFile(this), Toast.LENGTH_SHORT).show();
        }
        else{
            BottoneLivello4.setEnabled(true);
            BottoneLivello4.getBackground().setAlpha(255);
        }

        BottoneLivello4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyView.getInstance().setMatrix(12, 7);
                openLevelsActivity();

            }
        });

        BottoneLivello5 = findViewById(R.id.Livello5);

        //abilito il bottone solo se il livello precedente è stato superato
        if(MyView.getInstance().readFile(this)<5){
            BottoneLivello5.setEnabled(false);
            BottoneLivello5.getBackground().setAlpha(120);
        }
        else {
            BottoneLivello5.setEnabled(true);
            BottoneLivello5.getBackground().setAlpha(255);
        }

        BottoneLivello5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyView.getInstance().setMatrix(14, 8);
                openLevelsActivity();
            }
        });
    }

    public void openLevelsActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onPause() {
        super.onPause();
        MyView.getInstance().save();

        //CONTROLLA SE LO SCHERMO E' ATTIVO
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        if (pm!=null) {
            isScreenOn = pm.isInteractive();
        }
        if(!isScreenOn){
            if(MyView.getInstance().getMusic()){
                Music.pauseMusic();
            }
        }
    }
    public void onResume(){
        super.onResume();
        MyView.getInstance().startPauseMusic();
    }
}