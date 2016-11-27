package com.example.yao.threedtest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Yao on 2016/10/27.
 */
public class ErWeimaActivity extends AppCompatActivity{
    private Button bt;
    private TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获得运行权限。6.0版本之后必须获得运行权限，而相机属于运行权限之一，不加的话opera()方法会一直调用出错
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            //init(barcodeScannerView, getIntent(), null);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, 1);//1 can be another integer
        }
        setContentView(R.layout.activity_erweima);
        bt = (Button) findViewById(R.id.erweima_bt);
        tv = (TextView) findViewById(R.id.erweima_tv);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ErWeimaActivity.this,MipcaActivityCapture.class);
                startActivity(it);
            }
        });
    }
}
