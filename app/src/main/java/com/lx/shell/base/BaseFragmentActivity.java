package com.lx.shell.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lx.shell.mvp.model.toolsbean.NetWorkEvent;
import com.lx.shell.utils.NetWorkUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


/**
 * Created by Administrator on 2016/12/8 0008.
 */

public abstract class BaseFragmentActivity extends AppCompatActivity {
    private BroadcastReceiver netStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(savedInstanceState);
        EventBus.getDefault().register(this);
        initNetWork();
        initViews();
        initData();

    }

    // 初始化UI，setContentView
    protected abstract void initContentView(Bundle savedInstanceState);
    public abstract void initViews();
    public abstract void initData();




    public void initNetWork(){
        netStateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(
                        ConnectivityManager.CONNECTIVITY_ACTION)) {
                    if (NetWorkUtil.isNetWorkConnected(BaseFragmentActivity.this)) {
                        EventBus.getDefault().post(new NetWorkEvent(NetWorkEvent.AVAILABLE));
                    } else {
                        EventBus.getDefault().post(new NetWorkEvent(NetWorkEvent.UNAVAILABLE));
                    }
                }
            }
        };

        registerReceiver(netStateReceiver, new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Subscribe
    public void onEvent(NetWorkEvent event) {
        if (event.getType() == NetWorkEvent.UNAVAILABLE) {
          //  ToastUtil3.showToast(BaseFragmentActivity.this,R.string.no_network);
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netStateReceiver);
    }


}