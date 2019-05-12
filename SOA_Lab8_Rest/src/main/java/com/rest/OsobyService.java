package com.rest;

import com.DAO.UserDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/")
public class OsobyService {
    @GET // This annotation indicates GET request
    @Produces("application/json")
    @Path("/osoby")
    public Response get() throws URISyntaxException {
        Redirection redirection = new Redirection();
        redirection.location = new URI("/users/");
        return Response.status(302)
                .entity(redirection)
                .build();
    }

    public class Redirection implements Serializable{
        public URI location;
    }
}
