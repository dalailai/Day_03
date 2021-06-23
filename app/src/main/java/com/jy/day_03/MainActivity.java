package com.jy.day_03;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import com.jy.day_03.adapter.RecAdapter;
import com.jy.day_03.adapter.VpAdapter;
import com.jy.day_03.bean.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  桌面删除修改注释
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.rec)
    RecyclerView mRec;
    private List<Bean.ResultsBean> results;
    private RecAdapter adapter;
    private VpAdapter vpAdapter;
    private boolean isVpScroll;
    private int index;
    private boolean isRecScroll = false;
    private int last;

    //	20/1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiService.baseUrl)
                .build();
        retrofit.create(ApiService.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bean>() {
                    @Override
                    public void accept(Bean bean) throws Exception {
                        if (bean != null) {
                            results.addAll(bean.getResults());

                            adapter.notifyDataSetChanged();
                            vpAdapter.notifyDataSetChanged();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("retrofit", throwable.toString());
                    }
                });
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRec.setLayoutManager(manager);
        adapter = new RecAdapter(this, results = new ArrayList<>());
        mRec.setAdapter(adapter);

        vpAdapter = new VpAdapter(this, results);
        mVp.setAdapter(vpAdapter);
        adapter.setItemClick(new RecAdapter.ItemClick() {
            @Override
            public void onItemClick(View view, int position) {
//                mVp.setCurrentItem(position);
                Intent intent = new Intent(MainActivity.this, Img3.class);
                intent.putExtra("index", position);
                intent.putExtra("bean", (Serializable) results);
                startActivity(intent);
            }
        });
        isVpScroll = false;

        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mRec.scrollToPosition(position);
                manager.scrollToPositionWithOffset(position, 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mRec.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int first;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                last = manager.findLastCompletelyVisibleItemPosition();

                if (first <= last && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mVp.setCurrentItem(first++);
                    isVpScroll = false;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                first = manager.findFirstCompletelyVisibleItemPosition();
                
                mVp.setCurrentItem(first);

            }
        });

    }

    @OnClick({R.id.vp, R.id.rec})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.vp:
                break;
            case R.id.rec:
                break;
        }
    }
}
