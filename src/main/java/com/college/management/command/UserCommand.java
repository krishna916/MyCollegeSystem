package com.college.management.command;

import com.college.management.model.Role;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Component
public class UserCommand {

    private Long id;

    @NotNull
    @Email
    @NotBlank
    private String email;


    @NotNull
    @Size(min = 5, max = 25)
    @NotBlank
    private String password;

    private boolean enabled;


    private List<Role> roles;

    @NotNull(message = "is required")
    @Size(min = 1, max = 25)
    private String role;

    private Long countNewStudents;

    private AdminInformationCommand adminInformationCommand;

    private StudentCommand studentCommand;

    private UserPhotoCommand userPhotoCommand;

    public UserCommand(){}



    public UserCommand(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null) {
            this.email = null;
        } else {
            this.email = email.toLowerCase();
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public AdminInformationCommand getAdminInformationCommand() {
        return adminInformationCommand;
    }

    public void setAdminInformationCommand(AdminInformationCommand adminInformationCommand) {
        this.adminInformationCommand = adminInformationCommand;
    }

    public StudentCommand getStudentCommand() {
        return studentCommand;
    }

    public void setStudentCommand(StudentCommand studentCommand) {
        this.studentCommand = studentCommand;
    }

    public void setUserPhotoCommand(UserPhotoCommand userPhotoCommand) {
        this.userPhotoCommand = userPhotoCommand;
    }

    public UserPhotoCommand getUserPhotoCommand() {
        return userPhotoCommand;
    }

    public Long getCountNewStudents() {
        return countNewStudents;
    }

    public void setCountNewStudents(Long countNewStudents) {
        this.countNewStudents = countNewStudents;
    }

    @Override
    public String toString() {
        return "UserCommand{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                ", role='" + role + '\'' +
                ", adminInformationCommand=" + adminInformationCommand +
                ", studentCommand=" + studentCommand +
                '}';
    }
}
