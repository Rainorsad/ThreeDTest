package com.example.yao.threedtest;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.LinearLayout;

/**
 * Created by Yao on 2016/11/5.
 */
public class AnimalShowOneActivity extends AppCompatActivity{

    private LinearLayout lin_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animalone);

        lin_main = (LinearLayout) findViewById(R.id.activity_set_main_lin);
        for (int i=0;i<lin_main.getChildCount();i++){
            View view = lin_main.getChildAt(i);
            view.animate().setStartDelay(100 + i * 30).scaleX(1).scaleY(1);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4){
            for (int i=lin_main.getChildCount()-1;i>-1;i--){
                View view1 = lin_main.getChildAt(i);
                ViewPropertyAnimator viewPropertyAnimator = view1.animate().setStartDelay((lin_main.getChildCount()-1-i)*30)
                        .scaleX(0).scaleY(0);
                final int finalI = i;
                viewPropertyAnimator.setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                    }
                    @Override
                    public void onAnimationEnd(Animator animator) {
                        if (finalI ==0){
                            finish();
                        }
                    }
                    @Override
                    public void onAnimationCancel(Animator animator) {
                    }
                    @Override
                    public void onAnimationRepeat(Animator animator) {
                    }
                });
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
