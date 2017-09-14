package com.lx.shell.utils.adapter.cell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.lx.rentcheck.R;
import com.lx.shell.utils.Utils;
import com.lx.shell.utils.adapter.base.RVBaseViewHolder;
import com.lx.shell.utils.adapter.base.RVSimpleAdapter;


/**
 * Created by zhouwei on 17/1/23.
 */

public class LoadMoreCell extends RVAbsStateCell{
    public static final int mDefaultHeight = 80;//dp
    public LoadMoreCell(Object o) {
        super(o);
    }

    @Override
    public int getItemType() {
        return RVSimpleAdapter.LOAD_MORE_TYPE;
    }


    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {

    }

    @Override
    protected View getDefaultView(Context context) {
        // 设置LoadMore View显示的默认高度
        setHeight(Utils.dpToPx(context,mDefaultHeight));
        return LayoutInflater.from(context).inflate(R.layout.rv_load_more_layout,null);
    }
}
