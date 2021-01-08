package com.study.swmssystem.DAO;

import com.study.swmssystem.pojo.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDAO extends JpaRepository<Department, Integer> {
}
