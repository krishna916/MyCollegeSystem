package com.college.management.services;

import com.college.management.command.StudentCommand;
import com.college.management.model.Student;

public interface StudentService {

    StudentCommand findById(Long id);
    StudentCommand findByEmail(String email);

    void updateStudent(StudentCommand studentCommand, Long id);

    void saveStudentInfo(StudentCommand studentCommand);
}
