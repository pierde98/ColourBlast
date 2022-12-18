package it.unipg.pigdm.colourblast.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.concurrent.TimeUnit;

public class MatrixView extends View {

    private String[][] ProgresMatrix;
    private String[][] mat;
    private Paint paint;
    private int Ox, Oy, side;

    public MatrixView(Context context) {
        super(context);
        init(null);
    }

    public MatrixView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MatrixView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public MatrixView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void init(@Nullable AttributeSet set) {
        this.paint = new Paint();
        this.mat = MyView.getInstance().getMatrix(MyView.getInstance().getMatrixDim(), MyView.getInstance().getMatrixNumCol());
        //this.ProgresMatrix = MyView.getInstance().getProgressMatrix();
    }

    public void swapMatrix(String [][] matrix){
        this.mat = matrix;
        //this.ProgresMatrix = MyView.getInstance().getProgressMatrix();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        MyView.getInstance().createSquare();
        Ox = MyView.getInstance().getOx();
        Oy = MyView.getInstance().getOy();
        side = (MyView.getInstance().getScreenWidth() - (Ox * 2)) / mat.length;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                Rect rect = new Rect(Ox + (i * side), Oy + (j * side), Ox + (i * side) + side, Oy + (j * side) + side);
                this.paint.setColor(Color.parseColor(mat[i][j]));
                this.paint.setStyle(Paint.Style.FILL);
                canvas.drawRect(rect, this.paint);
            }
        }

        /*for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if(!ProgresMatrix[i][j].equals("")){//ANIMAZIONE
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                    }

                }
            }
        }*/

    }

    public void colorAnimation(int i, int j, String colour, String[][] ProgressMatrix) {
        if(ProgressMatrix[i][j] != ""){
            this.mat[i][j] = colour;
            ProgressMatrix[i][j] = "";
        }
        invalidate();

        if(i+1<this.mat.length)
            colorAnimation(i+1, j, colour, ProgressMatrix);
        else if(j+1<this.mat.length)
            colorAnimation(0,j+1, colour, ProgressMatrix);
    }
}
