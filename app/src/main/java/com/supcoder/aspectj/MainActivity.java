package com.supcoder.aspectj;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.supcoder.library.annotation.CalculateTime;

/**
 * @author noble
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button button2;
    private Button button3;
    private String TAG = "com.supcoder.aspectj.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                aopTest();
                break;
            case R.id.button3:
                initConstructor();
                break;
            default:
                break;
        }

    }


    /**
     * aop 统计
     */
    @CalculateTime(value = "1", type = 1)
    private void aopTest() {
        SystemClock.sleep(3000);
    }

    /**
     * 演示构造方法
     */
    private void initConstructor() {

//        Person person=new Person("张三",10);
//        person.setAge(80);
        Person person = new Person();
    }


}

