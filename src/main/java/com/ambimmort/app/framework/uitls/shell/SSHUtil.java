package com.ambimmort.app.framework.uitls.shell;


import java.io.IOException;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * Created by hedingwei on 5/14/15.
 */
public class SSHUtil {

    public static String runCommand(String cmd, String ip, int port, String username, String password) throws IOException, JSchException {

        SSHSession session = new SSHSession();
        Session s = session.openSession(username,password,ip,port);
        ShellExecutor se = new ShellExecutor(s);
        String result = se.execute(cmd);
        System.out.println(result);
        s.disconnect();
        return result;
    }

    public static String runMatlab(String script, String ip, int port, String username, String password) throws IOException, JSchException {
        SSHSession session = new SSHSession();
        Session s = session.openSession(username,password,ip,port);
        ShellExecutor se = new ShellExecutor(s);
        String result = se.execute("/mnt/temp/matlab/bin/matlab -nojvm -nodisplay -nosplash -nodesktop < "+script);
        System.out.println(result);
        s.disconnect();
        return result;
    }

    public static String runMkDir(String dir,String ip, int port, String username, String password) throws IOException, JSchException {
        SSHSession session = new SSHSession();
        Session s = session.openSession(username,password,ip,port);
        ShellExecutor se = new ShellExecutor(s);
        String result = se.execute("mkdir -p "+dir);
        System.out.println(result);
        s.disconnect();
        return result;
    }

    public static String runRm(String dir,String ip, int port, String username, String password) throws IOException, JSchException {
        SSHSession session = new SSHSession();
        Session s = session.openSession(username,password,ip,port);
        ShellExecutor se = new ShellExecutor(s);
        String result = se.execute("rm -rf "+dir);
        System.out.println(result);
        s.disconnect();
        return result;
    }



}
