package hu.horvathzoltan.exceptionmapper;

import hu.horvathzoltan.dto.ErrorDTO;
import hu.horvathzoltan.exception.VisitorIsNotRidingMachineException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class VisitorIsNotRidingMachineExceptionMapper implements ExceptionMapper<VisitorIsNotRidingMachineException> {

    @Override
    public Response toResponse(VisitorIsNotRidingMachineException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorDTO(exception.getMessage() + " - " + exception.getCause())).type(MediaType.APPLICATION_JSON).build();
    }

}
