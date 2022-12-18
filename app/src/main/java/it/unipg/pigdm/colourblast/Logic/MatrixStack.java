package it.unipg.pigdm.colourblast.Logic;
import java.util.ArrayList;

public class MatrixStack {

    String[][] initialMatrix;
    int dim;
    private ArrayList<String[][]> pila;

//COSTRUTTORE

    public MatrixStack(){
        pila = new ArrayList<String[][]>();
    }

//PUSH

    public void push(String[][] mat){
        pila.add(mat);
    }

//POP

    public void pop() {
       // if(size()>1);
            pila.remove(size()-1);
        }

//SIZE

    public int size() {
        return pila.size();
    }


//GET TOP MATRIX

    public String[][] getTopMatrix() {
        if (pila.isEmpty()) {
            return null;
        }
        return pila.get(pila.size() - 1);
    }

    public String[][] getInitialMatrix(){
        return this.initialMatrix;
    }

    public void restart(){
        for(int i=this.pila.size()-1; i>0; i--)
            this.pila.remove(i);
    }

    public void setInitialMatrix(){
        dim = this.pila.get(0).length;
        this.initialMatrix = new String[dim][dim];
        for(int i=0; i<dim; i++){
            for(int j=0; j<dim; j++){
                this.initialMatrix[i][j] = this.pila.get(0)[i][j];
            }
        }
    }
}

