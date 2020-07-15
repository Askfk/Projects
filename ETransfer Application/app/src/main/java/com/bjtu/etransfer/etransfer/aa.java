package com.bjtu.etransfer.etransfer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private int i = 0, j = 0, k = 0;
    private String beginStation, endStation;
    private long firstTime = 0;
    public static final int SHOW_RESPONSE = 0;
    private String netTime;
    private TimeGetter timegetter = new TimeGetter();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this,"62186217f0e1c6b85c5b00707aecc65d");
        EditText e = (EditText) findViewById(R.id.editText);
        EditText e2 = (EditText) findViewById(R.id.editText2);
        Button b99 = (Button)findViewById(R.id.button99);
        Button b98 = (Button)findViewById(R.id.button98);
        ImageButton imbt = (ImageButton)findViewById(R.id.imageButton);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);
        Button b4 = (Button) findViewById(R.id.button4);
        Button b5 = (Button) findViewById(R.id.button5);
        Button b6 = (Button) findViewById(R.id.button6);
        Button b7 = (Button) findViewById(R.id.button7);

        imbt.setOnClickListener(new MyButtonListener());
        b2.setOnClickListener(new MyButtonListener());
        b3.setOnClickListener(new MyButtonListener());
        b4.setOnClickListener(new MyButtonListener());
        b5.setOnClickListener(new MyButtonListener());
        b6.setOnClickListener(new MyButtonListener());
        b7.setOnClickListener(new MyButtonListener());
        b99.setOnClickListener(new MyButtonListener());
        b98.setOnClickListener(new MyButtonListener());

        sharedPreferences = getSharedPreferences("MassageSavers", Context.MODE_PRIVATE);
        i = sharedPreferences.getInt("beginPosition", 0);
        j = sharedPreferences.getInt("endPosition", 0);
        if(i == 1){
            beginStation = sharedPreferences.getString("beginVertex", null);
            e.setText(beginStation);
        }
        if(j == 1){
            endStation = sharedPreferences.getString("endVertex", null);
            e2.setText(endStation);
        }
        sendRequestWithHttpClient();
    }

    class MyButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            // TODO Auto-generated method stub
            EditText e = (EditText) findViewById(R.id.editText);
            EditText e2 = (EditText) findViewById(R.id.editText2);
            Editor editor = sharedPreferences.edit();
            String a,b;
            switch (v.getId()) {
                case R.id.imageButton: {
                    a = sharedPreferences.getString("beginVertex", null);
                    b = sharedPreferences.getString("endVertex", null);
                    editor.putString("beginVertex", b);
                    editor.putString("endVertex", a);
                    editor.commit();
                    e.setText(b);
                    e2.setText(a);
                    break;
                }

                case R.id.button2: {
                    Toast.makeText(MainActivity.this, "功能正在开发中，敬请期待！", Toast.LENGTH_SHORT).show();
                    break;
                }

                case R.id.button3: {
                    final Intent intent3 = new Intent();
                    a = sharedPreferences.getString("beginVertex", null);
                    b = sharedPreferences.getString("endVertex", null);
                    if(a.equals(b) == true)
                    {
                        break;
                    }
                    if(netTime.equals("") || beginStation.equals("") || endStation.equals(""))
                    {
                        break;
                    }
                    Stations stationsObj = new Stations();
                    stationsObj.setTime(netTime);
                    stationsObj.setStartStation(beginStation);
                    stationsObj.setEndStation(endStation);
                    stationsObj.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e == null){
                                intent3.setClass(MainActivity.this, showPath.class);
                                MainActivity.this.startActivity(intent3);
                            }else{
                                Toast.makeText(MainActivity.this, "请接入互联网", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    break;
                }

                case R.id.button4: {
                    Intent intent4 = new Intent();
                    intent4.setClass(MainActivity.this, xianluchaxun.class);
                    MainActivity.this.startActivity(intent4);
                    //finish();
                    break;
                }
                case R.id.button5: {
                    Intent intent5 = new Intent();
                    intent5.setClass(MainActivity.this, xianlutu.class);
                    MainActivity.this.startActivity(intent5);
                    //finish();
                    break;
                }
                case R.id.button6: {
                    Intent intent6 = new Intent();
                    intent6.setClass(MainActivity.this, about.class);
                    MainActivity.this.startActivity(intent6);
                    //finish();
                    break;
                }
                case R.id.button7: {
                    Intent intent7 = new Intent();
                    intent7.setClass(MainActivity.this, screendoor.class);
                    MainActivity.this.startActivity(intent7);
                    //finish();
                    break;
                }
                case R.id.button99: {
                    k = 1;
                    i = 1;
                    editor.putInt("beginPosition", i);
                    editor.putInt("Position", k);
                    editor.commit();
                    Intent intente = new Intent();
                    intente.setClass(MainActivity.this, zhandianchaxun.class);
                    MainActivity.this.startActivity(intente);
                    finish();
                    break;
                }
                case R.id.button98: {
                    k = 2;
                    j = 1;
                    editor.putInt("Position", k);
                    editor.putInt("endPosition", j);
                    editor.commit();
                    Intent intente2 = new Intent();
                    intente2.setClass(MainActivity.this, zhandianchaxun.class);
                    MainActivity.this.startActivity(intente2);
                    finish();
                    break;
                }
            }
        }
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            MyTextView textView_response =(MyTextView)findViewById(R.id.textView);
            super.handleMessage(msg);
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    textView_response.setText(response);
                    break;
                default:
                    break;
            }
        }
    };

    //方法：发送网络请求
    private void sendRequestWithHttpClient() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                //用HttpClient发送请求，分为五步
                //第一步：创建HttpClient对象
                HttpClient httpCient = new DefaultHttpClient();
                //第二步：创建代表请求的对象,参数是访问的服务器地址
                HttpGet httpGet = new HttpGet("http://qqpp777.xicp.net:28586/test.jsp");
                Message message = new Message();
                timegetter.setfullTime();
                timegetter.setHalfTime();
                netTime = timegetter.getfullTime();
                Editor editor = sharedPreferences.edit();
                editor.putString("nowtime",timegetter.getHalfTime());
                editor.commit();
                try {
                    //第三步：执行请求，获取服务器发还的相应对象
                    HttpResponse httpResponse = httpCient.execute(httpGet);
                    //第四步：检查相应的状态是否正常：检查状态码的值是200表示正常
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        //第五步：从相应对象当中取出数据，放到entity当中
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity,"utf-8");//将entity当中的数据转换为字符串

                        //在子线程中将Message对象发出去
                        message.what = SHOW_RESPONSE;
                        message.obj = response.toString();
                        editor.putString("LineMessage", response.toString());
                        editor.commit();
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block

                    message.what = SHOW_RESPONSE;
                    message.obj = sharedPreferences.getString("LineMessage", "目前地铁各条线路运行正常,请您放心乘坐北京地铁，如有突发信息，我们会及时提醒并通知您，感谢您的使用！");
                }
                handler.sendMessage(message);

            }
        }).start();//这个start()方法不要忘记了

    }



    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        } else {
            System.exit(0);
        }
    }
}
