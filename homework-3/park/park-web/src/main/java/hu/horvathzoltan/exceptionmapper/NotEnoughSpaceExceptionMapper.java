package hu.horvathzoltan.exceptionmapper;

import hu.horvathzoltan.dto.ErrorDTO;
import hu.horvathzoltan.exception.NotEnoughSpaceException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotEnoughSpaceExceptionMapper implements ExceptionMapper<NotEnoughSpaceException> {

    @Override
    public Response toResponse(NotEnoughSpaceException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorDTO(exception.getMessage() + " - " + exception.getCause())).type(MediaType.APPLICATION_JSON).build();
    }

}
