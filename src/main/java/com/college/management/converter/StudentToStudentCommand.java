package com.college.management.converter;

import com.college.management.command.StudentCommand;
import com.college.management.model.Student;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class StudentToStudentCommand implements Converter<Student, StudentCommand> {

    @Autowired
    private UserToUserCommand userToUserCommand;

    @Synchronized
    @Nullable
    @Override
    public StudentCommand convert(Student source) {

        if(source == null) {
            return null;
        }

        StudentCommand studentCommand = new StudentCommand();

        studentCommand.setId(source.getId());
        studentCommand.setFirstName(source.getFirstName());
        studentCommand.setLastName(source.getLastName());
        studentCommand.setAddress(source.getAddress());
        studentCommand.setPhone(source.getPhone());
        studentCommand.setGender(source.getGender());
        studentCommand.setState(source.getState());
        studentCommand.setCity(source.getCity());
        studentCommand.setBloodGroup(source.getBloodGroup());
        studentCommand.setDateOfBirth(source.getDateOfBirth());

        if(source.getUser() != null){
            studentCommand.setUserCommand(userToUserCommand.convert(source.getUser()));
        }


        return studentCommand;

    }
}
