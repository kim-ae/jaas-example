package com.kimae.resource;

import java.io.IOException;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

public class AuthResource {

    @GET
    @Path("/logout")
    @PermitAll
    public void logout(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException{
        request.getSession().invalidate();
        response.sendRedirect("/hello");
    }
}
