package com.lx.shell.mvp.model;


import com.lx.shell.mvp.callback.OnCommonCallback;

import java.util.Map;

/**
 * @auther lixu
 * Created by lixu on 2017/5/9 0009.
 */
public interface CommonModel {
    void request(String type, Map<String,String> paramList, OnCommonCallback onCommonCallback);
    void cancleRequest(String type);
}
