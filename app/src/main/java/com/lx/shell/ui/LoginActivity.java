package com.lx.shell.ui;

import android.view.View;
import android.widget.Button;

import com.apkfuns.logutils.LogUtils;
import com.lx.rentcheck.R;
import com.lx.shell.base.BaseActivity;
import com.lx.shell.core.Constance;
import com.lx.shell.mvp.model.toolsbean.EventTool;
import com.lx.shell.mvp.presenter.CommonPresenterImpl;
import com.lx.shell.mvp.view.CommonView;
import com.lx.shell.utils.SPUtil;
import com.lx.shell.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lixu on 2017/8/12.
 */
public class LoginActivity extends BaseActivity<CommonView,CommonPresenterImpl> implements CommonView{

    private Button btLogin;

    @Override
    protected CommonPresenterImpl creatPresenter() {
        return new CommonPresenterImpl();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        btLogin = (Button) findViewById(R.id.bt_login);
        btLogin.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void cancleDialog() {
        if(mPresenter != null) {
            mPresenter.cancleRequest(Constance.LOGIN_MVP);
        }
    }

    @Override
    public void onClick(View v) {
        Map<String,String> loginList = new HashMap<>();
        loginList.put("name","root");
        loginList.put("pwd","123456sS!");
        showProgress("正在登陆");
        mPresenter.requestM(Constance.LOGIN_MVP,loginList);

    }

    @Override
    public void commonSuccess(String successInfo) {
        dismissDialog();
        startActivity(MainActivity.class);
    }

    @Override
    public void CommonErro(String erroInfo) {
        dismissDialog();
        ToastUtil.showToast(LoginActivity.this, erroInfo, ToastUtil.TOAST_TYPE_WARNING);
    }

    @Subscribe
    public void onEvent(EventTool event) {
        if(event != null){
            SPUtil spUtil = new SPUtil(LoginActivity.this,SPUtil.USER);
            spUtil.putString(SPUtil.USER_UID,event.getMessage());
            LogUtils.d("保存UID成功"+event.getMessage());
        }
    }
}
