package com.lx.shell.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.lx.rentcheck.R;
import com.lx.shell.adapter.callback.OnMyItemClickListener;

import java.util.List;

/**
 * Created by lixu on 2017/8/8.
 */

public abstract class BaseRecyleViewAdapter extends RecyclerView.Adapter<BaseViewHolder>{
    public BaseFooterViewHolder mFootViewHolder;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private int mLayoutId;
    private Context mContext;
    private List<? extends BaseBean> mData;

    private  OnMyItemClickListener onMyItemClickListener;

    public BaseRecyleViewAdapter(Context context,List<? extends BaseBean> data,int layoutId){
        this.mContext = context;
        this.mData = data;
        this.mLayoutId = layoutId;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(mContext).inflate(mLayoutId, parent,
                    false);
            return new BaseViewHolder(view,mContext);
        }else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.widget_lv_footer, parent,
                    false);
            mFootViewHolder = new BaseFooterViewHolder(view,mContext);
            return mFootViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
        if(holder instanceof BaseFooterViewHolder){
            mFootViewHolder = (BaseFooterViewHolder) holder;
        }else{
            convert(holder,mData.get(position));
            if(onMyItemClickListener!=null){
                holder.convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onMyItemClickListener.onItemClickListener(holder.itemView, position);
                    }
                });

                holder.convertView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = holder.getLayoutPosition();
                        onMyItemClickListener.onItemLongClickListener(holder.itemView, position);
                        return true;
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() == 0 ? 0 : mData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    protected abstract <T extends BaseBean>void convert(BaseViewHolder holder, T bean);

    public void setItemClickListener(OnMyItemClickListener onMyItemClickListener){
        this.onMyItemClickListener = onMyItemClickListener;
    }

    /**
     * 设置底部加载状态
     * isLoading:true 正在加载中 false 到底部了
     */
    public void setLoadStatus(boolean isLoading){
        if(mFootViewHolder != null ) {
         //   LogUtils.d("mFootViewHolder == 正常 ");
            LogUtils.d("mFootViewHolder == 正常");
            if (isLoading) {
                mFootViewHolder.tv_state.setText(R.string.load_ing);
            } else {
                mFootViewHolder.tv_state.setText(R.string.load_over);
            }
        }else{
           LogUtils.d("mFootViewHolder == null");
        }
    }
}
