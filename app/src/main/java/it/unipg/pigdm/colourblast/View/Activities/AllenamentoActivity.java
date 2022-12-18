package it.unipg.pigdm.colourblast.View.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import it.unipg.pigdm.colourblast.R;
import it.unipg.pigdm.colourblast.View.MyView;
import it.unipg.pigdm.colourblast.View.Utils.Music;

public class AllenamentoActivity extends AppCompatActivity {

    private Spinner SpinnerDimensione;
    private Spinner SpinnerColori;
    private int Dimensione, NumeroColori;
    private String s1,s2;
    private Button BottoneGioca;
    private ImageButton BackButAllenamento;

    @Override
        protected void onCreate(Bundle savedInstanceState) {

        //impostazioni Light/Dark mode
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.DarkTheme);
        else
            setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allenamento);

        BackButAllenamento = findViewById(R.id.backButAllenamento);
        BackButAllenamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//SpinnerDimensione

        SpinnerDimensione = findViewById(R.id.SpinnerDimensione);

        ArrayAdapter<String> DimAdapter = new ArrayAdapter<>(
                this,
                R.layout.custom_spinner,
                getResources().getStringArray(R.array.listadim)
        );

        DimAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        SpinnerDimensione.setAdapter(DimAdapter);

        SpinnerDimensione.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {

                    s1 = String.valueOf(parent.getItemAtPosition(i).toString().charAt(0));
                    if(parent.getItemAtPosition(i).toString().charAt(1)!='x') {
                        s2 = String.valueOf(parent.getItemAtPosition(i).toString().charAt(1));
                        s1 = s1+s2;
                    }
                    Dimensione = Integer.parseInt(s1);
                    Toast.makeText(AllenamentoActivity.this, "Dimensione: " + Dimensione + "x" + Dimensione, Toast.LENGTH_SHORT).show();
                }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//SpinnerColori

        SpinnerColori = findViewById(R.id.SpinnerColori);

        ArrayAdapter<String> ColAdapter = new ArrayAdapter<>(
                this,
                R.layout.custom_spinner,
                getResources().getStringArray(R.array.listacol)
        );

        ColAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        SpinnerColori.setAdapter(ColAdapter);

        SpinnerColori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                NumeroColori = Integer.parseInt(String.valueOf(parent.getItemAtPosition(i).toString()));
                Toast.makeText(AllenamentoActivity.this, "Numero colori: " + NumeroColori, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

//Pulsante Gioca

        BottoneGioca = (Button) findViewById(R.id.BottoneGioca);
        BottoneGioca.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v){
                MyView.getInstance().setMatrix(Dimensione,NumeroColori);
                openAllenamento();
            }
        });
    }

    public void openAllenamento(){
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