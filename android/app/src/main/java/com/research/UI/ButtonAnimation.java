package com.research.UI;

import android.widget.Button;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;

import java.util.jar.Attributes;

@SuppressLint("AppCompatCustomView")
public class ButtonAnimation extends Button {
    public Boolean isOn = false;
    public String title = "";

    public void setIsOn(Boolean initialStatus) {
        isOn = initialStatus;
    }

    public void setIsText(String initialStatus) {
        title = initialStatus;
    }

    public ButtonAnimation(Context context) {
        super(context);
        setBackgroundColor(Color.GREEN);
        setText(title);
        setTextColor(Color.WHITE);
        if (isOn) {
            setBackgroundColor(Color.YELLOW);
        } else {
            setBackgroundColor(Color.BLUE);
        }
    }

    public ButtonAnimation(Context context, Attributes attrs) {
        super(context, (AttributeSet) attrs);
    }

    public ButtonAnimation(Context context, Attributes attrs, int style) {
        super(context, (AttributeSet) attrs, style);
    }
}