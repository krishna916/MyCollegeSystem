package com.college.management.services;

import com.college.management.command.UserCommand;
import com.college.management.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserCommand findByEmail(String email);

    boolean checkIfUserExists(String email);

    void registerStudent(UserCommand userCommand);

}
