package adapter;

import android.view.ActionMode;
import android.view.ViewGroup;

import com.yalantis.multiselection.lib.adapter.BaseLeftAdapter;

import bean.Contact;
import holder.Viewholder;

/**
 * Created by Yao on 2016/11/4.
 */
public class LeftAdapter extends BaseLeftAdapter<Contact,Viewholder> {
    private final ActionMode.Callback callback;
    public LeftAdapter(ActionMode.Callback callback) {
        super(Contact.class);
        this.callback = callback;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }
}
