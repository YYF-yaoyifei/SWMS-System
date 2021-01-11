package com.study.swmssystem.web;

import com.study.swmssystem.DAO.AnswerDAO;
import com.study.swmssystem.DAO.CourseDAO;
import com.study.swmssystem.DAO.HomeworkDAO;
import com.study.swmssystem.DAO.StudentDAO;
import com.study.swmssystem.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    AnswerDAO answerDAO;

    @Autowired
    CourseDAO courseDAO;

    @Autowired
    HomeworkDAO homeworkDAO;

    @RequestMapping("/s_personalsetting")
    public String s_personalsetting(Model m, HttpServletRequest request) throws Exception {
        Student student;
        String id = (String) request.getSession().getAttribute("id");
        student = studentDAO.getOne(Integer.parseInt(id));
        m.addAttribute("student", student);
        return "s_personalsetting";
    }

    @RequestMapping("/s_changename")
    public String s_changename(Model m, HttpServletRequest request, @RequestParam(value = "name") String name) throws Exception {
        String ID = (String) request.getSession().getAttribute("id");
        Student student = studentDAO.getOne(Integer.parseInt(ID));
        student.setName(name);
        m.addAttribute("student", student);
        return "s_personalsetting";
    }

    @RequestMapping("s_changepassword")
    public String s_changepassword(Model m, HttpServletRequest request, @RequestParam(value = "password") String password) throws Exception {
        String ID = (String) request.getSession().getAttribute("id");
        Student student = studentDAO.getOne(Integer.parseInt(ID));
        student.setPassword(password);
        m.addAttribute("student", student);
        return "s_personalsetting";
    }

    @RequestMapping("s_submithomework")
    public String submithomework(Model m, HttpServletRequest request) {
        Student student;
        String id = (String) request.getSession().getAttribute("id");
        student = studentDAO.getOne(Integer.parseInt(id));
        List<Course> courseList = student.getCourseList();
        m.addAttribute("courses", courseList);
        return "submithomework";
    }

    @RequestMapping("selecthomework")
    @ResponseBody
    public Object selecthomework(Model m, HttpServletRequest request, @RequestParam(value = "course") String courseid) {
        Course course = courseDAO.getOne(Integer.parseInt(courseid));
        List<Homework> homeworkList = course.getHomeworkList();
        return homeworkList;
    }

    @RequestMapping("s_showcheck")
    public String showcheck(Model m, HttpServletRequest request) {
        Student student;
        String id = (String) request.getSession().getAttribute("id");
        student = studentDAO.getOne(Integer.parseInt(id));
        List<Answer> answerList = student.getAnswerList();
        int i = 0;
        m.addAttribute("index", i);
        m.addAttribute("answers", answerList);
        return "showcheck";
    }

    @RequestMapping("/submithomework")
    public String submithomework(Model m, HttpServletRequest request, @RequestParam(value = "homework") String homeworkid, @RequestParam(value = "answer") String answer) throws Exception {
        Answer myanswer = new Answer();
        myanswer.setAnswer(answer);
        Homework homework = homeworkDAO.getOne(Integer.parseInt(homeworkid));
        myanswer.setHomework(homework);
        answerDAO.save(myanswer);
        Student student;
        String id = (String) request.getSession().getAttribute("id");
        student = studentDAO.getOne(Integer.parseInt(id));
        student.getAnswerList().add(myanswer);
        List<Announcement> announcementList = new LinkedList<>();
        List<Course> courses = student.getCourseList();
        for (Course course : courses) {
            for (Announcement announcement : course.getAnnouncementList()) {
                announcementList.add(announcement);
            }
        }
        List<Homework> homeworkList = new LinkedList<>();
        for (Course course : courses) {
            for (Homework homework1 : course.getHomeworkList()) {
                homeworkList.add(homework1);
            }
        }
        m.addAttribute("homeworks", homeworkList);
        m.addAttribute("announcements", announcementList);
        return "studenthomeworkmanage";
    }

    @RequestMapping("/studenthomeworkmanage")
    public String studenthomeworkmanage(Model m, HttpServletRequest request) throws Exception {
        Student student;
        String id = (String) request.getSession().getAttribute("id");
        student = studentDAO.getOne(Integer.parseInt(id));
        List<Announcement> announcementList = new LinkedList<>();
        List<Course> courses = student.getCourseList();
        for (Course course : courses) {
            for (Announcement announcement : course.getAnnouncementList()) {
                announcementList.add(announcement);
            }
        }
        List<Homework> homeworkList = new LinkedList<>();
        for (Course course : courses) {
            for (Homework homework : course.getHomeworkList()) {
                homeworkList.add(homework);
            }
        }
        m.addAttribute("homeworks", homeworkList);
        m.addAttribute("announcements", announcementList);
        return "studenthomeworkmanage";
    }

    @RequestMapping("/studentannouncemanage")
    public String studentannouncemanage(Model m, HttpServletRequest request) throws Exception {
        Student student;
        String id = (String) request.getSession().getAttribute("id");
        student = studentDAO.getOne(Integer.parseInt(id));
        List<Announcement> announcementList = new LinkedList<>();
        List<Course> courses = student.getCourseList();
        for (Course course : courses) {
            for (Announcement announcement : course.getAnnouncementList()) {
                announcementList.add(announcement);
            }
        }
        m.addAttribute("announcements", announcementList);
        return "studentannouncemanage";
    }

}
