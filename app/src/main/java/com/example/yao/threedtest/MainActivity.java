package com.example.yao.threedtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import pingxinyindaoye.PingXingYindaoActivity;
import sharesdk.ShareSdkActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.one)
    Button one;
    @InjectView(R.id.two)
    Button two;
    @InjectView(R.id.three)
    Button three;
    @InjectView(R.id.four)
    Button four;
    @InjectView(R.id.five)
    Button five;
    @InjectView(R.id.sex)
    Button sex;
    @InjectView(R.id.seven)
    Button seven;
    @InjectView(R.id.eight)
    Button eight;
    @InjectView(R.id.nine)
    Button nine;
    @InjectView(R.id.ten)
    Button ten;
    @InjectView(R.id.eleven)
    Button eleven;
    @InjectView(R.id.twelve)
    Button twelve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

    }

    private void jump(Class c) {
        Intent it = new Intent(MainActivity.this, c);
        startActivity(it);
    }

    @OnClick({R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.sex, R.id.seven,
            R.id.eight, R.id.nine, R.id.ten, R.id.eleven,R.id.twelve})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one:
                jump(MeiPaiActivity.class);
                break;
            case R.id.two:
                jump(XiaLashuaxinActivity.class);
                break;
            case R.id.three:
                jump(ErWeimaActivity.class);
                break;
            case R.id.four:
                jump(DituDingweiActivity.class);
                break;
            case R.id.five:
                jump(AnimalShowOneActivity.class);
                break;
            case R.id.sex:
                jump(YinDaojiemianActivity.class);
                break;
            case R.id.seven:
                jump(OkHttpActivity.class);
                break;
            case R.id.eight:
                jump(GlideTestActivity.class);
                break;
            case R.id.nine:
                jump(PingXingYindaoActivity.class);
                break;
            case R.id.ten:
                jump(YuanXinYindaoActivity.class);
                break;
            case R.id.eleven:
                jump(XMLJsonActivity.class);
                break;
            case R.id.twelve:
                jump(ShareSdkActivity.class);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
