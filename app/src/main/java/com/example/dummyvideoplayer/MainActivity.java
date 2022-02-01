package com.example.dummyvideoplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    SurfaceView surfaceView;
    TextView vidOut;
    MediaPlayer mediaPlayer;
    ImageButton pp;
    boolean play = false;
    int selected = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vidOut = findViewById(R.id.vidout);
        seekBar = findViewById(R.id.seekBar);
        pp = findViewById(R.id.playpause);
        surfaceView = findViewById(R.id.surfaceView);
        mediaPlayer = MediaPlayer.create(this,R.raw.video1);
        seekBar.setMax(mediaPlayer.getDuration()/1000);
        surfaceView.setKeepScreenOn(true);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                mediaPlayer.setDisplay(surfaceHolder);
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(mediaPlayer!=null && b){
                    mediaPlayer.seekTo(i*1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
        Handler mHandler = new Handler();
        MainActivity.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if(mediaPlayer != null){
                    int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);
                }
                mHandler.postDelayed(this, 1000);
            }
        });

    }
    public void playvid(View view){
        play = !play;
        if(play){
            mediaPlayer.start();
            pp.setImageResource(android.R.drawable.ic_media_pause);
        }
        else{
            mediaPlayer.pause();
            pp.setImageResource(android.R.drawable.ic_media_play);
        }
    }

    public void selectedplus(View view) throws IOException {
        if(selected==4){
            selected=1;
        }
        else{
            selected++;
        }
        try {
            Resources res = getResources(); //resource handle
            String url = "video" +selected;
            InputStream is = getClass().getClassLoader().getResourceAsStream("raw/");
            Integer resIdSound = res.getIdentifier (url,  "raw", this.getPackageName());
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(this, resIdSound);
                seekBar.setMax(mediaPlayer.getDuration()/1000);
                int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                seekBar.setProgress(mCurrentPosition);
                SurfaceHolder surfaceHolder = surfaceView.getHolder();
                mediaPlayer.setDisplay(surfaceHolder);
            } mediaPlayer.start();
            if(selected==1){
                vidOut.setText("Playing : Human Evolution");
            }
            if(selected==2){
                vidOut.setText("Playing : WWE John Cena vs The Rock Chinese");
            }
            if(selected==3){
                vidOut.setText("Playing : The One and Only Bully Maguire");
            }
            if(selected==4){
                vidOut.setText("Playing : Normal cat vs My cat Short");
            }

        } catch(Exception e) { e.printStackTrace(); }
    }

    public void selectedminus(View view) throws IOException {
        if(selected==1){
            selected=4;
        }
        else{
            selected--;
        }
        try {
            Resources res = getResources(); //resource handle
            String url = "video" +selected;
            InputStream is = getClass().getClassLoader().getResourceAsStream("raw/");
            Integer resIdSound = res.getIdentifier (url,  "raw", this.getPackageName());
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(this, resIdSound);
                seekBar.setMax(mediaPlayer.getDuration()/1000);
                int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                seekBar.setProgress(mCurrentPosition);
                SurfaceHolder surfaceHolder = surfaceView.getHolder();
                mediaPlayer.setDisplay(surfaceHolder);
            } mediaPlayer.start();
            if(selected==1){
                vidOut.setText("Playing : Human Evolution");
            }
            if(selected==2){
                vidOut.setText("Playing : WWE John Cena vs The Rock Chinese");
            }
            if(selected==3){
                vidOut.setText("Playing : The One and Only Bully Maguire");
            }
            if(selected==4){
                vidOut.setText("Playing : Normal cat vs My cat Short");
            }
        } catch(Exception e) { e.printStackTrace(); }

    }

}