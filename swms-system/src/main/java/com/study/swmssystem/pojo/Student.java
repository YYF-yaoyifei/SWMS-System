package com.study.swmssystem.pojo;

import com.study.swmssystem.Enum.Gender;

import javax.persistence.*;
import java.util.List;

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
    @Enumerated(EnumType.ORDINAL)
    Gender gender = Gender.male;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},optional = false)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},optional = false)
    @JoinColumn(name = "class_id")
    private Classes classes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_answer",joinColumns = {@JoinColumn(name = "student_id")},inverseJoinColumns = {@JoinColumn(name="answer_id")})
    private List<Answer> answerList;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},optional = false)
    @JoinColumn(name = "profession_id")
    private Profession profession;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_course",joinColumns = {@JoinColumn(name = "student_id")},inverseJoinColumns = {@JoinColumn(name="course_id")})
    private List<Course> courseList;

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isMan(){
        if(this.gender==Gender.male)
            return true;
        else if(this.gender==Gender.female)
            return false;
        return true;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}



