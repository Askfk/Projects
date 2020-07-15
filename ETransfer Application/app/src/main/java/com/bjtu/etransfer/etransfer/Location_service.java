package com.bjtu.etransfer.etransfer;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class Location_service extends Service {
    private Location location;
    public static LocationManager locationManager;
    public static LocationListener listener;
    private Handler handler;
    private Geocoder mGeocoder;
    private CountDownTimer timer;

    private MyBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class MyBinder extends Binder {


        private SharedPreferences sharedPreferences;
        private int mTripTime;


        public void startGPS() {
            sharedPreferences = getSharedPreferences("MassageSavers", Context.MODE_PRIVATE);
            mTripTime = sharedPreferences.getInt("TripTime", 30);
            Bmob.initialize(Location_service.this, "62186217f0e1c6b85c5b00707aecc65d");
            mGeocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            listener = new MyListener();
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Looper looper = Looper.myLooper();
            handler = new Handler(looper);

            locationManager.removeUpdates(listener);
            if (ActivityCompat.checkSelfPermission(Location_service.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Location_service.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, listener);
            startCountDownTime(mTripTime*60);
        }
    }
    private void showAddress(String latitude, String longitude) {

        try {
            List<Address> addrList = mGeocoder.getFromLocation(Double.parseDouble(latitude), Double.parseDouble(longitude), 1);
            if (addrList.size() > 0) {
                StringBuilder sb = new StringBuilder();
                Address addr = addrList.get(0);
                sb.append(addr.getAddressLine(0));
                Toast.makeText(Location_service.this, sb.toString(), Toast.LENGTH_SHORT).show();

            } else {
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MyListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            TelephonyManager tm = (TelephonyManager) Location_service.this
                    .getSystemService(Location_service.this.TELEPHONY_SERVICE);
            Location_service.this.location = location;
            String latitude = Double.toString(location.getLatitude());
            String longitude = Double.toString(location.getLongitude());
            user_Location locationobjs = new user_Location();
            locationobjs.setID(tm.getDeviceId());
            locationobjs.setLatitude(latitude);
            locationobjs.setLongtitude(longitude);

            if (!latitude.equals("") && !longitude.equals("")) {
                showAddress(latitude, longitude);
            }
            locationobjs.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if(e == null){
                        Toast.makeText(Location_service.this, "定位发送成功", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(Location_service.this, "定位发送失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onProviderDisabled(String provider) {}
    }

    private void startCountDownTime(long time) {
        /**
         * 最简单的倒计时类，实现了官方的CountDownTimer类（没有特殊要求的话可以使用）
         * 即使退出activity，倒计时还能进行，因为是创建了后台的线程。
         * 有onTick，onFinsh、cancel和start方法
         */
        timer = new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //每隔countDownInterval秒会回调一次onTick()方法

            }

            @Override
            public void onFinish() {
                locationManager.removeUpdates(listener);
            }
        };
        timer.start();// 开始计时
        //timer.cancel(); // 取消
    }
}
