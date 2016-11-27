package com.example.yao.threedtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Yao on 2016/11/8.
 */
public class GlideTestActivity extends AppCompatActivity{

    private ImageView img0,img1,img2,img3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        img0 = (ImageView) findViewById(R.id.glide_img0);
        img1 = (ImageView) findViewById(R.id.glide_img1);
        img2 = (ImageView) findViewById(R.id.glide_img2);
        img3 = (ImageView) findViewById(R.id.glide_img3);

        //原图
        Glide.with(this).load(R.drawable.headbackground).into(img0);

        //圆角图片
        Glide.with(this).load(R.drawable.headbackground).bitmapTransform(new CropCircleTransformation(this)).crossFade(1000).into(img1);

        //模糊图片处理
        Glide.with(this).load(R.drawable.headbackground).bitmapTransform(new BlurTransformation(this,25)).crossFade(1000).into(img2);

        //圆角+模糊图片处理
        Glide.with(this).load(R.drawable.headbackground).bitmapTransform(new RoundedCornersTransformation(this,30,0,RoundedCornersTransformation.CornerType.BOTTOM)).crossFade(1000).into(img3);
    }
}
