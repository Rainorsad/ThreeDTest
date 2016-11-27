package view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Yao on 2016/10/23.
 */
public class Image3DView extends ImageView{
    /**
     * 旋转角度的基准值
     */
    private static final float BASE_DEGREE = 50f;
    /**
     * 旋转深度的基准值
     */
    private static final float BASE_DEEP = 150f;
    private Camera mCamera;
    private Matrix mMaxtrix;
    private Bitmap mBitmap;
    /**
     * 当前图片对应的下标
     */
    private int mIndex;
    /**
     * 在前图片在X轴方向滚动的距离
     */
    private int mScrollX;
    /**
     * Image3DSwitchView控件的宽度
     */
    private int mLayoutWidth;
    /**
     * 当前图片的宽度
     */
    private int mWidth;
    /**
     * 当前旋转的角度
     */
    private float mRotateDegree;
    /**
     * 旋转的中心点
     */
    private float mDx;
    /**
     * 旋转的深度
     */
    private float mDeep;
    public Image3DView(Context context, AttributeSet attrs) {
        super(context);
        mCamera = new Camera();
        mMaxtrix = new Matrix();
    }
}
