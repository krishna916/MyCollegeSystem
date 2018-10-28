package com.college.management.model;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.IOException;
import java.util.Arrays;

@Entity
@Table(name="user_photo")
@DynamicUpdate
public class UserPhoto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="mimetype")
    private String mimetype;

    @Lob
    @Column(name="image")
    private byte[] image;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    public UserPhoto(){}


    public UserPhoto(String name, String mimetype, byte[] image){

        this.name = name;
        this.mimetype = mimetype;
        this.image = image;

    }


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

    public String getImage() throws IOException {

        byte[] encodeBase64 = Base64.encodeBase64(image);
        String base64encoded = new String(encodeBase64, "UTF-8");

        return base64encoded;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserPhoto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mimetype='" + mimetype + '\'' +
                ", image=" + Arrays.toString(image) +

                '}';
    }
}
