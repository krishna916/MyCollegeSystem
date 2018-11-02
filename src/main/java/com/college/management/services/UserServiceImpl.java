package com.college.management.services;

import com.college.management.command.StudentCommand;
import com.college.management.command.UserCommand;
import com.college.management.converter.StudentCommandToStudent;
import com.college.management.converter.UserCommandToUser;
import com.college.management.converter.UserToUserCommand;
import com.college.management.model.Department;
import com.college.management.model.Role;
import com.college.management.model.Student;
import com.college.management.model.User;
import com.college.management.repositories.DepartmentRepository;
import com.college.management.repositories.RoleRepository;
import com.college.management.repositories.StudentRepository;
import com.college.management.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private UserCommandToUser userCommandToUser;

    @Autowired
    private UserToUserCommand userToUserCommand;

    @Autowired
    private StudentCommandToStudent studentCommandToStudent;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = null;

        Optional<User> optionalUser = userRepository.findByEmail(username);

        if(optionalUser.isPresent()){
            user = optionalUser.get();
        }else{
            throw new UsernameNotFoundException("User Not Found");
        }

        return user;
    }

    @Override
    @Transactional
    public UserCommand findByEmail(String email) {

        User user = null;

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isPresent()){
            user = optionalUser.get();
        }else{
            throw new UsernameNotFoundException("User Not Found");
        }

        return userToUserCommand.convert(user);


    }

    @Override
    public boolean checkIfUserExists(String email) {

        Optional<User> checkUser = userRepository.checkIfExits(email);

        if(checkUser.isPresent()){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void registerStudent(UserCommand userCommand) {


        User user = userCommandToUser.convert(userCommand);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //user.getStudent().setFirstName(userCommand.getStudentCommand().getFirstName());
        //user.getStudent().setLastName(userCommand.getStudentCommand().getLastName());

       // user.setStudent(studentCommandToStudent.convert(userCommand.getStudentCommand()));

        Role role = roleRepository.findByName(user.getRole());

        user.getRoles().add(role);




        System.out.println(user);

        User savedUser = userRepository.save(user);

        StudentCommand studentCommand = userCommand.getStudentCommand();
        Student student = studentCommandToStudent.convert(studentCommand);

        Optional<Department> department = departmentRepository.findById(userCommand.getStudentCommand().getDepartmentCommand().getId());
        if(department.isPresent()){
            student.setDepartment(department.get());
        }


        Optional<User> justSavedUser = userRepository.findByEmail(savedUser.getEmail());
        if(justSavedUser.isPresent()){

            student.setUser(justSavedUser.get());
        }

        studentRepository.save(student);


        System.out.println(student);


       // return userToUserCommand.convert(savedUser);

    }

    @Override
    public Map<Long, String> findAllDepartments() {
        List<Department> tempDepartments = departmentRepository.selectDepartmentIdAndDepartmentName();

        List<Department> check = new ArrayList<>();

        Map<Long, String> tempDepartment = new LinkedHashMap<>();
        Iterator itr = tempDepartments.iterator();
        while (itr.hasNext()){
            Object[] objects = (Object[]) itr.next();
            //now have one array object for each row

//            Department department = new Department();
//            department.setId(Long.parseLong(String.valueOf(objects[0])));
//            department.setDepartmentName(String.valueOf(objects[1]));
//            check.add(department);


            tempDepartment.put(Long.parseLong(String.valueOf(objects[0])), String.valueOf(objects[1]));
        }
        return tempDepartment;
    }
}
