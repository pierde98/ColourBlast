package it.unipg.pigdm.colourblast.View;

import android.content.Context;

import java.util.ArrayList;

import it.unipg.pigdm.colourblast.Logic.Logic;
import it.unipg.pigdm.colourblast.View.Utils.Music;
import it.unipg.pigdm.colourblast.View.Utils.Sound;

public class MyView implements IView {

    private GameView gameView = new GameView();
    private static MyView instance = null;
    static Context c;
    public final static int soundBut=1;

    private MyView() {
        //TO-DO
    }

    public void setContext(Context context){
        c=context;
    }

    public Context getContext(){
        return c;
    }
    // VIEW METHODS

    @Override
    public void setMatrix(int Dim, int NumCol){
        gameView.setMatrix(Dim, NumCol);
    }

    @Override
    public int getMatrixDim(){
        return gameView.getMatrixDim();
    }

    @Override
    public int getMatrixNumCol(){
        return gameView.getMatrixNumCol();
    }

    @Override
    public void colorAnimation(String[][] m){
        gameView.colorAnimation(m);
    }

    @Override
    public int checkProgress(){
        return Logic.getInstance().checkProgress();
    }

    @Override
    public void setMatrixView(MatrixView matrix){
        gameView.setMatrixView(matrix);
    }

    @Override
    public void EnableBackMove(boolean b){
        gameView.EnableBackMove(b);
    }

    @Override
    public boolean isBackmoveEnabled() {
        return gameView.isBackmoveEnabled();
    }

    // LOGIC METHODS

    @Override
    public void setScreenDimensions(int height, int width){
        Logic.getInstance().setScreenDimensions(height, width);
    }

    @Override
    public int getScreenWidth(){
        return Logic.getInstance().getScreenWidth();
    }

    @Override
    public int getScreenHeight(){
        return Logic.getInstance().getScreenHeight();
    }

    @Override
    public String[][] getMatrix(int Dimensione, int NumeroColori) {
        return Logic.getInstance().getMatrix(Dimensione, NumeroColori);
    }

    @Override
    public void createSquare(){
        Logic.getInstance().createSquare();
    }

    @Override
    public int getOx(){
        return Logic.getInstance().getOx();
    }

    @Override
    public int getOy(){
        return Logic.getInstance().getOy();
    }

    @Override
    public void changeColour(String colour){
        Logic.getInstance().changeColour(colour);
    }

    @Override
    public void writeFile(Context context, int liv) {
        Logic.getInstance().writeFile(context, liv);
    }

    @Override
    public int readFile(Context context) {
        return Logic.getInstance().readFile(context);
    }

    @Override
    public void backMove(){
        Logic.getInstance().backMove();
    }

    @Override
    public void createStack(){
        Logic.getInstance().createStack();
    }

    @Override
    public void Push() {
        Logic.getInstance().Push();
    }

    @Override
    public void RestartMatrix(){
        Logic.getInstance().RestartMatrix();
    }

    //FA PARTIRE I SUONI
    public void sound(int is){
        if(Logic.getInstance().getSound()){
            switch(is) {
                case soundBut: Sound.startSound(c);
                    break;

            }
        }
    }

    //CREA IL MEDIAPLAYER PER LA MUSICA
    public static void createMusic(){
        Music.createMusic();
    }

    //FA PARTIRE O MENO LA MUSICA IN BASE ALLA PREFERENZA
    public void startPauseMusic(){
        if(Logic.getInstance().getMusic()){
            Music.startMusic();
        }
        else{
            if(Music.isPlaying())
                Music.pauseMusic();
        }
    }

    //METODI PER SALVARE STATO DI MUSICA E SUONI
    public void readProp(){
        Logic.getInstance().readProp();
    }

    //Per i suoni
    public void setSound(boolean bs){
        Logic.getInstance().setSound(bs);
    }
    public boolean getSound(){
        return Logic.getInstance().getSound();
    }

    //Per la musica
    public void setMusic(boolean bm){
        Logic.getInstance().setMusic(bm);
    }
    public boolean getMusic(){
        return Logic.getInstance().getMusic();
    }

    //FERMA LA MUSICA QUANDO VIENE CHIUSA L'APPLICAZIONE
    public void stopMusic(){
        if(Music.isPlaying())
            Music.stopMusic();
    }

    //FA PARTIRE LA MUSICA DOPO LO STOP
    public void startAfterStop(){
        if(Logic.getInstance().getMusic())
            Music.startMusic();
    }

    // STATIC METHODS

    public static IView getInstance() {
        if (instance == null)
            instance = new MyView();
        return instance;
    }
    @Override
    public void save(){
        Logic.getInstance().save();
    }
}