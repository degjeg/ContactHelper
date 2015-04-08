package contact.helper.android.androidcontacthelper;

/**
 * Created by danger on 15/4/8.
 */
public class GV {
    public Boolean isRecycled = null;

    private static GV instance = new GV();

    public static GV getInstance() {
        return instance;
    }

    public boolean isRecycled() {
        return isRecycled == null;
    }
}
