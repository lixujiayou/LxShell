package com.lx.shell.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lx.rentcheck.R;
import com.lx.shell.mvp.model.bean.ResultList_right;

import java.util.List;

/**
 * Created by lixu on 2017/8/8.
 */

public class EazyAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ResultList_right> resultList_rightList;

    public EazyAdapter(Context context, List<ResultList_right> resultList_rights){
        this.context = context;
        this.resultList_rightList = resultList_rights;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.text_cell_layout, parent,
                false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).tv_name.setText(resultList_rightList.get(position).getZhLabel());
        }
    }

    @Override
    public int getItemCount() {
        return resultList_rightList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;


        public ItemViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.text_content);

        }
    }
}
