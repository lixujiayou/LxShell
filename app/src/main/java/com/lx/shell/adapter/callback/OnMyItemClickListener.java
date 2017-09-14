package com.lx.shell.adapter.callback;

import android.view.View;

/**
 * Created by lixu on 2017/5/13.
 */

public interface OnMyItemClickListener {

    void onItemClickListener(View view, int position);
    void onItemLongClickListener(View view,int position);
    void onItemSubClickListener(View view, int position);
}
