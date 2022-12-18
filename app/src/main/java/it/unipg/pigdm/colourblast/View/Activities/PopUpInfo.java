package it.unipg.pigdm.colourblast.View.Activities;



import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;


import it.unipg.pigdm.colourblast.R;


public class PopUpInfo extends Dialog {

    ImageButton BottoneBack;

    PopUpInfo(Context context) {
        super(context);
        setContentView(R.layout.pop_up_info);
        setCancelable(true);
        setCanceledOnTouchOutside(false);


        BottoneBack = findViewById(R.id.backButInfo);
        BottoneBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v){
                dismiss();
            }
        });
        show();

    }

}
