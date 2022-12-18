package it.unipg.pigdm.colourblast.View.Activities;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import it.unipg.pigdm.colourblast.R;
import it.unipg.pigdm.colourblast.View.MyView;

public class PopUpSettMain extends Dialog {

    Switch SwitchMusica, SwitchSuoni;
    ImageButton BottoneBack;



    PopUpSettMain(Context context) {
        super(context);
        setContentView(R.layout.pop_up_sett_main);
        setCancelable(true);
        setCanceledOnTouchOutside(false);

        SwitchMusica = findViewById(R.id.switchMusica);
        SwitchSuoni = findViewById(R.id.switchSuono);
        BottoneBack = findViewById(R.id.backButMainSettings2);
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

        BottoneBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v){
                MyView.getInstance().save();
                dismiss();
            }
        });
        show();
    }

}