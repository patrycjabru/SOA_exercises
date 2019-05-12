package com.rest; // Note your package will be {{ groupId }}.rest

import com.DAO.UserDAO;
import com.DTO.User;

import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Path("/users")
public class UserService {
    @GET // This annotation indicates GET request
    @Produces("application/json")
    @Path("/{id}")
    public Response getUser(@PathParam("id") int id) {
        return Response.status(200)
                .entity(UserDAO.getUserById(id))
                .build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/")
    public Response createUser(User user) {
        UserDAO.addUser(user);
        return Response.status(200).build();
    }

    @DELETE
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        UserDAO.deleteUser(id);
        return Response.status(200).build();
    }

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/")
    public Response updateUser(User user) {
        try{
            UserDAO.updateUser(user);
        } catch (NullPointerException e) {
            return Response.status(400).entity("User with given id does not exist").build();
        } catch (Exception e) {
            return Response.status(500).build();
        }

        return Response.status(200).build();
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @HttpMethod("PATCH")
    public @interface PATCH {}

    @PATCH
    @Path("/")
    public Response updateUserAge(@QueryParam("id") int userId, @QueryParam("age") int age) {
        try{
            UserDAO.updateUserAge(userId, age);
        } catch (NullPointerException e) {
            return Response.status(400).entity("User with given id does not exist").build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
        return Response.status(200).build();
    }

    @GET
    @Path("/{id}/avatar")
    public Response getUserAvatar(@PathParam("id") int id) {
        User user = UserDAO.getUserById(id);
        if (user==null)
            return Response.status(404).entity("User with given id does not exist").build();

        byte[] imageData;
        try {
            File file = new File(user.getAvatar());
            BufferedImage image = ImageIO.read(file);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            imageData = baos.toByteArray();
        } catch (IOException e) {
            return Response.status(500).entity("Cannot open the file").build();
        }
        return Response.ok(new ByteArrayInputStream(imageData)).build();
    }
}