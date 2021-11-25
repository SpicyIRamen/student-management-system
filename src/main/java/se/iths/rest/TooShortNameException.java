package se.iths.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TooShortNameException extends WebApplicationException {
    public TooShortNameException(String message)
    {
        super(Response.status(Response.Status.NOT_ACCEPTABLE).entity(message).type(MediaType.APPLICATION_JSON).build());
    }
}
