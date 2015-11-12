package com.ambimmort.app.framework.lttask;

import java.io.File;

/**
 * Created by hedingwei on 6/20/15.
 */
public interface IFTPSingleFileDownloadedListener {

    public void processs(FTPSingleFileDownloadTask task,File downloadedFile) throws Throwable;
}
