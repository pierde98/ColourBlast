package it.unipg.pigdm.colourblast.View.Utils;

import android.content.Context;
import android.media.MediaPlayer;

import it.unipg.pigdm.colourblast.R;

public class Sound {
    private static MediaPlayer mediaPlayer;

    private Sound(){
    }

    //CREA IL MEDIAPLAYER PER LA RIPRODUZIONE DEI SUONI E LO RILASCIA UNA VOLTA TERMINATO
    private static void playSoundFile(Integer fileName, Context context){ //errore riscontrato (-19,0) e (-38,0) You are getting this error because you are calling mediaPlayer.start() before it has reached the prepared state.
        // I solved by creating a new object of MediaPlayer every time before playing and releasing it after that.
        mediaPlayer = MediaPlayer.create(context, fileName);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
            }
        });
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

    }

    //METODI PER LA RIPRODUZIONE DEI SINGOLI SUONI

    public static void startSound(Context context){
        playSoundFile(R.raw.buttonsound, context);
    }

}
