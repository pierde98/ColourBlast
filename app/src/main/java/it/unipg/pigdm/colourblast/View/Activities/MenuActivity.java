package it.unipg.pigdm.colourblast.View.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import it.unipg.pigdm.colourblast.R;
import it.unipg.pigdm.colourblast.View.MyView;
import it.unipg.pigdm.colourblast.View.Utils.HomeWatcher;
import it.unipg.pigdm.colourblast.View.Utils.Music;




public class MenuActivity extends AppCompatActivity {

    private Button BottoneLivelli, BottoneAllenamento, BottoneNewGame, BottoneAnnulla, BottoneConferma;
    private AlertDialog.Builder dialogBuilder, dialogBuilderAlert;
    private AlertDialog popUp, alert;
    HomeWatcher mHomeWatcher;
    private ImageView DarkLightModeBut;
    private TextView LDText;
    private String DarkModeValue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {


//impostazioni Light/Dark mode

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            DarkModeValue = "Dark Mode";
            setTheme(R.style.DarkTheme);
        }
        else{
            DarkModeValue = "Light Mode";
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        DarkLightModeBut = findViewById(R.id.bottoneDark);
        LDText = findViewById(R.id.testomodalita);

        if(DarkModeValue.equals("Dark Mode")){
            DarkLightModeBut.setImageResource(R.drawable.ic_lightmode);
            LDText.setText("Dark Mode");
        }

        else{
            DarkLightModeBut.setImageResource(R.drawable.ic_darkmode);
            LDText.setText("Light Mode");
        }

//per il pulsante impostazioni
        MyView.getInstance().setContext(getApplicationContext());
        MyView.getInstance().readProp();
        MyView.createMusic();
        MyView.getInstance().startAfterStop();

//Gestisce la muisca se l'app va in background

        mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                if (MyView.getInstance().getMusic()) {
                    Music.pauseMusic();
                }
            }
            @Override
            public void onHomeLongPressed() {
                if (MyView.getInstance().getMusic()) {
                    Music.pauseMusic();
                }
            }
        });

        mHomeWatcher.startWatch();


//PulsanteLivelli

        BottoneLivelli = findViewById(R.id.BottoneLivelli);
        BottoneLivelli.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v) {
                openLevelsActivity();
                MyView.getInstance().sound(MyView.soundBut);
            }
        });

//PulsanteAllenamento

        BottoneAllenamento = findViewById(R.id.BottoneAllenamento);
        BottoneAllenamento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v){
                openTrainingActivity();
                MyView.getInstance().sound(MyView.soundBut);

            }
        });



//DARK MODE

        DarkLightModeBut = findViewById(R.id.bottoneDark);
        DarkLightModeBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (DarkModeValue.equals("Light Mode")) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    DarkModeValue = "Dark Mode";
                    recreate();
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    DarkModeValue = "Light Mode";
                    recreate();
                }

            }
        });

//inizializzazione backmove

        MyView.getInstance().EnableBackMove(true);

    }

    //metodo per aprire il pop-up delle impostazioni
    public void onClickPopUpSett(android.view.View view) {
        MyView.getInstance().sound(MyView.soundBut);
        new PopUpSett(this);

    }

    //metodo per aprire il pop-up della guida
    public void onClickPopUpInfo(android.view.View view) {
        MyView.getInstance().sound(MyView.soundBut);
        new PopUpInfo(this);

    }


/*  public void createSettingsPopUp() {
        dialogBuilder = new AlertDialog.Builder(MenuActivity.this);
        final View PopUpView = getLayoutInflater().inflate(R.layout.popup_settings, null);

        SwitchDarkMode = PopUpView.findViewById(R.id.switchDarkMode);

        SwitchMusica = PopUpView.findViewById(R.id.SwitchMusica);
        SwitchSuoni = PopUpView.findViewById(R.id.SwitchSuono);
        SwitchMossaIndietro = PopUpView.findViewById(R.id.switchBack);
        BottoneNewGame = PopUpView.findViewById(R.id.NewGameBut);
        BottoneBack = PopUpView.findViewById(R.id.backButMainSettings);

        dialogBuilder.setView(PopUpView);
        popUp = dialogBuilder.create();
        popUp.show();
        popUp.setCanceledOnTouchOutside(false);


        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            SwitchDarkMode.setChecked(true);
        else
            SwitchDarkMode.setChecked(false);

        SwitchDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                }

            }
        });

        SwitchMusica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MyView.getInstance().setMusic(isChecked);
                MyView.getInstance().startPauseMusic();

            }
        });
        SwitchMusica.setChecked(MyView.getInstance().getMusic());

        SwitchSuoni.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MyView.getInstance().setSound(isChecked);
            }
        });
        SwitchSuoni.setChecked(MyView.getInstance().getSound());



        if(MyView.getInstance().isBackmoveEnabled())
            SwitchMossaIndietro.setChecked(true);
        else
            SwitchMossaIndietro.setChecked(false);

        SwitchMossaIndietro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MyView.getInstance().EnableBackMove(true);
                }
                else {
                    MyView.getInstance().EnableBackMove(false);
                }
            }
        });

        BottoneNewGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v){
                createAlert();
            }
        });

        BottoneBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v){
                popUp.dismiss();
            }
        });
    }

    //metodo per confermare di rincominciare il gioco

    public void createAlert(){
        dialogBuilderAlert = new AlertDialog.Builder(MenuActivity.this);
        final View PopUpViewAlert = getLayoutInflater().inflate(R.layout.alert_popup, null);

        BottoneAnnulla = PopUpViewAlert.findViewById(R.id.BottoneAnnulla);
        BottoneConferma = PopUpViewAlert.findViewById(R.id.BottoneConferma);

        dialogBuilderAlert.setView(PopUpViewAlert);
        alert = dialogBuilderAlert.create();
        alert.show();
        alert.setCanceledOnTouchOutside(false);

        BottoneConferma.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v){
                alert.dismiss();
                popUp.dismiss();
                MyView.getInstance().writeFile(MenuActivity.this, 1); //DA RIVEDERE DOVREBBE AZZERARMI I LIVELLI

            }
        });

        BottoneAnnulla.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v){
                alert.dismiss();
            }
        });


    }*/

//metodo per aprire LevelsActivity

    public void openLevelsActivity(){
        startActivity(new Intent(this, LevelsActivity.class));
    }

//metodo per aprire AllenamentoActivity

    public void openTrainingActivity(){
        startActivity(new Intent(this, AllenamentoActivity.class));
    }

//metodo musica

    public void onResume(){
        super.onResume();
        MyView.getInstance().startPauseMusic();
    }

    //metodo salva preferenze
    @Override
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

    public void onDestroy() {
        super.onDestroy();
        MyView.getInstance().stopMusic();
    }
}