package com.college.management.command;

import java.util.Arrays;

public class UserPhotoCommand {

    private Long id;

    private String name;

    private String mimetype;

    private byte[] photo;

    private String image;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "UserPhotoCommand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mimetype='" + mimetype + '\'' +
                ", photo=" + Arrays.toString(photo) +
                ", image='" + image + '\'' +
                '}';
    }
}
