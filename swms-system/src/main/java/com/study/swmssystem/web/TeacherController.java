package com.study.swmssystem.web;

import com.study.swmssystem.DAO.TeacherDAO;
import com.study.swmssystem.Enum.Gender;
import com.study.swmssystem.pojo.Teacher;
import jdk.nashorn.internal.runtime.options.Option;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class TeacherController {
    @Autowired
    TeacherDAO teacherDAO;

    @RequestMapping("/m_teachermanage")
    public String teachermanage(Model m){
        int i = 0;
        List<Teacher> teachers = teacherDAO.findAll();
        m.addAttribute("teachers", teachers);
        m.addAttribute("index", i);
        return "m_teachermanage";
    }

    @RequestMapping("/editteacher")
    public String editteacher(Model m, @RequestParam(value = "id")String id){
        Integer ID = Integer.parseInt(id);
        Optional<Teacher> teachers = teacherDAO.findById(ID);
        Teacher teacher = teachers.get();
        m.addAttribute("teacher",teacher);
        return "editteacher";
    }

    @RequestMapping("/editandsaveteacher")
    public String editandsaveteacher(Model m, @RequestParam(value = "id")String id, @RequestParam(value = "name")String name, @RequestParam(value = "password")String password){
        Integer ID = Integer.parseInt(id);
        Optional<Teacher> exitteacher = teacherDAO.findById(ID);
        Teacher teacher = exitteacher.get();
        teacher.setName(name);
        teacher.setPassword(password);
        teacherDAO.save(teacher);
        int i = 0;
        m.addAttribute("index", i);
        List<Teacher> teachers = teacherDAO.findAll();
        m.addAttribute("teachers", teachers);
        return "m_teachermanage";

    }
}
