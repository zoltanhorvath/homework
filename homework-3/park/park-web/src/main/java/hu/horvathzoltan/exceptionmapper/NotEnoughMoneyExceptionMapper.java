package hu.horvathzoltan.exceptionmapper;

import hu.horvathzoltan.dto.ErrorDTO;
import hu.horvathzoltan.exception.NotEnoughMoneyException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotEnoughMoneyExceptionMapper implements ExceptionMapper<NotEnoughMoneyException> {

    @Override
    public Response toResponse(NotEnoughMoneyException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorDTO(exception.getMessage() + " - " + exception.getCause())).type(MediaType.APPLICATION_JSON).build();
    }

}
