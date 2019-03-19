package com.aiitec.dispatcheventdemo.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * @Author: ailibin
 * @Time: 2019/02/26
 * @Description: 事件分发的父类控件
 * @Email: ailibin@qq.com
 */
public class LinearLayoutDispatchView extends LinearLayout {

    private static final String TAG = "ailibin";

    /**
     * return super to reflect to 0,return false to reflect to 1,return true to reflect to 2
     */
    private int dispatchParam, onInterceptParam, onTouchParam;

    public LinearLayoutDispatchView(Context context) {
        super(context);
    }

    public LinearLayoutDispatchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearLayoutDispatchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 事件分发
     *
     * @param ev
     * @return
     */
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

    /**
     * 是否拦截
     *
     * @param ev
     * @return
     */
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
