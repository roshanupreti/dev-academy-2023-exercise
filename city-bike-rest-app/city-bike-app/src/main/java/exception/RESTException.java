package exception;


import exception.wrapper.ErrorWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static jakarta.ws.rs.core.Response.Status;
import static jakarta.ws.rs.core.Response.status;
import static java.lang.String.format;

/**
 * A RuntimeException wrapper provider class.
 *
 * @author roshan
 */
@Provider
public class RESTException extends RuntimeException implements ExceptionMapper<RESTException> {

    private static final long serialVersionUID = 1L;

    private final Integer statusCode;

    private final String responseMessage;

    public RESTException() {
        //Absence of an empty constructor will lead to the failure of instance creation during the runtime.
        this.statusCode = null;
        this.responseMessage = null;
    }

    public RESTException(Integer statusCode, String responseMessage) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
    }

    /*  transient -> not to be serialized when persisted to streams of bytes */
    @Context
    private transient UriInfo uriInfo;

    @Context
    private transient HttpServletRequest request;

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    /**
     * Whenever {@link RESTException} is thrown, the server returns a response, with ErrorWrapper as the entity that's
     * created based on the exception parameters.
     * */
    @Override
    public Response toResponse(RESTException exception) {
        Status status = Status.fromStatusCode(exception.getStatusCode());
        ErrorWrapper errorWrapper = new ErrorWrapper();
        errorWrapper.setCode(status.getStatusCode());
        errorWrapper.setStatus(status.getReasonPhrase());
        errorWrapper.setReason(exception.getResponseMessage());
        errorWrapper.setAction(request.getMethod());
        errorWrapper.setApiPath(format("%s%s", request.getServletPath(), request.getPathInfo()));
        return status(status).entity(errorWrapper).type(MediaType.APPLICATION_JSON).build();
    }
}
