package com.ambimmort.nisp3.service.impl;

import com.ambimmort.nisp3.service.def.ISystemUtilsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by liu on 2015/6/30.
 */
@Component
public class SystemUtilsServiceImpl implements ISystemUtilsService{
    @Override
    public boolean isKeyWord(String s) {
        String[] keyWords={"ADD","ALL","ALTER","ANALYZE","AND","AS","ASC","ASENSITIVE","BEFORE","BETWEEN","BIGINT","BINARY","BLOB","BOTH","BY","CALL","CASCADE","CASE","CHANGE","CHAR","CHARACTER","CHECK","COLLATE","COLUMN","CONDITION","CONNECTION","CONSTRAINT","CONTINUE","CONVERT","CREATE","CROSS","CURRENT_DATE","CURRENT_TIME","CURRENT_TIMESTAMP","CURRENT_USER","CURSOR","DATABASE","DATABASES","DAY_HOUR","DAY_MICROSECOND","DAY_MINUTE","DAY_SECOND","DEC","DECIMAL","DECLARE","DEFAULT","DELAYED","DELETE","DESC","DESCRIBE","DETERMINISTIC","DISTINCT","DISTINCTROW","DIV","DOUBLE","DROP","DUAL","EACH","ELSE","ELSEIF","ENCLOSED","ESCAPED","EXISTS","EXIT","EXPLAIN","FALSE","FETCH","FLOAT","FLOAT4","FLOAT8","FOR","FORCE","FOREIGN","FROM","FULLTEXT","GOTO","GRANT","GROUP","HAVING","HIGH_PRIORITY","HOUR_MICROSECOND","HOUR_MINUTE","HOUR_SECOND","IF","IGNORE","IN","INDEX","INFILE","INNER","INOUT","INSENSITIVE","INSERT","INT","INT1","INT2","INT3","INT4","INT8","INTEGER","INTERVAL","INTO","IS","ITERATE","JOIN","KEY","KEYS","KILL","LABEL","LEADING","LEAVE","LEFT","LIKE","LIMIT","LINEAR","LINES","LOAD","LOCALTIME","LOCALTIMESTAMP","LOCK","LONG","LONGBLOB","LONGTEXT","LOOP","LOW_PRIORITY","MATCH","MEDIUMBLOB","MEDIUMINT","MEDIUMTEXT","MIDDLEINT","MINUTE_MICROSECOND","MINUTE_SECOND","MOD","MODIFIES","NATURAL","NOT","NO_WRITE_TO_BINLOG","NULL","NUMERIC","ON","OPTIMIZE","OPTION","OPTIONALLY","OR","ORDER","OUT","OUTER","OUTFILE","PRECISION","PRIMARY","PROCEDURE","PURGE","RAID0","RANGE","READ","READS","REAL","REFERENCES","REGEXP","RELEASE","RENAME","REPEAT","REPLACE","REQUIRE","RESTRICT","RETURN","REVOKE","RIGHT","RLIKE","SCHEMA","SCHEMAS","SECOND_MICROSECOND","SELECT","SENSITIVE","SEPARATOR","SET","SHOW","SMALLINT","SPATIAL","SPECIFIC","SQL","SQLEXCEPTION","SQLSTATE","SQLWARNING","SQL_BIG_RESULT","SQL_CALC_FOUND_ROWS","SQL_SMALL_RESULT","SSL","STARTING","STRAIGHT_JOIN","TABLE","TERMINATED","THEN","TINYBLOB","TINYINT","TINYTEXT","TO","TRAILING","TRIGGER","TRUE","UNDO","UNION","UNIQUE","UNLOCK","UNSIGNED","UPDATE","USAGE","USE","USING","UTC_DATE","UTC_TIME","UTC_TIMESTAMP","VALUES","VARBINARY","VARCHAR","VARCHARACTER","VARYING","WHEN","WHERE","WHILE","WITH","WRITE","X509","XOR","YEAR_MONTH","ZEROFILL"};
        for(int i=0;i<keyWords.length;i++){
            if(keyWords[i].equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }
    public String encrypt(String password){
        int t = 0;
        String hashedPassword =null;
        for (t = 1; t <= 12; t++) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            hashedPassword = passwordEncoder.encode(password);
        }
        return hashedPassword;
    }
    public String Base64Encode(String str) throws  Exception{
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
           return new String(decoder.decodeBuffer(str));
    }

    //删除文件夹以及文件
    public void deleteFile(File file) {
        try {
            if (file.exists()) {//判断文件是否存在
                if (file.isFile()) {//判断是否是文件
                    file.delete();//删除文件
                } else if (file.isDirectory()) {//否则如果它是一个目录
                    File[] files = file.listFiles();//声明目录下所有的文件 files[];
                    for (int i = 0; i < files.length; i++) {//遍历目录下所有的文件
                        this.deleteFile(files[i]);//把每个文件用这个方法进行迭代
                    }
                    file.delete();//删除文件夹
                }
            } else {
                System.out.println("所删除的文件不存在");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }




    //此方法是删除文件夹下面的所有文件，不删除文件夹
    public boolean delAllFile(String path){
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }

        }
        return flag;
    }



}
