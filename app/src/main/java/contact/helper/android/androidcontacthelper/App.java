package contact.helper.android.androidcontacthelper;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

/**
 * Created by danger on 15/4/8.
 */
public class App extends Application {
    private static final String TAG = "BaseActivityApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate");

        Intent intent = new Intent(this, TestService.class);
//        startService(intent);
    }


}
