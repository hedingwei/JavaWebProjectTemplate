package com.ambimmort.app.framework.springsecurity;

import org.springframework.context.ApplicationContext;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

public class SecurityMetadataSourceHelper {

	public static void reloadMetadataSource(ServletContext sc) {

		ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(sc);
	    //FactoryBean factoryBean = (FactoryBean) ctx.getBean("&filterInvocationDefinitionSource");
	    //FilterInvocationDefinitionSource fids = (FilterInvocationDefinitionSource) factoryBean.getObject();
	    //The property 'objectDefinitionSource' is deprecated，已过时
	    //filter.setObjectDefinitionSource(fids);
	    //FilterInvocationSecurityMetadataSource fids = (FilterInvocationSecurityMetadataSource) ctx.getBean("filterInvocationDefinitionSource");
	    

	    // directly get factory bean from ctx
		FilterInvocationSecurityMetadataSourceFactoryBean fac = ctx.getBean("&securityMetadataSource", FilterInvocationSecurityMetadataSourceFactoryBean.class);
	    
	    // create SecurityMetadataSource
	    FilterSecurityInterceptor filter = (FilterSecurityInterceptor) ctx.getBean("filterSecurityInterceptor");
	    filter.setSecurityMetadataSource(fac.getObject());

	}

}