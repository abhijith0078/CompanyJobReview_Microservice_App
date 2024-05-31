package com.abhijith.Job.Service;

import com.abhijith.Job.DTO.JobCompanyReviewDTO;
import com.abhijith.Job.Exception.JobException;
import com.abhijith.Job.Model.Job;

import java.util.List;

public interface JobService {
    List<JobCompanyReviewDTO> findAllJobs();
    Job addJob(Job job);

    JobCompanyReviewDTO findJobById(Long id) throws JobException;

    boolean deleteJob(Long id);

    boolean updateJob(Long id, Job job) throws JobException;
}
