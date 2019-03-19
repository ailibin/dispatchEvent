package com.aiitec.dispatcheventdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @Author: ailibin
 * @Time: 2019/02/26
 * @Description: 子类消费事件
 * @Email: ailibin@qq.com
 */
public class CustomerButton extends android.support.v7.widget.AppCompatButton {

    private static final String TAG = "ailibin";

    /**
     * return super to reflect to 0,return false to reflect to 1,return true to reflect to 2
     */
    private int dispatchParam, onTouchEventParam, onTouchParam;

    public CustomerButton(Context context) {
        super(context);
    }

    public CustomerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG, this.getClass().getSimpleName() + "---dispatchTouchEvent");
        switch (dispatchParam) {
            case 1:
                return false;
            case 2:
                return true;
            default:
                return super.dispatchTouchEvent(event);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, this.getClass().getSimpleName() + "---onTouchEvent");
        switch (onTouchEventParam) {
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

    public void setOnTouchEventParam(int param) {
        onTouchEventParam = param;
        Log.e(TAG, this.getClass().getSimpleName() + "---onTouchEventParam: " + onTouchEventParam);
    }

    public void setOnTouchParam(int param) {
        onTouchParam = param;
        Log.e(TAG, this.getClass().getSimpleName() + "---onTouchParam: " + onTouchParam);
    }


}
