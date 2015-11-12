package com.ambimmort.app.framework.lttask.example;

import com.ambimmort.app.framework.lttask.AbstractSubTask;
import com.ambimmort.app.framework.lttask.TaskModel;

import java.io.Serializable;

/**
 * Created by hedingwei on 6/17/15.
 */
public class SubTaskExample1 extends AbstractSubTask implements Serializable {


    @Override
    public int getSteps() {
        return 1;
    }

    @Override
    public void processStep(int i, TaskModel model) throws Throwable {



        for(int j=0;j<100;j++){

            if(isCancelled()) break;


            if (j % 2 == 0) {
                Thread.sleep(500);
            }

            if (Math.random() > 0.9999999999) {
                cancel("CRM数据库列与导入的数据步匹配，请联系服务提供商");
            }

            reportProgress(j);

        }




    }

}
