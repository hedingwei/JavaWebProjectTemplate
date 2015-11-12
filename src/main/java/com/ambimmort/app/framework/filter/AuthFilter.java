package com.ambimmort.app.framework.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hedingwei on 3/4/15.
 */
@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(request instanceof  HttpServletRequest){
            HttpServletRequest r = (HttpServletRequest)request;
            if(r.getUserPrincipal()!=null){ // 已经登陆
                r.setAttribute("x_m_username",r.getUserPrincipal().getName());


//                String contextPath = r.getContextPath();
//                String url=r.getRequestURI().replace(contextPath,"");
//                System.out.println("Rquest URI: "+url);
//                if(url.startsWith("/f")){
//                    request.getRequestDispatcher("/pub/forbidden.do").forward(request,response);
//                    return;
//                }

            }



        }

        filterChain.doFilter(request, response);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
