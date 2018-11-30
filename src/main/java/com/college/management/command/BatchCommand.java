package com.college.management.command;




import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class BatchCommand {

    private Long id;

    @NotBlank
    @Size(min = 5, max = 15)
    private String batchCode;

    @NotBlank
    private String batchLimit;

    private Set<StudentCommand> studentCommands = new HashSet<>();

    private ProfessorCommand professorCommand;

    private CourseCommand courseCommand;

    private DepartmentCommand departmentCommand;


    private int numberOfStudents;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public Set<StudentCommand> getStudentCommands() {
        return studentCommands;
    }

    public void setStudentCommands(Set<StudentCommand> studentCommands) {
        this.studentCommands = studentCommands;
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

    public DepartmentCommand getDepartmentCommand() {
        return departmentCommand;
    }

    public void setDepartmentCommand(DepartmentCommand departmentCommand) {
        this.departmentCommand = departmentCommand;
    }


    public String getBatchLimit() {
        return batchLimit;
    }

    public void setBatchLimit(String batchLimit) {
        this.batchLimit = batchLimit;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    @Override
    public String toString() {
        return "BatchCommand{" +
                "id=" + id +
                ", batchCode='" + batchCode + '\'' +
                ", batchLimit='" + batchLimit + '\'' +
                ", numberOfStudents='" + numberOfStudents + '\'' +
                ", professorCommand=" + professorCommand +
                ", courseCommand=" + courseCommand +
                ", departmentCommand=" + departmentCommand +
                '}';
    }
}
