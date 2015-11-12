package com.ambimmort.app.framework.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by hedingwei on 5/18/15.
 */
public class MenuTag extends SimpleTagSupport {


    private String name;

    private String style;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter writer = getJspContext().getOut();
        writer.write("<li class='parent parent-focus'>");
        writer.write("<a href=''><i class='"+getStyle()+"'></i> <span>"+getName()+"</span></a>");
        writer.write("<ul class='children'>");
        if(getJspBody()!=null){
            getJspBody().invoke(null);
        }
        writer.write("</ul>");
        writer.write("</li>");
    }
}
