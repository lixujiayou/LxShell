package com.lx.shell.utils.adapter.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.lx.shell.utils.ToolUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixu on 2017/8/15.
 */

public abstract class RVBaseAdapter<C extends RVBaseCell> extends RecyclerView.Adapter<RVBaseViewHolder> {

    protected List<C> mData;

    public RVBaseAdapter(){
        mData = new ArrayList<>();
    }

    public void setData(List<C> data){
        addAll(mData);
        notifyDataSetChanged();
    }


    public List<C> getData(){
        return mData;
    }


    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        for(int i = 0;i < getItemCount();i++){
            if(viewType == mData.get(i).getItemType()){
                return mData.get(i).onCreatViewHolder(parent,viewType);
            }
        }
        throw new RuntimeException("wrong viewType");
    }

    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {
        mData.get(position).onBindViewHolder(holder,position);
    }


    @Override
    public void onViewDetachedFromWindow(RVBaseViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

        int position = holder.getAdapterPosition();
        //越界检查
        if(position < 0 || position >= mData.size()){
            return;
        }
        //释放资源
        mData.get(position).releaseResource();

    }

    @Override
    public int getItemCount() {
        return mData == null?0:mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getItemType();
    }


    public void add(C cell){
        mData.add(cell);
        int index = mData.indexOf(cell);
        notifyItemChanged(index);
    }

    public void add(int index,C cell){
        mData.add(index,cell);
        notifyItemChanged(index);
    }

    public void remove(C cell){
        int index = mData.indexOf(cell);
        remove(cell);
        notifyItemChanged(index);
    }

    public void remove(int index){
        mData.remove(index);
        notifyItemChanged(index);
    }

    public void remove(int start,int count){
        if(start + count >mData.size()){
            return;
        }
        int size = getItemCount();
        for(int i = start;i < size;i++){
            mData.remove(i);
        }
        notifyItemRangeChanged(start,count);
    }



    public void addAll(List<C> cells){
        if(ToolUtil.isEmpty(cells)){
            return;
        }
        mData.addAll(cells);
        notifyItemRangeChanged(mData.size() - cells.size(),mData.size());
    }

    public void addAll(int index,List<C>cells){
        if(ToolUtil.isEmpty(cells)){
            return;
        }
        mData.addAll(index,cells);
        notifyItemRangeChanged(index,index+cells.size());
    }

    public void clear(){
        mData.clear();
        notifyDataSetChanged();
    }

    /**
     * 如果子类需要在onBindViewHolder 回调的时候做的操作可以在这个方法里做
     * @param holder
     * @param position
     */
    protected abstract void onViewHolderBound(RVBaseViewHolder holder, int position);



}
