package com.pluto.gifttalk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;

public class WelcomesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomes);
        SharedPreferences sharedPreferences = getSharedPreferences("sunMoon" , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(sharedPreferences.contains("isNotFirstIn")){
//            int num = sharedPreferences.getInt("isNotFirstIn", 0);
//            editor.putInt("isNotFirstIn" , num++);
//            editor.commit();
            sikpActivity(MainActivity.class);
        }else {
            editor.putInt("isNotFirstIn" , 1);
            editor.commit();
            sikpActivity(WelcomeActivity.class);
        }
    }

    private void sikpActivity(final Class clazz){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomesActivity.this , clazz );
                startActivity(intent);
                WelcomesActivity.this.finish();
            }
        }, 1500);
    }
}
