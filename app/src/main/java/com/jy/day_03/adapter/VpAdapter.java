package com.jy.day_03.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jy.day_03.MainActivity;
import com.jy.day_03.R;
import com.jy.day_03.bean.Bean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class VpAdapter extends PagerAdapter {

    private Context context;
    private List<Bean.ResultsBean> results;

    public VpAdapter(Context context, List<Bean.ResultsBean> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View inflate = View.inflate(context, R.layout.vp_item, null);
        ImageView imageView = inflate.findViewById(R.id.vp_item_img);

        Glide.with(context).load(results.get(position).getUrl()).into(imageView);
        container.addView(inflate);
        return inflate;
    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }


    public interface ImgClick{

        void onImgClick(View view,int position);
    }
}
