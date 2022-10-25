package com.research.UI;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class SwitchManager extends SimpleViewManager<Switch> {


    @NonNull
    @Override
    public String getName() {
        return "SwitchButton";
    }

    @NonNull
    @Override
    protected Switch createViewInstance(ThemedReactContext reactContext) {
        return new Switch(reactContext);
    }

    //add prop from Javascript
    @ReactProp(name = "IsTurnedOn")
    public void setSwitchStatus(Switch switchView, Boolean isTurnedOn) {
        switchView.setIsTurnedOn(isTurnedOn);
    }
}