package com.lx.shell.adapter.cell;

import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.lx.rentcheck.R;
import com.lx.shell.mvp.model.toolsbean.DetailEntry;
import com.lx.shell.utils.adapter.base.RVBaseCell;
import com.lx.shell.utils.adapter.base.RVBaseViewHolder;

import java.util.Random;

/**
 * Created by zhouwei on 17/2/4.
 */

public class DetailCell extends RVBaseCell<DetailEntry> {
    public DetailCell(DetailEntry detailEntry) {
        super(detailEntry);
    }

    @Override
    public int getItemType() {
        return 0;
    }

    @Override
    public RVBaseViewHolder onCreatViewHolder(ViewGroup parent, int viewType) {
        return new RVBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item_layout,null));
    }



    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {
    //    Picasso.with(holder.getItemView().getContext()).load(mData.imageUrl).into(holder.getImageView(R.id.grid_image));
     //   holder.setText(R.id.grid_title,mData.title);
    }

    private int  generateHeight(){
       return new Random().nextInt(100);
    }
}
