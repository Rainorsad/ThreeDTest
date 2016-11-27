package pingxinyindaoye;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Yao on 2016/11/14.
 */
public class MyScrollView extends ScrollView{
    public MyScrollView(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
