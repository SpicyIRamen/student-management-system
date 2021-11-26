package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teach_Id;

    @NotEmpty
    @NotNull
    @Size(min = 2)
    private String teachFirstName;

    @NotEmpty
    @NotNull
    @Size(min = 3)
    private String teachSurName;

    @ManyToOne
    private Student student;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Subject> subjects = new ArrayList<>();

    public Teacher (String teachFirstName, String teachSurName)
    {
        this.teachFirstName = teachFirstName;
        this.teachSurName = teachSurName;
    }
    public Teacher(){}

    public void addSubject(Subject subject){
        subjects.add(subject);
        subject.setTeacher(this);
    }

    public Long getTeach_Id() {
        return teach_Id;
    }

    public void setTeach_Id(Long teach_id) {
        this.teach_Id = teach_id;
    }

    public String getTeachFirstName() {
        return teachFirstName;
    }

    public void setTeachFirstName(String teachFirstName) {
        this.teachFirstName = teachFirstName;
    }

    public String getTeachSurName() {
        return teachSurName;
    }

    public void setTeachSurName(String teachSurName) {
        this.teachSurName = teachSurName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @JsonbTransient
    public List<Subject> getSubjects()
    {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects)
    {
        this.subjects = subjects;
    }
}
