package uce.edu.web.api.interfaces;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Consumes;
import uce.edu.web.api.domain.User;
import java.util.List;
import uce.edu.web.api.application.UserService;
import uce.edu.web.api.application.representation.UserRepresentation;

@Path("/users")
@ApplicationScoped
public class UserResource {
    @Inject
    UserService userService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserRepresentation> listarUsers() {
        return userService.listarUsers();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") Long id) {
        return Response.ok(userService.consultarPorId(id)).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        userService.crearUser(user);
        return Response.status(Response.Status.CREATED).entity(userService.mapperToUR(user)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@jakarta.ws.rs.PathParam("id") Long id,
            uce.edu.web.api.application.representation.UserRepresentation user) {
        userService.actualizarUser(id, user);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@jakarta.ws.rs.PathParam("id") Long id) {
        userService.borrarUser(id);
        return Response.noContent().build();
    }
}
