package com.nguyenhaidang_dangnh2406.moviescgv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.nguyenhaidang_dangnh2406.moviescgv.views.activitys.MasterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        screenHello();
    }

    private void screenHello(){
        Thread thread = new Thread(){
            public void run(){
                try {
                    //Thread will sleep for 1 seconds
                    sleep(1*1000);

                    //After 1 seconds redirect to another intent
                    Intent intent = new Intent (MainActivity.this, MasterActivity.class);
                    startActivity(intent);

                    // Remove activity
                    finish();
                }catch (Exception e){

                }
            }

        };
        //Start thread
        thread.start();
    }
}