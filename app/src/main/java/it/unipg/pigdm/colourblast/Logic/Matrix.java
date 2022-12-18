package it.unipg.pigdm.colourblast.Logic;
import java.util.ArrayList;
import java.util.Random;

public class Matrix {
    private static String[] colours = {"RED","BLUE","GREEN","PURPLE","MAROON","MAGENTA","LIGHTGREY","YELLOW"};
    private String[][] mat, ProgressMat;
    private String colour;
    private int dim, coloursNum;
    private ArrayList<String[][]> sequenzaAnimazione;

    public Matrix(int dim, int coloursNum) {
        this.dim = dim;
        this.coloursNum = coloursNum;
        this.mat = new String[dim][dim];
        Random random = new Random();

        // Questo primo ciclo ci assicura l'utilizzo di almeno un colore
        for(int index=0;index<this.coloursNum;index++){
            int i,j;
            do{                             // Calcolo posizione finchè non trovo
                i = random.nextInt(dim);    // una libera
                j = random.nextInt(dim);
            }
            while(mat[i][j]!= null);
            mat[i][j] = this.colours[index];    // Posizione libera trovata, inseriamo
            // il colore nella matrice.
        }

        // Questo ciclo analizza la matrice e riempie le posizioni ancora vuote,
        // cioè quelle che ancora non sono state scritte dal ciclo precedente.
        for(int i=0;i<this.dim;i++){
            for(int j=0;j<this.dim;j++){
                if(mat[i][j]== null){
                    mat[i][j] = colours[random.nextInt(coloursNum)];
                }
            }
        }

        //FATTO DA MICHAEL
        sequenzaAnimazione = new ArrayList<String[][]>();

        this.colour = this.mat[0][0];

        //creo la matrice di progresso;

        this.ProgressMat = new String[dim][dim];

        for(int i=0; i<this.dim; i++){
            for(int j=0; j<this.dim; j++){
                this.ProgressMat[i][j] = ""; //tutti gli altri elementi devono essere null;
            }
        }
        findAdjacentColours(0,0); //Qui inizializzo i colori adiacenti prima che l'utente effettua la prima mossa
    }

    public String[][] getMatrix() { //devo creare una nuova matrice altrimenti mi si aggiornano tutti gli elementi dello stack
        String[][] stackMat = new String[this.dim][this.dim];
        for(int i=0; i<this.dim; i++){
            for(int j=0; j<this.dim; j++){
                stackMat[i][j] = this.mat[i][j];
            }
        }
        return stackMat;
    }

    public void findAdjacentColours(int i, int j){
        if((this.mat[i][j].equals(this.colour)) && !(this.ProgressMat[i][j].equals(this.colour))){
            this.ProgressMat[i][j] = (this.mat[i][j]);

            //Casella a DX
            if( (i+1) < this.dim)   //condizione per evitare ArrayOutOfBoundException senza try-catch
                findAdjacentColours(i+1, j);

            //Casella SOPRA
            if( (j+1) < this.dim)   //condizione per evitare ArrayOutOfBoundException senza try-catch
                findAdjacentColours(i, j+1);

            //Casella SX
            if( (i-1) >= 0)         //condizione per evitare ArrayOutOfBoundException senza try-catch
                findAdjacentColours(i-1, j);

            //Casella SOTTO
            if( (j-1) >= 0)         //condizione per evitare ArrayOutOfBoundException senza try-catch
                findAdjacentColours(i, j-1);

        }
    }

    public void setColour(String c) {
        this.colour = c;
        this.sequenzaAnimazione.clear();
        changeAdjacentColours();
    }

    public String getColour() {
        return this.colour;
    }

    public void changeAdjacentColours(){
        for(int i=0; i<this.dim; i++){
            for(int j=0; j<this.dim; j++){

                if(!this.ProgressMat[i][j].equals("")){
                    this.mat[i][j] = this.colour;
                    newAnimationElement(this.mat);
                }

            }
        }
        findAdjacentColours(0,0);
    }

    public int checkProgress(){
        for(int i=0; i<this.dim; i++){
            for(int j=0; j<dim; j++){
                if(this.ProgressMat[i][j].equals(""))
                    return 0; //se incontro almeno una casella con null, significa che this.mat non è completata e quindi continua il gioco
            }
        }
        return 1;
    }

    public String[][] getProgressMatrix(){
        String[][] ProgMat = new String[this.dim][this.dim];
        for(int i=0; i<this.dim; i++){
            for(int j=0; j<this.dim; j++){
                ProgMat[i][j] = this.ProgressMat[i][j];
            }
        }
        return ProgMat;
    }

    public void setMatrix(String[][] matr) {

        for(int i=0; i<this.dim; i++){
            for(int j=0; j<this.dim; j++){
                this.mat[i][j] = matr[i][j];
            }
        }

        this.colour = this.mat[0][0];
        for(int i=0; i<this.dim; i++){
            for(int j=0; j<this.dim; j++){
                this.ProgressMat[i][j] = ""; //tutti gli altri elementi devono essere null;
            }
        }
        findAdjacentColours(0,0);
    }

    public void newAnimationElement(String[][] element){
        String[][] e = new String[this.dim][this.dim];

        for(int i=0; i<this.dim; i++){
            for(int j=0; j<this.dim; j++){
                e[i][j] = element[i][j];
            }
        }

        sequenzaAnimazione.add(e);
    }

    public ArrayList<String[][]> getSequenzaAnimazione(){
        return this.sequenzaAnimazione;
    }

    //I METODI TO STRING SERVONO SOLO PER VERIFICARE LA CLASSE, DOPO VANNO RIMOSSI

    public void mytoString() {

        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                System.out.print(this.mat[i][j] + "\t");
            }

            System.out.print("\n");
        }
    }

    public void progresstoString(){
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                System.out.print(this.ProgressMat[i][j] + "\t");
            }

            System.out.print("\n");
        }
    }

    public void SequenzaAnimazioneToString(){
        for(int k=0; k<this.sequenzaAnimazione.size(); k++){
            System.out.print(k+1+")\n");
            for (int i = 0; i < this.dim; i++) {
                for (int j = 0; j < this.dim; j++) {
                    System.out.print(this.sequenzaAnimazione.get(k)[i][j] + "\t");
                }

                System.out.print("\n");
            }
            System.out.print("\n\n");
        }
    }
}