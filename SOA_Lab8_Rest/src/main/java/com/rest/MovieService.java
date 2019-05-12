package com.rest;

import com.DAO.MovieDAO;
import com.DTO.Movie;

import javax.persistence.PostRemove;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/movies")
public class MovieService {
    @GET // This annotation indicates GET request
    @Produces("application/json")
    @Path("/{id}")
    public Response getMovie(@PathParam("id") int id) {
        return Response.status(200)
                .entity(MovieDAO.getMovieById(id))
                .build();
    }

    //TODO check if it's the right solution - uri-list
    @GET
    @Produces("application/json")
    @Consumes("text/uri-list")
    @Path("/uris")
    public Response getMovieURIs() {
        return Response.status(200)
                .entity(MovieDAO.getMovieURIs())
                .build();
    }

    @GET // This annotation indicates GET request
    @Produces("application/json")
    @Path("/")
    public Response getMoviesByTitle(String title) {
        return Response.status(200)
                .entity(MovieDAO.getMovieByTitle(title))
                .build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/{userId}")
    public Response createMovie(Movie movie, @PathParam("userId") int userId) {
        try {
            MovieDAO.addMovie(movie, userId);
        } catch (NullPointerException e) {
            Response.status(400).entity("User with given id does not exist").build();
        } catch (Exception e) {
            Response.status(500).build();
        }
        return Response.status(200).build();
    }

    @DELETE
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/{id}")
    public Response deleteMovie(@PathParam("id") int id) {
        MovieDAO.deleteMovie(id);
        return Response.status(200).build();
    }

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/")
    public Response updateMovie(Movie movie) {
        try{
            MovieDAO.updateMovie(movie);
        } catch (NullPointerException e) {
            return Response.status(400).entity("Movie with given id does not exist").build();
        } catch (Exception e) {
            return Response.status(500).build();
        }

        return Response.status(200).build();
    }
}
