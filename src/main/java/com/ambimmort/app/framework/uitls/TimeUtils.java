package com.ambimmort.app.framework.uitls;


import java.sql.Timestamp;

/**时间工具类
 * Created by liyuan on 2015/3/31.
 */
public class TimeUtils {
    public  static  Timestamp getCurrentTimeStamp(){

        return  new Timestamp(System.currentTimeMillis());
    }


}
