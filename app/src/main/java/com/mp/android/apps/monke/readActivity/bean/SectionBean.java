package com.mp.android.apps.monke.readActivity.bean;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.mp.android.apps.MyApplication;


/**
 * Created by newbiechen on 17-4-16.
 */

public class SectionBean {
    private String name;
    private int drawableId;

    public SectionBean(String name, @DrawableRes int drawableId){
        this.name = name;
        this.drawableId = drawableId;
    }

    public SectionBean(@StringRes int strRes, @DrawableRes int drawableId){
        this.name = MyApplication.getInstance()
                .getString(strRes);

        this.drawableId = drawableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }
}
