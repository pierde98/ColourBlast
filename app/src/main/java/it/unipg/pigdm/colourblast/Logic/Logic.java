package it.unipg.pigdm.colourblast.Logic;

import android.content.Context;

import java.util.ArrayList;

import it.unipg.pigdm.colourblast.Logic.GameLogic;
import it.unipg.pigdm.colourblast.Logic.ILogic;
import it.unipg.pigdm.colourblast.View.MyView;

public class Logic implements ILogic {

    //---------------------------------------------------------------
    // STATIC FIELDS
    //---------------------------------------------------------------
    private static Logic instance = null;

    //---------------------------------------------------------------
    // INSTANCE ATTRIBUTES
    //---------------------------------------------------------------
    private GameLogic gameLogic = new GameLogic();

    public Logic() {
        //TO-DO
    }

    //VIEW METHODS

    @Override
    public void colorAnimation(String[][] m){
        MyView.getInstance().colorAnimation(m);
    }

    //LOGIC METHODS

    @Override
    public void setScreenDimensions(int height, int width){
        gameLogic.createConstants(height, width);
    }

    @Override
    public int getScreenWidth(){
        return Constants.SCREEN_WIDTH;
    }

    @Override
    public int getScreenHeight(){
        return Constants.SCREEN_HEIGHT;
    }

    @Override
    public String[][] getMatrix(int Dimensione, int NumeroColori){
        return gameLogic.createMatrix(Dimensione, NumeroColori);
    }

    @Override
    public void createSquare(){
        gameLogic.createSquare();
    }

    @Override
    public int getOx(){
        return gameLogic.getOx();
    }

    @Override
    public int getOy(){
        return gameLogic.getOy();
    }

    @Override
    public void changeColour(String colour){
        gameLogic.changeColour(colour);
    }

    @Override
    public int checkProgress(){
        return gameLogic.checkProgress();
    }

    @Override
    public int readFile(Context context) {
        return Savings.getSaving(context).readLevelNumber();
    }

    @Override
    public void writeFile(Context context, int level) {
        Savings.getSaving(context).changeLevelNumber(level);
    }

    @Override
    public void createStack() {
        gameLogic.createStack();
    }

    @Override
    public void Push() {
        gameLogic.push();
    }

    @Override
    public void backMove(){
        gameLogic.backMove();
    }

    @Override
    public void RestartMatrix() {
        gameLogic.RestartMatrix();
    }

    @Override
    public void setMatrix(String[][] mat) {
        gameLogic.setMatrix(mat);
    }

    public  void readProp(){
        Savings.readProp();
    }

    //Per i suoni
    public void setSound(boolean bs){
        Savings.setSound(bs);
    }
    public boolean getSound(){
        return Savings.getSound();
    }

    //Per la musica
    public void setMusic(boolean bm){
        Savings.setMusic(bm);
    }
    public boolean getMusic(){
        return Savings.getMusic();
    }

    @Override
    public void save() {
        Savings.save();

    }


    //---------------------------------------------------------------
    // STATIC METHODS
    //---------------------------------------------------------------
    public static ILogic getInstance(){
        if (instance == null)
            instance = new Logic();
        return instance;
    }

    public Context getContext(){
        return MyView.getInstance().getContext();
    }
}
