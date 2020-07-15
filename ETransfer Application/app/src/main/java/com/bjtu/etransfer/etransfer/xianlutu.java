package com.bjtu.etransfer.etransfer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class xianlutu extends AppCompatActivity {
    private View view;
   // private LinearLayout lay;
    private Button bt10, bt11;
    private DragImageView iV94;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xianlutu);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        iV94 = (DragImageView) findViewById(R.id.imageView94);

        //lay = (LinearLayout) findViewById(layout);
        bt10 = (Button)findViewById(R.id.button10);
        bt11 = (Button)findViewById(R.id.button11) ;


        bt11.setTextColor(getResources().getColor(R.color.white));
        bt10.setTextColor(getResources().getColor(R.color.blueSlight));
        iV94.setImageResource(R.drawable.xianlutulowdpi1);
        bt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt11.setTextColor(getResources().getColor(R.color.white));
                bt10.setTextColor(getResources().getColor(R.color.blueSlight));
                iV94.setImageResource(R.drawable.xianlutulowdpi1);
            }
        });
        bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt11.setTextColor(getResources().getColor(R.color.blueSlight));
                bt10.setTextColor(getResources().getColor(R.color.white));
                iV94.setImageResource(R.drawable.nightbuslowdpi);
            }
        });


    }

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
        //intent.setClass(xianlutu.this, MainActivity.class);
        //xianlutu.this.startActivity(intent);
        finish();
    }

}
