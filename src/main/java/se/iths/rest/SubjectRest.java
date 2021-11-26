package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.service.SubjectService;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subject")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @Path("new")
    @POST
    public Response createSubject(Subject subject)
    {
        if (subject.getName().getBytes().length < 2 || subject.getName().isBlank())
        {
            throw new TooShortNameException("{\"Error\": \"Name requires at least 2 characters. \"}");
        }
        subjectService.createSubject(subject);
        return Response.ok(subject).build();
    }
}
