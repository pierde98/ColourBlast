package it.unipg.pigdm.colourblast.View.Utils;

import android.media.MediaPlayer;

import it.unipg.pigdm.colourblast.R;
import it.unipg.pigdm.colourblast.View.MyView;

public class Music {
    private static MediaPlayer player;

    private Music(){

    }

//CREA IL MEDIA PLAYER
    public static void createMusic(){
        player = MediaPlayer.create(MyView.getInstance().getContext(), R.raw.music);
        player.setLooping(true);
    }

//CONTROLLA SE STA SUONANDO
    public static boolean isPlaying(){
        return player.isPlaying();
    }

//FA PARTIRE LA MUSICA
    public static void startMusic(){
        player.start();
    }

//METTE IN PAUSA LA MUSICA
    public static void pauseMusic(){
        if(Music.isPlaying())
            player.pause();
    }

//FERMA LA MUSICA
    public static void stopMusic(){
        player.stop();
        player.release();
        player = null;
    }
}
