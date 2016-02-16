package com.xinpinget.android_jenkins.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by cc on 2/16/16.
 */
public class LabelView extends TextView {

    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setStatusColor(String color) {
        setText(color + "iii");
    }
}
