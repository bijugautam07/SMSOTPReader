package com.project.bijug.sms_otpreader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by bijug on 16-11-2017.
 */

public class SmsReceiver extends BroadcastReceiver {



    //interface
    private static SmsListener mListener;

    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){

            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;
            String msg_from;
            if (bundle != null){
                //---retrieve the SMS message received---
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        msg_from = msgs[i].getDisplayOriginatingAddress();
                        Log.d("Sender",msg_from);
                        if (msg_from.contains("SB")) {
                            String msgBody = msgs[i].getMessageBody();
                            Log.e("Message", msgBody);
                            mListener.messageReceived(msgBody);
                        }
                    }
                }catch(Exception e){
//                            Log.d("Exception caught",e.getMessage());
                }
            }
        }

    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }

}
