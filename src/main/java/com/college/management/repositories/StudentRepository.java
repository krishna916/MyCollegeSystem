package com.college.management.repositories;

import com.college.management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s join s.user u where u.email=?1")
    Student selectStudentFromUserEmail(String email);

    @Modifying
    @Query("update Student s set s.firstName = :firstName, s.lastName= :lastName, s.address= :address," +
            "s.gender= :gender, s.phone= :phone, s.state= :state, s.city= :city, s.bloodGroup= :bloodGroup where s.id= :id")
    void updateStudent(
                            @Param("firstName") String firstName,
                           @Param("lastName") String lastName,
                           @Param("address")String address,
                           @Param("gender")String gender,
                           @Param("phone")String phone,
                           @Param("state")String state,
                           @Param("city")String city,
                           @Param("bloodGroup")String bloodGroup,

                           @Param("id")long id
                    );
}
