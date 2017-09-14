package com.lx.shell.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Administrator on 2016/12/8 0008.
 */

public abstract class BaseFragment<V,T extends BasePresenter<V>> extends Fragment {
    public T mPresenter;
    public View mView;
    public Context mContext;
    private int layoutID;
    public boolean isVisible; //Fragment显示隐藏状态
 //   public static boolean isFirst;//是否第一次加载数据
    public LayoutInflater mInflater;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = inflater;
        layoutID = initCreatView();
        mView = inflater.inflate(layoutID,container,false);

        mPresenter = creatPresenter();
        if(mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        mContext = getActivity();

        initViews();
        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            initData();
        } else {
            isVisible = false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dettach();
    }

    public abstract void initTheme();
    public abstract int initCreatView();
    public abstract void initViews();
    public abstract void initData();
    public abstract T creatPresenter();

}
