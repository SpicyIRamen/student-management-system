package se.iths.rest;

import se.iths.entity.Student;
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
        if (subject.getSubName().getBytes().length < 2 || subject.getSubName().isBlank())
        {
            throw new TooShortNameException("{\"Error\": \"Name requires at least 2 characters. \"}");
        }
        subjectService.createSubject(subject);
        return Response.ok(subject).build();
    }


    @Path("getall")
    @GET
    public Response getAllSubjects()
    {
        String errorEmpty = "{\"Error\": \" There are no Subjects.\"}";
        List<Subject> foundSubject = subjectService.getAllSubjects();
        if (foundSubject == null || foundSubject.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorEmpty).type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundSubject).build();
    }

    @Path("query")
    @GET
    public Response getStudentsInSubject(@QueryParam("subName") String subName)
    {
        String message = "{\"Error\": \"There are no Subjects with that name.\"}";
        List<Student> foundStudent = subjectService.getSubjectByName(subName);
        if (foundStudent == null || foundStudent.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(message).type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundStudent).build();
    }
}
