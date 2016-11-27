package pingxinyindaoye;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.yao.threedtest.R;

/**
 * Created by Yao on 2016/11/14.
 */
public class PingXingYindaoActivity extends AppCompatActivity{
    private ViewPager vp;
    private int[] layoutids={R.layout.activity_pingxingkongjian,R.layout.activity_pingxingkongjiantwo,R.layout.activity_pingxingkongjianthree};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingxing);

        vp = (ViewPager) findViewById(R.id.activity_pingxingvp);
        vp.setOffscreenPageLimit(3);
        vp.setAdapter(new WeiComPagerAdapter(getSupportFragmentManager()));
        MyTransfomer myTransfomer = new MyTransfomer();
        vp.setOnPageChangeListener(myTransfomer);
        vp.setPageTransformer(true,myTransfomer);

    }

    class WeiComPagerAdapter extends FragmentPagerAdapter {
        public WeiComPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            MyFragment fragment = new MyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("layoutid",layoutids[position]);
            bundle.putInt("position",position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return layoutids.length;
        }
    }
}
