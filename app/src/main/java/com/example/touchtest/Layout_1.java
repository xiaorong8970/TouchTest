package com.example.touchtest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by 肖荣 on 2016/9/27.
 */

public class Layout_1 extends FrameLayout {
    public Layout_1(Context context) {
        super(context);
    }

    public Layout_1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Layout_1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("自定义标签",   "方法名==dispatchTouchEvent: " );
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("自定义标签",  "方法名==onInterceptTouchEvent: ");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("自定义标签", "方法名==onTouchEvent: " );
        return super.onTouchEvent(event);
    }
}
