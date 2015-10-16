package com.example.ekinwise.windowsmanagertest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by ekinwise on 11-09-2015.
 */
public class newServiceClass extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(newServiceClass.this,"new Service Started",Toast.LENGTH_LONG).show();
    }

//    public void stopService()
//    {
//        Toast.makeText(newServiceClass.this, "new Service Stopped", Toast.LENGTH_LONG).show();
//        this.stopSelf();
//    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(newServiceClass.this, "new Service Stopped", Toast.LENGTH_LONG).show();
        stopSelf();

    }

}
