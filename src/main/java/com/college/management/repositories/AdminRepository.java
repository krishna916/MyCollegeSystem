package com.college.management.repositories;

import com.college.management.model.AdminInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<AdminInformation, Long> {

    @Query("select a from AdminInformation a join a.user u where u.email=?1")
    AdminInformation selectFirstNameByEmail(String email);

}
