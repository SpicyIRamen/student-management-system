package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sub_id;

    @NotEmpty
    @NotNull
    @Size(min = 3)
    private String subName;

    @ManyToMany
    private List<Student> students = new ArrayList<>();

    @ManyToOne
    private Teacher teacher;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Long getSub_id() {
        return sub_id;
    }

    public void setSub_id(Long sub_id) {
        this.sub_id = sub_id;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
