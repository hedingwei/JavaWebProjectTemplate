package com.ambimmort.app.framework.lttask.example;

import com.ambimmort.app.framework.lttask.TaskModel;
import com.ambimmort.app.framework.lttask.AbstractSubTask;

import java.io.Serializable;

/**
 * Created by hedingwei on 6/17/15.
 */
public class SubTask5 extends AbstractSubTask implements Serializable {

    @Override
    public int getSteps() {
        return 1000;
    }

    @Override
    public void processStep(int i, TaskModel model) throws Throwable {

        if (i % 2 == 0) {
            Thread.sleep(500);
            if (Math.random() > 0.95) {
                log("Repository Log");
                cancel("step4 exception");
            }
        }

    }

}
