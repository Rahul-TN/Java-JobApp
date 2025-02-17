package com.RTN.JobApp.service;

import com.RTN.JobApp.model.JobPost;
import com.RTN.JobApp.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    public JobRepo repo;


  public void addjob(JobPost jobPost)
  {
        repo.addJob(jobPost);
  }

  public List<JobPost> getAllJobs()
  {
      return repo.getAllJobs();
  }

}
