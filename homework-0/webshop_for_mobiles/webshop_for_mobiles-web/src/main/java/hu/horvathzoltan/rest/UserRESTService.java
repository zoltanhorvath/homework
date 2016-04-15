package hu.horvathzoltan.rest;

import hu.horvathzoltan.annotation.BeanValidation;
import hu.horvathzoltan.dto.UserDTO;
import hu.horvathzoltan.service.UserManagementService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@BeanValidation
public class UserRESTService {

    @Inject
    private UserManagementService userManagementService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public UserDTO addUser(UserDTO userDTO) {
        return userDTO;
    }

    @DELETE
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO removeUser(@PathParam("username") String username) {
        return userManagementService.removeUser(username);
    }

    @PUT
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO editUser(@PathParam("username") String username, UserDTO userDTO) {
        return userManagementService.editUser(username, userDTO);
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getUser(@PathParam("username") String username) {
        return userManagementService.getUser(username);
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getUsers() {
        return userManagementService.getUsers();
    }

    @POST
    @Path("/login")
    public void login(@QueryParam("username") String username, @QueryParam("password") String password, @Context HttpServletRequest request) {
        UserDTO userDTO = userManagementService.getUser(username);
        if (userDTO != null && userDTO.getPassword().equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", userDTO);

        }
    }

    @POST
    @Path("/logout")
    public void logout(@Context HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
