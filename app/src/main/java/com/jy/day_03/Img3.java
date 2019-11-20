package com.jy.day_03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jy.day_03.adapter.VpAdapter;
import com.jy.day_03.bean.Bean;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Img3 extends AppCompatActivity {

    @BindView(R.id.i_vp)
    ViewPager mIVp;
    @BindView(R.id.i_tv)
    TextView mITv;
    private List<Bean.ResultsBean> results;
    private VpAdapter vpAdapter;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img3);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        index = intent.getIntExtra("index", -1);
        results = (List<Bean.ResultsBean>) intent.getSerializableExtra("bean");

        initView();
    }

    private void initView() {
        vpAdapter = new VpAdapter(this, results);
        mIVp.setAdapter(vpAdapter);
        mIVp.setCurrentItem(index);
        mITv.setText(setText( index+1 ,results.size()));
        mIVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mITv.setText(setText( position+1 ,results.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public String setText(int index,int max){
        return index+" 页 / "+max+" 页";
    }

    @OnClick({R.id.i_vp, R.id.i_tv})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.i_vp:
                break;
            case R.id.i_tv:
                break;
        }
    }
}
