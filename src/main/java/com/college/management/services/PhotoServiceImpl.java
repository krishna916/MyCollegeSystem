package com.college.management.services;

import com.college.management.model.User;
import com.college.management.model.UserPhoto;
import com.college.management.repositories.PhotoRepository;
import com.college.management.repositories.UserRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhotoRepository photoRepository;



    @Override
    @Transactional
    public void saveUserPhoto(UserPhoto userPhoto, Long userId) {

        User user = userRepository.selectById(userId);

        userPhoto.setUser(user);

        photoRepository.save(userPhoto);

    }

    @Override
    public String convertByteToString64(byte[] image) throws IOException {

        byte[] encodeBase64 = Base64.encodeBase64(image);
        String base64encoded = new String(encodeBase64, "UTF-8");
        return base64encoded;
    }

    @Override
    @Transactional
    public String findImageByUserId(Long id) throws IOException {

        Optional<UserPhoto> userPhotos = photoRepository.selectByUserId(id);

        if(userPhotos.isPresent()){
            UserPhoto userPhoto = userPhotos.get();



            return userPhoto.getImage();
        }

        return null;


    }

}
