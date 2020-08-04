package com.parth.appfortheblind;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class LoadingActivity extends Activity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        mediaPlayer =  MediaPlayer.create(this, R.raw.loading);
        mediaPlayer.start();

    }

    public void contToSelect(View v) {
        mediaPlayer.stop();

        Intent intent = new Intent(this, SelectActivity.class);
        startActivity(intent);

    }


}
