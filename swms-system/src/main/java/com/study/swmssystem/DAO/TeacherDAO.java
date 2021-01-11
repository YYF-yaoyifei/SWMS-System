package com.study.swmssystem.DAO;

import com.study.swmssystem.pojo.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherDAO extends JpaRepository<Teacher, Integer> {

}
