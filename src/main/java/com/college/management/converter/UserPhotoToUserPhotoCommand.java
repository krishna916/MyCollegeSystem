package com.college.management.converter;

import com.college.management.command.UserPhotoCommand;
import com.college.management.model.UserPhoto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserPhotoToUserPhotoCommand implements Converter<UserPhoto, UserPhotoCommand> {
    @Override
    public UserPhotoCommand convert(UserPhoto source) {

        if(source == null){
            return null;
        }

        UserPhotoCommand userPhotoCommand = new UserPhotoCommand();

        userPhotoCommand.setId(source.getId());
        userPhotoCommand.setName(source.getName());
        userPhotoCommand.setMimetype(source.getMimetype());

        try{
            userPhotoCommand.setImage(source.getImage());
        }catch (IOException e){

        }



        return userPhotoCommand;
    }
}
