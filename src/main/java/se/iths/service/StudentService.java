package se.iths.service;


import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public Student createStudent(Student student)
    {
        entityManager.persist(student);
        return student;
    }

    public void updateStudent(Student student)
    {
        entityManager.merge(student);
    }

    public Student findStudentById(Long id)
    {
        return entityManager.find(Student.class, id);
    }

    public List<Student> getStudentByLastName(String lastName)
    {
        return entityManager.createQuery("SELECT i FROM Student i WHERE i.lastName LIKE :lastName")
                .setParameter("lastName", lastName)
                .setMaxResults(3).getResultList();
    }

    public List<Student> getAllStudents()
    {
        return entityManager.createQuery("SELECT i from Student i", Student.class).getResultList();
    }

    public void deleteStudent(Student student)
    {
        if (!entityManager.contains(student))
        {
            student = entityManager.merge(student);
        }
        entityManager.remove(student);
    }
}
