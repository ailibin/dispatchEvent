package com.aiitec.dispatcheventdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import com.aiitec.dispatcheventdemo.widget.CustomerButton;
import com.aiitec.dispatcheventdemo.widget.LinearLayoutDispatchView;
import com.aiitec.dispatcheventdemo.widget.RelativeLayoutDispatchView;

/**
 * @Author: ailibin
 * @Time: 2019/02/26
 * @Description: 事件分发demo 事件传递 Activity->ViewGroup->View  事件消费从下往上 View->ViewGroup->Activity
 * @Email: ailibin@qq.com
 */
public class EventDispatchActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private TextView
            tv_rlt_dispatch, tv_rlt_onIntercept, tv_rlt_onTouchEvent,
            tv_ll_dispatch, tv_ll_onIntercept, tv_ll_onTouchEvent,
            tv_btn_dispatch, tv_btn_onTouchEvent, tv_activity_dispatch,
            tv_activity_onTouchEvent;
    private RelativeLayoutDispatchView rlt_dispatchView;
    private LinearLayoutDispatchView ll_dispatchView;
    private CustomerButton bt_dispatchView;

    private ListPopupWindow listPopupWindow;
    private String[] products = {"super", "false", "true"};
    private int selectType;

    private static final String TAG = "ailibin";
    private int dispatchParam = 0;
    private int onTouchParam = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_dispatch);
        findView();
        initListPopWindow();
        setViewOnclick();

    }

    private void initListPopWindow() {

        listPopupWindow = new ListPopupWindow(
                EventDispatchActivity.this);
        listPopupWindow.setAdapter(new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                products));
        listPopupWindow.setWidth(400);
        listPopupWindow.setHeight(400);
        listPopupWindow.setModal(true);
        listPopupWindow.setOnItemClickListener(
                EventDispatchActivity.this);


    }

    private void findView() {

//        rlt_dispatchView = findViewById(R.id.rlt_dispatchView);
        ll_dispatchView = findViewById(R.id.ll_dispatchView);
        bt_dispatchView = findViewById(R.id.bt_dispatchView);

        tv_rlt_dispatch = findViewById(R.id.tv_rlt_dispatch);
        tv_rlt_onIntercept = findViewById(R.id.tv_rlt_onIntercept);
        tv_rlt_onTouchEvent = findViewById(R.id.tv_rlt_onTouchEvent);
        tv_ll_dispatch = findViewById(R.id.tv_ll_dispatch);
        tv_ll_onIntercept = findViewById(R.id.tv_ll_onIntercept);
        tv_ll_onTouchEvent = findViewById(R.id.tv_ll_onTouchEvent);
        tv_btn_dispatch = findViewById(R.id.tv_btn_dispatch);
        tv_btn_onTouchEvent = findViewById(R.id.tv_btn_onTouchEvent);

        tv_activity_dispatch = findViewById(R.id.tv_activity_dispatch);
        tv_activity_onTouchEvent = findViewById(R.id.tv_activity_onTouchEvent);

    }

    private void setViewOnclick() {

        tv_rlt_dispatch.setOnClickListener(this);
        tv_rlt_onIntercept.setOnClickListener(this);
        tv_rlt_onTouchEvent.setOnClickListener(this);
        tv_ll_dispatch.setOnClickListener(this);
        tv_ll_onIntercept.setOnClickListener(this);
        tv_ll_onTouchEvent.setOnClickListener(this);
        tv_btn_dispatch.setOnClickListener(this);
        tv_btn_onTouchEvent.setOnClickListener(this);

        tv_activity_dispatch.setOnClickListener(this);
        tv_activity_onTouchEvent.setOnClickListener(this);

    }

    /**
     * activity中只有两种方法dispatchTouchEvent、onTouchEvent
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


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            case R.id.tv_rlt_dispatch:
//                selectType = 0;
//                showPopList(tv_rlt_dispatch);
//                break;
//            case R.id.tv_rlt_onIntercept:
//                selectType = 1;
//                showPopList(tv_rlt_onIntercept);
//                break;
//            case R.id.tv_rlt_onTouchEvent:
//                selectType = 2;
//                showPopList(tv_rlt_onTouchEvent);
//                break;

//            case R.id.tv_activity_dispatch:
//                selectType = 1;
//                showPopList(tv_activity_dispatch);
//                break;
//            case R.id.tv_activity_onTouchEvent:
//                selectType = 2;
//                showPopList(tv_activity_onTouchEvent);
//                break;
            case R.id.tv_ll_dispatch:
                selectType = 3;
                showPopList(tv_ll_dispatch);
                break;
            case R.id.tv_ll_onIntercept:
                showPopList(tv_ll_onIntercept);
                selectType = 4;
                break;
            case R.id.tv_ll_onTouchEvent:
                showPopList(tv_ll_onTouchEvent);
                selectType = 5;
                break;
            case R.id.tv_btn_dispatch:
                showPopList(tv_btn_dispatch);
                selectType = 6;
                break;
            case R.id.tv_btn_onTouchEvent:
                showPopList(tv_btn_onTouchEvent);
                selectType = 7;
                break;
            default:
                break;
        }

    }

    private void showPopList(View anchorView) {
        listPopupWindow.setAnchorView(anchorView);
        listPopupWindow.show();
    }

    public void setDispatchTouchEventParam(int param) {
        dispatchParam = param;
        Log.e(TAG, this.getClass().getSimpleName() + "---dispatchParam: " + dispatchParam);
    }

    public void setOnTouchEventParam(int param) {
        onTouchParam = param;
        Log.e(TAG, this.getClass().getSimpleName() + "---onTouchParam: " + onTouchParam);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (selectType) {
//            case 0:
//                tv_rlt_dispatch.setText("dispatch:(" + products[i] + ")");
//                rlt_dispatchView.setDispatchTouchEventParam(i);
//                break;
//            case 1:
//                tv_rlt_onIntercept.setText("onInter:(" + products[i] + ")");
//                rlt_dispatchView.setOnInterceptTouchEventParam(i);
//                break;
//            case 2:
//                tv_rlt_onTouchEvent.setText("onTouchE:(" + products[i] + ")");
//                rlt_dispatchView.setOnTouchEventParam(i);
//                break;

//            case 1:
//                tv_activity_dispatch.setText("dispatch:(" + products[i] + ")");
//                setDispatchTouchEventParam(i);
//                break;
//            case 2:
//                tv_activity_onTouchEvent.setText("onTouchE:(" + products[i] + ")");
//                setOnTouchEventParam(i);
//                break;
            case 3:
                tv_ll_dispatch.setText("dispatch:(" + products[i] + ")");
                ll_dispatchView.setDispatchTouchEventParam(i);
                break;
            case 4:
                tv_ll_onIntercept.setText("onInter:(" + products[i] + ")");
                ll_dispatchView.setOnInterceptTouchEventParam(i);
                break;
            case 5:
                tv_ll_onTouchEvent.setText("onTouchE:(" + products[i] + ")");
                ll_dispatchView.setOnTouchEventParam(i);
                break;
            case 6:
                tv_btn_dispatch.setText("dispatch:(" + products[i] + ")");
                bt_dispatchView.setDispatchTouchEventParam(i);
                break;
            case 7:
                tv_btn_onTouchEvent.setText("onTouchE:(" + products[i] + ")");
                bt_dispatchView.setOnTouchEventParam(i);
                break;
            default:
                break;
        }
        listPopupWindow.dismiss();
    }
}
