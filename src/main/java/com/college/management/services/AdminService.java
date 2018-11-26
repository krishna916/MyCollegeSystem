package com.college.management.services;


import com.college.management.command.*;
import com.college.management.model.AdminInformation;
import com.college.management.model.Department;
import com.college.management.model.User;

import java.util.List;
import java.util.Map;

public interface AdminService {


    // For Students //

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

    // ###################### Related to Course and Department################### //

    Map<Long, String> findAllDepartments();

    boolean checkIfCourseExists(String courseCode);

    void saveCourse(CourseCommand courseCommand);

    List<CourseCommand> showAllCourses();

    void deleteCourse(Long courseId);



    // ##########################  PROFESSOR RELATED   ###################  //

    boolean checkIfProfessorExists(String email);

    void saveProfessor(ProfessorCommand professorCommand);

    List<UserCommand> showAllProfessors();

    ProfessorCommand findProfessorById(Long id);

    void updateProfessor(ProfessorCommand professorCommand);

    void deleteProfessor(Long userId);


}
