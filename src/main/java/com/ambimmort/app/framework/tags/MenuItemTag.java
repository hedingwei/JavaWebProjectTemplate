package com.ambimmort.app.framework.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by hedingwei on 5/18/15.
 */
public class MenuItemTag extends SimpleTagSupport{

    private String def;

    private PageContext pageContext;

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter writer = getJspContext().getOut();

        PageContext pc =(PageContext) getJspBody().getJspContext();

        String contextPath = pc.getServletContext().getContextPath();

        String index = (String)pc.getRequest().getAttribute("viewIndex");

        if(index.equals(getDef().trim())){
            System.out.println("TTTT:TRUE");
            writer.write("<li class='active'>");
        }else{
            writer.write("<li >");
        }

        writer.write("<a href='"+contextPath+"/"+getDef()+".do'>");
        if(getJspBody()!=null){
            getJspBody().invoke(null);
        }
        writer.write("</a>");
        writer.write("</li>");

    }
}
