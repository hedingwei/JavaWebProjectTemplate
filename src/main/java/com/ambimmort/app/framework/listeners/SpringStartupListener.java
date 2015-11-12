package com.ambimmort.app.framework.listeners;

import com.ambimmort.app.framework.uitls.Application;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

public class SpringStartupListener extends ContextLoaderListener{

    static Logger logger = Logger.getLogger(SpringStartupListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
        logger.info("KKServer Application Started Successfully.");
        logger.info("Working Dir: "+Application.getWorkingDir().getAbsolutePath());
	}
	
	

}
