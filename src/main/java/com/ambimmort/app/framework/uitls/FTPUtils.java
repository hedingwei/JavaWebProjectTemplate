package com.ambimmort.app.framework.uitls;

import com.ambimmort.app.framework.uitls.ftp.FTPLoginException;
import com.ambimmort.app.framework.uitls.ftp.FTPRefuseConnectionException;
import org.apache.commons.net.ftp.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hedingwei on 6/19/15.
 */
public class FTPUtils {


    public static void connect(FTPClient ftpClient, String ip, int port) throws Throwable {
        try {
            ftpClient.connect(ip, port);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                FTPRefuseConnectionException ex = new FTPRefuseConnectionException(reply);
                ex.setReply(reply);
                throw ex;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new FTPRefuseConnectionException();
        }

    }

    public static void login(FTPClient ftpClient, String username, String password) throws Throwable {
        try {
            if (!ftpClient.login(username, password)) {
                ftpClient.disconnect();
                throw new FTPLoginException();
            }

            // 设置被动模式
            ftpClient.enterLocalPassiveMode();
            // 设置以二进制方式传输
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new FTPLoginException();
        }

    }

    public static boolean exists(FTPClient ftpClient, String file) throws IOException {
        return ftpClient.listFiles(file).length != 0;
    }

    public static FTPFile getFTPFile(FTPClient ftpClient, String file) throws Throwable {
//        FTPFile[] okFiles = ftpClient.listFiles(file, new FTPFileFilter() {
//            public boolean accept(FTPFile file) {
//                String fileName = file.getName().toLowerCase();
//                String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
//                return fileName.startsWith(today) && fileName.endsWith(".ok");
//            }
//        });
//        if (okFiles.length != 0) {
//            file = okFiles[0].getName().replaceAll("\\.ok$", ".zip");
//        } else {
//            return null;
//        }
        FTPFile[] files = ftpClient.listFiles(new String(file.getBytes(), ftpClient.getControlEncoding()));
        if (files.length != 0)
            return files[0];
        else
            return null;
    }

    public static FTPFile[] listFiles(FTPClient ftpClient, String dir) throws Throwable {
        return ftpClient.listFiles(dir);
    }

}
