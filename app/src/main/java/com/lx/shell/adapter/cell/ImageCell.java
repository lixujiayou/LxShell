package com.lx.shell.adapter.cell;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lx.rentcheck.R;
import com.lx.shell.mvp.model.toolsbean.Entry;
import com.lx.shell.utils.adapter.base.RVBaseCell;
import com.lx.shell.utils.adapter.base.RVBaseViewHolder;


/**
 * Created by zhouwei on 17/1/19.
 */

public class ImageCell extends RVBaseCell<Entry> {
    public static final int TYPE = 1;
    public ImageCell(Entry entry) {
        super(entry);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public RVBaseViewHolder onCreatViewHolder(ViewGroup parent, int viewType) {
        return new RVBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_cell_layout,null));
    }



    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {
      //  Picasso.with(holder.getItemView().getContext()).load(mData.imageUrl).into(holder.getImageView(R.id.image));
    }

}
