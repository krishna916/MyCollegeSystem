package com.college.management.services;

import com.college.management.command.*;
import com.college.management.converter.*;
import com.college.management.model.*;
import com.college.management.repositories.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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
    private ProfessorRepository professorRepository;


    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private BatchRepository batchRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserCommandToUser userCommandToUser;

    @Autowired
    private UserToUserCommand userToUserCommand;

    @Autowired
    private UserPhotoToUserPhotoCommand userPhotoToUserPhotoCommand;

    @Autowired
    private StudentCommandToStudent studentCommandToStudent;

    @Autowired
    private StudentToStudentCommand studentToStudentCommand;

    @Autowired
    private DepartmentToDepartmentCommand departmentToDepartmentCommand;

    @Autowired
    private DepartmentCommandToDepartment departmentCommandToDepartment;

    @Autowired
    private CourseCommandToCourse courseCommandToCourse;

    @Autowired
    private CourseToCourseCommand courseToCourseCommand;

    @Autowired
    private ProfessorCommandToProfessor professorCommandToProfessor;

    @Autowired
    private ProfessorToProfessorCommand professorToProfessorCommand;

    @Autowired
    private BatchCommandToBatch batchCommandToBatch;

    @Autowired
    private BatchToBatchCommand batchToBatchCommand;


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

        Optional<Department> department = departmentRepository.findById(studentCommand.getDepartmentCommand().getId());

        if(department.isPresent()){
            student.setDepartment(department.get());
        }

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
                tempUserCommand.getStudentCommand().setDepartmentCommand(departmentToDepartmentCommand.convert(newUser.getStudent().getDepartment()));

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


    // ######################  Related to Courses and Departments ##############//

    @Override
    @Transactional
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

    @Override
    @Transactional
    public boolean checkIfCourseExists(String courseCode) {

        Optional<Course> checkIfExists = courseRepository.findByCourseCode(courseCode);

        if(checkIfExists.isPresent()){
            return true;
        }else{
            return false;
        }


    }

    @Override
    @Transactional
    public void saveCourse(CourseCommand courseCommand) {




    Course course = courseCommandToCourse.convert(courseCommand);

    Optional<Department> department = departmentRepository.findById(courseCommand.getDepartmentCommand().getId());

    if(department.isPresent()){
        course.setDepartment(department.get());
    }



    courseRepository.save(course);




    }

    @Override
    public List<CourseCommand> showAllCourses() {

       List<Course> courses = courseRepository.findAllByOrderByDepartmentAsc();

       List<CourseCommand> courseCommands = new ArrayList<>();

       for(Course course: courses){

           CourseCommand courseCommand = courseToCourseCommand.convert(course);
           courseCommand.setDepartmentCommand(
                   departmentToDepartmentCommand.convert(course.getDepartment())
           );

           courseCommands.add(courseCommand);
       }

       return courseCommands;
    }

    @Override
    public void deleteCourse(Long courseId) {

        courseRepository.deleteById(courseId);


    }

    @Override
    public Map<Long, String> findAllCourse() {

        List<Course> courses = courseRepository.selectCourseNameAndCourseCode();



        Map<Long, String> tempCourses = new HashMap<>();

        for(Course course: courses){

            tempCourses.put(course.getId(), course.getCourseName() + ", " + course.getCourseCode() + ", " + course.getDepartmentName());
        }

        return tempCourses;
    }


    // ##########################  PROFESSOR RELATED   ###################  //


    @Override
    public boolean checkIfProfessorExists(String email) {


        Optional<User> checkIfUserExists = userRepository.findByEmail(email);

        if(checkIfUserExists.isPresent()){
            return true;
        }else{
            return false;
        }


    }

    @Override
    public void saveProfessor(ProfessorCommand professorCommand) {


        Professor professor = professorCommandToProfessor.convert(professorCommand);

        professor.setUser(userCommandToUser.convert(professorCommand.getUserCommand()));

        //encode password and enable account
        professor.getUser().setPassword(passwordEncoder.encode(professor.getUser().getPassword()));

        professor.getUser().setEnabled(true);


        //add role to professor
        Role role = roleRepository.findByName(professor.getUser().getRole());

        professor.getUser().getRoles().add(role);

        //add department to professor
        Optional<Department> department = departmentRepository.findById(professorCommand.getDepartmentCommand().getId());

        if(department.isPresent()){

            professor.setDepartment(department.get());
        }


        professorRepository.save(professor);



    }

    @Override
    public List<UserCommand> showAllProfessors() {

        List<User> users = userRepository.findAllProfessor();

        List<UserCommand> userCommands = new ArrayList<>();

        for(User user: users){

            UserCommand userCommand = userToUserCommand.convert(user);
            userCommand.setProfessorCommand(professorToProfessorCommand.convert(user.getProfessor()));
            userCommand.getProfessorCommand().setDepartmentCommand(departmentToDepartmentCommand.convert(user.getProfessor().getDepartment()));

            userCommands.add(userCommand);

        }

        return userCommands;
    }

    @Override
    public ProfessorCommand findProfessorById(Long id) {

        Optional<Professor> optionalProfessor = professorRepository.findById(id);

        Professor professor = new Professor();
        if(optionalProfessor.isPresent()){

            professor = optionalProfessor.get();
            ProfessorCommand professorCommand = professorToProfessorCommand.convert(professor);

            professorCommand.setUserCommand(userToUserCommand.convert(professor.getUser()));
            professorCommand.setDepartmentCommand(departmentToDepartmentCommand.convert(professor.getDepartment()));

            return professorCommand;
        }




        return null;
    }

    @Override
    public void updateProfessor(ProfessorCommand professorCommand) {


        professorRepository.updateProfessor(
                professorCommand.getFirstName(),
                professorCommand.getLastName(),
                professorCommand.getAddress(),
                professorCommand.getPhone(),
                professorCommand.getState(),
                professorCommand.getCity(),
                professorCommand.getBloodGroup(),
                professorCommand.getId()
        );

    }

    @Override
    public void deleteProfessor(Long userId) {

        professorRepository.deleteById(userId);
    }

    @Override
    public Map<Long, String> findAllProfessors() {

        //List<Professor> tempProfessors = professorRepository.selectProfessorIdAndProfessorName();

        List<Professor> tempProfessors = professorRepository.selectProfessorFirstNameAndLastName();

        System.out.println(tempProfessors);

        Map<Long, String> professors = new HashMap<>();

        for(Professor professor: tempProfessors){

            professors.put(professor.getId(), professor.getFirstName() + " " + professor.getLastName() + ", " + professor.getDepartmentName());

        }

        return professors;
    }




    //######################  Batch Related  ###################### //

    @Override
    public void saveBatch(BatchCommand batchCommand) {

        Batch batch = batchCommandToBatch.convert(batchCommand);

        batch.setDepartment(departmentCommandToDepartment.convert(batchCommand.getDepartmentCommand()));

        batch.setProfessor(professorCommandToProfessor.convert(batchCommand.getProfessorCommand()));

        batch.setCourse(courseCommandToCourse.convert(batchCommand.getCourseCommand()));

        System.out.println(batch);

        batchRepository.save(batch);

    }

    @Override
    public List<BatchCommand> showAllBatches() {

        List<Batch> batches = batchRepository.findAllByOrderByDepartmentAsc();

        List<BatchCommand> batchCommands = new ArrayList<>();

        for(Batch batch: batches){


            batch.setNumberOfStudents(batch.getStudents().size());
            BatchCommand batchCommand = batchToBatchCommand.convert(batch);

            batchCommand.setDepartmentCommand(departmentToDepartmentCommand.convert(batch.getDepartment()));
            batchCommand.setProfessorCommand(professorToProfessorCommand.convert(batch.getProfessor()));
            batchCommand.setCourseCommand(courseToCourseCommand.convert(batch.getCourse()));

            batchCommands.add(batchCommand);
        }


        return batchCommands;
    }

    @Override
    public BatchCommand findBatchById(Long batchId) {

        Optional<Batch> Optionalbatch = batchRepository.findById(batchId);



        if(Optionalbatch.isPresent()){

            Batch batch = Optionalbatch.get();

            BatchCommand batchCommand = batchToBatchCommand.convert(batch);

            batchCommand.setProfessorCommand(professorToProfessorCommand.convert(batch.getProfessor()));

            batchCommand.setDepartmentCommand(departmentToDepartmentCommand.convert(batch.getDepartment()));

            batchCommand.setCourseCommand(courseToCourseCommand.convert(batch.getCourse()));

            return batchCommand;

        }


        return null;
    }

    @Override
    public List<StudentCommand> findStudentsByDepartment(String departmentName, Long batchId) {

        List<Student> students = studentRepository.showByDepartment(departmentName);

        List<StudentCommand> studentCommands = new ArrayList<>();

        Iterator itr = students.iterator();

        while(itr.hasNext()){

            Student student = (Student)itr.next();
            Set<Batch> batches = student.getBatches();

            boolean ifExistsInBatch = false;

            for(Batch batch: batches){

                if(batchId == batch.getId()){
                    ifExistsInBatch = true;
                }

            }

            if(ifExistsInBatch){
                itr.remove();
            }

        }



        for(Student student: students){



            StudentCommand studentCommand = studentToStudentCommand.convert(student);

            studentCommand.setDepartmentCommand(departmentToDepartmentCommand.convert(student.getDepartment()));

            studentCommand.setUserCommand(userToUserCommand.convert(student.getUser()));

            studentCommand.getUserCommand().setUserPhotoCommand(
                    userPhotoToUserPhotoCommand.convert(student.getUser().getUserPhoto())
                );

            studentCommands.add(studentCommand);
        }

        return studentCommands;
    }
}
