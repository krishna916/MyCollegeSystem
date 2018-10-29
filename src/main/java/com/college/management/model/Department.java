package com.college.management.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="department_name", nullable = false, length = 50)
    private String name;

    @Column(name="department_code", nullable = false, length = 4)
    private String departmentCode;

    @Transient
    private String student;

    @Transient
    private String course;

    @Transient
    private String professor;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Student> students = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "department", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Professor> professors = new ArrayList<>();



    public Department(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
