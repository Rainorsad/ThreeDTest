package com.example.yao.threedtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import adapter.RecycleviewAdapter;
import view.MyRecycleviewItemStyle;

/**
 * Created by Yao on 2016/10/26.
 */
public class XiaLashuaxinActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private RecycleviewAdapter radapter;
    private SwipeRefreshLayout swi;
    private int index;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xialashuaxin);
        final LinearLayoutManager lm = new LinearLayoutManager(this);


        swi = (SwipeRefreshLayout) findViewById(R.id.swi);
        recyclerView = (RecyclerView) findViewById(R.id.recy_shuaxin);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        MyRecycleviewItemStyle m = new MyRecycleviewItemStyle(MyRecycleviewItemStyle.VERTICAL);
        m.setColor(0xFFDBD6D2);
        m.setSize(2);
        recyclerView.addItemDecoration(m);

        List<String> s = getData();
        radapter = new RecycleviewAdapter(s,this);
//        recyclerView.swapAdapter();
        recyclerView.setAdapter(radapter);

        setChose(s);


        //下拉刷新
//        swi.setColorSchemeColors(R.color.colorAccent,R.color.colorPrimary,R.color.one,R.color.two);
//        swi.setOnRefreshListener(this);

//        //上拉加载
//        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == 0 && index+1 == radapter.getItemCount()){
//                    Toast.makeText(XiaLashuaxinActivity.this,"上拉加载",Toast.LENGTH_SHORT).show();
//                    handler.sendEmptyMessageDelayed(2,3000);
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                index = lm.findLastVisibleItemPosition();
//            }
//        });
    }

    private List<String> getData() {
        List<String> s = new ArrayList<>();
        for (int i=0;i<25;i++){
            s.add("老杨头"+i);
        }
        return s;
    }

    private List<String> getDataTwo() {
        List<String> s = new ArrayList<>();
        for (int i=0;i<25;i++){
            s.add("老李头"+i);
        }
        return s;
    }

    @Override
    public void onRefresh() {
        Toast.makeText(XiaLashuaxinActivity.this,"下拉刷新",Toast.LENGTH_SHORT).show();
        handler.sendEmptyMessageDelayed(1,3000);
    }



    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    swi.setRefreshing(false);
                    List<String> s = getDataTwo();
                    radapter = new RecycleviewAdapter(s,XiaLashuaxinActivity.this);
                    recyclerView.setAdapter(radapter);
                    setChose(s);
                    break;
                case 2:
                    List<String> s1 = getData();
                    radapter = new RecycleviewAdapter(s1,XiaLashuaxinActivity.this);
                    recyclerView.setAdapter(radapter);
                    setChose(s1);
                    break;
            }
        }
    };

    public void setChose(final List<String> s){
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags = 0;
                int swipeFlags = 0;
                if (recyclerView.getLayoutManager() instanceof  LinearLayoutManager){
                    dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
                    swipeFlags = 0;
                }else {
                    dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
                    swipeFlags = 0;
                }
                Log.d("测试","dragFlags："+dragFlags+"     "+"swipeFlags："+swipeFlags);
                return makeMovementFlags(dragFlags,swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromposition = viewHolder.getAdapterPosition();
                int toposition = target.getAdapterPosition();
                if(fromposition < toposition){
                    for (int i = fromposition;i<toposition;i++){
                        Collections.swap(s,i,i+1);
                    }
                }else {
                    for (int i=fromposition;i>toposition;i--){
                        Collections.swap(s,i,i-1);
                    }
                }
                Log.d("位置打印","起始位置："+fromposition+"    "+"结束位置："+toposition);
                radapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
