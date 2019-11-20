package com.jy.day_03.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jy.day_03.R;
import com.jy.day_03.bean.Bean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final List<Bean.ResultsBean> beans;

    public RecAdapter(Context context, List<Bean.ResultsBean> beans) {
        this.context = context;
        this.beans = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        ViewHolder holder1 = (ViewHolder) holder;
        Glide.with(context).load(beans.get(position).getUrl()).into(holder1.mItemImg);
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    ItemClick itemClick;

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public interface ItemClick {
        void onItemClick(View view, int position);
    }


    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_img)
        ImageView mItemImg;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
