package com.lx.shell.mvp.presenter;

import com.lx.shell.base.BasePresenter;
import com.lx.shell.core.Constance;
import com.lx.shell.mvp.callback.OnCommonCallback;
import com.lx.shell.mvp.model.CommonModel;
import com.lx.shell.mvp.model.CommonModelImpl;
import com.lx.shell.mvp.view.CommonView;

import java.util.Map;

/**
 * Created by lixu on 2017/8/12.
 */

public class CommonPresenterImpl extends BasePresenter<CommonView> implements CommonPresenter{
    private CommonModel commonModel;

    @Override
    public void requestM(String type, Map<String, String> paramsList) {
        if(type.equals(Constance.LOGIN_MVP)){
            commonModel = CommonModelImpl.getInstance();
            commonModel.request(type, paramsList, new OnCommonCallback() {
                @Override
                public void requestSuccess(String successInfo) {
                    getView().commonSuccess(successInfo);
                }

                @Override
                public void requestErro(String erroInfo) {
                    getView().CommonErro(erroInfo);
                }
            });
        }else{}
    }

    @Override
    public void cancleRequest(String type) {
        if(type.equals(Constance.LOGIN_MVP)){
            if(commonModel != null){
                commonModel.cancleRequest(type);
            }
        }else{}
    }
}
