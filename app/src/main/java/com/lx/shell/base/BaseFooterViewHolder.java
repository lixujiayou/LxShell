package com.lx.shell.base;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.lx.rentcheck.R;

/**
 * Created by lixu on 2017/8/8.
 */

public class BaseFooterViewHolder extends BaseViewHolder{
    TextView tv_state;

    public BaseFooterViewHolder(View itemView, Context context) {
        super(itemView, context);
        tv_state = (TextView) itemView.findViewById(R.id.tv_more);
    }



}
