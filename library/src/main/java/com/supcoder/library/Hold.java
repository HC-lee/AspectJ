package com.supcoder.library;

import android.os.SystemClock;

import com.supcoder.library.annotation.CalculateTime;

public class Hold {

    @CalculateTime(value = "sasa0",type = 1)
    public void testTime(){
        SystemClock.sleep(1000);
    }
}

