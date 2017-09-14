package com.lx.shell.mvp.callback;


import java.util.List;

/**
 * @auther lixu
 * Created by lixu on 2017/5/9 0009.
 * query idea callback
 */
public interface OnLoadDataCallback {
    void loadDataList(List<?> ideaList);
    void loadDataMoreList(List<?> ideaList);
    void loadDataListErro(String erroInfo);
}
