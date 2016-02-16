package com.xinpinget.android_jenkins.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.xinpinget.android_jenkins.R;

/**
 * Created by cc on 2/16/16.
 */
public class LabelView extends TextView {

    enum State {
        Normal(R.drawable.bg_normal_state, R.string.normal_state),
        Error(R.drawable.bg_error_state, R.string.error_state);

        private final int mBg;
        private final int mState;

        State(int bg, int state) {
            mBg = bg;
            mState = state;
        }
    }

    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setStatusColor(String color) {
        State state = toState(color);
        setBackgroundResource(state.mBg);
        setText(state.mState);
    }

    private State toState(String color) {
        if (color.equals("blue"))
            return State.Normal;
        else
            return State.Error;
    }
}
