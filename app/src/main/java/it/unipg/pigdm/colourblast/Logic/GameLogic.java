package it.unipg.pigdm.colourblast.Logic;

import android.content.Context;

public class GameLogic {

//------------------------------
//----------ATTRIBUTI-----------
//------------------------------

    private Matrix matrix;
    private String[][] initialMatrix;
    private CentralSquare square;
    private Constants constants;
    private MatrixStack pila;


//------------------------------
//---------COSTRUTTORE----------
//------------------------------

    public GameLogic(){
    }

//------------------------------
//-----------METODI-------------
//------------------------------

    //METODI MATRIX

    public String[][] createMatrix(int Dimensione, int NumeroColori){
        this.matrix = new Matrix(Dimensione, NumeroColori);
        createStack();
        return this.matrix.getMatrix();
    }

    public void changeColour(String colour){
        this.matrix.setColour(colour);
        push();
        setMatrix(this.pila.getTopMatrix());
    }

    public int checkProgress() {
        return this.matrix.checkProgress();
    }

    public void setMatrix(String[][] mat) {
        Logic.getInstance().colorAnimation(this.pila.getTopMatrix());
        this.matrix.setMatrix(mat);
    }

    String getColour(){
        return this.matrix.getColour();
    }

    public void RestartMatrix() {
        this.pila.restart();
        setMatrix(this.pila.getTopMatrix());
    }

    //METODI MATRIXSTACK

    public void createStack(){
        this.pila = new MatrixStack();
        this.pila.push(this.matrix.getMatrix());
        this.pila.setInitialMatrix();
    }

    public void backMove() {
        if(this.pila.size()>1){
            this.pila.pop();
            setMatrix(this.pila.getTopMatrix());
        }
    }

    public void push(){
        this.pila.push(this.matrix.getMatrix());
    }

    //METODI CONSTANTS

    public void createConstants(int height, int width){
        constants = new Constants();
        this.constants.setSCREEN_HEIGHT(height);
        this.constants.setSCREEN_WIDTH(width);
    }

    //METODI SQUARE

    public void createSquare(){
        this.square = new CentralSquare();
    }

    public int getOx(){
        return this.square.getOx();
    }

    public int getOy(){
        return this.square.getOy();
    }




    //METODI GAMELOGIC


}
