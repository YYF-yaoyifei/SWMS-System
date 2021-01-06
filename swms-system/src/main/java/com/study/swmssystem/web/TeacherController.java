package com.study.swmssystem.web;

import com.study.swmssystem.DAO.TeacherDAO;
import com.study.swmssystem.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    TeacherDAO teacherDAO;

    @RequestMapping("/m_teachermanage")
    public String teachermanage(Model m){
        List<Teacher> teachers = teacherDAO.findAll();
        m.addAttribute("teachers", teachers);
        return "m_teachermanage";
    }
}
