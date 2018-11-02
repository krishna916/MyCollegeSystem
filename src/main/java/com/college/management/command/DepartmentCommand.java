package com.college.management.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class DepartmentCommand {

    private Long id;


    @NotBlank
    @Size(min = 5, max = 25)
    private String departmentName;

    @NotBlank
    @Size(min = 3, max = 4)
    private String departmentCode;

    private StudentCommand studentCommand;

    private ProfessorCommand professorCommand;

    private CourseCommand courseCommand;

    private List<StudentCommand> studentCommands = new ArrayList<>();

    private List<ProfessorCommand> professorCommands = new ArrayList<>();

    private List<CourseCommand> courseCommands = new ArrayList<>();

    public DepartmentCommand(){}

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

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {

        if(departmentCode != null){
            this.departmentCode = departmentCode.toUpperCase();
        }else{
            this.departmentCode = null;
        }

    }

    public StudentCommand getStudentCommand() {
        return studentCommand;
    }

    public void setStudentCommand(StudentCommand studentCommand) {
        this.studentCommand = studentCommand;
    }

    public ProfessorCommand getProfessorCommand() {
        return professorCommand;
    }

    public void setProfessorCommand(ProfessorCommand professorCommand) {
        this.professorCommand = professorCommand;
    }

    public CourseCommand getCourseCommand() {
        return courseCommand;
    }

    public void setCourseCommand(CourseCommand courseCommand) {
        this.courseCommand = courseCommand;
    }

    public List<StudentCommand> getStudentCommands() {
        return studentCommands;
    }

    public void setStudentCommands(List<StudentCommand> studentCommands) {
        this.studentCommands = studentCommands;
    }

    public List<ProfessorCommand> getProfessorCommands() {
        return professorCommands;
    }

    public void setProfessorCommands(List<ProfessorCommand> professorCommands) {
        this.professorCommands = professorCommands;
    }

    public List<CourseCommand> getCourseCommands() {
        return courseCommands;
    }

    public void setCourseCommands(List<CourseCommand> courseCommands) {
        this.courseCommands = courseCommands;
    }

    @Override
    public String toString() {
        return "DepartmentCommand{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", departmentCode='" + departmentCode + '\'' +


                '}';
    }
}
