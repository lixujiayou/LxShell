package com.lx.shell.mvp.model;

import com.lx.shell.base.BasePresenter;
import com.lx.shell.core.Constance;
import com.lx.shell.core.MallRequest;
import com.lx.shell.mvp.callback.OnCommonCallback;
import com.lx.shell.mvp.model.toolsbean.CallBackBean;
import com.lx.shell.mvp.model.toolsbean.EventTool;
import com.lx.shell.mvp.view.CommonView;
import com.lx.shell.utils.converter.ServiceGenerator;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lixu on 2017/8/12.
 */

public class CommonModelImpl extends BasePresenter<CommonView> implements CommonModel{

    private MallRequest userClient;
    private Call<CallBackBean> loginCall;


    public CommonModelImpl() {
        userClient = ServiceGenerator.createService(MallRequest.class);
    }

    public static CommonModelImpl getInstance() {
        return SingletonHolder.instance;
    }
    private static class SingletonHolder {
        public static final CommonModelImpl instance = new CommonModelImpl();
    }

    @Override
    public void request(String type, Map<String, String> paramList, final OnCommonCallback onCommonCallback) {
        //登录
        if(type.equals(Constance.LOGIN_MVP)){


            String uName = paramList.get(Constance.LOGIN_NAME_MVP);
            String uPwd = paramList.get(Constance.LOGIN_PWD_MVP);
            loginCall = userClient.login(uName,uPwd);
            loginCall.enqueue(new Callback<CallBackBean>() {
                @Override
                public void onResponse(Call<CallBackBean> call, Response<CallBackBean> response) {
                    int tCode = response.raw().code();
                    if(tCode == 200){
                        CallBackBean callBean = response.body();
                        if(callBean != null) {
                            EventTool eventTool = new EventTool();
                            eventTool.setResult(true);
                            eventTool.setMessage(callBean.getUID());
                            EventBus.getDefault().post(eventTool);
                            eventTool = null;
                            onCommonCallback.requestSuccess(callBean.getUID());
                        }else{
                            onCommonCallback.requestErro("服务器异常");
                        }

                    }else{
                        onCommonCallback.requestErro(Constance.getMsgByCode(tCode));
                    }
                }

                @Override
                public void onFailure(Call<CallBackBean> call, Throwable t) {
                    onCommonCallback.requestErro(Constance.getMsgByException(t));
                }
            });
        }
    }

    @Override
    public void cancleRequest(String type) {
        if(type.equals(Constance.LOGIN_MVP)){
            if(loginCall != null){
                if(!loginCall.isCanceled()){
                    loginCall.cancel();
                }
            }
        }else{

        }
    }
}
