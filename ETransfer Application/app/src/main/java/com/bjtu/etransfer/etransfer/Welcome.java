package com.bjtu.etransfer.etransfer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;


public class Welcome extends Activity {

    private int time = 0;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sharedPreferences = getSharedPreferences("LoginTimes", Context.MODE_PRIVATE);
        time = sharedPreferences.getInt("LoginTime",0);

        if(time < 1){
            Editor editor = sharedPreferences.edit();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    startActivity(new Intent(Welcome.this,ScrollLayoutActivity.class));
                    finish();

                }
            },3000);//这里停留时间为1000=1s
            time = 1;
            editor.putInt("LoginTime",time);
            editor.commit();
        }

        else{
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    startActivity(new Intent(Welcome.this,MainActivity.class));
                    finish();

                }
            },3000);//这里停留时间为1000=1s
        }
    }
}
