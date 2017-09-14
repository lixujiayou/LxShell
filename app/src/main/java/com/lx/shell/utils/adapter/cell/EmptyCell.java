package com.lx.shell.utils.adapter.cell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.lx.rentcheck.R;
import com.lx.shell.utils.adapter.base.RVBaseViewHolder;
import com.lx.shell.utils.adapter.base.RVSimpleAdapter;

/**
 * Created by lixu on 2017/8/15.
 */

public class EmptyCell extends RVAbsStateCell{


    public EmptyCell(Object o) {
        super(o);
    }

    @Override
    public int getItemType() {
        return RVSimpleAdapter.EMPTY_TYPE;
    }

    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {

    }

    @Override
    protected View getDefaultView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.rv_empty_layout,null);
    }
}
