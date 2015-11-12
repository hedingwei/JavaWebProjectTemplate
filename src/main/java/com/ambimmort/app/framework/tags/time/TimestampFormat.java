package com.ambimmort.app.framework.tags.time;

import org.apache.commons.codec.binary.Base64;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hedingwei on 6/20/15.
 */
public class TimestampFormat extends SimpleTagSupport {

    private String timestamp;

    private String format;


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public void doTag() throws JspException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        JspWriter writer = getJspContext().getOut();
        writer.write(sdf.format(new Date(Long.parseLong(timestamp))));
    }
}
