package com.ambimmort.app.framework.lttask.example;

import com.ambimmort.app.framework.lttask.AbstractMSTLTTask;
import com.ambimmort.app.framework.lttask.FTPSingleFileDownloadTask;
import com.ambimmort.app.framework.lttask.IFTPSingleFileDownloadedListener;
import com.ambimmort.app.framework.lttask.TaskModel;

import java.io.File;
import java.io.Serializable;

/**
 * Created by hedingwei on 6/17/15.
 */
public class ExampleTask extends AbstractMSTLTTask implements Serializable {


    public static String ID = "task_crm_import";


    @Override
    public void setSubTasks() throws Throwable {


        SubTask2 task= new SubTask2();
        SubTask2 task1= new SubTask2();
        SubTask2 task2= new SubTask2();

        add("subtask1", task);
        add("subtask2", task1);
        add("subtask3", task2);
////        add("subtask2",new SubTask2());
//        add("subtask3",new SubTask3());
//        add("subtask4", task2);
////        add("subtask4",new SubTask4());
////        add("subtask5",new SubTask5());
//
//        get("subtask1").setName("下载文件 abc.txt");
//
//        get("subtask2").setName("下载文件 abc1.txt");
////        get("subtask2").setName("本地CRM数据库合并");
//        get("subtask3").setName("本地CRM数据索引更新");
//        get("subtask4").setName("afkahsdfkah");
////        get("subtask4").setName("临时数据清理");
////        get("subtask5").setName("TTTT");
//
////        get("subtask1").setDescription("获取接口文件并校验");
////        get("subtask2").setDescription("本地CRM数据库合并");
//        get("subtask3").setDescription("本地CRM数据索引更新");
////        get("subtask4").setDescription("临时数据清理");
////        get("subtask5").setDescription("DDDDDD");
    }

    @Override
    public void onError(int i, TaskModel model, Throwable throwable) {
        throwable.printStackTrace();
    }
}
