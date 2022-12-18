package it.unipg.pigdm.colourblast.View.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import it.unipg.pigdm.colourblast.R;
import it.unipg.pigdm.colourblast.View.MyView;

public class PopUpSett extends Dialog {
    Switch SwitchMusica, SwitchSuoni, SwitchMossaIndietro;
    Button BottoneNewGame,BottoneAnnulla, BottoneConferma;
    ImageButton BottoneBack;
    AlertDialog.Builder dialogBuilder, dialogBuilderAlert;
    AlertDialog popUp, alert;


    PopUpSett(Context context){
        super(context);
        setContentView(R.layout.popup_settings);
        setCancelable(true);
        setCanceledOnTouchOutside(false);

        SwitchMusica = findViewById(R.id.SwitchMusica);
        SwitchSuoni = findViewById(R.id.SwitchSuono);
        SwitchMossaIndietro = findViewById(R.id.switchBack);
        BottoneNewGame = findViewById(R.id.NewGameBut);
        BottoneBack = findViewById(R.id.backButMainSettings);

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

        /*BottoneNewGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v){
                createAlert();
            }
        });*/

        BottoneBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v){
                MyView.getInstance().save();
                dismiss();
            }
        });
        show();
    }
//metodo per confermare di rincominciare il gioco
    /*
    public void createAlert(){
        dialogBuilderAlert = new AlertDialog.Builder(MenuActivity.class);
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


}
