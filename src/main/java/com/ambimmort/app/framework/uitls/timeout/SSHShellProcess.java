package com.ambimmort.app.framework.uitls.timeout;

import com.ambimmort.app.framework.uitls.shell.SSHSession;
import com.ambimmort.app.framework.uitls.shell.ShellExecutor;
import com.jcraft.jsch.Session;

import java.util.UUID;

/**
 * Created by hedingwei on 5/15/15.
 */
public class SSHShellProcess extends AbstractKillableProcess {

    private String username;
    private String password;
    private int port;
    private String host;
    String command;
    private String pidTag = UUID.randomUUID().toString()+"_amb";
    private String pid = null;
    private String output;
    private String killOutput;

    public SSHShellProcess(String command, String host, int port, String username, String password) {
        this.command = command;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
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

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getPidTag() {
        return pidTag;
    }

    public void setPidTag(String pidTag) {
        this.pidTag = pidTag;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getKillOutput() {
        return killOutput;
    }

    public void setKillOutput(String killOutput) {
        this.killOutput = killOutput;
    }

    public void getPid(){
        SSHSession session = new SSHSession();
        Session s = session.openSession(username, password, host, port);
        try {
            ShellExecutor se = new ShellExecutor(s);
            pid = se.execute("ps aux|grep "+pidTag+"|grep \"bash -c\"|grep -v \"ps aux\"|awk -F \" \" '{print $2}'");
            s.disconnect();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void run() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getPid();
            }
        }).start();

        SSHSession session = new SSHSession();
        Session s = session.openSession(username, password, host, port);
        try {
            ShellExecutor se = new ShellExecutor(s);
            output = se.execute(command + " | grep -v "+pidTag);
            s.disconnect();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


    }

    @Override
    public boolean kill() {
        if(pid!=null){
            SSHSession session = new SSHSession();
            Session s = session.openSession(username, password, host, port);
            try {
                ShellExecutor se = new ShellExecutor(s);
                killOutput = se.execute("kill -9 "+pid);
                s.disconnect();
                if(killOutput.trim().equals("")){
                    return true;
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return false;
    }
}
