package com.college.management.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;

import java.util.Set;

@Entity
@Table(name="student")
@DynamicUpdate
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true)
    private Long id;

    @Column(name="first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name="last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name="address" )
    private String address;

    @Column(name="gender", nullable = false, length = 20)
    private String gender;

    @Column(name="phone", length = 13)
    private String phone;

    @Column(name="blood_group", length = 20)
    private String bloodGroup;

    @Column(name="state", length = 50)
    private String state;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name="registered_date")
    private LocalDate registeredDate = LocalDate.now();

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name="user_id")
    private User user;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name="STUDENT_COURSE",
            joinColumns = @JoinColumn(name="student_id"),
            inverseJoinColumns = @JoinColumn(name="course_id")
    )
    private Set<Course> courses = new HashSet<>();

    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "department_id")
    private Department department ;

    @ManyToMany(mappedBy = "students")
    private Set<Batch> batches = new HashSet<>();



    public Student(){}

    public Student(Long id){
        this.id = id;
    }

    public Student(User user){
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Batch> getBatches() {
        return batches;
    }

    public void setBatches(Set<Batch> batches) {
        this.batches = batches;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", registeredDate=" + registeredDate +
                ", user=" + user +
                ", department=" + department +

                '}';
    }
}
