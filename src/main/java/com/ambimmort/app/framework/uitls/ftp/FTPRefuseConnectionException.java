package com.ambimmort.app.framework.uitls.ftp;

/**
 * Created by hedingwei on 6/19/15.
 */
public class FTPRefuseConnectionException extends Exception {
    private int reply;

    public int getReply() {
        return reply;
    }

    public void setReply(int reply) {
        this.reply = reply;
    }


    public FTPRefuseConnectionException(int reply) {
        this.reply = reply;
    }

    public FTPRefuseConnectionException(String message, int reply) {
        super(message);
        this.reply = reply;
    }

    public FTPRefuseConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int reply) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.reply = reply;
    }

    public FTPRefuseConnectionException() {
    }
}
