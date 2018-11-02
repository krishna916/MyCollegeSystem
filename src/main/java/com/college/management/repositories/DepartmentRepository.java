package com.college.management.repositories;

import com.college.management.model.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("select d.id, d.departmentName from Department d")
    List<Department> selectDepartmentIdAndDepartmentName();









}
