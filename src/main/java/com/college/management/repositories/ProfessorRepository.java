package com.college.management.repositories;

import com.college.management.model.Professor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("select s from Professor s join s.user u where u.email=?1")
    Professor selectProfessorFromUserEmail(String email);

    @Query("select p from Professor p join p.user u where u.id=?1")
    Professor selectProfessorFromUserid(Long userId);




    @Modifying
    @Query("update Professor s set s.firstName = :firstName, s.lastName= :lastName, s.address= :address," +
            " s.phone= :phone, s.state= :state, s.city= :city, s.bloodGroup= :bloodGroup where s.id= :id")
    void updateProfessor(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("address")String address,
            @Param("phone")String phone,
            @Param("state")String state,
            @Param("city")String city,
            @Param("bloodGroup")String bloodGroup,

            @Param("id")long id
    );

}
