package com.study.swmssystem.web;

import com.study.swmssystem.DAO.AdminDAO;
import com.study.swmssystem.DAO.StudentDAO;
import com.study.swmssystem.DAO.TeacherDAO;
import com.study.swmssystem.pojo.Admin;
import com.study.swmssystem.pojo.Student;
import com.study.swmssystem.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    AdminDAO adminDAO;
    @Autowired
    StudentDAO studentDAO;
    @Autowired
    TeacherDAO teacherDAO;


    @RequestMapping("/login")
    public String test(@RequestParam(value = "id")String id, @RequestParam(value = "password")String password , @RequestParam(value="type")String type) {
        if(type.equals("Manager")){
            Optional<Admin> optionalAdminDAO = adminDAO.findById(Integer.parseInt(id));
            if (optionalAdminDAO.isPresent() &&optionalAdminDAO.get().getPassword().equals(password)) {
                return "admin";
            }
        }
        else if(type.equals("Student")){
            Optional<Student> optionalStudent = studentDAO.findById(Integer.parseInt(id));
            if(optionalStudent.isPresent()&&optionalStudent.get().getPassword().equals(password))
                return "student";
        }
        else if(type.equals("Teacher")){
            Optional<Teacher> optionalTeacher = teacherDAO.findById(Integer.parseInt(id));
            if(optionalTeacher.isPresent()&&optionalTeacher.get().getPassword().equals(password))
                return "teacher";
        }

        return "errorPage";
    }

    @RequestMapping("/admin")
    public String admin(Model m) throws Exception {
        m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));

        return "login";
    }

    @RequestMapping("/m_studentmanage")
    public String m_studentmangage(Model m) throws Exception {
        m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));

        return "m_studentmanage";
    }

    @RequestMapping("/m_addstudent")
    public String m_addstudent(Model m) throws Exception{
        return "m_addstudent";
    }

    @RequestMapping("/m_addteacher")
        public String m_addteacher(Model m) throws Exception {
        return "m_addteacher";
    }

    @RequestMapping("/m_addcourse")
    public String m_addcourse(Model m) throws Exception{
        return "m_addcourse";
    }

    @RequestMapping("/m_coursemanage")
    public String m_coursemanage(Model m) throws Exception{
        return "m_coursemanage";
    }
}
