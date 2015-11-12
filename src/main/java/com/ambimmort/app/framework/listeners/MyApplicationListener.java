/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambimmort.app.framework.listeners;

import com.ambimmort.app.framework.uitls.AESUtils;
import com.ambimmort.app.framework.uitls.Application;
import com.ambimmort.app.framework.uitls.MD5Utils;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Web application lifecycle listener.
 *
 * @author hedingwei
 */
public class MyApplicationListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("workingdir:"+new File("./").getAbsolutePath());

    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
