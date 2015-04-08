package contact.helper.android.androidcontacthelper;


import android.os.Bundle;
import android.view.View;


public class MainActivity3 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main3);

        findViewById(R.id.tv).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
//        startActivity(new Intent(this, Main));
    }
}
