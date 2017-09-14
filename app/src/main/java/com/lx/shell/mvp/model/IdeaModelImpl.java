package com.lx.shell.mvp.model;


import com.lx.shell.core.Constance;
import com.lx.shell.core.MallRequest;
import com.lx.shell.mvp.callback.OnLoadDataCallback;
import com.lx.shell.mvp.model.bean.ResultList_right;
import com.lx.shell.mvp.model.bean.RightResult;
import com.lx.shell.utils.ToolUtil;
import com.lx.shell.utils.converter.ServiceGenerator;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @auther lixu
 * Created by lixu on 2017/5/9 0009.
 */
public class IdeaModelImpl implements IdeaModel{
    private MallRequest mClient;
    private Call<RightResult> mCall;


    public IdeaModelImpl() {
        mClient = ServiceGenerator.createService(MallRequest.class);
    }

    public static IdeaModelImpl getInstance() {
        return IdeaModelImpl.SingletonHolder.instance;
    }
    private static class SingletonHolder {
        public static final IdeaModelImpl instance = new IdeaModelImpl();
    }



    @Override
    public void loadIdeaList(Map<String,String>paramsList,final OnLoadDataCallback onLoadDataCallback) {

        String uid  = paramsList.get(Constance.List_UID_MVP);
        String page  = paramsList.get(Constance.List_PAGE_MVP);
        String pageSize  = paramsList.get(Constance.List_PAGESIZE_MVP);

        mCall = mClient.getBookList(uid,page,pageSize,"2002","1000157");
        mCall.enqueue(new Callback<RightResult>() {
            @Override
            public void onResponse(Call<RightResult> call, Response<RightResult> response) {
                int tCode = response.raw().code();
                if(tCode == 200){
                    RightResult callBean = response.body();
                    if(callBean != null) {
                        List<ResultList_right> resultList_rights = callBean.page.resultList;
                        if(!ToolUtil.isEmpty(resultList_rights)){
                            onLoadDataCallback.loadDataList(resultList_rights);
                        }else{
                            onLoadDataCallback.loadDataListErro("未请求到数据");
                        }
                    }else{
                        onLoadDataCallback.loadDataListErro("服务器异常");
                    }
                }else{
                    onLoadDataCallback.loadDataListErro(Constance.getMsgByCode(tCode));
                }
            }

            @Override
            public void onFailure(Call<RightResult> call, Throwable t) {
                onLoadDataCallback.loadDataListErro(Constance.getMsgByException(t));
            }
        });
    }

    @Override
    public void cancleLoad() {
        if(mCall != null){
            if(!mCall.isCanceled()){
                mCall.cancel();
            }
        }
    }
}
