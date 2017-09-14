package com.lx.shell.mvp.presenter;


import com.lx.shell.base.BasePresenter;
import com.lx.shell.mvp.callback.OnLoadDataCallback;
import com.lx.shell.mvp.model.IdeaModel;
import com.lx.shell.mvp.model.IdeaModelImpl;
import com.lx.shell.mvp.view.IdeaView;

import java.util.List;
import java.util.Map;

/**
 * @auther lixu
 * Created by lixu on 2017/5/9 0009.
 */
public class IdeaPresenterImpl extends BasePresenter<IdeaView> implements IdeaPresenter{
    private IdeaModel ideaModel = new IdeaModelImpl();

    @Override
    public void loadIdeaList(Map<String, String> paramsList) {
        ideaModel.loadIdeaList(paramsList, new OnLoadDataCallback() {
            @Override
            public void loadDataList(List<?> ideaList) {
                getView().showIdeaList(ideaList);

            }

            @Override
            public void loadDataMoreList(List<?> ideaList) {
                getView().showIdeaMoreList(ideaList);

            }

            @Override
            public void loadDataListErro(String erroInfo) {
                getView().showIdeaMoreErro(erroInfo);

            }
        });


    }

    @Override
    public void cancleLoad() {
        ideaModel.cancleLoad();
    }
}
