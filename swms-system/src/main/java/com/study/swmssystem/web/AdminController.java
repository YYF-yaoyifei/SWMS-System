package com.study.swmssystem.web;

import com.study.swmssystem.DAO.*;
import com.study.swmssystem.Enum.Gender;
import com.study.swmssystem.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    TeacherDAO teacherDAO;

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    DepartmentDAO departmentDAO;

    @Autowired
    ClassDAO classDAO;

    @Autowired
    CourseDAO courseDAO;

    @Autowired
    AdminDAO adminDAO;

    @Autowired
    ProfessionDAO professionDAO;

    @RequestMapping("/m_teachermanage")
    public String teachermanage(Model m) {
        int i = 0;
        List<Teacher> teachers = teacherDAO.findAll();
        m.addAttribute("teachers", teachers);
        m.addAttribute("index", i);
        return "m_teachermanage";
    }

    @RequestMapping("/editteacher")
    public String editteacher(Model m, @RequestParam(value = "id") String id) {
        Integer ID = Integer.parseInt(id);
        Optional<Teacher> teachers = teacherDAO.findById(ID);
        Teacher teacher = teachers.get();
        List<Department> departmentList = departmentDAO.findAll();
        m.addAttribute("departments", departmentList);
        m.addAttribute("teacher", teacher);
        return "editteacher";
    }

    @RequestMapping("/editandsaveteacher")
    public String editandsaveteacher(Model m, @RequestParam(value = "id") String id, @RequestParam(value = "name") String name, @RequestParam(value = "password") String password, @RequestParam(value = "gender") String sex, @RequestParam(value = "department") String department) {
        Integer ID = Integer.parseInt(id);
        Optional<Teacher> exitteacher = teacherDAO.findById(ID);
        Teacher teacher = exitteacher.get();
        teacher.setName(name);
        teacher.setPassword(password);
        if (sex.equals("female"))
            teacher.setGender(Gender.female);
        else
            teacher.setGender(Gender.male);
        Department department1 = departmentDAO.getOne(Integer.parseInt(department));
        teacher.setDepartment(department1);
        teacherDAO.save(teacher);
        int i = 0;
        m.addAttribute("index", i);
        List<Teacher> teachers = teacherDAO.findAll();
        m.addAttribute("teachers", teachers);
        return "m_teachermanage";
    }

    @RequestMapping("/deleteteacher")
    public String deleteteacher(Model m, @RequestParam(value = "id") String id) {
        Integer ID = Integer.parseInt(id);
        teacherDAO.deleteById(ID);
        int i = 0;
        m.addAttribute("index", i);
        List<Teacher> teachers = teacherDAO.findAll();
        m.addAttribute("teachers", teachers);
        return "m_teachermanage";
    }

    @RequestMapping("/addteacher")
    public String m_addteather(Model m, @RequestParam(value = "id") String id, @RequestParam(value = "name") String name, @RequestParam(value = "password") String password, @RequestParam(value = "department") String departmentid, @RequestParam(value = "sex") String sex) {
        Integer ID = Integer.parseInt(id);
        Teacher teacher = new Teacher();
        teacher.setId(ID);
        teacher.setName(name);
        teacher.setPassword(password);
        if (sex.equals("男")) {
            teacher.setGender(Gender.male);
        } else
            teacher.setGender(Gender.female);
        Department department = departmentDAO.getOne(Integer.parseInt(departmentid));
        teacher.setDepartment(department);
        teacherDAO.save(teacher);
        int i = 0;
        m.addAttribute("index", i);
        List<Teacher> teachers = teacherDAO.findAll();
        m.addAttribute("teachers", teachers);
        return "m_teachermanage";

    }

    @RequestMapping("/m_studentmanage")
    public String studentmanage(Model m) {
        int i = 0;
        List<Student> students = studentDAO.findAll();
        m.addAttribute("students", students);
        m.addAttribute("index", i);
        return "m_studentmanage";
    }

    @RequestMapping("/editstudent")
    public String editstudent(Model m, @RequestParam(value = "id") String id) {
        Integer ID = Integer.parseInt(id);
        Optional<Student> students = studentDAO.findById(ID);
        Student student = students.get();
        List<Department> departments = departmentDAO.findAll();
        List<Classes> classes = classDAO.findAll();
        m.addAttribute("classes", classes);
        m.addAttribute("departments", departments);
        m.addAttribute("student", student);
        return "editstudent";
    }

    @RequestMapping("/editandsavestudent")
    public String editandsavestudent(Model m, @RequestParam(value = "id") String id, @RequestParam(value = "name") String name, @RequestParam(value = "password") String password, @RequestParam(value = "gender") String sex, @RequestParam(value = "class1") String oneclass, @RequestParam(value = "department") String department) {
        Integer ID = Integer.parseInt(id);
        Optional<Student> exitstudent = studentDAO.findById(ID);
        Student student = exitstudent.get();
        student.setName(name);
        student.setPassword(password);
        if (sex.equals("female"))
            student.setGender(Gender.female);
        else
            student.setGender(Gender.male);
        Department department1 = departmentDAO.getOne(Integer.parseInt(department));
        student.setDepartment(department1);
        Classes classes = classDAO.getOne(Integer.parseInt(oneclass));
        student.setClasses(classes);
        studentDAO.save(student);
        int i = 0;
        m.addAttribute("index", i);
        List<Student> students = studentDAO.findAll();
        m.addAttribute("students", students);
        return "m_studentmanage";
    }

    @RequestMapping("/deletestudent")
    public String deletestudent(Model m, @RequestParam(value = "id") String id) {
        Integer ID = Integer.parseInt(id);
        studentDAO.deleteById(ID);
        int i = 0;
        m.addAttribute("index", i);
        List<Student> students = studentDAO.findAll();
        m.addAttribute("students", students);
        return "m_studentmanage";
    }

    @RequestMapping("/addstudent")
    public String m_addstudent(Model m, @RequestParam(value = "id") String id, @RequestParam(value = "name") String name, @RequestParam(value = "password") String password, @RequestParam(value = "department") String departmentid, @RequestParam(value = "class1") String classid, @RequestParam(value = "sex") String sex, @RequestParam(value = "profession")String professionid) {
        Integer ID = Integer.parseInt(id);
        Student student = new Student();
        student.setId(ID);
        student.setName(name);
        student.setPassword(password);
        if (sex.equals("男")) {
            student.setGender(Gender.male);
        } else
            student.setGender(Gender.female);
        Department department = departmentDAO.getOne(Integer.parseInt(departmentid));
        student.setDepartment(department);
        Profession profession1 = professionDAO.getOne(Integer.parseInt(professionid));
        student.setProfession(profession1);
        Classes classes = classDAO.getOne(Integer.parseInt(classid));
        student.setClasses(classes);
        studentDAO.save(student);
        int i = 0;
        m.addAttribute("index", i);
        List<Student> students = studentDAO.findAll();
        m.addAttribute("students", students);
        return "m_studentmanage";

    }


    @RequestMapping("/m_coursemanage")
    public String coursemanage(Model m) {
        int i = 0;
        List<Course> courses = courseDAO.findAll();
        m.addAttribute("courses", courses);
        m.addAttribute("index", i);
        return "m_coursemanage";
    }

    @RequestMapping("/editcourse")
    public String editcourse(Model m, @RequestParam(value = "id") String id) {
        Integer ID = Integer.parseInt(id);
        Optional<Course> courses = courseDAO.findById(ID);
        Course course = courses.get();
        m.addAttribute("course", course);
        return "editcourse";
    }

    @RequestMapping("/editandsavecourse")
    public String editandsavecourse(Model m, @RequestParam(value = "id") String id, @RequestParam(value = "name") String name, @RequestParam(value = "credit") String credit) {
        Integer ID = Integer.parseInt(id);
        Optional<Course> editcourse = courseDAO.findById(ID);
        Course course = editcourse.get();
        course.setName(name);
        course.setCredit(Integer.parseInt(credit));
        courseDAO.save(course);
        int i = 0;
        m.addAttribute("index", i);
        List<Course> courses = courseDAO.findAll();
        m.addAttribute("courses", courses);
        return "m_coursemanage";
    }

    @RequestMapping("/deletecourse")
    public String deletecourse(Model m, @RequestParam(value = "id") String id) {
        Integer ID = Integer.parseInt(id);
        courseDAO.deleteById(ID);
        int i = 0;
        m.addAttribute("index", i);
        List<Course> courses = courseDAO.findAll();
        m.addAttribute("courses", courses);
        return "m_coursemanage";
    }

    @RequestMapping("/addcourse")
    public String m_addcourse(Model m, @RequestParam(value = "id") String id, @RequestParam(value = "name") String name, @RequestParam(value = "credit") String credit) {
        Integer ID = Integer.parseInt(id);
        Course course = new Course();
        course.setId(ID);
        course.setName(name);
        course.setCredit(Integer.parseInt(credit));
        courseDAO.save(course);
        int i = 0;
        m.addAttribute("index", i);
        List<Course> courses = courseDAO.findAll();
        m.addAttribute("courses", courses);
        return "m_coursemanage";

    }

    @RequestMapping("/m_addstudent")
    public String m_addstudent(Model m) throws Exception {
        List<Department> departmentList = departmentDAO.findAll();
        m.addAttribute("departments", departmentList);
        List<Profession> professionList = professionDAO.findAll();
        m.addAttribute("professions", professionList);
        List<Classes> classesList = classDAO.findAll();
        m.addAttribute("classes", classesList);
        return "m_addstudent";
    }

    @RequestMapping("/m_addteacher")
    public String m_addteacher(Model m) throws Exception {
        List<Department> departmentList = departmentDAO.findAll();
        m.addAttribute("departments", departmentList);
        return "m_addteacher";
    }

    @RequestMapping("/m_addcourse")
    public String m_addcourse(Model m) throws Exception {
        return "m_addcourse";
    }

    @RequestMapping("/m_personalsetting")
    public String m_personalsetting(Model m, HttpServletRequest request) throws Exception{
        Admin admin;
        String id = (String)request.getSession().getAttribute("id");
        admin = adminDAO.getOne(Integer.parseInt(id));
        m.addAttribute("admin", admin);
        return "m_personalsetting";
    }

    @RequestMapping("/m_changeid")
    public String m_changeid(Model m, HttpServletRequest request, @RequestParam(value = "account")String id) throws Exception{
        String ID = (String)request.getSession().getAttribute("id");
        Admin admin = adminDAO.getOne(Integer.parseInt(ID));
        admin.setId(Integer.valueOf(id));
        m.addAttribute("admin", admin);
        return "m_personalsetting";
    }

    @RequestMapping("/m_changename")
    public String m_changename(Model m, HttpServletRequest request, @RequestParam(value = "name")String name) throws Exception{
        String ID = (String)request.getSession().getAttribute("id");
        Admin admin = adminDAO.getOne(Integer.parseInt(ID));
        admin.setName(name);
        m.addAttribute("admin", admin);
        return "m_personalsetting";
    }

    @RequestMapping("m_changepassword")
    public String m_changepassword(Model m, HttpServletRequest request, @RequestParam(value = "password")String password) throws Exception{
        String ID = (String)request.getSession().getAttribute("id");
        Admin admin = adminDAO.getOne(Integer.parseInt(ID));
        admin.setPassword(password);
        m.addAttribute("admin", admin);
        return "m_personalsetting";
    }

}
