package contact.helper.android.androidcontacthelper;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity1 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GV.getInstance().isRecycled = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        findViewById(R.id.tv).setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, MainActivity2.class));

        finish();
    }
}
