package com.college.management.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "course")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="course_name", nullable = false, length = 50)
    private String courseName;

    @Column(name="course_code", nullable = false, length = 4, unique = true)
    private String courseCode;

    @Column(name = "course_details")
    private String courseDetails;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Student> students = new HashSet<>();

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Professor> professors = new HashSet<>();

    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="department_id")
    private Department department;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course",cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Batch> batches = new ArrayList<>();



    @Transient
    private String departmentName;


    public Course(){}

    public Course(Long id, String courseName, String courseCode, String departmentName) {
        this.id = id;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.departmentName = departmentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {

        if(courseCode != null){
            this.courseCode = courseCode.toUpperCase();
        }else {

            this.courseCode = null;
        }
    }

    public String getCourseDetails() {
        return courseDetails;
    }

    public void setCourseDetails(String courseDetails) {
        this.courseDetails = courseDetails;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(getId(), course.getId()) &&
                Objects.equals(getCourseName(), course.getCourseName()) &&
                Objects.equals(getCourseCode(), course.getCourseCode()) &&
                Objects.equals(getCourseDetails(), course.getCourseDetails()) &&
                Objects.equals(getStudents(), course.getStudents()) &&
                Objects.equals(getProfessors(), course.getProfessors()) &&
                Objects.equals(getDepartment(), course.getDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCourseName(), getCourseCode(), getCourseDetails(), getStudents(), getProfessors(), getDepartment());
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", courseDetails='" + courseDetails + '\'' +
                ", department=" + department +
                '}';
    }
}
