package com.ambimmort.app.framework.tags.base64;

import org.apache.commons.codec.binary.Base64;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by hedingwei on 5/18/15.
 */
public class DecodeTag extends SimpleTagSupport {


    private String var;

    private String charset;

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter writer = getJspContext().getOut();
        if(charset==null){
            writer.write(new String(Base64.decodeBase64(var),"utf-8"));
        }else{
            writer.write(new String(Base64.decodeBase64(var),charset));
        }

    }
}
