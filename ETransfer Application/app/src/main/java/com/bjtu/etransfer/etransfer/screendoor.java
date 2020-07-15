package com.bjtu.etransfer.etransfer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class screendoor extends AppCompatActivity {

    private SharedPreferences sharedPreferences1;
    private int k;
//    private long firstTime = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xianluxuanzescreendoor);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        // setContentView(R.layout.test);
        sharedPreferences1 = getSharedPreferences("users", Context.MODE_PRIVATE);

        Button b12 = (Button) findViewById(R.id.button12);
        b12.setOnClickListener(new screendoor.MyButtonListener());
        Button b13 = (Button) findViewById(R.id.button13);
        b13.setOnClickListener(new screendoor.MyButtonListener());
        Button b14 = (Button) findViewById(R.id.button14);
        b14.setOnClickListener(new screendoor.MyButtonListener());
        Button b15 = (Button) findViewById(R.id.button15);
        b15.setOnClickListener(new screendoor.MyButtonListener());
        Button b16 = (Button) findViewById(R.id.button16);
        b16.setOnClickListener(new screendoor.MyButtonListener());
        Button b17 = (Button) findViewById(R.id.button17);
        b17.setOnClickListener(new screendoor.MyButtonListener());
        Button b18 = (Button) findViewById(R.id.button18);
        b18.setOnClickListener(new screendoor.MyButtonListener());
        Button b19  = (Button) findViewById(R.id.button19);
        b19.setOnClickListener(new screendoor.MyButtonListener());
        Button b20  = (Button) findViewById(R.id.button20);
        b20.setOnClickListener(new screendoor.MyButtonListener());
        Button b21 = (Button) findViewById(R.id.button21);
        b21.setOnClickListener(new screendoor.MyButtonListener());
        Button b22 = (Button) findViewById(R.id.button22);
        b22.setOnClickListener(new screendoor.MyButtonListener());
        Button b23 = (Button) findViewById(R.id.button23);
        b23.setOnClickListener(new screendoor.MyButtonListener());
        Button b24 = (Button) findViewById(R.id.button24);
        b24.setOnClickListener(new screendoor.MyButtonListener());
        Button b25 = (Button) findViewById(R.id.button25);
        b25.setOnClickListener(new screendoor.MyButtonListener());
        Button b26 = (Button) findViewById(R.id.button26);
        b26.setOnClickListener(new screendoor.MyButtonListener());
        Button b27 = (Button) findViewById(R.id.button27);
        b27.setOnClickListener(new screendoor.MyButtonListener());
        Button b28 = (Button) findViewById(R.id.button28);
        b28.setOnClickListener(new screendoor.MyButtonListener());
        Button b29  = (Button) findViewById(R.id.button29);
        b29.setOnClickListener(new screendoor.MyButtonListener());
        Button b30  = (Button) findViewById(R.id.button30);
        b30.setOnClickListener(new screendoor.MyButtonListener());
        Button b31 = (Button) findViewById(R.id.button31);
        b31.setOnClickListener(new screendoor.MyButtonListener());
    }

    class MyButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            // TODO Auto-generated method stub
            SharedPreferences.Editor editor = sharedPreferences1.edit();

            switch (v.getId()) {

                case R.id.button12: {
                    Intent intent12 = new Intent();
                    intent12.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent12);
                    k=1;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }case R.id.button13: {
                    Intent intent13 = new Intent();
                    intent13.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent13);k=2;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }
                case R.id.button14: {
                    Intent intent14 = new Intent();
                    intent14.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent14);k=4;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }case R.id.button15: {
                    Intent intent15 = new Intent();
                    intent15.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent15);k=5;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }
                case R.id.button16: {
                    Intent intent16 = new Intent();
                    intent16.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent16);k=6;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }case R.id.button17: {
                    Intent intent17 = new Intent();
                    intent17.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent17);k=7;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }
                case R.id.button18: {
                    Intent intent18 = new Intent();
                    intent18.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent18);k=8;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }case R.id.button19: {
                    Intent intent19 = new Intent();
                    intent19.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent19);k=9;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }
                case R.id.button20: {
                    Intent intent20 = new Intent();
                    intent20.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent20);k=10;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }
                case R.id.button21: {
                    Intent intent21 = new Intent();
                    intent21.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent21);k=13;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }
                case R.id.button22: {
                    Intent intent22 = new Intent();
                    intent22.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent22);k=141;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }
                case R.id.button23: {
                    Intent intent23 = new Intent();
                    intent23.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent23);k=149;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }
                case R.id.button24: {
                    Intent intent24 = new Intent();
                    intent24.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent24);k=15;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }case R.id.button25: {
                    Intent intent25 = new Intent();
                    intent25.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent25);k=16;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }
                case R.id.button26: {
                    Intent intent26 = new Intent();
                    intent26.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent26);k=24;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }case R.id.button27: {
                    Intent intent27 = new Intent();
                    intent27.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent27);k=25;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }
                case R.id.button28: {
                    Intent intent28 = new Intent();
                    intent28.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent28);k=27;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }case R.id.button29: {
                    Intent intent29 = new Intent();
                    intent29.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent29);k=30;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }
                case R.id.button30: {
                    Intent intent30 = new Intent();
                    intent30.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent30);k=31;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }
                case R.id.button31: {
                    Intent intent31 = new Intent();
                    intent31.setClass(screendoor.this, linethirteendoor.class);
                    screendoor.this.startActivity(intent31);k=999;
                    editor.putInt("Line", k);
                    editor.commit();
                    break;
                }

            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        //Intent intent = new Intent();
        //intent.setClass(screendoor.this, MainActivity.class);
        //screendoor.this.startActivity(intent);
        finish();
    }
}
