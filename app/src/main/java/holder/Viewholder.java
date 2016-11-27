package holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yao.threedtest.R;

import bean.Contact;

/**
 * Created by Yao on 2016/11/4.
 */
public class Viewholder extends RecyclerView.ViewHolder {
    TextView name;
    TextView comment;
    ImageView avatar;
    public Viewholder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        comment = (TextView) itemView.findViewById(R.id.comment);
        avatar = (ImageView) itemView.findViewById(R.id.yal_ms_avatar);
    }
    public static void bind(Viewholder viewHolder, Contact contact) {
        viewHolder.name.setText(contact.getName());
        viewHolder.avatar.setImageURI(contact.getPhotoUri());
        viewHolder.comment.setText(String.valueOf(contact.getTimesContacted()));
    }
}
