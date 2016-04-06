package com.kimae.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path("/hello")
public class HelloWorld {
    
    @Context
    private HttpServletRequest request;
    
    @GET
    @Produces("text/plain")
    public String myTest() {
        return "Protected Area for " + request.getUserPrincipal().getName();
    }
}
