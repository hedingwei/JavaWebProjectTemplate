package com.ambimmort.app.framework.lttask;

import com.ambimmort.app.framework.uitls.FTPUtils;
import com.ambimmort.app.framework.uitls.ftp.FTPLoginException;
import com.ambimmort.app.framework.uitls.ftp.FTPRefuseConnectionException;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.util.Properties;

/**
 * Created by ShiYun on 2015/7/6 0006.
 */
public class SFTPSingleFileDownloadTask extends AbstractSubTask implements Serializable {

    File downloadedFile = null;

    File tmpFile = null; // 临时文件

    ChannelSftp ftpclient;

    long size = 0;

    long time = 0;

    BufferedInputStream bis = null;

    FileOutputStream output = null;

    boolean isReadFinished = false;

    byte[] buffer = new byte[1024 * 1024];

    int totalSteps = 0;

    private String host = "";

    private int port = 21;

    private String username = "anonymous";

    private String password = "";

    private String remoteFilePath = null;

    private ISFTPSingleFileDownloadedListener fileDownloadedListener = null;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemoteFilePath() {
        return remoteFilePath;
    }

    public void setRemoteFilePath(String remoteFilePath) {
        this.remoteFilePath = remoteFilePath;
    }

    public File getDownloadedFile() {
        return downloadedFile;
    }

    public ISFTPSingleFileDownloadedListener getFileDownloadedListener() {
        return fileDownloadedListener;
    }

    public void setFileDownloadedListener(ISFTPSingleFileDownloadedListener fileDownloadedListener) {
        this.fileDownloadedListener = fileDownloadedListener;
    }

    public SFTPSingleFileDownloadTask(String host, int port, String username, String password, String remoteFilePath, ISFTPSingleFileDownloadedListener fileDownloadedListener) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.remoteFilePath = remoteFilePath;
        this.fileDownloadedListener = fileDownloadedListener;
    }

    public SFTPSingleFileDownloadTask() {
    }

    @Override
    public void init(TaskModel model) throws Throwable {

        tmpFile = new File(getTempWorkingDir(), "tmpFile.downloadedFile");

        JSch jsch = new JSch(); // 创建JSch对象
        Session session = jsch.getSession(username, host, port); // 根据用户名，主机ip，端口获取一个Session对象

        if (password != null) {
            session.setPassword(password); // 设置密码
        }

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config); // 为Session对象设置properties
//        session.setTimeout(timeout); // 设置timeout时间
        session.connect(); // 通过Session建立链接

        Channel channel = session.openChannel("sftp"); // 打开SFTP通道
        channel.connect(); // 建立SFTP通道的连接

        ftpclient = (ChannelSftp) channel;

        String filename = "default";

        if(remoteFilePath==null){
            log("未指定远程目标文件");
            cancel("未指定远程目标文件，导致任务取消");
        }

//        FTPFile ftpFile = FTPUtils.getFTPFile(ftpclient, remoteFilePath);
//        if (ftpFile == null) {
//            log("指定的远程目标文件不存在："+remoteFilePath);
//            cancel("指定的远程目标文件不存在，导致下载任务取消");
//
//        } else {
//            filename = ftpFile.getName();
//            downloadedFile = new File(getTempWorkingDir(),filename);
//            System.out.println("ttt:"+downloadedFile.getAbsolutePath());
//            size = ftpFile.getSize();
//            time = ftpFile.getTimestamp().getTimeInMillis();
//        }
//
//        InputStream is = ftpclient.retrieveFileStream(remoteFilePath);

//        bis = new BufferedInputStream(is, 1024 * 1024 * 10);

        totalSteps = (int) Math.ceil((size / (double) buffer.length));


        output = new FileOutputStream(tmpFile);

    }

    @Override
    public int getSteps() throws Throwable {
        FTPClient ftpclient = new FTPClient();
        FTPUtils.connect(ftpclient, host, port);
        FTPUtils.login(ftpclient, username, password);

        if(remoteFilePath==null){
            log("未指定远程目标文件");
            cancel("未指定远程目标文件，导致任务取消");
        }
        String filename = "default";
        FTPFile ftpFile = FTPUtils.getFTPFile(ftpclient, remoteFilePath);

        if (ftpFile == null) {
            log("指定的远程目标文件不存在："+remoteFilePath);
            cancel("指定的远程目标文件不存在，导致下载任务取消");

        } else {
            size = ftpFile.getSize();
            time = ftpFile.getTimestamp().getTimeInMillis();
        }
        totalSteps = (int) Math.ceil((size / (double) buffer.length));
        ftpclient.disconnect();

        return totalSteps;
    }

    @Override
    public void processStep(int i, TaskModel model) throws Throwable {
        if(isReadFinished) return;
        int j = 0;
        int readSize = 0;
        if (i % 5 == 0) {
            Thread.sleep(100);
        }
        while ((j < buffer.length)) {
            readSize = bis.read(buffer);
            j += readSize;
            if (readSize < 0) {
                isReadFinished = true;
                break;
            }
            output.write(buffer, 0, readSize);
            output.flush();
        }
    }

    @Override
    public void onFinished(TaskModel model) throws Throwable {
        bis.close();
        output.close();
        ftpclient.disconnect();
        tmpFile.renameTo(downloadedFile);
        log("成功下载文件:"+getDownloadedFile().getAbsolutePath()+"\t大小:"+getDownloadedFile().length());
        if(this.fileDownloadedListener!=null){
            fileDownloadedListener.processs(this,downloadedFile);
        }
    }

    @Override
    public void onError(int i, TaskModel model, Throwable throwable) {
        throwable.printStackTrace();
        if(throwable instanceof FTPRefuseConnectionException){
            log("FTP 初始化异常：FTP服务器拒绝链接，请联系相关管理员检查FTP服务器设置");
            cancel("FTP 初始化异常：FTP服务器拒绝链接，请联系相关管理员检查FTP服务器设置");
        }else if(throwable instanceof FTPLoginException){
            log("FTP 初始化异常：FTP拒绝用户登录，请联系相关管理员检查FTP服务器设置");
            cancel("FTP 初始化异常：FTP拒绝用户登录，请联系相关管理员检查FTP服务器设置");
        }

    }

    @Override
    public void onInitError(TaskModel model, Throwable throwable) {
        throwable.printStackTrace();
        if(throwable instanceof FTPRefuseConnectionException){
            log("FTP 初始化异常：FTP服务器拒绝链接，请联系相关管理员检查FTP服务器设置");
            cancel("FTP 初始化异常：FTP服务器拒绝链接，请联系相关管理员检查FTP服务器设置");
        }else if(throwable instanceof FTPLoginException){
            log("FTP 初始化异常：FTP拒绝用户登录，请联系相关管理员检查FTP服务器设置");
            cancel("FTP 初始化异常：FTP拒绝用户登录，请联系相关管理员检查FTP服务器设置");
        }

    }

    @Override
    public void onCancelled(int i, TaskModel model, String reason) throws Throwable {
        bis.close();
        output.close();
        ftpclient.disconnect();
        log("操作被取消，无法完成下载");
    }
}
