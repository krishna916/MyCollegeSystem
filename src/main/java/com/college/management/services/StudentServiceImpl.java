package com.college.management.services;

import com.college.management.command.StudentCommand;
import com.college.management.converter.StudentCommandToStudent;
import com.college.management.converter.StudentToStudentCommand;
import com.college.management.model.Student;
import com.college.management.model.User;
import com.college.management.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@PreAuthorize("hasRole('ROLE_STUDENT')")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCommandToStudent studentCommandToStudent;

    @Autowired
    private StudentToStudentCommand studentToStudentCommand;

    @Override
    public StudentCommand findById(Long id) {

        Optional<Student> student = studentRepository.findById(id);

        if(student.isPresent()){
            return studentToStudentCommand.convert(student.get());
        }else {

            return null;
        }
    }

    @Override
    @Transactional
    public StudentCommand findByEmail(String email) {

        Student student = studentRepository.selectStudentFromUserEmail(email);




        return studentToStudentCommand.convert(student);
    }

    @Override
    @Transactional
    public void updateStudent(StudentCommand studentCommand, Long id) {

        //studentRepository.save(studentCommandToStudent.convert(studentCommand));



        studentRepository.updateStudent(studentCommand.getFirstName(),
                studentCommand.getLastName(),
                studentCommand.getAddress(),
                studentCommand.getGender(),
                studentCommand.getPhone(),
                studentCommand.getState(),
                studentCommand.getCity(),
                studentCommand.getBloodGroup(), id);

    }

    @Override
    @Transactional
    public void saveStudentInfo(StudentCommand studentCommand) {

        //User user = new User();
        Student detachedStudent = studentCommandToStudent.convert(studentCommand);

        Student savedStudent = studentRepository.save(detachedStudent);
        System.out.println("Saved StudentId: " + savedStudent.getId());


    }
}
