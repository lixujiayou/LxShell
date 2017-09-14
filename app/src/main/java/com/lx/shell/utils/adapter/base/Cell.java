package com.lx.shell.utils.adapter.base;

import android.view.ViewGroup;

/**
 * Created by lixu on 2017/8/15.
 */

public interface Cell {
    /**
     * 回收资源
     */
    public void releaseResource();

    /**
     * 获取ViewType
     * @return
     */
    public int getItemType();

    /**
     * 创建ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    public RVBaseViewHolder onCreatViewHolder(ViewGroup parent,int viewType);


    /**
     * 数据绑定
     */
    public void onBindViewHolder(RVBaseViewHolder holder,int position);

}
