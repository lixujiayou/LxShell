package com.lx.shell.mvp.view;


/**
 * @auther lixu
 * Created by lixu on 2017/5/9 0009.
 * 通用view，只有成功和失败
 */
public interface CommonView {
    void commonSuccess(String successInfo);
    void CommonErro(String erroInfo);
}
