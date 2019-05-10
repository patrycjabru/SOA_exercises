package com.rest; // Note your package will be {{ groupId }}.rest

import com.DAO.UserDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloRestService {
    @GET // This annotation indicates GET request
    @Produces("application/json")
    @Path("/get")
    public Response getUser() {
        return Response.status(200)
                .entity(UserDAO.getUser("Patrycja"))
                .build();
    }
}