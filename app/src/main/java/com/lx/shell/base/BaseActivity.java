package com.lx.shell.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import com.lx.shell.ui.MainActivity;
import com.lx.rentcheck.R;
import com.lx.shell.core.MallRequest;
import com.lx.shell.mvp.model.toolsbean.NetWorkEvent;
import com.lx.shell.utils.NetWorkUtil;
import com.lx.shell.utils.converter.ServiceGenerator;
import com.lx.shell.utils.dao.DaoUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


/**
 * @auther lixu
 * Created by lixu on 2017/5/3 0003.
 */
public abstract class BaseActivity<V,T extends BasePresenter<V>> extends AppCompatActivity implements View.OnClickListener{
    public T mPresenter;
    private BroadcastReceiver netStateReceiver;
    public MallRequest userClient;
    public Toolbar mToolbar;
    private static List<Activity> activities = new ArrayList<>();
    private ProgressDialog bar;
   // public DaoSession mDaoSession;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        if (!(this instanceof MainActivity)) {
            activities.add(this);
        }
        DaoUtils.init(this);

        try {
            userClient = ServiceGenerator.createService(MallRequest.class);
        } catch (Exception e) {
            Log.d("qqqqqqqq", "ServiceGenerator" + e.toString());
        }
        mPresenter = creatPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
       // mToolbar.setNavigationIcon(R.drawable.icon_cancle);
        initNetWork();
        initView();

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        initData();
    }

    public void initNetWork(){
        netStateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(
                        ConnectivityManager.CONNECTIVITY_ACTION)) {
                    if (NetWorkUtil.isNetWorkConnected(BaseActivity.this)) {
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
    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    /**
     * 跳转页面
     *
     * @param clz         所跳转的Activity类
     * @param requestCode 请求码
     */
    public void startActivityForResult(Class<?> clz, int requestCode) {
        startActivityForResult(new Intent(this, clz), requestCode);
    }
    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        startActivity(intent);
    }

    /**
     * 跳转页面
     *
     * @param clz         所跳转的Activity类
     * @param bundle      跳转所携带的信息
     * @param requestCode 请求码
     */
    public void startActivityForResult(Class<?> clz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    /**
     * 关闭所有Activity（除MainActivity以外）
     */
    public void finishActivity() {
        for (Activity activity : activities) {
            activity.finish();
        }
    }

    /**
     * 跳转到指定的Activity
     *
     * @param clz 指定的Activity对应的class
     */
    public void goTo(Class<?> clz) {
        if (clz.equals(MainActivity.class)) {
            finishActivity();
        } else {
            for (int i = activities.size() - 1; i >= 0; i--) {
                if (clz.equals(activities.get(i).getClass())) {
                    break;
                } else {
                    activities.get(i).finish();
                }
            }
        }
    }


    //show ProgressDialog
    public void showProgress(String msg) {

        if(bar == null) {

            bar=new ProgressDialog(this);

            bar.setMessage(msg);

            bar.setIndeterminate(true);

            bar.setCancelable(true);

        }

        if(bar.isShowing()) {

            bar.setMessage(msg);

        }

        bar.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                cancleDialog();
            }
        });

        bar.show();

    }




    public void dismissDialog() {

        if(bar!=null && bar.isShowing()) {
            bar.dismiss();
        }

    }





    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null) {
            mPresenter.dettach();
        }
        activities.remove(this);
        unregisterReceiver(netStateReceiver);
    }

    protected abstract T creatPresenter();
    protected abstract int getLayout();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void cancleDialog();
}
