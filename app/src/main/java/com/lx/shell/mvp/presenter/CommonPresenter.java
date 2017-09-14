package com.lx.shell.mvp.presenter;

import java.util.Map;

/**
 * @auther lixu
 * Created by lixu on 2017/08/12 0009.
 */
public interface CommonPresenter {
    void requestM(String type, Map<String,String> paramsList);
    void cancleRequest(String type);
}
