package com.aiitec.dispatcheventdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * @Author: ailibin
 * @Time: 2019/02/26
 * @Description: 事件分发的父类控件, 最外层控件
 * @Email: ailibin@qq.com
 */
public class RelativeLayoutDispatchView extends RelativeLayout {


    private static final String TAG = "ailibin";
    /**
     * return super to reflect to 0,return false to reflect to 1,return true to reflect to 2
     */
    private int dispatchParam, onInterceptParam, onTouchParam;

    public RelativeLayoutDispatchView(Context context) {
        this(context, null);
    }

    public RelativeLayoutDispatchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RelativeLayoutDispatchView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public RelativeLayoutDispatchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, this.getClass().getSimpleName() + "---dispatchTouchEvent");
        switch (dispatchParam) {
            case 1:
                return false;
            case 2:
                return true;
            default:
                return super.dispatchTouchEvent(ev);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, this.getClass().getSimpleName() + "---onInterceptTouchEvent");
        switch (onInterceptParam) {
            case 1:
                return false;
            case 2:
                return true;
            default:
                return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, this.getClass().getSimpleName() + "---onTouchEvent");
        switch (onTouchParam) {
            case 1:
                return false;
            case 2:
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    public void setDispatchTouchEventParam(int param) {
        dispatchParam = param;
        Log.e(TAG, this.getClass().getSimpleName() + "---dispatchParam: " + dispatchParam);
    }

    public void setOnInterceptTouchEventParam(int param) {
        onInterceptParam = param;
        Log.e(TAG, this.getClass().getSimpleName() + "---onInterceptParam: " + onInterceptParam);
    }

    public void setOnTouchEventParam(int param) {
        onTouchParam = param;
        Log.e(TAG, this.getClass().getSimpleName() + "---onTouchParam: " + onTouchParam);
    }
}
