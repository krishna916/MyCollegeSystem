package com.college.management.services;


import com.college.management.command.StudentCommand;
import com.college.management.command.UserCommand;
import com.college.management.model.AdminInformation;
import com.college.management.model.User;

import java.util.List;

public interface AdminService {

    AdminInformation selectFirstNameByEmail(String email);

     void saveBasicStudentInUser(UserCommand userCommand);

     void saveStudent(StudentCommand studentCommand);

     StudentCommand findStudentById(Long id);

    boolean checkIfStudentExists(Long id);

    void updateStudent(StudentCommand studentCommand);

    void deleteStudent(Long id);

    boolean checkIfUserExists(String email);

    Long counNewlyRegisteredStudents();

    void approveStudent(Long id);

    void deleteNewStudent(Long id);

    List<UserCommand> selectNewlyRegisteredStudents();





    List<User> findAllStudent();


}
