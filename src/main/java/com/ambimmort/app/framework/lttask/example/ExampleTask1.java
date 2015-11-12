package com.ambimmort.app.framework.lttask.example;

import com.ambimmort.app.framework.lttask.AbstractMSTLTTask;

import java.io.Serializable;

/**
 * Created by hedingwei on 7/27/15.
 */
public class ExampleTask1 extends AbstractMSTLTTask implements Serializable {

    @Override
    public void setSubTasks() throws Throwable {

        add("mystep1",new SubTaskExample1());
        add("mystep2",new SubTaskExample1());
        add("mystep3",new SubTaskExample1());

        get("mystep1").setDescription("这个任务是做啥的");
        get("mystep2").setDescription("这个任务是做啥的2");
        get("mystep3").setDescription("这个任务是做啥的3");

        get("mystep1").setName("MyStep111");
        get("mystep2").setName("MyStep222");
        get("mystep3").setName("MyStep333");



    }
}
