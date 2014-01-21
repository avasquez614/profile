package org.craftercms.profile.management.web;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.Log4jConfigListener;

/**
 * Create a simple way to override Log4j.xml files.
 */
public class ExtendedLog4jConfigListener extends Log4jConfigListener {
    @Override
    public void contextInitialized(final ServletContextEvent event) {
        try {
            super.contextInitialized(event);
        } catch (IllegalArgumentException ex) {
            Logger log = LoggerFactory.getLogger(ExtendedLog4jConfigListener.class);
            log.info("File classpath:crafter/profile/admin-console/extension/log4j-override.xml was not found, " +
                "going with build-in " + "log4j settings");
        }
    }
}
