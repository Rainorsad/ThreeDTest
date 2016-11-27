package com.example.yao.threedtest;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import adapter.MyAdapter;
import bean.MeipaiBean;
import httputils.HttpUtil;
import recycleviewsetting.SpacesItemDecoration;
import url.Url;


/**
 * Created by Yao on 2016/10/24.
 */
public class MeiPaiActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private AsyncHttpClient ac;
    private MyAdapter adapter;
    private VideoView videlview;
    private Toolbar toolbar;
    private HttpUtil httpUtil;
    private Type type;
    private VideoThread videoThread;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meipai);
        type = new TypeToken<List<MeipaiBean>>() {}.getType();
        /**
         * 播放器初始化
         */
        videlview = (VideoView) findViewById(R.id.videovew);
        final MediaController me = new MediaController(this);
        videlview.setMediaController(new MediaController(this));
        me.setMediaPlayer(videlview);

        videoThread = new VideoThread();
        videoThread.start();
        /**
         * 数据初始化
         */
        httpUtil = new HttpUtil();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SpacesItemDecoration(8));

        /**
         * toolbar设值
         */
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        getSupportActionBar().setHomeButtonEnabled(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(null);
        toolbar.setSubtitle(null);
        toolbar.setNavigationIcon(null);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_meipai1:
                        popuwindowshow();
                        break;
//                    case R.id.item_meipai2:
//                        showSuan();
//                        break;
//                    case R.id.item_meipai3:
//                        showGaoxiao();
//                        break;
//                    case R.id.item_meipai4:
//                        Toast.makeText(MeiPaiActivity.this,"4",Toast.LENGTH_SHORT).show();
//                        break;
                }
                return true;
            }
        });




        /**
         * 网络访问初始化
         */
        RequestParams params = new RequestParams();
        params.put("id","13");
        httpUtil.getHttp(Url.gaoxiao,params,type,handler);

        /**
         * 获取网页中的视频地址
         */
        getMp4Path("http://www.meipai.com/media/595116562");

    }

    private void getMp4Path(final String s) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect(s).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Element mp4path = doc.head();
                String content = mp4path.select("meta[property=\"og:video:url\"]").attr("content");

                Message message = handler.obtainMessage();
                message.obj = content;
                message.what = 3;
                handler.sendMessage(message);

            }
        }).start();
    }

    private void showGaoxiao() {
        RequestParams params = new RequestParams();
        params.put("id",13);
        httpUtil.getHttp(Url.gaoxiao,params,type,handler);
    }

    /**
     * 旅行界面
     */
    private void showSuan() {
        RequestParams params = new RequestParams();
        params.put("id",16);
        httpUtil.getHttp(Url.gaoxiao,params,type,handler);
    }

    /**
     * 搞笑界面
     */
    private void showJU() {
        RequestParams params = new RequestParams();
        params.put("id",3);
        httpUtil.getHttp(Url.gaoxiao,params,type,handler);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    List<MeipaiBean> beans = (List<MeipaiBean>) msg.obj;

                    adapter = new MyAdapter(MeiPaiActivity.this,beans);
                    recyclerView.setAdapter(adapter);
                    adapter.setmOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(View view, MeipaiBean meipaiBean) {
                            String s = meipaiBean.getUrl();
                            getMp4Path(s);
                        }
                    });
                    break;
                case 3:
                    String s = (String) msg.obj;
                    Message ms = videoThread.handler.obtainMessage();
                    ms.what = 2;
                    ms.obj = s;
                    videoThread.handler.sendMessage(ms);
                    break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    class VideoThread extends Thread{
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 2:
                        String s = (String) msg.obj;
                        setVideo(s);
                        break;
                }
            }
        };

       public void setVideo(String u) {
            Uri url = Uri.parse(u);
            videlview.setVideoURI(url);
            videlview.requestFocus();
            videlview.start();
        }
    }

    public void popuwindowshow(){
        View v = LayoutInflater.from(this).inflate(R.layout.item_popuwndow,null);
        final PopupWindow popupWindow = new PopupWindow(v, toolbar.getWidth(),500);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_spinner_listview_background));
        popupWindow.showAsDropDown(toolbar,5,-5);
        TextView bt1 = new TextView(this);
        TextView bt2 = new TextView(this);
        TextView bt3 = new TextView(this);
        bt1 = (TextView) v.findViewById(R.id.bt_gaoxiao);
        bt2 = (TextView) v.findViewById(R.id.bt_lvyou);
        bt3 = (TextView) v.findViewById(R.id.bt_shejiao);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSuan();
                popupWindow.dismiss();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGaoxiao();
                popupWindow.dismiss();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showJU();
                popupWindow.dismiss();
            }
        });
    }
}
