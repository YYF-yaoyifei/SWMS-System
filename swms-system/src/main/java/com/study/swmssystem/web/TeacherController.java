package com.study.swmssystem.web;

import com.study.swmssystem.DAO.AnnouncementDAO;
import com.study.swmssystem.DAO.CourseDAO;
import com.study.swmssystem.DAO.HomeworkDAO;
import com.study.swmssystem.DAO.TeacherDAO;
import com.study.swmssystem.Enum.Gender;
import com.study.swmssystem.pojo.*;
import jdk.nashorn.internal.runtime.options.Option;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class TeacherController {
    @Autowired
    TeacherDAO teacherDAO;

    @Autowired
    AnnouncementDAO announcementDAO;

    @Autowired
    CourseDAO courseDAO;

    @Autowired
    HomeworkDAO homeworkDAO;

    @RequestMapping("/t_personalsetting")
    public String m_personalsetting(Model m, HttpServletRequest request) throws Exception{
        Teacher teacher;
        String id = (String)request.getSession().getAttribute("id");
        teacher = teacherDAO.getOne(Integer.parseInt(id));
        m.addAttribute("teacher", teacher);
        return "t_personalsetting";
    }

    @RequestMapping("/t_changename")
    public String t_changename(Model m, HttpServletRequest request, @RequestParam(value = "name")String name) throws Exception{
        String ID = (String)request.getSession().getAttribute("id");
        Teacher teacher = teacherDAO.getOne(Integer.parseInt(ID));
        teacher.setName(name);
        m.addAttribute("teacher", teacher);
        return "t_personalsetting";
    }

    @RequestMapping("t_changepassword")
    public String t_changepassword(Model m, HttpServletRequest request, @RequestParam(value = "password")String password) throws Exception{
        String ID = (String)request.getSession().getAttribute("id");
        Teacher teacher = teacherDAO.getOne(Integer.parseInt(ID));
        teacher.setPassword(password);
        m.addAttribute("teacher", teacher);
        return "t_personalsetting";
    }

    @RequestMapping("t_announcemanage")
    public String t_announcemanage(Model m, HttpServletRequest request)throws Exception{
        String ID = (String)request.getSession().getAttribute("id");
        Teacher teacher = teacherDAO.getOne(Integer.parseInt(ID));
        List<Announcement> announcementList = new LinkedList<>();
        List<Course> courseList = teacher.getCourseList();
        for(Course course:courseList){
            for(Announcement announcement:course.getAnnouncementList()){
                announcementList.add(announcement);
            }
        }
        int i = 0;
        m.addAttribute("index", i);
        m.addAttribute("announcements", announcementList);
        return "t_announcemanage";
    }

    @RequestMapping("/editannounce")
    public String editannounce(Model m,HttpServletRequest request, @RequestParam(value = "id")String announceid){
        Announcement announcement = announcementDAO.getOne(Integer.parseInt(announceid));
        m.addAttribute("announcement", announcement);
        String ID = (String)request.getSession().getAttribute("id");
        Teacher teacher = teacherDAO.getOne(Integer.parseInt(ID));
        List<Course> courses = teacher.getCourseList();
        HttpSession session = request.getSession();
        session.setAttribute("announcementid", announcement.getId());
        m.addAttribute("courses", courses);
        return "editannounce";
    }

    @RequestMapping("/deleteannounce")
    public String deleteannounce(Model m, HttpServletRequest request, @RequestParam(value = "id")String announceid) throws Exception{
        Announcement announcement = announcementDAO.getOne(Integer.parseInt(announceid));
        announcementDAO.delete(announcement);
        String ID = (String)request.getSession().getAttribute("id");
        Teacher teacher = teacherDAO.getOne(Integer.parseInt(ID));
        List<Announcement> announcementList = new LinkedList<>();
        List<Course> courseList = teacher.getCourseList();
        for(Course course:courseList){
            for(Announcement announcement1:course.getAnnouncementList()){
                announcementList.add(announcement1);
            }
        }
        int i = 0;
        m.addAttribute("index", i);
        m.addAttribute("announcements", announcementList);
        return "t_announcemanage";
    }

    @RequestMapping("/addannounce")
    public String addannounce(Model m, HttpServletRequest request)throws Exception{
        String ID = (String)request.getSession().getAttribute("id");
        Teacher teacher = teacherDAO.getOne(Integer.parseInt(ID));
        List<Course> courses = teacher.getCourseList();
        m.addAttribute("courses", courses);
        return "addannounce";
    }

    @RequestMapping("/submitannounce")
    public String submitannounce(Model m, HttpServletRequest request, @RequestParam(value = "theme")String theme, @RequestParam(value = "courseid")String courseid, @RequestParam(value = "content")String content)throws Exception{
        Announcement announcement = new Announcement();
        announcement.setTheme(theme);
        announcement.setName(theme);
        Timestamp  ts=new Timestamp(new Date().getTime());
        announcement.setTime(ts);
        announcement.setContent(content);
        Course course = courseDAO.getOne(Integer.parseInt(courseid));
        announcement.setCourse(course);
        announcementDAO.save(announcement);
        String ID = (String)request.getSession().getAttribute("id");
        Teacher teacher = teacherDAO.getOne(Integer.parseInt(ID));
        List<Announcement> announcementList = new LinkedList<>();
        List<Course> courseList = teacher.getCourseList();
        for(Course course1:courseList){
            for(Announcement announcement1:course1.getAnnouncementList()){
                announcementList.add(announcement1);
            }
        }
        int i = 0;
        m.addAttribute("index", i);
        m.addAttribute("announcements", announcementList);
        return "t_announcemanage";
    }

    @RequestMapping("/editandsaveannounce")
    public String editandsaveannounce(Model m, HttpServletRequest request, @RequestParam(value = "theme")String theme, @RequestParam(value = "courseid")String courseid, @RequestParam(value = "content")String content)throws Exception{
        String ID = (String)request.getSession().getAttribute("id");
        Teacher teacher = teacherDAO.getOne(Integer.parseInt(ID));
        HttpSession session = request.getSession();
        Integer id = (Integer)request.getSession().getAttribute("announcementid");
        Announcement announcement = announcementDAO.getOne(id);
        announcement.setTheme(theme);
        announcement.setContent(content);
        Course course = courseDAO.getOne(Integer.parseInt(courseid));
        announcement.setCourse(course);
        List<Announcement> announcementList = new LinkedList<>();
        List<Course> courseList = teacher.getCourseList();
        for(Course course1:courseList){
            for(Announcement announcement1:course1.getAnnouncementList()){
                announcementList.add(announcement1);
            }
        }
        int i = 0;
        m.addAttribute("index", i);
        m.addAttribute("announcements", announcementList);
        return "t_announcemanage";
    }






    @RequestMapping("t_homeworkmanage")
    public String t_homeworkmanage(Model m, HttpServletRequest request)throws Exception{
        String ID = (String)request.getSession().getAttribute("id");
        Teacher teacher = teacherDAO.getOne(Integer.parseInt(ID));
        List<Homework> homeworkList = new LinkedList<>();
        List<Course> courseList = teacher.getCourseList();
        for(Course course:courseList){
            for(Homework homework:course.getHomeworkList()){
                homeworkList.add(homework);
            }
        }
        int i = 0;
        m.addAttribute("index", i);
        m.addAttribute("homeworks", homeworkList);
        return "t_homeworkmanage";
    }

    @RequestMapping("/edithomework")
    public String edithomework(Model m,HttpServletRequest request, @RequestParam(value = "id")String announceid){
        Homework homework = homeworkDAO.getOne(Integer.parseInt(announceid));
        m.addAttribute("homework", homework);
        String ID = (String)request.getSession().getAttribute("id");
        Teacher teacher = teacherDAO.getOne(Integer.parseInt(ID));
        List<Course> courses = teacher.getCourseList();
        HttpSession session = request.getSession();
        session.setAttribute("homeworkid", homework.getId());
        m.addAttribute("courses", courses);
        return "edithomework";
    }

    @RequestMapping("/deletehomework")
    public String deletehomework(Model m, HttpServletRequest request, @RequestParam(value = "id")String announceid) throws Exception{
        Homework hommework = homeworkDAO.getOne(Integer.parseInt(announceid));
        homeworkDAO.delete(hommework);
        String ID = (String)request.getSession().getAttribute("id");
        Teacher teacher = teacherDAO.getOne(Integer.parseInt(ID));
        List<Homework> homeworkList = new LinkedList<>();
        List<Course> courseList = teacher.getCourseList();
        for(Course course:courseList){
            for(Homework homework1 :course.getHomeworkList()){
                homeworkList.add(homework1);
            }
        }
        int i = 0;
        m.addAttribute("index", i);
        m.addAttribute("homeworks", homeworkList);
        return "t_homeworkmanage";
    }

    @RequestMapping("/addhomework")
    public String addhomework(Model m, HttpServletRequest request)throws Exception{
        String ID = (String)request.getSession().getAttribute("id");
        Teacher teacher = teacherDAO.getOne(Integer.parseInt(ID));
        List<Course> courses = teacher.getCourseList();
        m.addAttribute("courses", courses);
        return "addhomework";
    }

    @RequestMapping("/t_submithomework")
    public String t_submithomework(Model m, HttpServletRequest request, @RequestParam(value = "theme")String theme, @RequestParam(value = "courseid")String courseid, @RequestParam(value = "content")String content)throws Exception{
        Homework homework = new Homework();
        homework.setTheme(theme);
        homework.setName(theme);
        Timestamp  ts=new Timestamp(new Date().getTime());
        homework.setEndtime(ts);
        homework.setContent(content);
        Course course = courseDAO.getOne(Integer.parseInt(courseid));
        homework.setCourse(course);
        homeworkDAO.save(homework);
        String ID = (String)request.getSession().getAttribute("id");
        Teacher teacher = teacherDAO.getOne(Integer.parseInt(ID));
        List<Homework> homeworkList = new LinkedList<>();
        List<Course> courseList = teacher.getCourseList();
        for(Course course1:courseList){
            for(Homework homework1:course1.getHomeworkList()){
                homeworkList.add(homework1);
            }
        }
        int i = 0;
        m.addAttribute("index", i);
        m.addAttribute("homeworks", homeworkList);
        return "t_homeworkmanage";
    }

    @RequestMapping("/editandsavehomework")
    public String editandsavehomework(Model m, HttpServletRequest request, @RequestParam(value = "theme")String theme, @RequestParam(value = "courseid")String courseid, @RequestParam(value = "content")String content)throws Exception{
        String ID = (String)request.getSession().getAttribute("id");
        Teacher teacher = teacherDAO.getOne(Integer.parseInt(ID));
        HttpSession session = request.getSession();
        Integer id = (Integer)request.getSession().getAttribute("homeworkid");
        Homework homework = homeworkDAO.getOne(id);
        homework.setTheme(theme);
        homework.setContent(content);
        Course course = courseDAO.getOne(Integer.parseInt(courseid));
        homework.setCourse(course);
        List<Homework> homeworkList = new LinkedList<>();
        List<Course> courseList = teacher.getCourseList();
        for(Course course1:courseList){
            for(Homework homework1:course1.getHomeworkList()){
                homeworkList.add(homework1);
            }
        }
        int i = 0;
        m.addAttribute("index", i);
        m.addAttribute("homeworks", homeworkList);
        return "t_homeworkmanage";
    }

}
