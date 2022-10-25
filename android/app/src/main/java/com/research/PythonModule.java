package com.research;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.Timestamp;


public class PythonModule extends ReactContextBaseJavaModule {
    Context context;
    String TAG = "Python Running";
    Python py = Python.getInstance();
    PyObject pyobj = py.getModule("script");

    PythonModule(ReactApplicationContext context) {
        super(context);
        this.context = context.getApplicationContext();


    }


    @NonNull
    @Override

    public String getName() {
        return "PythonModule";
    }


    @ReactMethod
    public void returnFromPython (String params, String params2, Promise promise) {
        PyObject obj = pyobj.callAttr("main", "12", "14");
        String result = obj.toString();
//        Log.d(TAG, "returnFromPython: "+params+ params2);
        try {
             Toast.makeText(getReactApplicationContext(),"passing and returning data from Python.. " + result,Toast.LENGTH_SHORT).show();
            promise.resolve(result);
        } catch(Exception e) {
            promise.reject("Error ", e);
        }
    }
    @ReactMethod
    public void passingToPython (String params, String params2, Promise promise) {
        PyObject obj = pyobj.callAttr("main", params, params2);
        String result = obj.toString();
        try {
              Toast.makeText(getReactApplicationContext(),"passing only " + result,Toast.LENGTH_SHORT).show();
            promise.resolve(result);
        } catch(Exception e) {
            promise.reject("Create Function  Error", e);
        }
    }
    @ReactMethod
    public void InvokePython() {
  
        

        PyObject obj = pyobj.callAttr("main", "120", "14");
        // Log.d(TAG, "InvokePython" + obj);
        // Log.d(TAG, "InvokePython: ");
         Toast.makeText(getReactApplicationContext(),"Invoke Python ",Toast.LENGTH_SHORT).show();
        // WritableMap data = Arguments.createMap();
    }


   public String storageImage(Bitmap bitmap, String name) {
       File sd = Environment.getExternalStorageDirectory();
       String filesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()+ "/Camera/";
        File imageFile = new File(filesDir, name+ ".jpg");
        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
            Log.d(TAG, "persistImage: "+filesDir+ "keep spirit");
        } catch (Exception e) {
            Log.d("hello", "Error due to i havent sleep", e);
        }
        return imageFile.toString();
    }
    public String convertImageFromBitmap(Bitmap bitmap) {
        String parse = "";
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
       try {
           bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
           String path = MediaStore.Images.Media.insertImage(getReactApplicationContext().getContentResolver(), bitmap, "Title", null);
           Log.d(TAG, "convertImageFromBitmap: "+ Uri.parse(path));
           parse = Uri.parse(path).toString();
           Toast.makeText(getReactApplicationContext(),"Check Your file  " + parse + Uri.parse((path)),Toast.LENGTH_SHORT).show();
           return parse;
       }catch (Exception err){
           Toast.makeText(getReactApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();

       }
       return parse ;
    }
//    @ReactMethod
//    public void convertImageFromBitmap(String bitmap , Promise promise) {
//        String parse = "";
//        Log.d(TAG, "convertImageFromBitmap: "+bitmap);
//        byte [] encodeByte = Base64.decode(bitmap,Base64.DEFAULT);
//        Log.d(TAG, "convertImageFromBitmap 1: "+encodeByte);
//
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//
//        try {
//            Bitmap bitmaps = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//            bitmaps.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//            String path = MediaStore.Images.Media.insertImage(getReactApplicationContext().getContentResolver(), bitmaps, "Title", null);
//            Log.d(TAG, "convertImageFromBitmap: "+ Uri.parse(path));
//            parse = Uri.parse(path).toString();
//            Toast.makeText(getReactApplicationContext(),"Success  " + parse + Uri.parse((path)),Toast.LENGTH_SHORT).show();
//            promise.resolve(Uri.parse(path).toString());
//        }catch (Exception err){
//            Toast.makeText(getReactApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
//            promise.reject("error uri",err);
//        }
//
//    }
    @SuppressLint("LongLogTag")
    public String returnFile (Bitmap bitmap, String name) {
        File sd = Environment.getExternalStorageDirectory();
        String filesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()+ "/Camera/";
        File imageFile = new File(filesDir, name+ ".jpg");
        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();

            Log.d(TAG, "persistImage: "+filesDir+ "kosong");
               Toast.makeText(getReactApplicationContext(),"Check Your file  " + filesDir,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("Error due to i havent sleep", "i need a sleep", e);
        }
        return  imageFile.toString();
    }
    @ReactMethod
    public void generateMathplotlib(Promise promise) {
        PyObject pyScript  = py.getModule("matplotlibScript");
        PyObject obj = pyScript.callAttr("main", "1,7,-3", "3,-4,5");
     //

        String str = obj.toString();

        byte data[] = android.util.Base64.decode(str, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(data,0,data.length); //convert to  bitmap
        //        SET IMAGE AFTER THAT
        Log.d(TAG, "generateMathplotlib: "+bmp);
        Toast.makeText(getReactApplicationContext(),"Processing... Matplotlib to be Bitmap " + bmp,Toast.LENGTH_SHORT).show();
        convertImageFromBitmap(bmp);
        storageImage(bmp,"math");
        WritableMap arr = Arguments.createMap();
//        arr.putString(bmp.toString());
        try {
            promise.resolve(bmp.toString());
        } catch(Exception e) {
            promise.reject("Error due to i havent sleep", e);
        }

    }




    @ReactMethod
    public void  plot3D (String script, Promise promise) {
        PyObject pyScripts  = py.getModule(script);
        PyObject obj = pyScripts.callAttr("main");
        String str = obj.toString();
        byte data[] = android.util.Base64.decode(str, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(data,0,data.length); //convert to  bitmap
       Timestamp timestamp = new Timestamp(System.currentTimeMillis());
         Toast.makeText(getReactApplicationContext(),"Processing... " + bmp,Toast.LENGTH_SHORT).show();
        String loc = returnFile(bmp,"math"+ timestamp.getTime());

        try {
            promise.resolve(loc);
        } catch(Exception e) {
            promise.reject("Error due to i havent sleep", e);
        }

    }

    





    @ReactMethod
    public void processMathplotlib (String x,String y, Promise promise) {
        PyObject pyScript  = py.getModule("matplotlibScript");
        PyObject obj = pyScript.callAttr("main", x, y);

        String str = obj.toString();

        byte data[] = android.util.Base64.decode(str, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(data,0,data.length); //convert to  bitmap
        //        SET IMAGE AFTER THAT
        Log.d(TAG, "generateMathplotlib: "+bmp);
         Timestamp timestamp = new Timestamp(System.currentTimeMillis());
         Toast.makeText(getReactApplicationContext(),"Processing...  " + bmp,Toast.LENGTH_SHORT).show();
        String loc = returnFile(bmp,"math"+ timestamp.getTime());
        
        
        try {
            promise.resolve(loc);
        } catch(Exception e) {
            promise.reject("Error due to i havent sleep", e);
        }

    }


}
