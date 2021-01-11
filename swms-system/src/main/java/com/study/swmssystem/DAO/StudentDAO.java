package com.study.swmssystem.DAO;

import com.study.swmssystem.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDAO extends JpaRepository<Student, Integer> {
}
