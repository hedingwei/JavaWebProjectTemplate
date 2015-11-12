package com.ambimmort.nisp3.service.def;

import java.io.File;

/**
 * Created by liu on 2015/6/30.
 */
public interface ISystemUtilsService {
    public boolean isKeyWord(String s);

    public boolean delAllFile(String path);

    public void deleteFile(File file);

    public String Base64Encode(String str) throws Exception;

    public String encrypt(String password);
}
