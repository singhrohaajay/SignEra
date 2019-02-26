package com.linguistics.tts_stt;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


public class Video extends AppCompatActivity {

    static int index = 1;

    VideoView videoView;
    TextView textView;
    int noOfAlphabets=0;
    int noOfWords=0;
    int aplhaCpunter=0;
    int wordCount=0;
    char alphabets[];
     String words[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.vdVw);
        textView = findViewById(R.id.textView);
        //Set MediaController  to enable play, pause, forward, etc options.
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        //Location of Media File

        final Intent intent = getIntent();
        final String sentence = intent.getStringExtra("Key");
         words = sentence.split("\\W+");
         noOfWords = words.length;

        alphabets = words[wordCount].toCharArray();
        noOfAlphabets=alphabets.length;



        String s = "";
        s += alphabets[0];
        final String str=s;
        int imageId;
        imageId = getResourseId(this, s, "raw", getPackageName());
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + imageId);
        //Starting VideView By Setting MediaController and URI
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer arg0) {
                videoView.requestFocus();
                videoView.start();
            }
        });


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer arg0) {
                videoView.requestFocus();
                videoView.start();
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                if(index==noOfAlphabets&&wordCount==noOfWords){

                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
                if(index==noOfAlphabets&&wordCount<noOfWords){
                    index=0;
                    noOfAlphabets=words[++wordCount].length();
                    alphabets=words[wordCount].toCharArray();
                }
                if (index < noOfAlphabets&&wordCount<noOfWords) {

                    String s = "";
                    s += alphabets[index];
                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                    int Id;
                    Id = getResourseId(getApplicationContext(), s, "raw", getPackageName());
                    Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + Id);


                    videoView.setVideoURI(uri);

                    index++;

                }
            }
        });


    }


    public static int getResourseId(Context context, String pVariableName, String pResourcename, String pPackageName) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            throw new RuntimeException("Error getting Resource ID.", e);
        } finally {


        }
    }

}
