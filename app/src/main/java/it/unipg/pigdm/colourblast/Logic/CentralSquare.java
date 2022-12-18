package it.unipg.pigdm.colourblast.Logic;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CentralSquare extends Drawable {

    private final Paint paint;
    private int x;
    private int y;
    private int Ox;
    private int Oy;


    public CentralSquare(){

        this.paint = new Paint();
        this.x = Constants.SCREEN_WIDTH;
        this.Ox = this.x/10;
        this.y = Constants.SCREEN_HEIGHT;
        this.Oy = this.y/8;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth((float) 2.5);
        canvas.drawRect(Ox, Oy, x-Ox, Oy+(x-2*Ox),this.paint);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    public int getOx(){
        return this.Ox;
    }

    public int getOy(){
        return this.Oy;
    }
}
