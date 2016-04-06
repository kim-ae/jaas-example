package com.kimae;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.kimae.resource.AuthResource;
import com.kimae.resource.HelloWorld;

@ApplicationPath("/")
public class App extends Application {
    public Set<Class<?>> getClasses() {        
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(HelloWorld.class);
        s.add(AuthResource.class);
        return s;
    }
}
