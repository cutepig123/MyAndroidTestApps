package com.example.test7_sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.gsm.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver{

	private void sendSMS_Direct(Context context,String phoneNumber, String message)
    {        
//        PendingIntent pi = PendingIntent.getActivity(this, 0,
//            new Intent(), 0);                
//        SmsManager sms = SmsManager.getDefault();
//        sms.sendTextMessage(phoneNumber, null, message, pi, null);   
		
		 SmsManager sms   = SmsManager.getDefault();
		  sms.sendTextMessage(phoneNumber, null, message, null, null);
//		 Intent startActivity = new Intent();
//	        startActivity.setClass(context, MainActivity.class);
//	        startActivity.setAction(MainActivity.class.getName());
//	        startActivity.setFlags(
//	                Intent.FLAG_ACTIVITY_NEW_TASK
//	                | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//	        context.startActivity(startActivity); 
    }    
	
	private void startOtherActivity(Context context)
    {        
		 Intent startActivity = new Intent();
	        startActivity.setClass(context, MainActivity.class);
	        startActivity.setAction(MainActivity.class.getName());
	        startActivity.setFlags(
	                Intent.FLAG_ACTIVITY_NEW_TASK
	                | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
	        context.startActivity(startActivity); 
    }    
	 @Override
    public void onReceive(Context context, Intent intent) 
    {
        //---get the SMS message passed in---
        Bundle bundle = intent.getExtras();        
        SmsMessage[] msgs = null;
        String str = "";            
        if (bundle != null)
        {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];            
            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                
                str += "SMS from " + msgs[i].getOriginatingAddress();                     
                str += " :";
                String sBody =msgs[i].getMessageBody().toString();
                str += sBody;
                str += "\n";
                
                String []cmds =sBody.split(" ");
                
                if( cmds.length==3 && cmds[0].equals("SendSMS"))
                {
                	sendSMS_Direct(context,cmds[1], cmds[2]);
                	Log.d("Tag","Remote Control: " + "SendSMS " + cmds[1] + "," + cmds[2]);
                }
                if( cmds.length==2 && cmds[0].equals("Toast"))
                {
                	 Toast.makeText(context, "Remote Control: " + cmds[1], 
                            Toast.LENGTH_SHORT).show();
                	Log.d("Tag","Toast " + cmds[1] );
                }
            }
            //---display the new SMS message---
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }                         
    }

}
