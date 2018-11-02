package com.college.management.repositories;

import com.college.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("select u.email from User u where u.email =?1")
    Optional<User> checkIfExits(String email);

    @Query("select u from User u where u.id = ?1")
    User selectById(Long id);

    @Query("select u from User u join u.roles r where r.name = 'ROLE_STUDENT' and u.enabled= true order by u.student.department.departmentName")
    List<User> findAllStudent();

    @Query("select u from User u join u.roles r where r.name= 'ROLE_STUDENT' and u.enabled= false ")
    List<User> selectNewlyRegisteredStudents();

    Long countByEnabledFalse();

    @Modifying
    @Query("update User u set u.enabled = true where u.id=?1")
    void approveStudent(Long id);

    @Query("select u from User u join u.roles r where r.name = 'ROLE_PROFESSOR' order by u.professor.department.departmentName")
    List<User> findAllProfessor();


}
