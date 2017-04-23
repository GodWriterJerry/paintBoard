package com.example.administrator.paintboard0422;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/4/22.
 */

public class myView extends View{
    private Paint mPaint;
    private Path mPath;
    private boolean isUp;
    private float startX,startY;
    private Canvas mCanvas;

    public myView(Context context) {
        super(context);
    }

    public myView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPath=new Path();
        mPaint=new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(),event.getY());
                startX=event.getX();
                startY=event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = (startX+event.getX())/2;
                float endY = (startY+event.getY())/2;
                mPath.quadTo(startX,startY,endX,endY);
                //mPath.lineTo(event.getX(),event.getY());
                startX=event.getX();
                startY=event.getY();
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                isUp=true;
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);
    }

    public void reset() {
        mPath.reset();
        invalidate();
    }

    public void redPaint(){
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        Log.i("redPaint","had  run!!");
    }

}
