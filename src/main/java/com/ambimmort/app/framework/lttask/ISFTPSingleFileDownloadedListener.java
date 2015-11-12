package com.ambimmort.app.framework.lttask;

import java.io.File;

/**
 * Created by ShiYun on 2015/7/6 0006.
 */
public interface ISFTPSingleFileDownloadedListener {
    public void processs(SFTPSingleFileDownloadTask task,File downloadedFile) throws Throwable;
}
