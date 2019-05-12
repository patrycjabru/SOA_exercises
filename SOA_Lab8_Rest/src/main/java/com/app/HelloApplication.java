package com.app; // Note your package will be {{ groupId }}.rest

import com.rest.MovieService;
import com.rest.OsobyService;
import com.rest.UserService;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
public class HelloApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    public HelloApplication() {
        // Register our hello service
        singletons.add(new UserService());
        singletons.add(new MovieService());
        singletons.add(new OsobyService());
    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}