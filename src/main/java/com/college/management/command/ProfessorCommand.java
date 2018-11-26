package com.college.management.command;

import com.college.management.model.Course;
import com.college.management.model.Department;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class ProfessorCommand {

    private Long id;


    @NotNull(message = "is required")
    @NotBlank
    @Size(min = 3, max = 20)
    private String firstName;

    @NotNull(message = "is required")
    @NotBlank
    @Size(min = 3, max = 20)
    private String lastName;

    @NotBlank @NotNull(message = "is required")
    @Size(min=5, max = 100)
    private String address;


    @Size(min = 4, max = 6)
    private String gender;

    @Size(min = 10, max = 13)
    private String phone;

    private String bloodGroup;

    @NotBlank
    @NotNull(message = "is required")
    @Size(min = 1, max = 30)
    private String state;

    @NotBlank
    @NotNull(message = "is required")
    @Size(min = 1, max = 30)
    private String city;

    private String dob;

    private LocalDate dateOfBirth;

    private Long userId;

    private UserCommand userCommand;

    private String course;

    private Set<CourseCommand> coursesCommand = new HashSet<>();

    private DepartmentCommand departmentCommand;


    public ProfessorCommand(){}



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


    public String getDob() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        if(dateOfBirth == null){
            return null;
        }else{
            String date = dateOfBirth.format(formatter);

            return date;
        }


    }

    public void setDob(String dob) {

        if (dob == null) {
            this.dateOfBirth = null;
        } else {

            this.dateOfBirth = LocalDate.parse(dob);
        }
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public UserCommand getUserCommand() {
        return userCommand;
    }

    public void setUserCommand(UserCommand userCommand) {
        this.userCommand = userCommand;
    }


    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Set<CourseCommand> getCoursesCommand() {
        return coursesCommand;
    }

    public void setCoursesCommand(Set<CourseCommand> coursesCommand) {
        this.coursesCommand = coursesCommand;
    }

    public DepartmentCommand getDepartmentCommand() {
        return departmentCommand;
    }

    public void setDepartmentCommand(DepartmentCommand departmentCommand) {
        this.departmentCommand = departmentCommand;
    }

    @Override
    public String toString() {
        return "StudentCommand{" +
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
                ", userCommand=" + userCommand +
                ", departmentCommand=" + departmentCommand +
                '}';
    }

}
