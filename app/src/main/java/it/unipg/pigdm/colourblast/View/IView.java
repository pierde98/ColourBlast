package it.unipg.pigdm.colourblast.View;

import android.content.Context;

import java.util.ArrayList;

public interface IView {

    //VIEW METHODS

    void setMatrix(int Dim, int NumCol);

    int getMatrixNumCol();

    int getMatrixDim();

    void colorAnimation(String[][] m);

    void setMatrixView(MatrixView matrix);

    //LOGIC METHODS

    String[][] getMatrix(int Dimensione, int NumeroColori);

    void createSquare();

    int getOx();

    int getOy();

    void setScreenDimensions(int height, int width);

    int getScreenWidth();

    int getScreenHeight();

    void changeColour(String colour);

    int checkProgress();

    int readFile(Context context);

    void writeFile(Context context, int liv);

    void backMove();

    void createStack();

    void Push();

    void RestartMatrix();

    void EnableBackMove(boolean b);

    boolean isBackmoveEnabled();

    Context getContext();

    void setContext(Context applicationContext);

    void sound(int is);

    void setSound(boolean isChecked);

    boolean getSound();

    void startPauseMusic();

    void setMusic(boolean isChecked);

    boolean getMusic();

    void stopMusic();

    void readProp();

    void startAfterStop();

    void save();


}
