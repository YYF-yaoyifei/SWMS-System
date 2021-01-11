package com.study.swmssystem.web;

import com.study.swmssystem.DAO.*;
import com.study.swmssystem.pojo.Admin;
import com.study.swmssystem.pojo.Student;
import com.study.swmssystem.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Autowired
    DepartmentDAO departmentDAO;
    @Autowired
    ClassDAO classDAO;

    @RequestMapping("/login_in")
    public String test(Model m, HttpServletRequest request, @RequestParam(value = "id") String id, @RequestParam(value = "password") String password, @RequestParam(value = "type") String type) {
        HttpSession session = request.getSession();
        /*Admin admin = new Admin();
        admin.setId(123);
        admin.setPassword("123");
        admin.setGender(Gender.male);
        adminDAO.save(admin);

        Student student = new Student();
        student.setId(123);
        student.setPassword("123");
        student.setGender(Gender.male);
        studentDAO.save(student);

        Teacher teacher = new Teacher();
        teacher.setId(123);
        teacher.setPassword("123");
        teacher.setGender(Gender.male);
        teacherDAO.save(teacher);*/

        if (type.equals("Manager")) {
            Optional<Admin> optionalAdminDAO = adminDAO.findById(Integer.parseInt(id));
            if (optionalAdminDAO.isPresent() && optionalAdminDAO.get().getPassword().equals(password)) {
                session.setAttribute("type", type);
                session.setAttribute("id", id);
                session.setAttribute("password", password);
                m.addAttribute("admin", optionalAdminDAO.get());
                return "admin";
            }
        } else if (type.equals("Student")) {
            Optional<Student> optionalStudent = studentDAO.findById(Integer.parseInt(id));
            if (optionalStudent.isPresent() && optionalStudent.get().getPassword().equals(password)) {
                session.setAttribute("type", type);
                session.setAttribute("id", id);
                session.setAttribute("password", password);
                m.addAttribute("student", optionalStudent.get());
                return "student";
            }

        } else if (type.equals("Teacher")) {
            Optional<Teacher> optionalTeacher = teacherDAO.findById(Integer.parseInt(id));
            if (optionalTeacher.isPresent() && optionalTeacher.get().getPassword().equals(password)) {
                session.setAttribute("type", type);
                session.setAttribute("id", id);
                session.setAttribute("password", password);
                m.addAttribute("teacher", optionalTeacher.get());
                return "teacher";
            }

        }
        return "errorPage";
    }

    @RequestMapping("/login")
    public String admin(Model m) throws Exception {
        m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));

        return "login";
    }

    @RequestMapping("/loginout")
    public String login(Model m) throws Exception {
        return "login";
    }
}
