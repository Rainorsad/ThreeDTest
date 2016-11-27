package com.example.yao.threedtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

/**
 * Created by Yao on 2016/11/9.
 */
public class YuanXinYindaoActivity extends AppCompatActivity{
    private ViewFlipper flipper;
    private GestureDetector detector;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuanxinyindao);

        flipper = (ViewFlipper) findViewById(R.id.vf);
        detector = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            /**
             * 按下（onDown）： 刚刚手指接触到触摸屏的那一刹那，就是触的那一下。
             抛掷（onFling）： 手指在触摸屏上迅速移动，并松开的动作。
             长按（onLongPress）： 手指按在持续一段时间，并且没有松开。
             滚动（onScroll）： 手指在触摸屏上滑动。
             按住（onShowPress）： 手指按在触摸屏上，它的时间范围在按下起效，在长按之前。
             抬起（onSingleTapUp）：手指离开触摸屏的那一刹那。
             * @param e1
             * @param e2
             * @param velocityX
             * @param velocityY
             * @return
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e1.getX() - e2.getX() > 120.0F) {//
                    flipper.setInAnimation(YuanXinYindaoActivity.this, R.anim.tip_left_in); //view进入时使用的动画
                    flipper.setOutAnimation(YuanXinYindaoActivity.this, R.anim.tip_left_out); //view退出时使用的动画
                    if (flipper.getDisplayedChild() < 2) {
                        flipper.showNext(); //用于显示fragment下一个view
                    }
                } else if (e1.getX() - e2.getX() < -120.0F) {
                    flipper.setInAnimation(YuanXinYindaoActivity.this, R.anim.tip_right_in);
                    flipper.setOutAnimation(YuanXinYindaoActivity.this, R.anim.tip_right_out);
                    if (flipper.getDisplayedChild() > 0) {
                        flipper.showPrevious(); //用于显示fragment上一个view
                    }
                }
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (detector != null) {
            return detector.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }
}
