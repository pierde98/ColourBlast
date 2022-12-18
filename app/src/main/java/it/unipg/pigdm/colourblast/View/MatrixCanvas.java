package it.unipg.pigdm.colourblast.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MatrixCanvas extends SurfaceView implements Runnable {

    Thread thread = null;
    Boolean canDraw = false;
    Canvas canvas;
    SurfaceHolder holder;
    private String[][] ProgresMatrix;
    private String[][] mat;
    private Paint paint;
    private int Ox, Oy, side;


    public MatrixCanvas(Context context) {
        super(context);
        holder = getHolder();
        this.paint = new Paint();
        this.mat = MyView.getInstance().getMatrix(MyView.getInstance().getMatrixDim(), MyView.getInstance().getMatrixNumCol());
    }

    public MatrixCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        holder = getHolder();
        this.paint = new Paint();
        this.mat = MyView.getInstance().getMatrix(MyView.getInstance().getMatrixDim(), MyView.getInstance().getMatrixNumCol());
    }

    public MatrixCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        holder = getHolder();
        this.paint = new Paint();
        this.mat = MyView.getInstance().getMatrix(MyView.getInstance().getMatrixDim(), MyView.getInstance().getMatrixNumCol());
    }

    @Override
    public void run() {
        while(canDraw){
            if(!holder.getSurface().isValid()){
                continue;
            }

            canvas = holder.lockCanvas();
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
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void onResume(){
        canDraw = true;
        thread = new Thread(this);
        thread.start();

    }

    public void onPause(){
        canDraw = false;

        while(true){
            try {
                thread.join();
                break;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        thread = null;
    }
}
