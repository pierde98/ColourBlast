package it.unipg.pigdm.colourblast.Logic;

import android.content.Context;

import java.util.ArrayList;

public interface ILogic {

    //VIEW METHODS

    void colorAnimation(String[][] m);

    //LOGIC METHODS

    void createSquare();

    void setScreenDimensions(int height, int width);

    int getScreenHeight();

    int getScreenWidth();

    String[][] getMatrix(int Dimensione, int NumeroColori);

    int getOx();

    int getOy();

    void changeColour(String colour);

    int checkProgress();

    void writeFile(Context context, int i);

    int readFile(Context context);

    void backMove();

    void createStack();

    void Push();

    void setMatrix(String[][] mat);

    void RestartMatrix();

    Context getContext();

    void readProp();

    void setSound(boolean bs);

    void setMusic(boolean bm);

    boolean getSound();

    boolean getMusic();

    void save();
}
