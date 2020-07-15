package com.bjtu.etransfer.etransfer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



public class linethirteendoor extends AppCompatActivity {
    private View view;
    private SharedPreferences sharedPreferences;
    private int k;
    private ImageView iv21;
    private ImageView iv22;
    private TextView tv9;
    private TextView tv11;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linethirteendoor);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ImageView iv21=(ImageView) findViewById(R.id.imageView21);
        ImageView iv22=(ImageView) findViewById(R.id.imageView22);
        TextView tv9=(TextView)  findViewById(R.id.textView9);
        TextView tv11=(TextView)  findViewById(R.id.textView11);

        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        k = sharedPreferences.getInt("Line", 0);
        switch(k){
            case 1:{
                iv21.setImageResource(R.drawable.f1);
              //  iv22.setImageResource(R.drawable.m2120);
                tv9.setText(R.string.m1comp);
                tv11.setText(R.string.m1door);
                break;
            }
            case 2:{
                iv21.setImageResource(R.drawable.f2);
                iv22.setImageResource(R.drawable.m2120);
                tv9.setText(R.string.m2comp);
                tv11.setText(R.string.m2door);
                break;
            }
            case 4:{
                iv21.setImageResource(R.drawable.f4);
                iv22.setImageResource(R.drawable.m4020);
                tv9.setText(R.string.m4comp);
                tv11.setText(R.string.m4door);
                break;
            }
            case 5:{
                iv21.setImageResource(R.drawable.f5);
                iv22.setImageResource(R.drawable.m5020);
                tv9.setText(R.string.m5comp);
                tv11.setText(R.string.m5door);
                break;
            }
            case 6:{
                iv21.setImageResource(R.drawable.f6);
                iv22.setImageResource(R.drawable.m6020);
                tv9.setText(R.string.m6comp);
                tv11.setText(R.string.m6door);
                break;
            }
            case 7:{
                iv21.setImageResource(R.drawable.f7);
                iv22.setImageResource(R.drawable.m7020);
                tv9.setText(R.string.m7comp);
                tv11.setText(R.string.m7door);
                break;
            }
            case 8:{
                iv21.setImageResource(R.drawable.f8);
                iv22.setImageResource(R.drawable.m8020);
                tv9.setText(R.string.m8comp);
                tv11.setText(R.string.m8door);
                break;
            }
            case 9:{
                iv21.setImageResource(R.drawable.f9);
                iv22.setImageResource(R.drawable.m9020);
                tv9.setText(R.string.m9comp);
                tv11.setText(R.string.m9door);
                break;
            }
            case 10:{
                iv21.setImageResource(R.drawable.f10);
                iv22.setImageResource(R.drawable.m10020);
                tv9.setText(R.string.m10comp);
                tv11.setText(R.string.m10door);
                break;
            }
            case 13:{
                iv21.setImageResource(R.drawable.f13);
                iv22.setImageResource(R.drawable.m13120);
                tv9.setText(R.string.m13comp);
                tv11.setText(R.string.m13door);
                break;
            }
            case 141:{
                iv21.setImageResource(R.drawable.f14);
                iv22.setImageResource(R.drawable.m14020);
                tv9.setText(R.string.m114comp);
                tv11.setText(R.string.m114door);
                break;
            }
            case 149:{
                iv21.setImageResource(R.drawable.f14);
                iv22.setImageResource(R.drawable.m14020);
                tv9.setText(R.string.m914comp);
                tv11.setText(R.string.m914door);
                break;
            }
            case 15:{
                iv21.setImageResource(R.drawable.f15);
                iv22.setImageResource(R.drawable.m15120);
                tv9.setText(R.string.m15comp);
                tv11.setText(R.string.m15door);
                break;
            }
            case 16:{
                iv21.setImageResource(R.drawable.f16);
                iv22.setImageResource(R.drawable.m16020);
                tv9.setText(R.string.m16comp);
                tv11.setText(R.string.m16door);
                break;
            }
            case 24:{
                iv21.setImageResource(R.drawable.f24);
                iv22.setImageResource(R.drawable.m24020);
                tv9.setText(R.string.m24comp);
                tv11.setText(R.string.m24door);
                break;
            }
            case 25:{
                iv21.setImageResource(R.drawable.f25);
                iv22.setImageResource(R.drawable.m25020);
                tv9.setText(R.string.m25comp);
                tv11.setText(R.string.m25door);
                break;
            }
            case 27:{
                iv21.setImageResource(R.drawable.f27);
                iv22.setImageResource(R.drawable.m27020);
                tv9.setText(R.string.m27comp);
                tv11.setText(R.string.m27door);
                break;
            }
            case 30:{
                iv21.setImageResource(R.drawable.f30);
                iv22.setImageResource(R.drawable.m30020);
                tv9.setText(R.string.m30comp);
                tv11.setText(R.string.m30door);
                break;
            }
            case 31:{
                iv21.setImageResource(R.drawable.f31);
                iv22.setImageResource(R.drawable.m31008);
                tv9.setText(R.string.m31comp);
                tv11.setText(R.string.m31door);
                break;
            }
            case 999:{
                iv21.setImageResource(R.drawable.ss2);

                tv9.setText(R.string.m999comp);
                tv11.setText(R.string.m999door);
                break;
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
