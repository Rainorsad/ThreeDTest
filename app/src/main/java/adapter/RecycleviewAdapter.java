package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yao.threedtest.R;

import java.util.List;

/**
 * Created by Yao on 2016/10/26.
 */
public class RecycleviewAdapter extends RecyclerView.Adapter{
    private List<String> s;
    private Context context;
    public RecycleviewAdapter(List<String> s, Context context){
        super();
        this.s =s;
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shuaxin,null);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder holder1 = (MyHolder) holder;
        holder1.tv.setText(s.get(position));
    }

    @Override
    public int getItemCount() {
        return s.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_shuaxin_tv);
        }
    }

}
