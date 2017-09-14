package com.lx.shell.mvp.model;


import com.lx.shell.mvp.callback.OnLoadDataCallback;

import java.util.Map;

/**
 * @auther lixu
 * Created by lixu on 2017/5/9 0009.
 */
public interface IdeaModel {
    void loadIdeaList(Map<String,String> paramsList,OnLoadDataCallback onLoadDataCallback);
    void cancleLoad();
}
