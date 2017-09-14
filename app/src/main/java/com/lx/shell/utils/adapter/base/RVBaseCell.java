package com.lx.shell.utils.adapter.base;

/**
 * Created by lixu on 2017/8/15.
 */

public abstract class RVBaseCell<T> implements Cell{
    public T mData;
    public RVBaseCell(T t){
        mData = t;
    }

    @Override
    public void releaseResource() {

    }
}
