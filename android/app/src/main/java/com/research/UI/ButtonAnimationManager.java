package com.research.UI;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;

public class ButtonAnimationManager extends SimpleViewManager<ButtonAnimation> {

    @Override
    public String getName() {
        return "ButtonAnimation";
    }

//        @ReactPropGroup(names = {"isOn", "title"})
    @ReactProp(name = "title")
    public void setButtonStatus(ButtonAnimation btnTitle, String title) {
        btnTitle.setIsText(title);

    }

    @Override
    protected ButtonAnimation createViewInstance(ThemedReactContext reactContext) {
        return new ButtonAnimation(reactContext);
    }
}
