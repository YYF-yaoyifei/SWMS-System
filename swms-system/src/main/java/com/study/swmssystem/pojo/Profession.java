package com.study.swmssystem.pojo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "profession")
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "profession", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Classes> classesList;

    @OneToMany(mappedBy = "profession", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> studentList;

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Classes> getClassesList() {
        return classesList;
    }

    public void setClassesList(List<Classes> classesList) {
        this.classesList = classesList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
