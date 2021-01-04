package com.study.swmssystem.DAO;

import com.study.swmssystem.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category,Integer> {

}