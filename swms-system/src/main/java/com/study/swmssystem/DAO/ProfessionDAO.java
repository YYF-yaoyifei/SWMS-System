package com.study.swmssystem.DAO;

import com.study.swmssystem.pojo.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionDAO extends JpaRepository<Profession, Integer> {
}
