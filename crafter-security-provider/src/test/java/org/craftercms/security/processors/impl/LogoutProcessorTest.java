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
package org.craftercms.security.processors.impl;

import org.bson.types.ObjectId;
import org.craftercms.commons.http.RequestContext;
import org.craftercms.profile.api.Profile;
import org.craftercms.security.authentication.Authentication;
import org.craftercms.security.authentication.AuthenticationManager;
import org.craftercms.security.authentication.LogoutSuccessHandler;
import org.craftercms.security.authentication.impl.DefaultAuthentication;
import org.craftercms.security.processors.RequestSecurityProcessorChain;
import org.craftercms.security.utils.SecurityUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link org.craftercms.security.processors.impl.LogoutProcessor}.
 *
 * @author avasquez
 */
public class LogoutProcessorTest {

    private static final String USERNAME = "jdoe";

    private LogoutProcessor processor;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private LogoutSuccessHandler logoutSuccessHandler;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        processor = new LogoutProcessor();
        processor.setAuthenticationManager(authenticationManager);
        processor.setLogoutSuccessHandler(logoutSuccessHandler);
    }

    @Test
    public void testLogout() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest(LogoutProcessor.DEFAULT_LOGOUT_METHOD,
                LogoutProcessor.DEFAULT_LOGOUT_URL);
        MockHttpServletResponse response = new MockHttpServletResponse();
        RequestContext context = new RequestContext(request, response);
        RequestSecurityProcessorChain chain = mock(RequestSecurityProcessorChain.class);

        Profile profile = new Profile();
        profile.setUsername(USERNAME);

        Authentication auth = new DefaultAuthentication(new ObjectId().toString(), profile);

        SecurityUtils.setAuthentication(request, auth);

        processor.processRequest(context, chain);

        verify(chain, never()).processRequest(context);

        assertNull(SecurityUtils.getAuthentication(request));

        verify(logoutSuccessHandler).handle(context, auth);
    }

}
