package com.lx.shell.adapter.cell;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lx.rentcheck.R;
import com.lx.shell.utils.adapter.base.RVBaseCell;
import com.lx.shell.utils.adapter.base.RVBaseViewHolder;


/**
 * Created by zhouwei on 17/2/15.
 */

public class HeaderCell extends RVBaseCell<Object> {
    private static final String URL = "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg";
    public HeaderCell(Object o) {
        super(o);
    }

    @Override
    public int getItemType() {
        return 0;
    }

    @Override
    public RVBaseViewHolder onCreatViewHolder(ViewGroup parent, int viewType) {
        return new RVBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.test_state_layout,null));
    }


    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {
      //  ImageView view = holder.getImageView(R.id.header_image);
     //   Picasso.with(holder.getItemView().getContext()).load(URL).into(view);
    }
}
