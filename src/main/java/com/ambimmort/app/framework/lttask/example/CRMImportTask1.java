package com.ambimmort.app.framework.lttask.example;

import com.ambimmort.app.framework.lttask.AbstractMSTLTTask;
import com.ambimmort.app.framework.lttask.FTPSingleFileDownloadTask;
import com.ambimmort.app.framework.lttask.IFTPSingleFileDownloadedListener;

import java.io.File;
import java.io.Serializable;

/**
 * Created by hedingwei on 6/17/15.
 */
public class CRMImportTask1 extends AbstractMSTLTTask implements Serializable {


    public static String ID = "task_crm_import";

//    public CRMImportTask(){
//        setId(ID);
//        getModel().setName("海南CRM接口导入任务");
//        getModel().setDescription("海南省电信CRM接口数据导入任务，共3个步骤");
//    }


    @Override
    public void setSubTasks() throws Throwable {
        FTPSingleFileDownloadTask task = new FTPSingleFileDownloadTask();
        task.setHost("localhost");
        task.setPort(2121);
        task.setUsername("admin");
        task.setPassword("admin");
        task.setRemoteFilePath("abc.txt");

        task.setFileDownloadedListener(new IFTPSingleFileDownloadedListener() {
            @Override
            public void processs(FTPSingleFileDownloadTask task, File downloadedFile) throws Throwable {
                System.out.println("print "+downloadedFile.getAbsolutePath());
            }
        });




        FTPSingleFileDownloadTask task1 = new FTPSingleFileDownloadTask();
        task1.setHost("localhost");
        task1.setPort(2121);
        task1.setUsername("admin");
        task1.setPassword("admin");
        task1.setRemoteFilePath("abc1.txt");



        task1.setFileDownloadedListener(new IFTPSingleFileDownloadedListener() {
            @Override
            public void processs(FTPSingleFileDownloadTask task, File downloadedFile) throws Throwable {
                System.out.println("print "+downloadedFile.getAbsolutePath());
            }
        });

        add("subtask1", task);
        add("subtask2", task1);
//        add("subtask2",new SubTask2());
        add("subtask3",new SubTask3());

//        add("subtask4",new SubTask4());
//        add("subtask5",new SubTask5());

        get("subtask1").setName("下载文件 abc.txt");

        get("subtask2").setName("下载文件 abc1.txt");
//        get("subtask2").setName("本地CRM数据库合并");
        get("subtask3").setName("本地CRM数据索引更新");

//        get("subtask4").setName("临时数据清理");
//        get("subtask5").setName("TTTT");

//        get("subtask1").setDescription("获取接口文件并校验");
//        get("subtask2").setDescription("本地CRM数据库合并");
        get("subtask3").setDescription("本地CRM数据索引更新");
//        get("subtask4").setDescription("临时数据清理");
//        get("subtask5").setDescription("DDDDDD");
    }
}
