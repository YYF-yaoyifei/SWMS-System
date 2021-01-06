package com.study.swmssystem.pojo;

import com.study.swmssystem.Enum.Gender;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name ="password")
    private String password;

    @Column(name = "phonenumber")
    private String phongnumber;

    @Column(name = "email")
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    Gender gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhongnumber() {
        return phongnumber;
    }

    public void setPhongnumber(String phongnumber) {
        this.phongnumber = phongnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}



