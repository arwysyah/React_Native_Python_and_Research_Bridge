package com.research.UI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import android.widget.Button;

import java.util.jar.Attributes;

@SuppressLint("AppCompatCustomView")
public class Switch extends Button {
    public Boolean isTurnedOn = false;

    public void setIsTurnedOn(Boolean switchStatus) {
        isTurnedOn = switchStatus;
        changeColor();
    }

    public Switch(Context context) {
        super(context);
        this.setTextColor(Color.RED);
        this.setText("This is Button Native");
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isTurnedOn = !isTurnedOn;
                changeColor();
                animatedView();
            }
        });
        changeColor();
    }

    public Switch(Context context, Boolean isTurnedOn) {
        super(context);
        this.isTurnedOn = isTurnedOn;
    }

    public  void runAnimation(Boolean isTurnedOn){
        int yAxis = isTurnedOn ? 500:-500;
        int transateY = yAxis;
        animate().translationYBy(yAxis).setDuration(200);
        setTranslationY(transateY);

    }
    private void animatedView() {
//    new TranslateAnimation(0, 100, 0, 100);
//    Animator trans = null;
//    trans.setDuration(250);
//    trans.setInterpolator(new AccelerateInterpolator(1.0f));

        runAnimation(isTurnedOn);
//        if (isTurnedOn) {
//            runAnimation(isTurnedOn);
//
//        }else{
//            runAnimation(isTurnedOn);
//        }
    }


    private void changeColor() {
        String nativeText = "Button Native UI Animation";
        if (isTurnedOn) {
            setBackgroundColor(Color.GREEN);
            setText("I am GREEN "+nativeText);
            setTextSize(12);
//            AnimationSet set = new AnimationSet( true );
//            Animation translate = new TranslateAnimation( -100, 0, 0, 0);
//            translate.setDuration( 900 );
//            set.addAnimation( translate );

        } else {
            setBackgroundColor(Color.BLUE);
            setText("I am BLUE "+ nativeText);
            setTextSize(12);
        }
    }

    public Switch(Context context, Attributes attrs) {
        super(context, (AttributeSet) attrs);
    }

    public Switch(Context context, Attributes attrs, int style) {
        super(context, (AttributeSet) attrs, style);
    }


}