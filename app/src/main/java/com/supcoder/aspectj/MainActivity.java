package com.supcoder.aspectj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.supcoder.library.annotation.CalculateTime;
import com.supcoder.library.hold;

/**
 *
 * @author noble
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @CalculateTime(value = "初始化View", type = 1)
    private void initView(){
        Log.e("MainActivity","方法执行了");
        final hold hold = new hold();
        new Thread(new Runnable() {
            @Override
            public void run() {
                hold.testTime();
            }
        }).start();
    }
}

