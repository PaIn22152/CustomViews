package com.perdev.customviews;

import static android.view.WindowManager.LayoutParams.FLAG_IGNORE_CHEEK_PRESSES;
import static android.view.WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
import static android.view.WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class JavaTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_test);

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        this.getWindow()
                .addFlags(FLAG_TURN_SCREEN_ON | FLAG_SHOW_WHEN_LOCKED | FLAG_IGNORE_CHEEK_PRESSES);
        //FLAG_TURN_SCREEN_ON | FLAG_SHOW_WHEN_LOCKED | FLAG_IGNORE_CHEEK_PRESSES

    }


}
