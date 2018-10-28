package com.college.management.converter;

import com.college.management.command.UserCommand;
import com.college.management.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserCommand implements Converter<User, UserCommand>{

    @Autowired
    private StudentToStudentCommand studentToStudentCommand;

    @Autowired
    private UserPhotoToUserPhotoCommand userPhotoToUserPhotoCommand;

    @Override
    public UserCommand convert(User source) {



        if(source == null){
            return null;
        }

        UserCommand userCommand = new UserCommand();

        userCommand.setId(source.getId());
        userCommand.setEmail(source.getEmail());
        userCommand.setPassword(source.getPassword());
        userCommand.setEnabled(source.isEnabled());




        return userCommand;
    }
}
