package com.project.bijug.sms_otpreader;

/**
 * Created by bijug on 16-11-2017.
 */

public interface SmsListener {
    public void messageReceived(String messageText);
}
