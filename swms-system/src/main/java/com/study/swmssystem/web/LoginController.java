package com.study.swmssystem.web;

import com.study.swmssystem.DAO.*;
import com.study.swmssystem.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    AdminDAO adminDAO;
    @Autowired
    StudentDAO studentDAO;
    @Autowired
    TeacherDAO teacherDAO;
    @Autowired
    DepartmentDAO departmentDAO;
    @Autowired
    ClassDAO classDAO;


    @RequestMapping("/login_in")
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

    @RequestMapping("/login")
    public String admin(Model m) throws Exception {
        m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));

        return "login";
    }

    @RequestMapping("/m_addstudent")
    public String m_addstudent(Model m) throws Exception{
        List<Department> departmentList = departmentDAO.findAll();
        m.addAttribute("departments",departmentList);
        List<Classes> classesList = classDAO.findAll();
        m.addAttribute("classes",classesList);
        return "m_addstudent";
    }

    @RequestMapping("/m_addteacher")
    public String m_addteacher(Model m) throws Exception {
        List<Department> departmentList = departmentDAO.findAll();
        m.addAttribute("departments",departmentList);
        return "m_addteacher";
    }

    @RequestMapping("/m_addcourse")
    public String m_addcourse(Model m) throws Exception{
        return "m_addcourse";
    }
}
