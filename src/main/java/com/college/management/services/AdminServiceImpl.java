package com.college.management.services;

import com.college.management.command.StudentCommand;
import com.college.management.command.UserCommand;
import com.college.management.converter.StudentCommandToStudent;
import com.college.management.converter.StudentToStudentCommand;
import com.college.management.converter.UserCommandToUser;
import com.college.management.converter.UserToUserCommand;
import com.college.management.model.AdminInformation;
import com.college.management.model.Role;
import com.college.management.model.Student;
import com.college.management.model.User;
import com.college.management.repositories.AdminRepository;
import com.college.management.repositories.RoleRepository;
import com.college.management.repositories.StudentRepository;
import com.college.management.repositories.UserRepository;
import com.fasterxml.jackson.databind.BeanProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private StudentRepository studentRepository;



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserCommandToUser userCommandToUser;

    @Autowired
    private UserToUserCommand userToUserCommand;

    @Autowired
    private StudentCommandToStudent studentCommandToStudent;

    @Autowired
    private StudentToStudentCommand studentToStudentCommand;

    @Override
    @Transactional
    public AdminInformation selectFirstNameByEmail(String email) {

        AdminInformation admin = adminRepository.selectFirstNameByEmail(email);

        return admin;
    }

    @Override
    @Transactional
    public void saveBasicStudentInUser(UserCommand UserCommand) {

        User user = userCommandToUser.convert(UserCommand);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setEnabled(true);

        Role role = roleRepository.findByName(user.getRole());

        user.getRoles().add(role);


        userRepository.save(user);


    }

    @Override
    @Transactional
    public void saveStudent(StudentCommand studentCommand) {


        Student student = studentCommandToStudent.convert(studentCommand);

        student.getUser().setPassword(passwordEncoder.encode(student.getUser().getPassword()));

        student.getUser().setEnabled(true);

        //adding role to student
        Role role = roleRepository.findByName(student.getUser().getRole());
        student.getUser().getRoles().add(role);

        System.out.println(student);

        studentRepository.save(student);

    }

    @Override
    @Transactional
    public StudentCommand findStudentById(Long id) {

        Optional<Student> checkstudent = studentRepository.findById(id);

        Student student = null;
        if(checkstudent.isPresent()){
            student = checkstudent.get();
        }
        StudentCommand studentCommand = studentToStudentCommand.convert(student);
        return studentCommand;
    }

    @Override
    @Transactional
    public boolean checkIfStudentExists(Long id) {

        Optional<Student> checkstudent = studentRepository.findById(id);

        if(checkstudent.isPresent()){

            Student student = checkstudent.get();
            System.out.println(student);

            return true;
        }


        return false;
    }

    @Override
    @Transactional
    public void updateStudent(StudentCommand studentCommand) {


        //Student student = studentCommandToStudent.convert(studentCommand);



        studentRepository.updateStudent(
                studentCommand.getFirstName(),
                studentCommand.getLastName(),
                studentCommand.getAddress(),
                studentCommand.getGender(),
                studentCommand.getPhone(),
                studentCommand.getState(),
                studentCommand.getCity(),
                studentCommand.getBloodGroup(),
                studentCommand.getId()
        );


    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {

        userRepository.deleteById(id);


    }

    @Override
    @Transactional
    public boolean checkIfUserExists(String email) {

        Optional<User> checkUserIfExists = userRepository.findByEmail(email);

        if(checkUserIfExists.isPresent()){
            return true;
        }

        return false;
    }

    @Override
    public Long counNewlyRegisteredStudents() {

        Long count = userRepository.countByEnabledFalse();
        return count;
    }

    @Override
    @Transactional
    public void approveStudent(Long id) {

        userRepository.approveStudent(id);

    }

    @Override
    @Transactional
    public void deleteNewStudent(Long id) {

        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<UserCommand> selectNewlyRegisteredStudents() {

        List<User> newUsers = userRepository.selectNewlyRegisteredStudents();

        System.out.println(newUsers);

        if (newUsers != null) {

            List<UserCommand> userCommand = new ArrayList<>();
            for (User newUser : newUsers) {

                UserCommand tempUserCommand = userToUserCommand.convert(newUser);

               // System.out.println(newUser.getStudent().getFirstName() + newUser.getStudent().getLastName());
                tempUserCommand.setStudentCommand(studentToStudentCommand.convert(newUser.getStudent()));

                 userCommand.add(tempUserCommand);
                 System.out.print(tempUserCommand);
            }
            return userCommand;
        } else {

            return null;
        }
    }

    @Override
    @Transactional
    public List<User> findAllStudent() {

        List<User> students = userRepository.findAllStudent();

//        System.out.println("\n\n");
//        for (User student: students) {
//            System.out.println(student + "" +student.getStudent() + student.getUserPhoto());
//        }
//        System.out.println("\n\n");
        return students;
    }
}
