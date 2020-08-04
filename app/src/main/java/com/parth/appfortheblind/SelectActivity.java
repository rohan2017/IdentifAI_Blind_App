package com.parth.appfortheblind;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class SelectActivity extends Activity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;
    private NavController controller;
    MediaPlayer mediaPlayer;

    private static boolean handled = false;

    float x1,x2;
    float y1, y2;

    public void cam() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        mDetector = new GestureDetectorCompat(this,this);

        mDetector.setOnDoubleTapListener(this);

        controller = new NavController();

        mediaPlayer =  MediaPlayer.create(this, R.raw.selection);
        mediaPlayer.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        mediaPlayer.stop();
        SelectActivity.handled = false;
        this.mDetector.onTouchEvent(event);

        if(!SelectActivity.handled) {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN: {
                    x1 = event.getX();
                    y1 = event.getY();
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    x2 = event.getX();
                    y2 = event.getY();


                    if (x1 < x2) {

                    }

                    // if right to left sweep event on screen
                    if (x1 > x2) {

                    }

                    // if UP to Down sweep event on screen
                    if (y1 < y2) {
                        controller.next_item(this);
                        Log.d(DEBUG_TAG, "SWIPE DOWN: ");
                    }

                    //if Down to UP sweep event on screen
                    if (y1 > y2) {
                        controller.prev_item(this);
                        Log.d(DEBUG_TAG, "SWIPE UP: ");
                    }
                    break;
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDown: " + event.toString());
        //FullscreenActivity.handled = true;
        return false;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {

        Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
        //FullscreenActivity.handled = true;
        return false;
    }

    @Override
    public void onLongPress(MotionEvent event) {

        try {
            controller.deselect_item(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
        SelectActivity.handled = true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(DEBUG_TAG, "onScroll: " + e1.toString()+e2.toString());
        //FullscreenActivity.handled = true;
        return false;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
        SelectActivity.handled = true;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {

        //controller.repeat_item(this);

        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        SelectActivity.handled = true;
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        try {
            controller.select_item(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        SelectActivity.handled = true;
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
        SelectActivity.handled = true;
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());

        controller.repeat_item(this);
        SelectActivity.handled = true;
        return true;
    }
}