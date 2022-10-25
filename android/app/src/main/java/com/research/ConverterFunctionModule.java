package com.research;


import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;


public class ConverterFunctionModule extends ReactContextBaseJavaModule {
    Python py = Python.getInstance();
    private DeviceEventManagerModule.RCTDeviceEventEmitter mEmitter = null;


    private void sendEvent(String eventName, Integer message) {
       WritableMap params = Arguments.createMap();


        if (mEmitter == null) {
            mEmitter = getReactApplicationContext().getJSModule((DeviceEventManagerModule.RCTDeviceEventEmitter.class));
        }

        Runnable runnable = new Runnable() {
            public void run() {
                int finishedNumber = 301;
//                params.putInt("counter",finishedNumber);
                mEmitter.emit("counter",finishedNumber);
            }
        };
       Handler handler = new Handler();
        if (message == 300) {
            handler.postDelayed(runnable, 300);
        }
        if (mEmitter != null) {

            mEmitter.emit(eventName, message);
        }
    }

    String TAG =  "Converter";
    ConverterFunctionModule  (ReactApplicationContext context){
        super(context);
    }
    @NonNull
    @Override

    public String getName() {
        return "ConverterFunctionModule";
    }
    @ReactMethod
    public void returnValue (){
        Log.d(TAG, "returnValue: hello from Java");
    }

    @ReactMethod
    public void sendEvents (){

        int start = 0;
        int end = 300;
        while (start <end){
            start++;
            sendEvent("counter",start);
        }
        Log.d(TAG, "returnValue: send Event");
    }
    @ReactMethod
    public void returnFromNative(String params, String params2, Promise promise) {
        PyObject pyobj = py.getModule("script");
        PyObject obj = pyobj.callAttr("main", "12", "14");
        String st = obj.toString();
        Log.d(TAG, "returnFromNative: "+params+ params2);
         Toast.makeText(getReactApplicationContext(),"returnFromNative: "+params+ params2,Toast.LENGTH_SHORT).show();

        try {

            promise.resolve(st);
        } catch(Exception e) {
            promise.reject("Create Event Error", e);
        }
    }



    @ReactMethod
    public void createFunction(String name, String location) {

        Toast.makeText(getReactApplicationContext(),"Test Invoke Native "+name  +'\t'+location,Toast.LENGTH_SHORT).show();
    }

}




