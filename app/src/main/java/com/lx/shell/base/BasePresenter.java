package com.lx.shell.base;

import java.lang.ref.WeakReference;

/**
 * @auther lixu
 * Created by lixu on 2017/5/3 0003.
 */
public class BasePresenter<T> {

    //弱引用
    public WeakReference<T> weakReference;

    //关联
    public void attachView(T view){
        weakReference = new WeakReference<T>(view);
    }

    //解除关联
    public void dettach(){
        if(weakReference != null){
            weakReference.clear();
        }
    }

    public T getView(){
        return weakReference.get();
    }
}
