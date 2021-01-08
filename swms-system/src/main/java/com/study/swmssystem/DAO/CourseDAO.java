package com.study.swmssystem.DAO;

import com.study.swmssystem.pojo.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDAO extends JpaRepository<Course, Integer> {

}
