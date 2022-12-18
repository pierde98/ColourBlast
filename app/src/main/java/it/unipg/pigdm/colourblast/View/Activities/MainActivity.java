package it.unipg.pigdm.colourblast.View.Activities;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import it.unipg.pigdm.colourblast.R;
import it.unipg.pigdm.colourblast.View.MatrixView;
import it.unipg.pigdm.colourblast.View.MyView;
import it.unipg.pigdm.colourblast.View.Utils.Music;

import static android.graphics.Color.parseColor;
import static it.unipg.pigdm.colourblast.R.*;

public class MainActivity extends AppCompatActivity {

    private String[] colours = {"RED","BLUE","GREEN","PURPLE","MAROON","MAGENTA","LIGHTGREY","YELLOW", "#ffffff", "#000000"};
    private ImageButton pauseBut, backMoveBut,backBut;
    private AlertDialog.Builder dialogBuilder, dialogBuilderEsito, dialogBuilderAlert;
    private AlertDialog popUp, popUpEsito, alert;
    private Button butLivelli, butHome, RicominciaGrigliaBut, RicominciaLivelloBut, BottoneAnnulla, BottoneConferma, but1, but2, but3, but4, but5, but6, but7, but8, but9, but10;
    private Space space4, space6, space7, space8, space9;
    private MatrixView matrix;
    private TextView mosse, alertText;
    private int me = 0;
    private int mt, liv;
    private String MosseEffettuate, MosseTotali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//impostazioni Light/Dark mode
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(style.DarkTheme);
        else
            setTheme(style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

//impostazioni del numero di mosse

        this.mosse = findViewById(id.TestoMosse);
        this.MosseEffettuate = "0";
        this.mt = (int) (30* (MyView.getInstance().getMatrixDim() * MyView.getInstance().getMatrixNumCol())/(17*6))+4;
        this.MosseTotali = "" + mt;
        this.mosse.setText(this.MosseEffettuate + "/" + this.MosseTotali);

//impostazione dimensioni schermo

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        MyView.getInstance().setScreenDimensions(dm.heightPixels, dm.widthPixels);

//impostazioni MatrixStack e MatrixView

        matrix = findViewById(id.matrixView);
        MyView.getInstance().setMatrixView(matrix);
        //MyView.getInstance().createStack();
        //MyView.getInstance().setInitialMatrix();

//impostazioni backMoveBut

        backMoveBut = findViewById(id.BottoneBackmove);
        backMoveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyView.getInstance().backMove();
                if(me > 0)
                    updateMove(me - 1);
            }
        });

        if(MyView.getInstance().isBackmoveEnabled() == false)
            backMoveBut.setVisibility(View.GONE);


//aggiunta dinamica dei bottoni nel layout

        but1 = findViewById(id.but1);
        but1.setLayoutParams(new LinearLayout.LayoutParams((dm.widthPixels/5)-15,(dm.widthPixels/5)-15));
        but1.setBackgroundColor(parseColor(colours[0]));
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyView.getInstance().changeColour(colours[0]);
                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, anim.buttoncolor_anim));
                updateMove(me + 1);
                if(MyView.getInstance().checkProgress() == 1)
                    esitoPartita();
            }
        });

        but2 = findViewById(id.but2);
        but2.setLayoutParams(new LinearLayout.LayoutParams((dm.widthPixels/5)-15,(dm.widthPixels/5)-15));
        but2.setBackgroundColor(parseColor(colours[1]));
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyView.getInstance().changeColour(colours[1]);
                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, anim.buttoncolor_anim));
                updateMove(me + 1);
                if(MyView.getInstance().checkProgress() == 1)
                    esitoPartita();
            }
        });

        but3 = findViewById(id.but3);
        but3.setLayoutParams(new LinearLayout.LayoutParams((dm.widthPixels/5)-15,(dm.widthPixels/5)-15));
        but3.setBackgroundColor(parseColor(colours[2]));
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyView.getInstance().changeColour(colours[2]);
                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, anim.buttoncolor_anim));
                updateMove(me + 1);
                if(MyView.getInstance().checkProgress() == 1)
                    esitoPartita();
            }
        });

        but4 = findViewById(id.but4);
        but4.setLayoutParams(new LinearLayout.LayoutParams((dm.widthPixels/5)-15,(dm.widthPixels/5)-15));
        but4.setBackgroundColor(parseColor(colours[3]));
        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyView.getInstance().changeColour(colours[3]);
                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, anim.buttoncolor_anim));
                updateMove(me + 1);
                if(MyView.getInstance().checkProgress() == 1)
                    esitoPartita();
            }
        });

        but5 = findViewById(id.but5);
        but5.setLayoutParams(new LinearLayout.LayoutParams((dm.widthPixels/5)-15,(dm.widthPixels/5)-15));
        but5.setBackgroundColor(parseColor(colours[4]));
        but5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyView.getInstance().changeColour(colours[4]);
                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, anim.buttoncolor_anim));
                updateMove(me + 1);
                if(MyView.getInstance().checkProgress() == 1)
                    esitoPartita();
            }
        });

        but6 = findViewById(id.but6);
        but6.setLayoutParams(new LinearLayout.LayoutParams((dm.widthPixels/5)-15,(dm.widthPixels/5)-15));
        but6.setBackgroundColor(parseColor(colours[5]));
        but6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyView.getInstance().changeColour(colours[5]);
                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, anim.buttoncolor_anim));
                updateMove(me + 1);
                if(MyView.getInstance().checkProgress() == 1)
                    esitoPartita();
            }
        });

        but7 = findViewById(id.but7);
        but7.setLayoutParams(new LinearLayout.LayoutParams((dm.widthPixels/5)-15,(dm.widthPixels/5)-15));
        but7.setBackgroundColor(parseColor(colours[6]));
        but7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyView.getInstance().changeColour(colours[6]);
                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, anim.buttoncolor_anim));
                updateMove(me + 1);
                if(MyView.getInstance().checkProgress() == 1)
                    esitoPartita();
            }
        });

        but8 = findViewById(id.but8);
        but8.setLayoutParams(new LinearLayout.LayoutParams((dm.widthPixels/5)-15,(dm.widthPixels/5)-15));
        but8.setBackgroundColor(parseColor(colours[7]));
        but8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyView.getInstance().changeColour(colours[7]);
                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, anim.buttoncolor_anim));
                updateMove(me + 1);
                if(MyView.getInstance().checkProgress() == 1)
                    esitoPartita();
            }
        });

        but9 = findViewById(id.but9);
        but9.setLayoutParams(new LinearLayout.LayoutParams((dm.widthPixels/5)-15,(dm.widthPixels/5)-15));
        but9.setBackgroundColor(parseColor(colours[8]));
        but9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyView.getInstance().changeColour(colours[8]);
                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, anim.buttoncolor_anim));
                updateMove(me + 1);
                if(MyView.getInstance().checkProgress() == 1)
                    esitoPartita();
            }
        });

        but10 = findViewById(id.but10);
        but10.setLayoutParams(new LinearLayout.LayoutParams((dm.widthPixels/5)-15,(dm.widthPixels/5)-15));
        but10.setBackgroundColor(parseColor(colours[9]));
        but10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyView.getInstance().changeColour(colours[9]);
                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, anim.buttoncolor_anim));
                updateMove(me + 1);
                if(MyView.getInstance().checkProgress() == 1)
                    esitoPartita();
            }
        });

        //rendo visibili solo i bottoni a cui corrisponde un colore nella matrice

        if(MyView.getInstance().getMatrixNumCol()<4){
            but4.setVisibility(View.INVISIBLE);
            space4 = findViewById(id.space4);
            space4.setVisibility(View.INVISIBLE);
        }

        if(MyView.getInstance().getMatrixNumCol()<5) {
            but5.setVisibility(View.INVISIBLE);
        }

        if(MyView.getInstance().getMatrixNumCol()<6) {
            but6.setVisibility(View.INVISIBLE);
            space6 = findViewById(id.space6);
            space6.setVisibility(View.INVISIBLE);
        }

        if(MyView.getInstance().getMatrixNumCol()<7){
            but7.setVisibility(View.INVISIBLE);
            space7 = findViewById(id.space7);
            space7.setVisibility(View.INVISIBLE);
        }

        if(MyView.getInstance().getMatrixNumCol()<8){
            but8.setVisibility(View.INVISIBLE);
            space8 = findViewById(id.space8);
            space8.setVisibility(View.INVISIBLE);
        }

        if(MyView.getInstance().getMatrixNumCol()<9) {
            but9.setVisibility(View.INVISIBLE);
            space9 = findViewById(id.space9);
            space9.setVisibility(View.INVISIBLE);
        }

        if(MyView.getInstance().getMatrixNumCol()<10) {
            but10.setVisibility(View.INVISIBLE);
        }

//impostazioni del popup_main
        
        pauseBut = findViewById(id.pauseBut);
        pauseBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v){
                createPopUp();
            }
        });
    }

//metodo per aprire il pop-up delle impostazioni
    public void onClickPopUpSett1(android.view.View view) {
        new PopUpSettMain(this);

    }

//metodo per la creazione del popup_main_settings

    public void createPopUp(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View PopUpView = getLayoutInflater().inflate(layout.popup_main, null);

        this.RicominciaLivelloBut = PopUpView.findViewById(id.RicominciaLivelloBut);
        this.RicominciaGrigliaBut = PopUpView.findViewById(id.RicominciaGrigliaBut);
        this.butLivelli = PopUpView.findViewById(id.butLivello);
        this.butHome = PopUpView.findViewById(id.butHome);
        this.backBut = PopUpView.findViewById(id.backButMainSettings);

        this.RicominciaLivelloBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v){
                createAlert("Sei sicuro di voler incominciare un nuovo livello?");
            }
        });

        this.RicominciaGrigliaBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v){
                createAlert("Sei sicuro di voler rincominciare lo stesso livello?");
            }
        });

        this.butLivelli.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v){
                createAlert("Sei sicuro di voler uscire dalla partita?");
            }
        });

        this.butHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v){
                createAlert("Sei sicuro di voler tornare alla home?");
            }
        });

        this.backBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v){
                popUp.dismiss();
            }
        });
        dialogBuilder.setView(PopUpView);
        popUp = dialogBuilder.create();
        popUp.show();
        popUp.setCanceledOnTouchOutside(false);
    }

    public void createAlert(String text){
        dialogBuilderAlert = new AlertDialog.Builder(MainActivity.this);
        final View PopUpViewAlert = getLayoutInflater().inflate(layout.alert_popup, null);

        alertText = PopUpViewAlert.findViewById(id.TestoConferma);
        alertText.setText(text);

        BottoneAnnulla = PopUpViewAlert.findViewById(id.BottoneAnnulla);
        BottoneConferma = PopUpViewAlert.findViewById(id.BottoneConferma);

        dialogBuilderAlert.setView(PopUpViewAlert);
        alert = dialogBuilderAlert.create();
        alert.show();
        alert.setCanceledOnTouchOutside(false);

        BottoneConferma.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v){
                if(alertText.getText().equals("Sei sicuro di voler incominciare un nuovo livello?")){
                    recreate();
                    alert.dismiss();
                    popUp.dismiss();
                }
                if(alertText.getText().equals("Sei sicuro di voler rincominciare lo stesso livello?")){
                    MyView.getInstance().RestartMatrix();
                    updateMove(0);
                    alert.dismiss();
                    popUp.dismiss();
                }
                if(alertText.getText().equals("Sei sicuro di voler uscire dalla partita?")){
                    alert.dismiss();
                    popUp.dismiss();
                    finish();
                }
                if(alertText.getText().equals("Sei sicuro di voler tornare alla home?")){
                    alert.dismiss();
                    popUp.dismiss();
                    Intent a = new Intent(MainActivity.this, MenuActivity.class);
                    a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //MyView.getInstance().stopMusic(); questa riga fa in modo che la musica non si sovrapponga
                    startActivity(a);
                }
            }
        });

        BottoneAnnulla.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v){
                alert.dismiss();
            }
        });
    }

    //metodo per cambiare il testo di this.mosse

    public void updateMove(int me){
        this.me = me;
        this.MosseEffettuate = "" + this.me;
        this.mosse.setText(MosseEffettuate+ "/" + MosseTotali);
    }

    //metodo per controllare se la partita è stata vinta o persa

    /*public void esitoPartita(){
        if(this.me <= this.mt){
            createPopUpEsito(); //gli dovrò mandare una gif di coriandoli o roba simile + suono felice
            this.liv = MyView.getInstance().readFile(this);
            Toast.makeText(MainActivity.this, "Ultimo livello sbloccato " + MyView.getInstance().readFile(this), Toast.LENGTH_SHORT).show();
            if( this.liv<5)
                this.liv++;

            MyView.getInstance().writeFile(this,  this.liv);

        }
        else {
            createPopUpEsito(); //gli dovrò mandare una gif di hai perso o roba simile + suono triste
        }
    }*/

    //metodo per controllare se la partita è stata vinta o persa

    public void esitoPartita(){
        if(this.me <= this.mt){
            createPopUpEsitoVittoria(); //gli dovrò mandare una gif di coriandoli o roba simile + suono felice
        }
        if(this.me > this.mt) {
            createPopUpEsitoSconfitta(); //gli dovrò mandare una gif di hai perso o roba simile + suono triste
        }
    }

    public void createPopUpEsitoVittoria(){
        dialogBuilderEsito = new AlertDialog.Builder(this);
        final View PopUpEsitoWin = getLayoutInflater().inflate(layout.popup_vittoria, null);

        dialogBuilderEsito.setView(PopUpEsitoWin);
        popUpEsito = dialogBuilderEsito.create();
        popUpEsito.show();
    }

    public void createPopUpEsitoSconfitta(){
        dialogBuilderEsito = new AlertDialog.Builder(this);
        final View PopUpEsitoLose = getLayoutInflater().inflate(layout.popup_sconfitta, null);

        dialogBuilderEsito.setView(PopUpEsitoLose);
        popUpEsito = dialogBuilderEsito.create();
        popUpEsito.show();
    }

    public void onClickGoHome(android.view.View view) {
        startActivity(new Intent(this, MenuActivity.class));
        Music.stopMusic();
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