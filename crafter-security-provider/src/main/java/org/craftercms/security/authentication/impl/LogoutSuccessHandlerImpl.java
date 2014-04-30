/*
 * Copyright (C) 2007-2014 Crafter Software Corporation.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.craftercms.security.authentication.impl;

import org.craftercms.commons.http.RequestContext;
import org.craftercms.security.authentication.Authentication;
import org.craftercms.security.authentication.LogoutSuccessHandler;
import org.craftercms.security.exception.SecurityProviderException;
import org.springframework.beans.factory.annotation.Required;

import java.io.IOException;

/**
 * Default implementation for {@link org.craftercms.security.authentication.impl.LogoutSuccessHandlerImpl}, which
 * redirects to a target URL.
 *
 * @author avasquez
 */
public class LogoutSuccessHandlerImpl extends BaseHandler implements LogoutSuccessHandler {

    protected String targetUrl;

    @Required
    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    /**
     * Redirects to the target URL.
     *
     * @param context           the request context
     * @param authentication    the authentication object, just invalidated
     */
    @Override
    public void handle(RequestContext context, Authentication authentication) throws SecurityProviderException,
            IOException {
        redirectToUrl(context.getRequest(), context.getResponse(), targetUrl);
    }

}
