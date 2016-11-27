package bean;

import android.net.Uri;

/**
 * Created by Yao on 2016/11/4.
 */
public class Contact implements Comparable<Contact> {
    private String name;
    private Uri photouri;
    private String timescontacted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getPhotoUri() {
        return photouri;
    }

    public void setPhotoUri(Uri photouri) {
        this.photouri = photouri;
    }

    public String getTimesContacted() {
        return timescontacted;
    }

    public void setTimesContacted(String timescontacted) {
        this.timescontacted = timescontacted;
    }

    @Override
    public int compareTo(Contact contact) {
        return 0;
    }
}
