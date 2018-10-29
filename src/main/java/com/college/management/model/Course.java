package com.college.management.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="course_name", nullable = false, length = 50)
    private String name;

    @Column(name="course_code", nullable = false, length = 4)
    private String courseCode;

    @Column(name = "course_details")
    private String courseDetails;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Student> students = new HashSet<>();

    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="department_id")
    private Department department;


    public Course(){}



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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(getId(), course.getId()) &&
                Objects.equals(getName(), course.getName()) &&
                Objects.equals(getDepartment(), course.getDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDepartment());
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                '}';
    }
}
