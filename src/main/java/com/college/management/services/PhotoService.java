package com.college.management.services;

import com.college.management.model.UserPhoto;

import java.io.IOException;

public interface PhotoService {


    public void saveUserPhoto(UserPhoto userPhoto, Long userId);

    public String convertByteToString64(byte[] image) throws IOException;

    public String findImageByUserId(Long id) throws IOException;


}
