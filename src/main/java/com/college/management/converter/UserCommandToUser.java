package com.college.management.converter;

import com.college.management.command.UserCommand;
import com.college.management.model.User;
import lombok.Synchronized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserCommandToUser implements Converter<UserCommand, User> {

    @Autowired
    private RoleCommandToRole roleConvertor;


    @Synchronized
    @Nullable
    @Override
    public User convert(UserCommand userCommand) {



        if(userCommand == null){
            return null;
        }

        final User user = new User();

        user.setId(userCommand.getId());
        user.setEmail(userCommand.getEmail());
        user.setPassword(userCommand.getPassword());
        user.setRole(userCommand.getRole());



        return user;

    }
}
