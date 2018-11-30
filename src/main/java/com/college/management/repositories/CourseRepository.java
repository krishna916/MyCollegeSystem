package com.college.management.repositories;

import com.college.management.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByCourseCode(String courseCode);

    List<Course> findAllByOrderByDepartmentAsc();

    @Query("select new Course(c.id, c.courseName, c.courseCode, c.department.departmentName) from Course c")
    List<Course> selectCourseNameAndCourseCode();

}
