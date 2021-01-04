package com.study.swmssystem.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.util.Date;

@Controller
public class HelloController {
 
    @RequestMapping("/hello")
    public String hello(Model m) throws Exception {
    	m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));

        return "hello";
    }
    

    
    
}
