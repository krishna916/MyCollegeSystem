package com.college.management.converter;

import com.college.management.command.CourseCommand;
import com.college.management.model.Course;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CourseToCourseCommand implements Converter<Course, CourseCommand> {
    @Override
    public CourseCommand convert(Course source) {

        if(source == null){
            return null;
        }

        CourseCommand courseCommand = new CourseCommand();

        courseCommand.setId(source.getId());
        courseCommand.setCourseName(source.getCourseName());
        courseCommand.setCourseCode(source.getCourseCode());
        courseCommand.setCourseDetails(source.getCourseDetails());

        return courseCommand;
    }
}
