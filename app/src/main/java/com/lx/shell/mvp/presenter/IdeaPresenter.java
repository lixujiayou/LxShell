package com.lx.shell.mvp.presenter;

import java.util.Map;

/**
 * @auther lixu
 * Created by lixu on 2017/5/9 0009.
 */
public interface IdeaPresenter {
    void loadIdeaList(Map<String,String> paramsList);
    void cancleLoad();
}
