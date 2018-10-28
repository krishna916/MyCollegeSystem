package com.college.management.converter;

import com.college.management.command.StudentCommand;
import com.college.management.model.Student;
import com.college.management.model.User;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class StudentCommandToStudent implements Converter<StudentCommand, Student> {

    @Autowired
    private UserCommandToUser userCommandToUser;

    @Synchronized
    @Nullable
    @Override
    public Student convert(StudentCommand source) {

        if(source == null){
            return null;
        }

        Student student = new Student();

        student.setId(source.getId());
        student.setFirstName(source.getFirstName());
        student.setLastName(source.getLastName());
        student.setAddress(source.getAddress());
        student.setGender(source.getGender());
        student.setPhone(source.getPhone());
        student.setBloodGroup(source.getBloodGroup());
        student.setCity(source.getCity());
        student.setState(source.getState());
        student.setDateOfBirth(source.getDateOfBirth());





        student.setUser(userCommandToUser.convert(source.getUserCommand()));




        return student;
    }
}
