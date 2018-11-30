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
    private String departmentName;

    @Column(name="department_code", nullable = false, length = 4, unique = true)
    private String departmentCode;

    @Transient
    private Student student;

    @Transient
    private Course course;

    @Transient
    private Professor professor;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Student> students = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Course> courses = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Professor> professors = new ArrayList<>();


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Batch> batches = new ArrayList<>();

    private String batch;

    public Department(){}

    public Department(Long id,String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {

        if(departmentCode != null){
            this.departmentCode = departmentCode.toUpperCase();
        }

        this.departmentCode = null;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", departmentCode='" + departmentCode + '\'' +


                '}';
    }
}
