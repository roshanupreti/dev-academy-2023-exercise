package exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ApplicationException extends RuntimeException implements ExceptionMapper<ApplicationException> {

    private final Integer statusCode;
    private final String responseMessage;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public ApplicationException() {
        this.statusCode = null;
        this.responseMessage = null;
    }

    public ApplicationException(Integer statusCode, String responseMessage) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
    }

    /**
     * Map an exception to a {@link Response}. Returning {@code null} results in a
     * {@link Response.Status#NO_CONTENT} response. Throwing a runtime exception results in a
     * {@link Response.Status#INTERNAL_SERVER_ERROR} response.
     *
     * @param exception the exception to map to a response.
     * @return a response mapped from the supplied exception.
     */
    @Override
    public Response toResponse(ApplicationException exception) {
        return Response
                .status(statusCode)
                .entity(responseMessage)
                .build();
    }
}
