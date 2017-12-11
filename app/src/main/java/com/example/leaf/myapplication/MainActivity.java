package com.example.leaf.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import java.lang.reflect.GenericArrayType;
import java.util.Timer;

import static android.graphics.Paint.Style.FILL;
import static android.graphics.Paint.Style.STROKE;
import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new G(this));

    }

    public class G extends View {


        int i,j=0;
        int puntos=0;
        int LimiteInferior=1600;
        int LimiteX = 900;
        int posX= resetPosX();
        int posPlayerX = 500;
        boolean isGameOver =false;
        Paint pincel,pincelJugador = new Paint();


        public G (Context c) {
            super(c);
            pincel.setAntiAlias(true);
            pincel.setColor(Color.RED);
            pincel.setStyle(FILL);
            pincel.setStrokeWidth(5);
            pincel.setTextSize(72);
            pincelJugador.setColor(Color.BLUE);
            pincelJugador.setStyle(FILL);

        }


        @Override
        protected void onDraw(Canvas canvas) {


            canvas.drawText(String.valueOf(puntos), 400, 500, pincel);

            if (!isGameOver) {

                canvas.drawCircle(posX, -100 + i, 60, pincel);
                canvas.drawCircle(LimiteX - posX, -100 + j, 60, pincel);
                canvas.drawRect(posPlayerX, 1400, posPlayerX + 120, 1500, pincelJugador);

                isGameOver = collison(posX, -100 + i, LimiteX - posX, -100 + j, posPlayerX, 1400);
                i = (int) (i + Math.floor(Math.random() * 30) + 1);
                j = (int) (j + Math.floor(Math.random() * 30) + 1);


                if (i > LimiteInferior && j > LimiteInferior) {
                    i = 0;
                    j = 0;
                    puntos += 1;
                    posX = resetPosX();
                }
                invalidate();

            } else {
                drawNewCanvas(canvas, puntos, pincel);
            }
        }
        //generacion de nueva coordenada X para las bolas
        public int resetPosX(){
            int cx = (int) (Math.floor(Math.random()*400)+30);
            return cx;
        }

        //deteccion de colision
        public boolean collison(int ox, int oy, int ox2,int oy2, int px, int py){
            boolean b;
            if ((abs(ox-px)<60 && abs(oy-py)<60 )|| (abs(ox2-px)<60 && abs(oy2-py)<60)) {
                b=true;
            } else if ((abs(ox-(px+120))<60 && abs(oy-py)<60) || (abs(ox2-(px+120))<60 && abs(oy2-py)<60))
            {
                b=true;
            } else b=false;
            return b;

        }

        //game over
        public void drawNewCanvas(Canvas c, int p, Paint pnt){
            c.drawColor(Color.BLACK);
            c.drawText("GAME OVER", 400, 500, pnt);
            c.drawText("Score ->" + p, 400, 600, pnt);


        }

        //control eje x
        public boolean onTouchEvent(MotionEvent tap){
            boolean tapped=true;
            int taptap=(int)tap.getX();


            if (taptap>getWidth()/2){
                posPlayerX=posPlayerX+5;
            } else if (taptap<getWidth()/2){
                posPlayerX=posPlayerX-5;
            }
            return tapped;
        }


    }
}
