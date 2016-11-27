package pingxinyindaoye;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.yao.threedtest.R;

/**
 * Created by Yao on 2016/11/14.
 */
public class MyTransfomer implements ViewPager.PageTransformer,ViewPager.OnPageChangeListener{

    private Boolean pageChanger = false; // 用来监听界面是否切换，是否需要启动动画
    /**
     * pageScrolled
     * 动画效果 颜色渐变效果
     * @param view
     * @param position 每个页面滑动过程中的偏移量，范围是 -1 --- 1
     */
    @Override
    public void transformPage(View view, float position) {
        ViewGroup vp = (ViewGroup) view.findViewById(R.id.rl);
        View bg_one = vp.findViewById(R.id.backimg_01);
        View bg_two = vp.findViewById(R.id.backimg_02);
        View bg_container = vp.findViewById(R.id.bg_container);

        final MyScrollView mscv = (MyScrollView) vp.findViewById(R.id.mscv);

        int bg_green = view.getResources().getColor(R.color.green);
        int bg_blue = view.getResources().getColor(R.color.blue);

        //viewpager设置背景颜色
        View parent = (View) view.getParent();
        int tag = (int) view.getTag();
        //颜色渐变
        ArgbEvaluator argbEvaluator = new ArgbEvaluator(); //颜色估值器
        int color = bg_green;
        switch (tag){
            case 0:
                color = (int) argbEvaluator.evaluate(Math.abs(position),bg_green,bg_blue);
                break;
            case 1:
                color = (int) argbEvaluator.evaluate(Math.abs(position),bg_blue,bg_green);
                break;
            case 2:
                color = (int) argbEvaluator.evaluate(Math.abs(position),bg_green,bg_blue);
                break;
        }
        parent.setBackgroundColor(color);

        //position == 0 时背景图片切换
        if (position == 0){
            if (pageChanger) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(bg_one, "translationX", 0, -bg_one.getWidth());
                animator.setDuration(400);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        //动画执行的百分比
                        Log.d("测试","运行到此处"+mscv.getWidth() * animation.getAnimatedFraction());
                        mscv.smoothScrollTo((int) (mscv.getWidth() * animation.getAnimatedFraction()), 0);
                    }
                });
                animator.start();

                ObjectAnimator animator1 = ObjectAnimator.ofFloat(bg_two, "translationX", bg_two.getWidth(), 0);
                animator1.setDuration(400);
                animator1.start();
                pageChanger = false;
            }
        }else if (position >-1 && position<1){ //图片还需要旋转动画
            int width = bg_one.getWidth();
            int height = bg_one.getHeight();
            bg_container.setPivotX(width/2);
            bg_container.setPivotY(height);
            bg_container.setRotation(30*position);
        }else if (position == -1 || position == 1){//还原旋转后的页面
            bg_one.setTranslationX(0);
            bg_two.setTranslationX(0);
            mscv.smoothScrollTo(0,0);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        pageChanger = true;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
