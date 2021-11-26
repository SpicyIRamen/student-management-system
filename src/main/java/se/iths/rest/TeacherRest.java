package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @Path("new")
    @POST
    public Response createTeacher(Teacher teacher)
    {
        if (teacher.getTeachFirstName().getBytes().length < 2 || teacher.getTeachFirstName().isBlank())
        {
            throw new TooShortNameException("{\"Error\": \"Name requires at least 2 characters. \"}");
        }
        teacherService.createTeacher(teacher);
        return Response.ok(teacher).build();
    }


    @Path("getall")
    @GET
    public Response getAllTeachers()
    {
        String errorEmpty = "{\"Error\": \" There are no Teachers.\"}";
        List<Teacher> foundTeacher = teacherService.getAllTeachers();
        if (foundTeacher == null || foundTeacher.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorEmpty).type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundTeacher).build();
    }
}
