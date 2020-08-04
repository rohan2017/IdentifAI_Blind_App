package com.parth.appfortheblind;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class NavController {

    private ArrayList<MenuItem> menu;
    private MenuItem current_item;
    private MediaPlayer mediaPlayer;


    public NavController(){
        this.menu = new ArrayList<MenuItem>();
        current_item = this.construct_menu();
    }

    public void select_item(Context context) throws IOException, InterruptedException {
        if(current_item.down != null){

            mediaPlayer.stop();

            mediaPlayer = MediaPlayer.create(context, R.raw._beepboop);

            mediaPlayer.start();
            TimeUnit.MILLISECONDS.sleep(200);


            this.current_item = current_item.down;
            mediaPlayer = MediaPlayer.create(context, this.current_item.sound_ID);
            mediaPlayer.start();
        }
    }

    public void deselect_item(Context context) throws InterruptedException {

        if(current_item.up != null){

            mediaPlayer.stop();

            mediaPlayer = MediaPlayer.create(context, R.raw._beep);

            mediaPlayer.start();
            TimeUnit.MILLISECONDS.sleep(200);


            this.current_item = current_item.up;
            mediaPlayer = MediaPlayer.create(context, this.current_item.sound_ID);
            mediaPlayer.start();
        }
    }

    public void repeat_item(Context context){
        mediaPlayer.stop();

        mediaPlayer = MediaPlayer.create(context, this.current_item.sound_ID);
        mediaPlayer.start();
    }

    public void next_item(Context context){
        if(current_item.next != null){
            this.current_item = current_item.next;

            mediaPlayer = MediaPlayer.create(context, this.current_item.sound_ID);
            mediaPlayer.start();

        }
    }

    public void prev_item(Context context){
        if(current_item.prev != null){
            this.current_item = current_item.prev;
            mediaPlayer.stop();

            mediaPlayer = MediaPlayer.create(context, this.current_item.sound_ID);
            mediaPlayer.start();

        }
    }

    public MenuItem construct_menu(){

        //Menu 1
        MenuItem SceneDescribe = new MenuItem(R.raw.scenedescription);
        MenuItem OCR = new MenuItem(R.raw.texttospeech);
        MenuItem MoneyRecognize = new MenuItem(R.raw.money);
        MenuItem Emotion = new MenuItem(R.raw.emotion);

        MenuItem home = new MenuItem(R.raw.selection);

        MenuItem SceneDescribeR = new MenuItem(R.raw.scenedescription);

        MenuItem OCRR = new MenuItem(R.raw.texttospeech);

        MenuItem MoneyRecognizeR = new MenuItem(R.raw.money);

        MenuItem EmotionR = new MenuItem(R.raw.emotion);

        SceneDescribe.next = Emotion;
        SceneDescribe.prev = OCR;
        SceneDescribe.down = SceneDescribeR;
        SceneDescribe.up = home;

        OCR.next = SceneDescribe;
        OCR.prev = MoneyRecognize;
        OCR.down = OCRR;
        OCR.up = home;
        //add_location.down = beep;

        MoneyRecognize.next = OCR;
        MoneyRecognize.prev = Emotion;
        MoneyRecognize.down = MoneyRecognizeR;
        MoneyRecognize.up = home;
        // add_person.down = beep;

        Emotion.next = MoneyRecognize;
        Emotion.prev = SceneDescribe;
        Emotion.down = EmotionR;
        Emotion.up = home;

        //complete.up = settings;
        //beep.up = get_directions;

        this.menu.add(SceneDescribe);
        this.menu.add(OCR);
        this.menu.add(MoneyRecognize);
        this.menu.add(Emotion);
        this.menu.add(home);
        this.menu.add(SceneDescribeR);
        this.menu.add(OCRR);
        this.menu.add(MoneyRecognizeR);
        this.menu.add(EmotionR);

        //this.menu.add(beep);
       // this.menu.add(complete);
        return null;

    }

}
