package com.ambimmort.nisp3.controller;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by hedingwei on 6/1/15.
 */
@Component
public class AuthenticationEventListener implements ApplicationListener<AbstractAuthenticationEvent> {

    private static Logger logger = Logger.getLogger(AuthenticationEventListener.class.getName());

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent authenticationEvent) {
        if (authenticationEvent instanceof InteractiveAuthenticationSuccessEvent) {
            // ignores to prevent duplicate logging with AuthenticationSuccessEvent
            return;
        }

        Authentication authentication = authenticationEvent.getAuthentication();
        String auditMessage = "Login attempt with username: " + authentication.getName() + "\t\tSuccess: " + authentication.isAuthenticated();
        logger.info(auditMessage);
        System.out.println(auditMessage);

    }



}