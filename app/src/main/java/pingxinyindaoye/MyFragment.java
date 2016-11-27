package pingxinyindaoye;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.yao.threedtest.R;

/**
 * Created by Yao on 2016/11/14.
 */
public class MyFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int layoutid = bundle.getInt("layoutid");
        int position = bundle.getInt("position");
        View v=inflater.inflate(layoutid,null);
        ImageView imgone = (ImageView) v.findViewById(R.id.backimg_01);
        ImageView imgtwo = (ImageView) v.findViewById(R.id.backimg_02);
        ImageView imgscr = (ImageView) v.findViewById(R.id.pg_content1);
        ImageView imgscr2 = (ImageView) v.findViewById(R.id.pg_content2);

        PercentRelativeLayout percentRelativeLayout = (PercentRelativeLayout) v.findViewById(R.id.rl);

        Glide.with(getActivity()).load(R.drawable.bg).into(imgone);
        Glide.with(getActivity()).load(R.drawable.bgtwo).into(imgtwo);
        Glide.with(getActivity()).load(R.drawable.qqtupian).into(imgscr);
        Glide.with(getActivity()).load(R.drawable.bgtwo).into(imgscr2);

        v.setTag(position);
        return v;
    }
}
