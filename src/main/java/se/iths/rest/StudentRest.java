package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @Path("new")
    @POST
    public Response createStudent(Student student)
    {
        if (student.getFirstName().getBytes().length < 2 || student.getFirstName().isBlank())
        {
            throw new TooShortNameException("{\"Error\": \"Name requires at least 2 characters. \"}");
        }
        if (student.getLastName().getBytes().length < 3 || student.getLastName().isBlank())
        {
            throw new TooShortNameException("{\"Error\": \"Surname requires at least 3 characters. \"}");
        }
        if (student.getEmail().getBytes().length < 6 || student.getEmail().isBlank())
        {
            throw new TooShortNameException("{\"Error\": \"Requires a total of 6 characters in the form of an email. \"}");
        }
        try {
            studentService.createStudent(student);
            return Response.ok(student).build();
        } catch (WebApplicationException i){
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @Path("update/{id}")
    @PATCH
    public Response updateStudent(@PathParam("id") Long id, Student student) {

        Student updatedStudent = studentService.findStudentById(id);

        String errorMessage = "{\"Error\": \"No students found with id " + id + "\"}";
        try {

            if (updatedStudent == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(errorMessage).type(MediaType.APPLICATION_JSON).build();
            }

            updatedStudent.setFirstName(student.getFirstName());
            updatedStudent.setLastName(student.getLastName());
            updatedStudent.setEmail(student.getEmail());
            updatedStudent.setPhoneNumber(student.getPhoneNumber());

            studentService.updateStudent(updatedStudent);

            return Response.accepted(updatedStudent).build();
        } catch (WebApplicationException i) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage).type(MediaType.APPLICATION_JSON).build();
        }

    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        Student student = studentService.findStudentById(id);
        try {
            if (student == null) {
                String errorMessage = "{\"Error\": \"No students found with that id.\"}";
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(errorMessage).type(MediaType.APPLICATION_JSON).build();
            }

            return Response.accepted(student).build();
        } catch (WebApplicationException i) {
            String errorMessage = "{\"Error\": \"No students found with that id.\"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).type(MediaType.APPLICATION_JSON).build();
        }

    }

    @Path("getall")
    @GET
    public List<Student> getAllStudent() {
        List<Student> foundAllStudents = studentService.getAllStudents();
        if (foundAllStudents == null || foundAllStudents.isEmpty()) {
            String errorMessage = "{\"Error\": \"No students found.\"}";
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(errorMessage).type(MediaType.APPLICATION_JSON).build());
        }

        return studentService.getAllStudents();
    }

    @Path("query")
    @GET
    public Response getStudentByLastName(@QueryParam("lastname") String lastname)
    {
        String message = "{\"Error\": \"No student found with that lastname.\"}";
            List<Student> foundStudent = studentService.getStudentByLastName(lastname);
            if (foundStudent == null || foundStudent.isEmpty()) {
                throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                        .entity(message).type(MediaType.APPLICATION_JSON).build());
            }
            return Response.ok(foundStudent).build();
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id")Long id)
    {
        Student getStudent = studentService.findStudentById(id);
        String errorMessage = "{\"Error\": \"Student with id " + id + " was not found\"}";
        if (getStudent == null)
        {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(errorMessage).type(MediaType.APPLICATION_JSON).build());
        }

        studentService.deleteStudent(getStudent);
        String message = "{\"Notice\": \"Student with id " + id + " has been deleted.\"}";
        return Response.status(Response.Status.ACCEPTED)
                .entity(message).type(MediaType.APPLICATION_JSON).build();
    }

}
