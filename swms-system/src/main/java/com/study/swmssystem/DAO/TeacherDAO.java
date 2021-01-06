package com.study.swmssystem.DAO;

import com.study.swmssystem.pojo.Student;
import com.study.swmssystem.pojo.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherDAO extends JpaRepository<Teacher,Integer> {

}
