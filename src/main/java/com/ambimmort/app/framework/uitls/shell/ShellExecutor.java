package com.ambimmort.app.framework.uitls.shell;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hedingwei on 5/14/15.
 */
public class ShellExecutor {
    private static final int BLOCK_SIZE = 1024;
    private Session session;

    public ShellExecutor(Session session) {
        this.session = session;
    }

    private ChannelExec openChannelExec(Session session) {
        ChannelExec channelExec = null;
        try {
            Channel channel = session.openChannel("exec");
            channelExec = (ChannelExec) channel;
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return channelExec;
    }

    public String execute(String command) throws IOException, JSchException {
        ChannelExec channelExec = openChannelExec(session);
        StringBuffer buffer = executeCommand(command, channelExec);
        closeChannelExec(channelExec);
        return buffer.toString();
    }

    private StringBuffer executeCommand(String command, ChannelExec channelExec)
            throws IOException, JSchException {
        InputStream result = channelExec.getInputStream();
        channelExec.setCommand(command);
        channelExec.connect();
        System.out.println("id: " + channelExec.getId());

        StringBuffer buffer = generateResult(channelExec,result);
        return buffer;
    }

    private StringBuffer generateResult(ChannelExec channel, InputStream in)
            throws IOException {
        StringBuffer buffer = new StringBuffer();
        byte[] tmp = new byte[BLOCK_SIZE];

        while(true){
            while(in.available()>0){
                int i=in.read(tmp, 0, 1024);
                if(i<0)break;
                buffer.append(new String(tmp, 0, i));

            }
            if(channel.isClosed()){
                if(in.available()>0) continue;
                System.out.println("exit-status: "+channel.getExitStatus());
                break;
            }
            try{Thread.sleep(1000);}catch(Exception ee){}
        }


        return buffer;
    }

    private void closeChannelExec(ChannelExec channelExec) {
        channelExec.disconnect();
    }
}
