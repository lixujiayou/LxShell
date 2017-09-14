package com.lx.shell.adapter.cell;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.lx.rentcheck.R;
import com.lx.shell.mvp.model.bean.ResultList_right;
import com.lx.shell.utils.adapter.base.RVBaseCell;
import com.lx.shell.utils.adapter.base.RVBaseViewHolder;


/**
 * Created by zhouwei on 17/1/19.
 */

public class TextCell extends RVBaseCell<ResultList_right> {
    public static final int TYPE = 0;
    public TextCell(ResultList_right entry) {
        super(entry);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public RVBaseViewHolder onCreatViewHolder(ViewGroup parent, int viewType) {
        return new RVBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.text_cell_layout,null));
    }

    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {
        LogUtils.d("适配器："+mData.getIntId());
        holder.setText(R.id.text_content,mData.getIntId());
    }
}
