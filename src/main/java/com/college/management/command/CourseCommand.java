package com.college.management.command;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class CourseCommand {

    private Long id;

    @NotBlank
    @Size(min = 5, max = 50)
    private String courseName;

    @NotBlank
    @Size(min = 3, max = 4)
    private String courseCode;

    @Size(min = 15, max = 100)
    private String courseDetails;

    private StudentCommand studentCommand;

    private Set<StudentCommand> studentCommands = new HashSet<>();

    private Set<ProfessorCommand> professorCommands = new HashSet<>();

    private DepartmentCommand departmentCommand;


    public CourseCommand(){}

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

    public StudentCommand getStudentCommand() {
        return studentCommand;
    }

    public void setStudentCommand(StudentCommand studentCommand) {
        this.studentCommand = studentCommand;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {

        if (courseCode != null) {
            this.courseCode = courseCode.toUpperCase();
        }else{
            this.courseCode = null;
        }
    }

    public String getCourseDetails() {
        return courseDetails;
    }

    public void setCourseDetails(String courseDetails) {
        this.courseDetails = courseDetails;
    }

    public Set<StudentCommand> getStudentCommands() {
        return studentCommands;
    }

    public void setStudentCommands(Set<StudentCommand> studentCommands) {
        this.studentCommands = studentCommands;
    }

    public Set<ProfessorCommand> getProfessorCommands() {
        return professorCommands;
    }

    public void setProfessorCommands(Set<ProfessorCommand> professorCommands) {
        this.professorCommands = professorCommands;
    }

    public DepartmentCommand getDepartmentCommand() {
        return departmentCommand;
    }

    public void setDepartmentCommand(DepartmentCommand departmentCommand) {
        this.departmentCommand = departmentCommand;
    }

    @Override
    public String toString() {
        return "CourseCommand{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", courseDetails='" + courseDetails + '\'' +

                ", departmentCommand=" + departmentCommand +
                '}';
    }
}
