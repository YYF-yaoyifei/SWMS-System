package com.study.swmssystem.DAO;

import com.study.swmssystem.pojo.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementDAO extends JpaRepository<Announcement, Integer> {

}
