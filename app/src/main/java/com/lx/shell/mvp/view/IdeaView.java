package com.lx.shell.mvp.view;


import java.util.List;

/**
 * @auther lixu
 * Created by lixu on 2017/5/9 0009.
 * idea list view
 */
public interface IdeaView {
    void showIdeaList(List<?> ideaList);
    void showIdeaMoreList(List<?> ideaList);
    void showIdeaMoreErro(String erroInfo);
}
