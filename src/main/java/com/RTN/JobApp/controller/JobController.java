package com.RTN.JobApp.controller;


import com.RTN.JobApp.model.JobPost;
import com.RTN.JobApp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class JobController {

    @Autowired
    public JobService service;
    @RequestMapping({"/","home"})
    public String home()
    {
        return "home";
    }
    @GetMapping("/addjob")
    public String addJob()
    {
        return "addjob";
    }
    @PostMapping("handleForm")
    public String handleForm(@ModelAttribute JobPost jobPost, Model model)
    {
        model.addAttribute("jobPost",jobPost);
        service.addjob(jobPost);

        return "success";
    }
    @GetMapping("/viewalljobs")
    public String viewAllJobs(Model model)
    {
        List<JobPost> jobPosts=service.getAllJobs();
        model.addAttribute("jobPosts",jobPosts);
        return "viewalljobs";
    }


}
