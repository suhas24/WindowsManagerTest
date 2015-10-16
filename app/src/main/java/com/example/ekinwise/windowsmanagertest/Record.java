package com.example.ekinwise.windowsmanagertest;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.os.Process;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ekinwise on 10-09-2015.
 */
public  class Record extends BroadcastReceiver
{

    MainActivity ma = new MainActivity();
    @Override
    public void onReceive(Context context, Intent intent) throws NullPointerException{

        try {
            // TELEPHONY MANAGER class object to register one listner
            TelephonyManager tmgr = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            //Create Listner
            MyPhoneStateListener PhoneListener = new MyPhoneStateListener(context);

            // Register listener for LISTEN_CALL_STATE
            tmgr.listen(PhoneListener, PhoneStateListener.LISTEN_CALL_STATE);

        } catch (Exception e) {
            Log.e("Phone Receive Error", " " + e);
        }
    }


    private class MyPhoneStateListener extends PhoneStateListener {
        Context context;
        public MyPhoneStateListener(Context c){
            context=c;
        }

        public void onCallStateChanged(int state, String incomingNumber) {

            Log.d("MyPhoneListener",state+"   incoming no:"+incomingNumber);

            if (state == 1) {

                Log.d("State","Ringing");
                Intent startInt= new Intent(context,MyPOPUPWindow.class);
                context.startService(startInt);

            }
            else if(state==0) {
                Log.d("State",state+"" );
                Intent stopInt=new Intent(context,MyPOPUPWindow.class);
                context.stopService(stopInt);
            }
        }
    }



}
