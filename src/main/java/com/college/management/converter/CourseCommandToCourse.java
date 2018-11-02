package com.college.management.converter;

import com.college.management.command.CourseCommand;
import com.college.management.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandToCourse implements Converter<CourseCommand, Course> {


    @Autowired
    private DepartmentCommandToDepartment departmentCommandToDepartment;


    @Override
    public Course convert(CourseCommand source) {

        if(source == null){
            return null;
        }

        Course course = new Course();

        course.setId(source.getId());
        course.setCourseName(source.getCourseName());

        System.out.println(source.getCourseCode());



        course.setCourseCode(source.getCourseCode());

        System.out.println(course.getCourseCode());
        course.setCourseDetails(source.getCourseDetails());

        if( source.getDepartmentCommand() == null ){
            course.setDepartment(departmentCommandToDepartment.convert(source.getDepartmentCommand()));
        }


        return course;
    }
}
