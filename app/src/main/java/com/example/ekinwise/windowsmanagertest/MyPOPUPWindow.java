package com.example.ekinwise.windowsmanagertest;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by ekinwise on 10-09-2015.
 */
public class MyPOPUPWindow extends Service {
    WindowManager myWindowManager;
    LinearLayout myLayout;
    Button stop;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myWindowManager = (WindowManager)getSystemService(WINDOW_SERVICE);
        myLayout = new LinearLayout(this);

        stop = new Button(this);
        LinearLayout.LayoutParams mylayoutparam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
                myLayout.setBackgroundColor(Color.argb(66, 255, 0, 0));
        ViewGroup.LayoutParams btnParameters = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        stop.setText("Stop");
        stop.setLayoutParams(btnParameters);
        myLayout.setLayoutParams(mylayoutparam);

        final WindowManager.LayoutParams parameters = new WindowManager.LayoutParams(400,150,WindowManager.LayoutParams.TYPE_PHONE,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
        parameters.x = 0;
        parameters.y = 0;
        parameters.gravity = Gravity.CENTER|Gravity.CENTER;

        myLayout.addView(stop);
        myWindowManager.addView(myLayout,parameters);


        myLayout.setOnTouchListener(new View.OnTouchListener() {
            private WindowManager.LayoutParams updatedParameters = parameters;
            int x, y;
            float touchedX, touchedY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = updatedParameters.x;
                        y = updatedParameters.y;

                        touchedX = event.getRawX();
                        touchedY = event.getRawY();

                        break;

                    case MotionEvent.ACTION_MOVE:
                        updatedParameters.x = (int) (x + (event.getRawX() - touchedX));
                        updatedParameters.y = (int) (y + (event.getRawY() - touchedY));

                        myWindowManager.updateViewLayout(myLayout, updatedParameters);
                    default:
                        break;

                }


                return false;
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWindowManager.removeView(myLayout);
                stopSelf();

            }
        });

        //this.stopSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myWindowManager.removeView(myLayout);
        stopSelf();
    }

}
