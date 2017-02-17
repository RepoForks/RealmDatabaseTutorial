package com.okason.prontoquotes.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by vokafor on 1/15/2017.
 */

public class FontAwesome extends TextView {
    public FontAwesome(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public FontAwesome(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontAwesome(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fontawesome-webfont.ttf");
        setTypeface(tf);
    }
}
