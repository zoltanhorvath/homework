package hu.horvathzoltan.exceptionmapper;

import hu.horvathzoltan.dto.ErrorDTO;

import javax.inject.Inject;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Inject
    private Logger logger;
    @Override
    public Response toResponse(ValidationException exception) {
        logger.log(Level.SEVERE, "Validation Exception", exception);
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorDTO(exception.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }

}