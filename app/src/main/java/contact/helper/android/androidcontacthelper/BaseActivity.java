package contact.helper.android.androidcontacthelper;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public abstract class BaseActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "recycled:" + GV.getInstance().isRecycled() + getClass().getSimpleName()
                        + ",pid:" + +android.os.Process.myPid()

        );
        if (GV.getInstance().isRecycled()) {
            GV.getInstance().isRecycled = false;
            finish();
        }

    }


}
