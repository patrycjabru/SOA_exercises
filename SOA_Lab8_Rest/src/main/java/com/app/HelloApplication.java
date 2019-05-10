package com.app; // Note your package will be {{ groupId }}.rest

import com.rest.HelloRestService;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
public class HelloApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    public HelloApplication() {
        // Register our hello service
        singletons.add(new HelloRestService());
    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}