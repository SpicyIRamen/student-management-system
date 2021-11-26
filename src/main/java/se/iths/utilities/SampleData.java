package se.iths.utilities;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class SampleData {
    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void sampleData() {
        Teacher teacher1 = new Teacher("John", "Johnsson");
        Teacher teacher2 = new Teacher("Anders", "Andersson");

        Student student1 = new Student("Sean", "Werner", "sw@hotmail.com", "1234");
        Student student2 = new Student("John", "Wick", "jw@hotmail.com", "4321");
        Student student3 = new Student("Batman", "Batmansson", "IAmBatman@outlook.com", "1337");

        Subject subject1 = new Subject("Sceince");
        Subject subject2 = new Subject("Biology");
        Subject subject3 = new Subject("Language");


        teacher1.addSubject(subject1);
        teacher1.addSubject(subject2);
        teacher1.addSubject(subject3);

        student1.addSubject(subject1);
        student1.addSubject(subject2);

        student2.addSubject(subject2);
        student2.addSubject(subject3);

        student3.addSubject(subject1);
        student3.addSubject(subject2);

        entityManager.persist(teacher1);
        entityManager.persist(teacher2);

        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);
    }
}
