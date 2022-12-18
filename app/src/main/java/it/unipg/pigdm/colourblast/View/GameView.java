package it.unipg.pigdm.colourblast.View;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GameView {

//------------------------------
//----------ATTRIBUTI-----------
//------------------------------

    private int Dim, NumCol;
    private boolean backmove;
    private int livello = 1;
    private MatrixView matrix;
    private ArrayList<String[][]> sequenzaAnimazione;;

//------------------------------
//---------COSTRUTTORE----------
//------------------------------

    public GameView(){
    }

//------------------------------
//-----------METODI-------------
//------------------------------

    public void setMatrix(int Dim, int NumCol){
        this.Dim = Dim;
        this.NumCol = NumCol;
    }

    public void setMatrixView(MatrixView matrix){
        this.matrix = matrix;
    }

    public int getMatrixDim(){
        return this.Dim;
    }

    public int getMatrixNumCol(){
        return this.NumCol;
    }

    public void colorAnimation(String [][] m){
            this.matrix.swapMatrix(m);
    }

    public void EnableBackMove(boolean b) {
        this.backmove = b;
    }

    public boolean isBackmoveEnabled() {
        return this.backmove;
    }
}
